/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core.stub;

import java.util.Collection;

import org.jmock.core.Stub;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class Stubs
{
   public static Stub doAll( Stub stub1, Stub stub2 )
   {
      return doAll( new Stub[] { stub1, stub2 } );
   }

   public static Stub doAll( Stub stub1, Stub stub2, Stub stub3 )
   {
      return doAll( new Stub[] { stub1, stub2, stub3 } );
   }

   public static Stub doAll( Stub stub1, Stub stub2, Stub stub3, Stub stub4 )
   {
      return doAll( new Stub[] { stub1, stub2, stub3, stub4 } );
   }

   public static Stub doAll( Stub[] stubs )
   {
      return new DoAllStub( stubs );
   }

   public static Stub onConsecutiveCalls( Stub stub1, Stub stub2 )
   {
      return onConsecutiveCalls( new Stub[] { stub1, stub2 } );
   }

   public static Stub onConsecutiveCalls( Stub stub1, Stub stub2, Stub stub3 )
   {
      return onConsecutiveCalls( new Stub[] { stub1, stub2, stub3 } );
   }

   public static Stub onConsecutiveCalls( Stub stub1, Stub stub2, Stub stub3, Stub stub4 )
   {
      return onConsecutiveCalls( new Stub[] { stub1, stub2, stub3, stub4 } );
   }

   public static Stub onConsecutiveCalls( Stub[] stubs )
   {
      return new StubSequence( stubs );
   }

   public static Stub returnIteratorStub( Collection collection )
   {
      return new ReturnIteratorStub( collection );
   }

   public static Stub returnIteratorStub( Object[] array )
   {
      return new ReturnIteratorStub( array );
   }

   public static Stub returnValue( boolean result )
   {
      return returnValue( new Boolean( result ) );
   }

   public static Stub returnValue( byte result )
   {
      return returnValue( new Byte( result ) );
   }

   public static Stub returnValue( char result )
   {
      return returnValue( new Character( result ) );
   }

   public static Stub returnValue( double result )
   {
      return returnValue( new Double( result ) );
   }

   public static Stub returnValue( float result )
   {
      return returnValue( new Float( result ) );
   }

   public static Stub returnValue( int result )
   {
      return returnValue( new Integer( result ) );
   }

   public static Stub returnValue( long result )
   {
      return returnValue( new Long( result ) );
   }

   public static Stub returnValue( Object o )
   {
      return new ReturnStub( o );
   }

   public static Stub returnValue( short result )
   {
      return returnValue( new Short( result ) );
   }

   public static Stub throwException( Throwable throwable )
   {
      return new ThrowStub( throwable );
   }

   private Stubs()
   {

   }
}