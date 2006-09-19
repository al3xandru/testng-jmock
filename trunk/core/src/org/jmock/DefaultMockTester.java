/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jmock.core.CoreMock;
import org.jmock.core.DynamicMock;
import org.jmock.core.Formatting;
import org.jmock.core.Verifiable;
import org.jmock.util.Dummy;
import org.jmock.util.Verifier;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class DefaultMockTester implements MockTester
{
   private List objectsThatRequireVerification = new ArrayList();

   public DefaultMockTester()
   {

   }

   public String defaultMockNameForType( Class mockedType )
   {
      return "mock" + Formatting.classShortName( mockedType );
   }

   public Mock mock( Class mockedType )
   {
      return mock( mockedType, defaultMockNameForType( mockedType ) );
   }

   public Mock mock( Class mockedType, String roleName )
   {
      Mock newMock = new Mock( newCoreMock( mockedType, roleName ) );
      registerToVerify( newMock );
      return newMock;
   }

   public DynamicMock newCoreMock( Class mockedType, String roleName )
   {
      return new CoreMock( mockedType, roleName );
   }

   public Object newDummy( Class dummyType )
   {
      return Dummy.newDummy( dummyType );
   }

   public Object newDummy( Class dummyType, String name )
   {
      return Dummy.newDummy( dummyType, name );
   }

   public Object newDummy( String name )
   {
      return Dummy.newDummy( name );
   }

   public void registerToVerify( Verifiable verifiable )
   {
      objectsThatRequireVerification.add( verifiable );
   }

   public void unregisterToVerify( Verifiable verifiable )
   {
      objectsThatRequireVerification.remove( verifiable );
   }

   public void verify()
   {
      for( Iterator iterator = objectsThatRequireVerification.iterator(); iterator.hasNext(); ){
         Verifiable verifiable = (Verifiable) iterator.next();
         verifiable.verify();
      }
      Verifier.verifyObject( this );
   }
}