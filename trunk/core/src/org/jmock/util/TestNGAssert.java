/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util;

import org.jmock.core.Constraint;
import org.testng.Assert;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class TestNGAssert implements NewAssert
{
   public void assertEquals( boolean expected, boolean actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( byte expected, byte actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( char expected, char actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( double expected, double actual, double delta )
   {
      try{
         Assert.assertEquals( actual, expected, delta );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( float expected, float actual, float delta )
   {
      try{
         Assert.assertEquals( actual, expected, delta );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( int expected, int actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( long expected, long actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( Object expected, Object actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( short expected, short actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, boolean expected, boolean actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, byte expected, byte actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, char expected, char actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, double expected, double actual, double delta )
   {
      try{
         Assert.assertEquals( actual, expected, delta, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, float expected, float actual, float delta )
   {
      try{
         Assert.assertEquals( actual, expected, delta, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, int expected, int actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, long expected, long actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, Object expected, Object actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, short expected, short actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String expected, String actual )
   {
      try{
         Assert.assertEquals( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, String expected, String actual )
   {
      try{
         Assert.assertEquals( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertFalse( boolean condition )
   {
      try{
         Assert.assertFalse( condition );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertFalse( String message, boolean condition )
   {
      try{
         Assert.assertFalse( condition, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotNull( Object object )
   {
      try{
         Assert.assertNotNull( object );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotNull( String message, Object object )
   {
      try{
         Assert.assertNotNull( object, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotSame( Object expected, Object actual )
   {
      try{
         Assert.assertNotSame( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotSame( String message, Object expected, Object actual )
   {
      try{
         Assert.assertNotSame( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertNull( Object object )
   {
      try{
         Assert.assertNull( object );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertNull( String message, Object object )
   {
      try{
         Assert.assertNull( object, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertSame( Object expected, Object actual )
   {
      try{
         Assert.assertSame( actual, expected );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertSame( String message, Object expected, Object actual )
   {
      try{
         Assert.assertSame( actual, expected, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertThat( boolean actual, Constraint constraint )
   {
      assertThat( new Boolean( actual ), constraint );
   }

   public void assertThat( byte actual, Constraint constraint )
   {
      assertThat( new Byte( actual ), constraint );
   }

   public void assertThat( char actual, Constraint constraint )
   {
      assertThat( new Character( actual ), constraint );
   }

   public void assertThat( double actual, Constraint constraint )
   {
      assertThat( new Double( actual ), constraint );
   }

   public void assertThat( float actual, Constraint constraint )
   {
      assertThat( new Float( actual ), constraint );
   }

   public void assertThat( int actual, Constraint constraint )
   {
      assertThat( new Integer( actual ), constraint );
   }

   public void assertThat( long actual, Constraint constraint )
   {
      assertThat( new Long( actual ), constraint );
   }

   public void assertThat( Object actual, Constraint constraint )
   {
      if( !constraint.eval( actual ) ){
         StringBuffer message = new StringBuffer( "\nExpected: " );
         constraint.describeTo( message );
         message.append( "\n    got : " )
               .append( actual )
               .append( '\n' );
         try{
            Assert.fail( message.toString() );
         }
         catch( AssertionError e ){
            throw new TestNGMockAssertionError( e.getMessage() );
         }
      }
   }

   public void assertThat( short actual, Constraint constraint )
   {
      assertThat( new Short( actual ), constraint );
   }

   public void assertTrue( boolean condition )
   {
      try{
         Assert.assertTrue( condition );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void assertTrue( String message, boolean condition )
   {
      try{
         Assert.assertTrue( condition, message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void fail()
   {
      try{
         Assert.fail();
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }

   public void fail( String message )
   {
      try{
         Assert.fail( message );
      }
      catch( AssertionError e ){
         throw new TestNGMockAssertionError( e.getMessage() );
      }
   }
}