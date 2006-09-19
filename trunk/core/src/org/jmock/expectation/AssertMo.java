/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.expectation;

import org.jmock.Assert;
import org.jmock.core.Verifiable;
import org.jmock.util.JMockUtils;
import org.jmock.util.MockAssertionError;
import org.jmock.util.NotImplementedException;


public class AssertMo extends Assert
{

    protected AssertMo() {
        super();
    }

    public static void assertEquals( String description,
                                     Object[] expectedArray,
                                     Object[] actualArray ) {
        assertEquals(description + " (different lengths)",
                     expectedArray.length,
                     actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(description + " (element " + i + ")",
                         expectedArray[i],
                         actualArray[i]);
        }
    }

    public static void assertExcludes( String description,
                                       String excludeString,
                                       String targetString ) {
        assertTrue(description
                   + "\nExclude String: "
                   + excludeString
                   + "\n Target String: "
                   + targetString,
                   targetString.indexOf(excludeString) == -1);
    }

    public static void assertIncludes( String description,
                                       String includeString,
                                       String targetString ) {
        assertTrue(description
                   + "\nInclude String: "
                   + includeString
                   + "\n Target String: "
                   + targetString,
                   targetString.indexOf(includeString) != -1);
    }

    public static void assertStartsWith( String description,
                                         String startString,
                                         String targetString ) {
        assertTrue(description
                   + "\n Start String: "
                   + startString
                   + "\nTarget String: "
                   + targetString,
                   targetString.startsWith(startString));
    }

    public static void assertVerifyFails( Verifiable aVerifiable ) {
        boolean threwException = false;
        try {
            aVerifiable.verify();
        }
        catch (Error ex) {
           if (ex instanceof MockAssertionError) {
               threwException = true;
           } else {
               throw ex;
           }
        }
        

        assertTrue("Should not have verified", threwException);
    }

    static protected void failNotEquals( String message,
                                         Object expected,
                                         Object actual ) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + "\nExpected:<" + expected + ">\nReceived:<" + actual + ">");
    }

    public static void notImplemented( String mockName ) {
        throw new NotImplementedException("Not Implemented in " + mockName);
    }

    public static void assertFails( String message, Runnable runnable ) {
        try {
            runnable.run();
        }
        catch (Error expected) {
            Error example = JMockUtils.newMockAssertionError();
            if (expected instanceof MockAssertionError || 
                  expected.getClass().isAssignableFrom( example.getClass() )) {
                return;
            } else {
                throw expected;
            }
        }
        fail(message);
    }
}
