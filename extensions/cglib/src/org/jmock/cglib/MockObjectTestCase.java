/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.cglib;

import org.jmock.AbstractMockObjectTestCase;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.DynamicMock;


public abstract class MockObjectTestCase extends AbstractMockObjectTestCase
{
   private CGLIBMockTester mockTester;

   public MockObjectTestCase()
   {
      super();
      mockTester = new CGLIBMockTester();
   }

   public MockObjectTestCase( String name )
   {
      super( name );
      mockTester = new CGLIBMockTester();
   }

   protected final MockTester getMockTester()
   {
      return getCGLIBMockTester();
   }

   protected final CGLIBMockTester getCGLIBMockTester()
   {
      return mockTester;
   }

    public Mock mock(Class mockedClass, String roleName, 
            		 Class[] constructorArgumentTypes, Object[] constructorArguments) 
    {
		 return mockTester.mock( mockedClass, roleName, constructorArgumentTypes, constructorArguments );
    }

    public Mock mock(Class mockedClass, Class[] constructorArgumentTypes, Object[] constructorArguments) {
        return mockTester.mock(mockedClass, defaultMockNameForType(mockedClass),
                    constructorArgumentTypes, constructorArguments);
    }
    
    protected DynamicMock newCoreMock( Class mockedType, String roleName ) {
        return mockTester.newCoreMock(mockedType, roleName);
    }
    
    protected DynamicMock newCoreMock( Class mockedClass, String roleName,
                                       Class[] constructorArgumentTypes, Object[] constructorArguments) 
    {
        return mockTester.newCoreMock(mockedClass, roleName, constructorArgumentTypes, constructorArguments);
    }
}
