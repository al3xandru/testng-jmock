/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock.cglib;

import java.util.ArrayList;
import org.jmock.Mock;
import org.jmock.cglib.CGLIBMockTester;
import org.jmock.util.NotImplementedException;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;


public class MockConcreteClassAcceptanceTest 
{
    private CGLIBMockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new CGLIBMockTester();
    }

    @AfterMethod
    public void verify() {
        mockTester.verify();
    }

    @Test
    public void canMockConcreteClasses() throws Exception {
        Mock listMock = mockTester.mock(ArrayList.class, "listMock");

        assertTrue("proxy is an ArrayList", listMock.proxy() instanceof ArrayList);

        ArrayList proxy = (ArrayList)listMock.proxy();
        Object newElement = mockTester.newDummy("newElement");

        listMock.expects(once()).method("add").with(eq(newElement)).will(returnValue(true));

        proxy.add(newElement);
        //listMock.verify();
    }
    
    public static class ClassWithConstructorArguments {
        public ClassWithConstructorArguments(int arg1, String arg2) {
            // do nothing
        }
        
        public String mockedMethod() { throw new NotImplementedException("not implemented"); }
    }
    
    @Test
    public void canMockConcreteClassesWithConstructorArguments() throws Exception {
        Mock mock = mockTester.mock(ClassWithConstructorArguments.class, 
                		 new Class[]{int.class,String.class}, 
                		 new Object[]{new Integer(1), ""});
        
        mock.expects(once()).method("mockedMethod").withNoArguments().will(returnValue("result"));
        
        assertEquals("result", ((ClassWithConstructorArguments)mock.proxy()).mockedMethod());
        
        //mock.verify();
    }
    
    @Test
    public void canGiveExplicitNameToMockOfConcreteClassesWithConstructorArguments() throws Exception {
        String mockName = "MOCK_NAME";
        Mock mock = mockTester.mock(ClassWithConstructorArguments.class,
                		 mockName,
                		 new Class[]{int.class,String.class}, 
                		 new Object[]{new Integer(1), ""});
        
        assertEquals(mockName, mock.toString());
        
        //mock.verify();
    }
    
    public static class ClassWithComplexConstructor {
        public String resultOfMockedMethodCalledInConstructor;
        
        public ClassWithComplexConstructor() {
            resultOfMockedMethodCalledInConstructor = mockedMethod();
        }
        
        public String mockedMethod() { return "ORIGINAL_RESULT"; }
    }
    
    @Test
    public void canMockClassesWithComplexConstructors() throws Exception {
        String mockName = "MOCK_NAME";
        Mock mock = mockTester.mock(ClassWithComplexConstructor.class,mockName);
        
        mock.expects(once()).method("mockedMethod").withNoArguments().will(returnValue("result"));
        
        assertEquals("mocked result",
                	 "result", ((ClassWithComplexConstructor)mock.proxy()).mockedMethod());
        assertEquals("original result",
                	 "ORIGINAL_RESULT", ((ClassWithComplexConstructor)mock.proxy()).resultOfMockedMethodCalledInConstructor);
        
        //mock.verify();
    }
}


