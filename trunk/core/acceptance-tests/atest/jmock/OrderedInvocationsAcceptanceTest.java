/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.jmock.core.DynamicMockError;
import org.jmock.expectation.AssertMo;
import org.jmock.util.MockAssertionError;


public class OrderedInvocationsAcceptanceTest
        extends MockObjectTestCase
{
    private Mock mock;
    private ExampleInterface proxy;

    public static interface ExampleInterface
    {
        void hello();

        void goodbye();

        void moreTeaVicar();

        int count();
    }

    public void setUp() {
        mock = mock(ExampleInterface.class, "mock");
        proxy = (ExampleInterface)mock.proxy();
    }

    public void testOrderedCallsCanOccurInOrder() {
        mock.stubs().method("hello").id("hello call");
        mock.stubs().method("goodbye").after("hello call");

        proxy.hello();
        proxy.goodbye();
    }

    public void testOrderedCallsMustNotOccurOutOfOrder() {
        String priorCall = "HELLO-CALL-ID";

        mock.stubs().method("hello").id(priorCall);
        mock.stubs().method("goodbye").after(priorCall);

        try {
            proxy.goodbye();
            fail("should have thrown DynamicMockError");
        }
        catch (Error ex) {
				if (ex instanceof DynamicMockError) {
                assertTrue("error message should contain id of prior call",
                           ex.getMessage().indexOf(priorCall) >= 0);
				} else {
                throw ex;
				}
        }
    }

    public void testOrderingDoesNotAffectUnrelatedCalls() {
        mock.stubs().method("hello").id("hello call");
        mock.stubs().method("goodbye").after("hello call");
        mock.stubs().method("moreTeaVicar");

        proxy.hello();
        proxy.moreTeaVicar();
        proxy.goodbye();
    }

    public void testOrderingConstraintsDoNotImplyExpectedCall() {
        mock.stubs().method("hello").isVoid().id("hello call");
        mock.stubs().method("goodbye").after("hello call");
    }

    public void testCanUseMethodNameAsDefaultInvocationID() {
        mock.stubs().method("hello").isVoid();
        mock.stubs().method("goodbye").after("hello"); // should not throw error
    }

    public void testUsingSameMethodNameAsParameterToAfterIsAnError() {
        mock.stubs().method("count").will(returnValue(1));
        try {
            mock.stubs().method("count").after("count").will(returnValue(2));
        }
        catch (Error ex) {
            if ( !(ex instanceof MockAssertionError) ){
                fail("should have failed");
				}
            AssertMo.assertIncludes("should include repeated invokedMethod name",
                                    "count", ex.getMessage());
        }
    }

    public void testCanSpecifyOrderOverDifferentMocks() {
        Mock otherMock = mock(ExampleInterface.class, "otherMock");
        ExampleInterface otherProxy = (ExampleInterface)otherMock.proxy();

        otherMock.stubs().method("hello").isVoid();

        mock.stubs().method("goodbye").after(otherMock, "hello");

        otherProxy.hello();
        proxy.goodbye();
    }

    public void testDetectsUnexpectedOrderOverDifferentMocks() {
        String otherMockName = "otherMock";
        String priorCall = "HELLO-CALL-ID";
        Mock otherMock = mock(ExampleInterface.class, otherMockName);

        otherMock.stubs().method("hello").id(priorCall);
        mock.stubs().method("goodbye").after(otherMock, priorCall);

        try {
            proxy.goodbye();
            fail("expected DynamicMockError");
        }
        catch (Error ex) {
				if (ex instanceof DynamicMockError) {
                assertTrue("error message should contain id of prior call",
                           ex.getMessage().indexOf(priorCall) >= 0);
                assertTrue("error message should contain name of mock receiving prior call",
                           ex.getMessage().indexOf(otherMockName) >= 0);
				} else {
                throw ex;
				}
        }
    }

    public void testAllowsSameInvocationMultipleTimes() {
        mock.stubs().method("hello").id("hello #1");
        mock.stubs().method("hello").after("hello #1").id("hello #2");
        mock.stubs().method("hello").after("hello #2").id("hello #3");
        mock.stubs().method("goodbye").after("hello #3");

        proxy.hello();
        proxy.hello();
        proxy.hello();
        proxy.goodbye();
    }

    public void testDetectsDuplicateIDs() {
        String duplicateID = "DUPLICATE-ID";

        mock.stubs().method("hello").id(duplicateID);

        try {
            mock.stubs().method("hello").id(duplicateID);
        }
        catch (Error ex) {
            if ( !(ex instanceof MockAssertionError) ){
                fail("should have failed");
				}
            AssertMo.assertIncludes("error message contains duplicate id",
                                    duplicateID, ex.getMessage());
        }
    }

    public void testDetectsMissingIDs() {
        String missingID = "MISSING-ID";

        try {
            mock.stubs().method("hello").after(missingID);
        }
        catch (Error ex) {
            if ( !(ex instanceof MockAssertionError) ){
                fail("should have failed");
				}
            AssertMo.assertIncludes("error message contains missing id",
                                    missingID, ex.getMessage());
        }
    }

    public void testResetClearsIDs() {
        String id = "ID";

        mock.stubs().method("hello").id(id);
        mock.reset();
        mock.stubs().method("goodbye").id(id);
    }
}
