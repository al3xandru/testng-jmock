package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.DynamicMockError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;


/* Acceptance test for issue JMOCK-30 (http://jira.codehaus.org/browse/JMOCK-30).
 */
public class CascadedFailuresAcceptanceTest {
    public interface MockedType {
        void f(int i);
        void g();
    }

    public static class ObjectUnderTest {
        private MockedType other;

        public ObjectUnderTest(MockedType other) {
            this.other = other;
        }

        public void methodUnderTest(int i) {
            try {
                other.f(i);
            }
            finally {
                other.g();
            }
        }
    }

    private static final int VALID_ARGUMENT = 2;
    private static final int INVALID_ARGUMENT = 1;

    private Mock mock;
    private ObjectUnderTest objectUnderTest;
    private MockTester mockTester;

    @BeforeMethod
    public void setUp() {
        mockTester = new DefaultMockTester();
        mock = mockTester.mock( MockedType.class, "mock" );
        objectUnderTest = new ObjectUnderTest( (MockedType)mock.proxy() );

        mock.stubs().method("f").with(eq(VALID_ARGUMENT));
    }

    @Test
    public void mockReportsFirstFailure() {
        try {
            objectUnderTest.methodUnderTest(INVALID_ARGUMENT);
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
				    assertEquals( "invoked method", "f", ((DynamicMockError)err).getInvocation().invokedMethod.getName() );
				} else {
                throw err;
				}
        }
    }

    @Test
    public void resetClearsFirstFailure() {
        try {
            objectUnderTest.methodUnderTest(INVALID_ARGUMENT);
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }

        mock.reset();
        mock.stubs().method("f").with(eq(VALID_ARGUMENT));

        try {
            objectUnderTest.methodUnderTest(VALID_ARGUMENT);
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                assertEquals( "invoked method", "g", ((DynamicMockError)err).getInvocation().invokedMethod.getName() );
				} else {
                throw err;
				}
        }
    }

    @Test
    public void successfulVerifyClearsFirstFailure() {
        try {
            objectUnderTest.methodUnderTest(INVALID_ARGUMENT);
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }

        mock.verify();

        try {
            objectUnderTest.methodUnderTest(VALID_ARGUMENT);
            fail("should have thrown DynamicMockError");
        }
        catch( Error err ) {
            if (err instanceof DynamicMockError) {
                assertEquals( "invoked method", "g", ((DynamicMockError)err).getInvocation().invokedMethod.getName() );
				} else {
                throw err;
				}
        }
    }
}
