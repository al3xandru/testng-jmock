/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.DynamicMockError;
import org.jmock.expectation.AssertMo;
import org.jmock.util.MockAssertionError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;


public class OrderedInvocationsAcceptanceTest
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

    private MockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new DefaultMockTester();
        mock = mockTester.mock(ExampleInterface.class, "mock");
        proxy = (ExampleInterface)mock.proxy();
    }

    @AfterMethod
    public void verify() {
        mockTester.verify();
    }

    @Test
    public void orderedCallsCanOccurInOrder() {
        mock.stubs().method("hello").id("hello call");
        mock.stubs().method("goodbye").after("hello call");

        proxy.hello();
        proxy.goodbye();
    }

    @Test
    public void orderedCallsMustNotOccurOutOfOrder() {
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

    @Test
    public void orderingDoesNotAffectUnrelatedCalls() {
        mock.stubs().method("hello").id("hello call");
        mock.stubs().method("goodbye").after("hello call");
        mock.stubs().method("moreTeaVicar");

        proxy.hello();
        proxy.moreTeaVicar();
        proxy.goodbye();
    }

    @Test
    public void orderingConstraintsDoNotImplyExpectedCall() {
        mock.stubs().method("hello").isVoid().id("hello call");
        mock.stubs().method("goodbye").after("hello call");
    }

    @Test
    public void canUseMethodNameAsDefaultInvocationID() {
        mock.stubs().method("hello").isVoid();
        mock.stubs().method("goodbye").after("hello"); // should not throw error
    }

    @Test
    public void usingSameMethodNameAsParameterToAfterIsAnError() {
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

    @Test
    public void canSpecifyOrderOverDifferentMocks() {
        Mock otherMock = mockTester.mock(ExampleInterface.class, "otherMock");
        ExampleInterface otherProxy = (ExampleInterface)otherMock.proxy();

        otherMock.stubs().method("hello").isVoid();

        mock.stubs().method("goodbye").after(otherMock, "hello");

        otherProxy.hello();
        proxy.goodbye();
    }

    @Test
    public void detectsUnexpectedOrderOverDifferentMocks() {
        String otherMockName = "otherMock";
        String priorCall = "HELLO-CALL-ID";
        Mock otherMock = mockTester.mock(ExampleInterface.class, otherMockName);

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

    @Test
    public void allowsSameInvocationMultipleTimes() {
        mock.stubs().method("hello").id("hello #1");
        mock.stubs().method("hello").after("hello #1").id("hello #2");
        mock.stubs().method("hello").after("hello #2").id("hello #3");
        mock.stubs().method("goodbye").after("hello #3");

        proxy.hello();
        proxy.hello();
        proxy.hello();
        proxy.goodbye();
    }

    @Test
    public void detectsDuplicateIDs() {
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

    @Test
    public void detectsMissingIDs() {
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

    @Test
    public void resetClearsIDs() {
        String id = "ID";

        mock.stubs().method("hello").id(id);
        mock.reset();
        mock.stubs().method("goodbye").id(id);
    }
}
