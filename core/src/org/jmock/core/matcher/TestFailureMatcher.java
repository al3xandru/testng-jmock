/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core.matcher;

import org.jmock.core.Invocation;
import org.jmock.core.InvocationMatcher;
import org.jmock.util.JMockUtils;


public class TestFailureMatcher
        implements InvocationMatcher
{
    private String errorMessage;

    public TestFailureMatcher( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public boolean matches( Invocation invocation ) {
        return true;
    }

    public boolean hasDescription() {
        return true;
    }

    public StringBuffer describeTo( StringBuffer buffer ) {
        return buffer.append(errorMessage);
    }

    public void invoked( Invocation invocation ) {
        throw JMockUtils.newMockAssertionError(errorMessage);
    }

    public void verify() {
        // Always verify
    }
}
