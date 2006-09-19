/*
 * Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core.matcher;

import org.jmock.core.InvocationMatcher;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class InvocationMatchers
{
   public static InvocationMatcher atLeastOnce()
   {
      return new InvokeAtLeastOnceMatcher();
   }

   public static InvocationMatcher atMostOnce()
   {
      return new InvokeAtMostOnceMatcher();
   }

   public static InvocationMatcher exactly( int expectedCount )
   {
      return new InvokeCountMatcher( expectedCount );
   }

   public static InvocationMatcher never()
   {
      return new TestFailureMatcher( "not expected" );
   }

   public static InvocationMatcher never( String errorMessage )
   {
      return new TestFailureMatcher( "not expected (" + errorMessage + ")" );
   }

   public static InvocationMatcher once()
   {
      return new InvokeOnceMatcher();
   }

   private InvocationMatchers()
   {
   }
}