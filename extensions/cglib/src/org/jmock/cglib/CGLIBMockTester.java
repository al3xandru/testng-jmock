package org.jmock.cglib;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.core.DynamicMock;

/**
 *
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */ 
public class CGLIBMockTester extends DefaultMockTester
{
   public CGLIBMockTester()
   {
      super();
   }

   public Mock mock( Class mockedClass, Class[] constructorArgumentTypes,
         Object[] constructorArguments )
   {
      return mock( mockedClass, defaultMockNameForType( mockedClass ), constructorArgumentTypes,
            constructorArguments );
   }

   public Mock mock( Class mockedClass, String roleName, Class[] constructorArgumentTypes,
         Object[] constructorArguments )
   {
      Mock newMock = new Mock( newCoreMock( mockedClass, roleName, constructorArgumentTypes,
            constructorArguments ) );
      registerToVerify( newMock );
      return newMock;
   }

   public DynamicMock newCoreMock( Class mockedType, String roleName )
   {
      return new CGLIBCoreMock( mockedType, roleName );
   }

   public DynamicMock newCoreMock( Class mockedClass, String roleName,
         Class[] constructorArgumentTypes, Object[] constructorArguments )
   {
      return new CGLIBCoreMock( mockedClass, roleName, constructorArgumentTypes,
            constructorArguments );
   }
}
