/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core.stub;

import org.jmock.core.Invocation;
import org.jmock.core.Stub;
import org.jmock.util.JMockUtils;


public class TestFailureStub implements Stub
{
    String errorMessage;

    public TestFailureStub( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public Object invoke( Invocation invocation ) throws Throwable {
        throw JMockUtils.newMockAssertionError(errorMessage);
    }

    public StringBuffer describeTo( StringBuffer buffer ) {
        return buffer.append("fails the test and reports \"" + errorMessage + "\"");
    }
}
