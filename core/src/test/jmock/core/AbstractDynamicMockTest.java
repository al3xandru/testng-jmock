/*  Copyright (c) 2000-2004 jMock.org
 */
package test.jmock.core;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.jmock.core.CoreMock;
import org.jmock.core.DynamicMock;
import org.jmock.core.DynamicMockError;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationDispatcher;
import org.jmock.core.LIFOInvocationDispatcher;
import org.jmock.expectation.AssertMo;
import org.jmock.util.JMockUtils;

import test.jmock.core.testsupport.MockInvocationDispatcher;
import test.jmock.core.testsupport.MockInvokable;
import test.jmock.core.testsupport.MockStub;


public abstract class AbstractDynamicMockTest extends TestCase
{
    private static final String MOCK_NAME = "Test coreMock";

    private DummyInterface proxy;
    private DynamicMock coreMock;
    private MockInvocationDispatcher mockDispatcher = new MockInvocationDispatcher();
    private MockInvokable mockInvokable = new MockInvokable();

    public void setUp() {
        coreMock = createDynamicMock( MOCK_NAME, mockDispatcher );

        try {
            proxy = (DummyInterface)coreMock.proxy();
        }
        catch (ClassCastException ex) {
            fail("proxy is not of expected interface type");
        }
    }

    protected abstract DynamicMock createDynamicMock( String name, InvocationDispatcher dispatcher );
    protected abstract Class mockedType();

    protected DummyInterface proxy() {
        return proxy;
    }

    public void testReportsMockedType() {
        assertSame("mocked type",
                   mockedType(), coreMock.getMockedType());
    }

    public void testAnnotatesAssertionFailedErrorsWithDetailsOfInvocation()
        throws Throwable 
    {
        final String originalMessage = "original message";
        
        Throwable throwable = JMockUtils.newMockAssertionError(originalMessage);
        mockDispatcher.dispatchThrowable = throwable;
        
        try {
            proxy.noArgVoidMethod();
            fail("should throw AssertionFailedError");
        }
        catch (AssertionFailedError err) {
            AssertMo.assertIncludes("should contain original message", originalMessage, err.getMessage());
            AssertMo.assertIncludes("should contain coreMock name", MOCK_NAME, err.getMessage());
        }
    }

    public void testReturnsConfiguredResultFromProxy() throws Throwable {
        final String RESULT = "configured result";

        mockDispatcher.dispatchResult = RESULT;

        assertSame("result is returned by coreMock", RESULT, proxy.oneArgMethod("arg"));
    }

    public void testPropagatesExceptionsThroughProxy() throws Throwable {
        final Throwable throwable = new DummyThrowable();

        mockDispatcher.dispatchThrowable = throwable;

        try {
            proxy.noArgVoidMethod();
        }
        catch (Throwable ex) {
            assertSame("exception is caught by coreMock", throwable, ex);
            return;
        }
        fail("Should have thrown exception");
    }

    public void testVerifiesByVerifyingDispatcher() throws Exception {
        mockDispatcher.verifyCalls.setExpected(1);

        coreMock.verify();

        // Can't use Verifier as we are verifying "verify"
        mockDispatcher.verifyExpectations();
    }

    public void testTestsEqualityForProxy() throws Exception {
        coreMock = createDynamicMock( "coreMock", new LIFOInvocationDispatcher());
        proxy = (DummyInterface)coreMock.proxy();

        assertTrue("should be equal", proxy.equals(proxy));
        assertFalse("should not be equal", proxy.equals(new Object()));
        assertFalse("shuold not be equal to null", proxy.equals(null));
    }

    public void testCanOverrideEqualsForProxyBySettingAStub() throws Exception {
        mockDispatcher.dispatchResult = new Boolean(false);

        mockDispatcher.dispatchInvocation.setExpected(new Invocation(proxy,
                                                                     Object.class.getMethod("equals", new Class[]{Object.class}),
                                                                     new Object[]{"not a proxy"}));

        assertFalse("Passes invocation of equals to dispatcher",
                    proxy.equals("not a proxy"));

        mockDispatcher.verifyExpectations();
    }

    public void testCalculatesHashCodeForProxy() throws Exception {
        coreMock = new CoreMock(DummyInterface.class, "coreMock");

        proxy = (DummyInterface)coreMock.proxy();

        assertEquals("same hash code", proxy.hashCode(), proxy.hashCode());
    }

    public void testCanOverrideHashCodeForProxyBySettingAStub() throws Exception {
        int expectedHashCode = 1;

        mockDispatcher.dispatchResult = new Integer(expectedHashCode);
        mockDispatcher.dispatchInvocation.setExpected(new Invocation(proxy, Object.class.getMethod("hashCode", new Class[0]),
                                                                     new Object[0]));

        assertEquals("proxy hashCode", expectedHashCode, proxy.hashCode());

        mockDispatcher.verifyExpectations();
    }

    public void testGeneratesMockNameFromInterfaceNameIfNoNameSpecified() throws Exception {
        assertEquals("mockString", CoreMock.mockNameFromClass(String.class));
    }

    public void testReturnsNameFromToString() {
        AssertMo.assertIncludes("result of toString() should include name",
                                MOCK_NAME, coreMock.toString());
    }

    public void testAddsInvokablesToDispatcher() {
        mockDispatcher.addInvokable.setExpected(mockInvokable);

        coreMock.addInvokable(mockInvokable);

        mockDispatcher.verifyExpectations();
    }

    public void testExposesDefaultStubOfDispatcher() {
        MockStub dummyStub = new MockStub("dummyStub");

        mockDispatcher.setDefaultStub.setExpected(dummyStub);

        coreMock.setDefaultStub(dummyStub);

        mockDispatcher.verifyExpectations();
    }

    public void testResetsDispatcher() {
        mockDispatcher.clearCalls.setExpected(1);

        coreMock.reset();

        mockDispatcher.verifyExpectations();
    }

    public void testVerifyFailuresIncludeMockName() {
        mockDispatcher.verifyFailure = JMockUtils.newMockAssertionError("verify failure");

        mockDispatcher.verifyCalls.setExpected(1);

        try {
            coreMock.verify();
        }
        catch (AssertionFailedError expected) {
            AssertMo.assertIncludes("Should include mock name", MOCK_NAME, expected.getMessage());
            mockDispatcher.verifyExpectations();
            return;
        }
        fail("Should have thrown exception");
    }

    public void testAlwaysReportsFirstFailureThatOccurredDuringTheTest() throws Throwable {
        DynamicMockError firstFailure = null;

        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("first failure");

        try {
            proxy.noArgVoidMethod();
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                firstFailure = (DynamicMockError)err;
            } else {
                throw err;
            }
        }

        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("second failure");

        try {
            proxy.noArgVoidMethod();
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                assertSame( "should have thrown first error", firstFailure, err );
                return;
            } else {
                throw err;
            }
        }

        fail("should have thrown DynamicMockError");
    }

    public void testForgetsPreviousFailureWhenReset() throws Throwable {
        DynamicMockError firstFailure = null;

        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("first failure");
        try {
            proxy.noArgVoidMethod();
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                firstFailure = (DynamicMockError)err;
            } else {
                throw err;
            }
        }

        coreMock.reset();

        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("second failure");
        try {
            proxy.noArgVoidMethod();
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                assertNotSame( "should have not have rethrown first error", firstFailure, err );
                return;
            } else {
               throw err;
            }
        }
    }

    public void testForgetsPreviousFailureWhenVerified() throws Throwable {
        DynamicMockError firstFailure = null;

        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("first failure");
        try {
            proxy.noArgVoidMethod();
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                firstFailure = (DynamicMockError)err;
            } else {
                throw err;
            }
        }

        coreMock.verify();

        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("second failure");
        try {
            proxy.noArgVoidMethod();
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                assertNotSame( "should have not have rethrown first error", firstFailure, err );
                return;
            } else {
                throw err;
            }
        }
    }
    
    public void testCanCallMethodsDefinedByObjectClassAfterFailure() throws Throwable {
        mockDispatcher.dispatchThrowable = JMockUtils.newMockAssertionError("first failure");
        try {
            proxy.noArgVoidMethod();
            fail("should have thrown DynamicMockError");
        }
        catch(Error err) {
            if (err instanceof DynamicMockError) {
                // expected
            } else {
                throw err;
            }
        }
        
        mockDispatcher.dispatchThrowable = null;
        
        mockDispatcher.dispatchResult = "toString result";
        proxy.toString();
        
        mockDispatcher.dispatchResult = Boolean.FALSE;
        proxy.equals("some object");
        
        mockDispatcher.dispatchResult = new Integer(1);
        proxy.hashCode();
    }
}
