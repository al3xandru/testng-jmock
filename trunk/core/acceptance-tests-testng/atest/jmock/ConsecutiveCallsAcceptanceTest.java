/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.Stub;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;


public class ConsecutiveCallsAcceptanceTest 
{
    public interface Greeter
    {
        public String greeting();
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
    public void canEasilySpecifySequenceOfStubsForSameMethod() {
        Mock mock = mockTester.mock(Greeter.class);
        Greeter greeter = (Greeter)mock.proxy();

        mock.expects(atLeastOnce()).method("greeting").withNoArguments()
                .will(onConsecutiveCalls(returnValue("hello"),
                                         returnValue("bonjour"),
                                         returnValue("guten Tag")));

        assertEquals("hello", greeter.greeting());
        assertEquals("bonjour", greeter.greeting());
        assertEquals("guten Tag", greeter.greeting());
    }
    
    @Test
    public void canSpecifySequenceUsingArrayOfStubs() {
        Mock mock = mockTester.mock(Greeter.class);
        Greeter greeter = (Greeter)mock.proxy();
        
        mock.expects(atLeastOnce()).method("greeting").withNoArguments()
                .will(onConsecutiveCalls(new Stub[]{
                        returnValue("hello"),
                        returnValue("bonjour"),
                        returnValue("guten Tag")}));
        
        assertEquals("hello", greeter.greeting());
        assertEquals("bonjour", greeter.greeting());
        assertEquals("guten Tag", greeter.greeting());
    }
}
