package org.jmock.util.junit;

import junit.framework.AssertionFailedError;

import org.jmock.util.MockAssertionError;

public class JUnitMockAssertionError extends AssertionFailedError implements MockAssertionError
{
   public JUnitMockAssertionError()
   {
      super();
   }

   public JUnitMockAssertionError( boolean detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }

   public JUnitMockAssertionError( char detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }

   public JUnitMockAssertionError( double detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }

   public JUnitMockAssertionError( float detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }

   public JUnitMockAssertionError( int detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }
   
   public JUnitMockAssertionError( long detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }
   
   public JUnitMockAssertionError( Object detailMessage )
   {
      super( String.valueOf( detailMessage ) );
   }

   public JUnitMockAssertionError( String message )
   {
      super( message );
   }
}