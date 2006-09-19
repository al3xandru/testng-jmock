package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.util.MockAssertionError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.matcher.InvocationMatchers.*;


public class InvokedExactCountAcceptanceTest {
    private Mock mock;
    private MockedType proxy;

    interface MockedType {
        void m();
    }

    private MockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new DefaultMockTester();
        mock = mockTester.mock(MockedType.class,"mock");
        proxy = (MockedType)mock.proxy();
        mock.expects(exactly(2)).method("m").withNoArguments();
    }

    @AfterMethod
    public void verify() {
        mockTester.verify();
    }

    /*
     * Call reset() in the catch blocks for these two methods, 
     * otherwise the MockObjectTestCase infrastructure picks up
     * the errors and rethrows the exception.
     */
    @Test
    public void failsWhenCalledFewerThanTheExactNumberOfTimes() {
        proxy.m();
        try {
            mock.verify();
        }
        catch( Error error ) {
            if ( !(error instanceof MockAssertionError) ){
                fail("Should have failed");
				}				
            mock.reset();
        }
    }
    
    
    public void testFailsWhenCalledMoreThanTheExactNumberOfTimes() {
        proxy.m();
        proxy.m();
        try {
            proxy.m();
        }
        catch( Error error ) {
            if ( !(error instanceof MockAssertionError) ){
                fail("Should have failed");
				}				
            mock.reset();
        }
    }

    public void testPassesWhenCalledTheExactNumberOfTimes() {
        proxy.m();
        proxy.m();
        mock.verify();
    }
}
