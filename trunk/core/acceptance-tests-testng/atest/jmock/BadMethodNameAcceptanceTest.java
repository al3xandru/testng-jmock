package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.expectation.AssertMo;
import org.jmock.util.MockAssertionError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;

public class BadMethodNameAcceptanceTest
{
    private static final String INVALID_METHOD_NAME = "invalidMethodName()";
    private static final String METHOD_NAME_NOT_IN_MOCKED_INTERFACE = "methodNameNotInMockedInterface";

    private MockTester mockTester;

    @BeforeMethod
    public void init(){
        mockTester = new DefaultMockTester();
    }

    @Test
    public void invalidMethodNameCausesTestError() {
        Mock mock = mockTester.mock(Types.class);

        try {
            mock.stubs().method(INVALID_METHOD_NAME);
        }
        catch( IllegalArgumentException ex ) {
            AssertMo.assertIncludes( "should contain invalid method name",
                                     INVALID_METHOD_NAME, ex.getMessage() );
            return;
        }
        fail("should have caused test error");
    }

    @Test
    public void methodNameNotInMockedTypeCausesTestFailure() {
        Mock mock = mockTester.mock(Types.class);

        try {
            mock.stubs().method(METHOD_NAME_NOT_IN_MOCKED_INTERFACE);
        }
        catch( Error ex ) {
				if (ex instanceof MockAssertionError) {
                AssertMo.assertIncludes( "should contain invalid method name",
                                          METHOD_NAME_NOT_IN_MOCKED_INTERFACE, ex.getMessage() );
                 return;
				} else {
                throw ex;
				}
        }
        fail("should have caused test failure");
    }
}
