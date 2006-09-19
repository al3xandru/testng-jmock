package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.DynamicMockError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;

public class InvokeAtMostOnceAcceptanceTest {
	public interface MockedType {
		public void m();
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
	public void passesIfMethodExpectedAtMostOnceIsNotCalled() {
		Mock mock = mockTester.mock(MockedType.class, "mock");
		
		mock.expects(atMostOnce()).method("m");
	}
	
    @Test
	public void passesIfMethodExpectedAtMostOnceIsCalledOnceOnly() {
		Mock mock = mockTester.mock(MockedType.class, "mock");
		
		mock.expects(atMostOnce()).method("m");
		
		MockedType proxy = (MockedType)mock.proxy();
		proxy.m();
	}
	
    @Test
	public void failsIfMethodExpectedAtMostOnceIsCalledMoreThanOnce() {
		Mock mock = mockTester.mock(MockedType.class, "mock");
		
		mock.expects(atMostOnce()).method("m");
		
		MockedType proxy = (MockedType)mock.proxy();
		proxy.m();
		
		try {
			proxy.m();
			fail("should have thrown DynamicMockError");
		}
      catch( Error err ) { 
          if (err instanceof DynamicMockError) {
			     /* expected */ 
			 } else {
              throw err;
		    }
		}
	}
	
}
