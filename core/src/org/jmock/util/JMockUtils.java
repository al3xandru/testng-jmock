/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util;

import java.lang.reflect.Constructor;

import org.jmock.core.DynamicMock;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationDispatcher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public final class JMockUtils
{
   private static boolean isTestNGAvailable;
   private static NewAssert jassert;

   static{
      try{
         Class.forName( "org.testng.Assert" );
         jassert = (NewAssert) Class.forName("org.jmock.util.TestNGAssert").newInstance();
         isTestNGAvailable = true;
      }
      catch( Exception e ){
         isTestNGAvailable = false;
         try{
            jassert = (NewAssert) Class.forName("org.jmock.util.JUnitAssert").newInstance();
         }catch( Exception ex ){
            // ignore ??
            // not even junit in classpath ??
         }
      }
   }

   public static NewAssert getAssert()
   {
      return jassert;
   }

   public static boolean isTestNGAvailable()
   {
      return isTestNGAvailable;
   }

   public static Error newDynamickMockError( DynamicMock dynamicMock, Invocation invocation,
         InvocationDispatcher dispatcher, String message )
   {
      if( isTestNGAvailable ){
         return newDynamicMockError( "TestNGDynamicMockError", dynamicMock, invocation, dispatcher, message );
      }else{
         return newDynamicMockError( "JUnitDynamicMockError", dynamicMock, invocation, dispatcher, message );
      }
   }

   public static Error newMockAssertionError()
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError" );
      }else{
         return newMockError( "JUnitMockAssertionError" );
      }
   }

   public static Error newMockAssertionError( boolean message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( char message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( double message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( float message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( int message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( long message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( Object message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", String.valueOf(message) );
      }else{
         return newMockError( "JUnitMockAssertionError", String.valueOf(message) );
      }
   }

   public static Error newMockAssertionError( String message )
   {
      if( isTestNGAvailable ){
         return newMockError( "TestNGMockAssertionError", message );
      }else{
         return newMockError( "JUnitMockAssertionError", message );
      }
   }

   private static Error newDynamicMockError( String name, DynamicMock dynamicMock, Invocation invocation,
         InvocationDispatcher dispatcher, String message ){
      try{
         Class klass = Class.forName("org.jmock.util."+name);
         Constructor ctr = klass.getConstructor( new Class[]{ DynamicMock.class,
                 Invocation.class, InvocationDispatcher.class, String.class} );
         return (Error) ctr.newInstance( new Object[]{ dynamicMock, invocation, dispatcher, message} );
      }catch( Exception e ){
         // ignore, let client fail with NPE
      }
      return null;
   }

   private static Error newMockError(String name){
      return newMockError(name,null);
   }
   
   private static Error newMockError( String name, String message ){
      try{
         Class klass = Class.forName("org.jmock.util."+name);
         if( message != null ){
            Constructor ctr = klass.getConstructor( new Class[]{ String.class} );
            return (Error) ctr.newInstance( new Object[]{  message} );
         }else{
            Constructor ctr = klass.getConstructor( new Class[0] );
            return (Error) ctr.newInstance( new Object[0] );
         }
      }catch( Exception e ){
         // ignore, let client fail with NPE
      }
      return null;
   }

   private JMockUtils()
   {

   }
}
