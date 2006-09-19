package atest.jmock.cglib;

import org.jmock.MockTester;
import org.jmock.cglib.CGLIBMockTester;
import org.jmock.util.MockAssertionError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;

public class ErrorDetectionAcceptanceTest {
    public class NonStaticInnerClass {}
    
    private MockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new CGLIBMockTester();
    }

    @Test
    public void reportsUsefulErrorMessageWhenTryingToMockNonStaticInnerClass() {
        try {
            mockTester.mock(NonStaticInnerClass.class);
        }
        catch (Error e) {
				if (!(e instanceof MockAssertionError)) {
                fail("should have failed");
				}
            assertTrue("should report error", 
                       e.getMessage().indexOf("non-static inner class") >= 0);
            assertTrue("should report name of inner class", 
                       e.getMessage().indexOf(NonStaticInnerClass.class.getName()) >= 0);
        }
        
    }
}
