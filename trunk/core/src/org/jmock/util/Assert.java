/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util;

import org.jmock.core.Constraint;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class Assert
{
   private static final IAssert assertion = JMockUtils.getAssert();

   public static void assertEquals( boolean expected, boolean actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( byte expected, byte actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( char expected, char actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( double expected, double actual, double delta )
   {
      assertion.assertEquals( expected, actual, delta );
   }

   public static void assertEquals( float expected, float actual, float delta )
   {
      assertion.assertEquals( expected, actual, delta );
   }

   public static void assertEquals( int expected, int actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( long expected, long actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( Object expected, Object actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( short expected, short actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( String message, boolean expected, boolean actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, byte expected, byte actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, char expected, char actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, double expected, double actual, double delta )
   {
      assertion.assertEquals( message, expected, actual, delta );
   }

   public static void assertEquals( String message, float expected, float actual, float delta )
   {
      assertion.assertEquals( message, expected, actual, delta );
   }

   public static void assertEquals( String message, int expected, int actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, long expected, long actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, Object expected, Object actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, short expected, short actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String expected, String actual )
   {
      assertion.assertEquals( expected, actual );
   }

   public static void assertEquals( String message, String expected, String actual )
   {
      assertion.assertEquals( message, expected, actual );
   }

   public static void assertFalse( boolean condition )
   {
      assertion.assertFalse( condition );
   }

   public static void assertFalse( String message, boolean condition )
   {
      assertion.assertFalse( message, condition );
   }

   public static void assertNotNull( Object object )
   {
      assertion.assertNotNull( object );
   }

   public static void assertNotNull( String message, Object object )
   {
      assertion.assertNotNull( message, object );
   }

   public static void assertNotSame( Object expected, Object actual )
   {
      assertion.assertNotSame( expected, actual );
   }

   public static void assertNotSame( String message, Object expected, Object actual )
   {
      assertion.assertNotSame( message, expected, actual );
   }

   public static void assertNull( Object object )
   {
      assertion.assertNull( object );
   }

   public static void assertNull( String message, Object object )
   {
      assertion.assertNull( message, object );
   }

   public static void assertSame( Object expected, Object actual )
   {
      assertion.assertSame( expected, actual );
   }

   public static void assertSame( String message, Object expected, Object actual )
   {
      assertion.assertSame( message, expected, actual );
   }

   public static void assertThat( boolean actual, Constraint constraint )
   {
      assertThat( new Boolean( actual ), constraint );
   }

   public static void assertThat( byte actual, Constraint constraint )
   {
      assertThat( new Byte( actual ), constraint );
   }

   public static void assertThat( char actual, Constraint constraint )
   {
      assertThat( new Character( actual ), constraint );
   }

   public static void assertThat( double actual, Constraint constraint )
   {
      assertThat( new Double( actual ), constraint );
   }

   public static void assertThat( float actual, Constraint constraint )
   {
      assertThat( new Float( actual ), constraint );
   }

   public static void assertThat( int actual, Constraint constraint )
   {
      assertThat( new Integer( actual ), constraint );
   }

   public static void assertThat( long actual, Constraint constraint )
   {
      assertThat( new Long( actual ), constraint );
   }

   public static void assertThat( Object actual, Constraint constraint )
   {
      if( !constraint.eval( actual ) ){
         StringBuffer message = new StringBuffer( "\nExpected: " );
         constraint.describeTo( message );
         message.append( "\n    got : " )
               .append( actual )
               .append( '\n' );
         fail( message.toString() );
      }
   }

   public static void assertThat( short actual, Constraint constraint )
   {
      assertThat( new Short( actual ), constraint );
   }

   public static void assertTrue( boolean condition )
   {
      assertion.assertTrue( condition );
   }

   public static void assertTrue( String message, boolean condition )
   {
      assertion.assertTrue( message, condition );
   }

   public static void fail()
   {
      assertion.fail();
   }

   public static void fail( String message )
   {
      assertion.fail( message );
   }
   
   public static interface IAssert {
     void assertThat( boolean actual, Constraint constraint );

     void assertThat( byte actual, Constraint constraint );

     void assertThat( char actual, Constraint constraint );

     void assertThat( double actual, Constraint constraint );

     void assertThat( float actual, Constraint constraint );

     void assertThat( int actual, Constraint constraint );

     void assertThat( long actual, Constraint constraint );

     void assertThat( Object actual, Constraint constraint );

     void assertThat( short actual, Constraint constraint );

     // -------------------------------------------------

     void assertTrue( String message, boolean condition );

     void assertTrue( boolean condition );

     void assertFalse( String message, boolean condition );

     void assertFalse( boolean condition );

     void fail( String message );

     void fail();

     void assertEquals( String message, Object expected, Object actual );

     void assertEquals( Object expected, Object actual );

     void assertEquals( String message, String expected, String actual );

     void assertEquals( String expected, String actual );

     void assertEquals( String message, double expected, double actual, double delta );

     void assertEquals( double expected, double actual, double delta );

     void assertEquals( String message, float expected, float actual, float delta );

     void assertEquals( float expected, float actual, float delta );

     void assertEquals( String message, long expected, long actual );

     void assertEquals( long expected, long actual );

     void assertEquals( String message, boolean expected, boolean actual );

     void assertEquals( boolean expected, boolean actual );

     void assertEquals( String message, byte expected, byte actual );

     void assertEquals( byte expected, byte actual );

     void assertEquals( String message, char expected, char actual );

     void assertEquals( char expected, char actual );

     void assertEquals( String message, short expected, short actual );

     void assertEquals( short expected, short actual );

     void assertEquals( String message, int expected, int actual );

     void assertEquals( int expected, int actual );

     void assertNotNull( Object object );

     void assertNotNull( String message, Object object );

     void assertNull( Object object );

     void assertNull( String message, Object object );

     void assertSame( String message, Object expected, Object actual );

     void assertSame( Object expected, Object actual );

     void assertNotSame( String message, Object expected, Object actual );

     void assertNotSame( Object expected, Object actual );     
   }
}