/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util.testng;

import org.jmock.core.DynamicMock;
import org.jmock.core.DynamicMockError;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationDispatcher;

/**
 * @since 1.0
 */
public class TestNGDynamicMockError extends TestNGMockAssertionError implements DynamicMockError
{
   private final InvocationDispatcher dispatcher;
   private final DynamicMock dynamicMock;
   private final Invocation invocation;

   public TestNGDynamicMockError( DynamicMock dynamicMock, Invocation invocation,
         InvocationDispatcher dispatcher, String message )
   {
      super( message );
      this.dynamicMock = dynamicMock;
      this.invocation = invocation;
      this.dispatcher = dispatcher;
   }

   public InvocationDispatcher getDispatcher()
   {
      return dispatcher;
   }

   public DynamicMock getDynamicMock()
   {
      return dynamicMock;
   }

   public Invocation getInvocation()
   {
      return invocation;
   }

   public String getMessage()
   {
      return writeTo( new StringBuffer() ).toString();
   }

   public StringBuffer writeTo( StringBuffer buffer )
   {
      buffer.append( dynamicMock.toString() )
            .append( ": " )
            .append( super.getMessage() )
            .append( "\n" );
      buffer.append( "Invoked: " );
      invocation.describeTo( buffer );
      buffer.append( "\n" );
      buffer.append( "Allowed:\n" );
      dispatcher.describeTo( buffer );
      return buffer;
   }
}
