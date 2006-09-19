package atest.jmock;

import org.jmock.MockObjectTestCase;
import org.jmock.Mock;
import org.jmock.util.MockAssertionError;


public class InvokedExactCountAcceptanceTest extends MockObjectTestCase {
    private final Mock mock = mock(MockedType.class,"mock");
    private final MockedType proxy = (MockedType)mock.proxy();

    interface MockedType {
        void m();
    }

    protected void setUp() throws Exception {
        mock.expects(exactly(2)).method("m").withNoArguments();
    }
    
    /*
     * Call reset() in the catch blocks for these two methods, 
     * otherwise the MockObjectTestCase infrastructure picks up
     * the errors and rethrows the exception.
     */
    public void testFailsWhenCalledFewerThanTheExactNumberOfTimes() {
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
