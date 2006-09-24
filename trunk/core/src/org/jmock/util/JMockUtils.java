/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.util;


import java.lang.reflect.Constructor;

import org.jmock.core.DynamicMock;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationDispatcher;
import org.jmock.util.Assert.IAssert;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 * @author <a href='mailto:the_mindstorm@evolva.ro'>Alexandru Popescu</a>
 */
public final class JMockUtils {
    public static final String MODE_SYSPROPERTY= "jmock.mode";
    public static final String JUNIT_MODE= "junit";
    public static final String TESTNG_MODE= "testng";

    private static final String TESTNG_ASSERTION_CLASS= "org.testng.Assert";
    private static final String PACKAGE_NAME= "org.jmock.util.";
    private static final String TESTNG_ASSERT_CLASS= "testng.TestNGAssert";
    private static final String JUNIT_ASSERT_CLASS= "junit.JUnitAssert";
    private static final String TESTNG_DYNAMICERROR_CLASS= PACKAGE_NAME
        + "testng.TestNGDynamicMockError";
    private static final String JUNIT_DYNAMICERROR_CLASS= PACKAGE_NAME
        + "junit.JUnitDynamicMockError";
    private static final String TESTNG_ASSERTERROR_CLASS= PACKAGE_NAME
        + "testng.TestNGMockAssertionError";
    private static final String JUNIT_ASSERTERROR_CLASS= PACKAGE_NAME
        + "junit.JUnitMockAssertionError";

    private static final String runMode;
    private static final boolean isTestNGAvailable;
    private static final IAssert assertionImplementation;
    private static final Class dynamicAssertErrorClass;
    private static final Class assertErrorClass;

    static {
        runMode= getRunMode();
        isTestNGAvailable= TESTNG_MODE.equals(runMode);
        assertionImplementation= loadAssertionImplementation();
        assertErrorClass= loadClass(isTestNGAvailable ? TESTNG_ASSERTERROR_CLASS
                                                      : JUNIT_ASSERTERROR_CLASS);
        dynamicAssertErrorClass= loadClass(isTestNGAvailable ? TESTNG_DYNAMICERROR_CLASS
                                                             : JUNIT_DYNAMICERROR_CLASS);
    }

    public static IAssert getAssert() {
        return assertionImplementation;
    }

    public static Error newDynamickMockError(DynamicMock dynamicMock,
                                             Invocation invocation,
                                             InvocationDispatcher dispatcher,
                                             String message) {
        return newDynamicMockError(dynamicAssertErrorClass,
                                   dynamicMock,
                                   invocation,
                                   dispatcher,
                                   message);
    }

    public static Error newMockAssertionError() {
        return newMockError(assertErrorClass, "");
    }

    public static Error newMockAssertionError(boolean message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(char message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(double message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(float message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(int message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(long message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(Object message) {
        return newMockError(assertErrorClass, String.valueOf(message));
    }

    public static Error newMockAssertionError(String message) {
        return newMockError(assertErrorClass, message);
    }

    private static Error newDynamicMockError(Class errorClass,
                                             DynamicMock dynamicMock,
                                             Invocation invocation,
                                             InvocationDispatcher dispatcher,
                                             String message) {
        try {
            Constructor ctr= errorClass.getConstructor(new Class[] {
                                                           DynamicMock.class, Invocation.class,
                                                           InvocationDispatcher.class, String.class
                                                       });

            return (Error) ctr.newInstance(new Object[] {
                                               dynamicMock, invocation, dispatcher, message
                                           });
        }
        catch(Exception e) {
            throw new LinkageError("Cannot create error instance of type " + errorClass.getName());
        }
    }

    private static Error newMockError(Class errorClass, String message) {
        try {
            Constructor ctr= errorClass.getConstructor(new Class[] { String.class });

            return (Error) ctr.newInstance(new Object[] { message });
        }
        catch(Exception e) {
          throw new LinkageError("Cannot create error instance of type " + errorClass.getName());
        }
    }

    private JMockUtils() {

    }

    private static String getRunMode() {
        final String mode= System.getProperty(MODE_SYSPROPERTY);

        if((null != mode) && JUNIT_MODE.equalsIgnoreCase(mode)) {
            return JUNIT_MODE;
        }
        else if((null != mode) && TESTNG_MODE.equalsIgnoreCase(mode)) {
            return TESTNG_MODE;
        }
        else {
            // HINT: autodiscovery
            try {
                Class.forName(TESTNG_ASSERTION_CLASS);

                return TESTNG_MODE;
            }
            catch(Exception ex) {
                return JUNIT_MODE;
            }
        }
    }

    private static IAssert loadAssertionImplementation() {
        String assertionClassName= isTestNGAvailable ? TESTNG_ASSERT_CLASS : JUNIT_ASSERT_CLASS;
        try {
            return (IAssert) Class.forName(assertionClassName).newInstance();
        }
        catch(Exception ex) {
            throw new LinkageError("Cannot create assertion class " + assertionClassName
                                   + " for mode " + runMode);
        }
    }

    private static Class loadClass(String className) {
        try {
            return Class.forName(className);
        }
        catch(Exception ex) {
            throw new LinkageError("Cannot load class " + className + " for mode " + runMode);
        }
    }
}
