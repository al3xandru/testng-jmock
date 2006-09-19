/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util;

import org.jmock.core.Constraint;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public interface NewAssert
{
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