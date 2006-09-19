package org.jmock.core;

import org.jmock.util.MockAssertionError;

public interface DynamicMockError extends MockAssertionError
{
   InvocationDispatcher getDispatcher();

   DynamicMock getDynamicMock();

   Invocation getInvocation();

   StringBuffer writeTo( StringBuffer buffer );
}
