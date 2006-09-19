package org.jmock;

import org.jmock.core.DynamicMock;
import org.jmock.core.Verifiable;

public interface MockTester
{
   String defaultMockNameForType( Class mockedType );

   Mock mock( Class mockedType );

   Mock mock( Class mockedType, String roleName );

   DynamicMock newCoreMock( Class mockedType, String roleName );

   Object newDummy( Class dummyType );

   Object newDummy( Class dummyType, String name );

   Object newDummy( String name );

   void registerToVerify( Verifiable verifiable );

   void unregisterToVerify( Verifiable verifiable );

   void verify();
}