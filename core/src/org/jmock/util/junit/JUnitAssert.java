/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util.junit;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.jmock.core.Constraint;
import org.jmock.util.Assert.IAssert;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class JUnitAssert implements IAssert
{
   public void assertEquals( boolean expected, boolean actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( byte expected, byte actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( char expected, char actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( double expected, double actual, double delta )
   {
      try{
         Assert.assertEquals( expected, actual, delta );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( float expected, float actual, float delta )
   {
      try{
         Assert.assertEquals( expected, actual, delta );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( int expected, int actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( long expected, long actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( Object expected, Object actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( short expected, short actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, boolean expected, boolean actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, byte expected, byte actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, char expected, char actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, double expected, double actual, double delta )
   {
      try{
         Assert.assertEquals( message, expected, actual, delta );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, float expected, float actual, float delta )
   {
      try{
         Assert.assertEquals( message, expected, actual, delta );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, int expected, int actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, long expected, long actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, Object expected, Object actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, short expected, short actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String expected, String actual )
   {
      try{
         Assert.assertEquals( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertEquals( String message, String expected, String actual )
   {
      try{
         Assert.assertEquals( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertFalse( boolean condition )
   {
      try{
         Assert.assertFalse( condition );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertFalse( String message, boolean condition )
   {
      try{
         Assert.assertFalse( message, condition );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotNull( Object object )
   {
      try{
         Assert.assertNotNull( object );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotNull( String message, Object object )
   {
      try{
         Assert.assertNotNull( message, object );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotSame( Object expected, Object actual )
   {
      try{
         Assert.assertNotSame( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertNotSame( String message, Object expected, Object actual )
   {
      try{
         Assert.assertNotSame( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertNull( Object object )
   {
      try{
         Assert.assertNull( object );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertNull( String message, Object object )
   {
      try{
         Assert.assertNull( message, object );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertSame( Object expected, Object actual )
   {
      try{
         Assert.assertSame( expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertSame( String message, Object expected, Object actual )
   {
      try{
         Assert.assertSame( message, expected, actual );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
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
            fail( message.toString() );
         }
         catch( AssertionFailedError e ){
            throw new JUnitMockAssertionError( e.getMessage() );
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
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void assertTrue( String message, boolean condition )
   {
      try{
         Assert.assertTrue( message, condition );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void fail()
   {
      try{
         Assert.fail();
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }

   public void fail( String message )
   {
      try{
         Assert.fail( message );
      }
      catch( AssertionFailedError e ){
         throw new JUnitMockAssertionError( e.getMessage() );
      }
   }
}