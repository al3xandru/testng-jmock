/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock;

import org.jmock.core.Constraint;
import org.jmock.util.JMockUtils;
import org.jmock.util.NewAssert;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class Assert
{
   private static final NewAssert nassert = JMockUtils.getAssert();

   public static void assertEquals( boolean expected, boolean actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( byte expected, byte actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( char expected, char actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( double expected, double actual, double delta )
   {
      nassert.assertEquals( expected, actual, delta );
   }

   public static void assertEquals( float expected, float actual, float delta )
   {
      nassert.assertEquals( expected, actual, delta );
   }

   public static void assertEquals( int expected, int actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( long expected, long actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( Object expected, Object actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( short expected, short actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( String message, boolean expected, boolean actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, byte expected, byte actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, char expected, char actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, double expected, double actual, double delta )
   {
      nassert.assertEquals( message, expected, actual, delta );
   }

   public static void assertEquals( String message, float expected, float actual, float delta )
   {
      nassert.assertEquals( message, expected, actual, delta );
   }

   public static void assertEquals( String message, int expected, int actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, long expected, long actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, Object expected, Object actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String message, short expected, short actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertEquals( String expected, String actual )
   {
      nassert.assertEquals( expected, actual );
   }

   public static void assertEquals( String message, String expected, String actual )
   {
      nassert.assertEquals( message, expected, actual );
   }

   public static void assertFalse( boolean condition )
   {
      nassert.assertFalse( condition );
   }

   public static void assertFalse( String message, boolean condition )
   {
      nassert.assertFalse( message, condition );
   }

   public static void assertNotNull( Object object )
   {
      nassert.assertNotNull( object );
   }

   public static void assertNotNull( String message, Object object )
   {
      nassert.assertNotNull( message, object );
   }

   public static void assertNotSame( Object expected, Object actual )
   {
      nassert.assertNotSame( expected, actual );
   }

   public static void assertNotSame( String message, Object expected, Object actual )
   {
      nassert.assertNotSame( message, expected, actual );
   }

   public static void assertNull( Object object )
   {
      nassert.assertNull( object );
   }

   public static void assertNull( String message, Object object )
   {
      nassert.assertNull( message, object );
   }

   public static void assertSame( Object expected, Object actual )
   {
      nassert.assertSame( expected, actual );
   }

   public static void assertSame( String message, Object expected, Object actual )
   {
      nassert.assertSame( message, expected, actual );
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
      nassert.assertTrue( condition );
   }

   public static void assertTrue( String message, boolean condition )
   {
      nassert.assertTrue( message, condition );
   }

   public static void fail()
   {
      nassert.fail();
   }

   public static void fail( String message )
   {
      nassert.fail( message );
   }
}