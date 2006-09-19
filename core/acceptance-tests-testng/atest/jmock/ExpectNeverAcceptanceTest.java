/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.expectation.AssertMo;
import org.jmock.core.DynamicMockError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;


public class ExpectNeverAcceptanceTest
{
    private static interface MockedInterface {
        public void method();
    }

    private MockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new DefaultMockTester();
    }

    @AfterMethod
    public void verify() {
        mockTester.verify();
    }

    @Test
    public void expectNeverOverridesStubAndFailsIfCalled() {
        Mock mock = mockTester.mock(MockedInterface.class, "mock");

        mock.stubs().method("method").withNoArguments();
        mock.expects(never()).method("method").withNoArguments();

        try {
            ((MockedInterface)mock.proxy()).method();
        }
        catch (Error error) {
            if ( !(error instanceof DynamicMockError) ){
                fail("expected DynamicMockError");
				}
        }
    }

    @Test
    public void expectNeverVerifiesIfNotCalled() {
        Mock mock = mockTester.mock(MockedInterface.class, "mock");

        mock.stubs().method("method").withNoArguments().isVoid();
        mock.expects(never()).method("method").withNoArguments();
    }

    @Test
    public void expectNeverCanExplicitlyDescribeError() {
        Mock mock = mockTester.mock(MockedInterface.class,"mock");
        String errorMessage = "errorMessage";

        mock.expects(never(errorMessage)).method("method").withNoArguments();
        mock.expects(never(errorMessage)).method("method").withNoArguments();

        try {
            ((MockedInterface)mock.proxy()).method();
        }
        catch (Error error) {
            if ( !(error instanceof DynamicMockError) ){
                fail("expected DynamicMockError");
				}
            AssertMo.assertIncludes( "should contain explicit error message", errorMessage, error.getMessage() );
        }
    }
}
