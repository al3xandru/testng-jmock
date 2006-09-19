/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core;

import java.util.List;

import org.jmock.core.constraint.IsAnything;
import org.jmock.core.matcher.ArgumentsMatcher;
import org.jmock.core.matcher.MethodNameMatcher;
import org.jmock.core.matcher.NoArgumentsMatcher;
import org.jmock.core.stub.CustomStub;
import org.jmock.core.stub.ReturnStub;
import org.jmock.util.JMockUtils;
import org.jmock.util.MockAssertionError;


public abstract class AbstractDynamicMock
    implements DynamicMock
{
    private InvocationDispatcher invocationDispatcher;
    private Class mockedType;
    private String name;
    private Error failure = null;


    public AbstractDynamicMock( Class mockedType, String name ) {
        this(mockedType, name, new LIFOInvocationDispatcher());
    }

    public AbstractDynamicMock( Class mockedType,
                                String name,
                                InvocationDispatcher invocationDispatcher ) {
        this.mockedType = mockedType;
        this.name = name;
        this.invocationDispatcher = invocationDispatcher;

        setupDefaultBehaviour();
    }

    abstract public Object proxy();

    public Class getMockedType() {
        return mockedType;
    }

    public void setDefaultStub( Stub newDefaultStub ) {
        invocationDispatcher.setDefaultStub(newDefaultStub);
    }

    public void addInvokable( Invokable invokable ) {
        invocationDispatcher.add(invokable);
    }

    public void reset() {
        //TODO write tests for this
        invocationDispatcher.clear();
        forgetFailure();
        setupDefaultBehaviour();
    }

    public void verify() {
        forgetFailure();

        try {
            invocationDispatcher.verify();
        } catch (Error ex) {
            if (ex instanceof MockAssertionError) {
                throw JMockUtils.newMockAssertionError( "mock object " + name + ": " + ex.getMessage());
            } else {
                throw ex;
            }
        }
    }

    public String toString() {
        return this.name;
    }

    public String getMockName() {
        return this.name;
    }

    public static String mockNameFromClass( Class c ) {
        return "mock" + Formatting.classShortName(c);
    }

    protected Object mockInvocation( Invocation invocation ) throws Throwable {
        if (failure != null && (invocation.invokedMethod.getDeclaringClass() != Object.class) ) {
            throw failure;
        }
    
        try {
            Object result = invocationDispatcher.dispatch(invocation);
            invocation.checkReturnTypeCompatibility(result);
            return result;
        }
        catch (Error error) {
            if (error instanceof MockAssertionError) {
                failure = JMockUtils.newDynamickMockError( this, invocation, invocationDispatcher, error.getMessage());
                failure.fillInStackTrace();
                throw failure;
            } else {
                throw error;
            }
        }
    }

    private void setupDefaultBehaviour() {
        addInvokable(hiddenInvocationMocker("toString",
                                            NoArgumentsMatcher.INSTANCE,
                                            new ReturnStub(name)));
        addInvokable(hiddenInvocationMocker("equals",
                                            new ArgumentsMatcher(new Constraint[]{new IsAnything()}),
                                            new IsSameAsProxyStub()));
        addInvokable(hiddenInvocationMocker("hashCode",
                                            NoArgumentsMatcher.INSTANCE,
                                            new HashCodeStub()));
    }

    private void forgetFailure() {
        failure = null;
    }

    private static final InvocationMocker.Describer NO_DESCRIPTION =
        new InvocationMocker.Describer() {
            public boolean hasDescription() {
                return false;
            }

            public void describeTo( StringBuffer buffer, List matchers, Stub stub, String name ) {
            }
        };

    private InvocationMocker hiddenInvocationMocker( String methodName,
                                                     InvocationMatcher arguments,
                                                     Stub stub )
    {
        InvocationMocker invocationMocker = new InvocationMocker(NO_DESCRIPTION);

        invocationMocker.addMatcher(new MethodNameMatcher(methodName));
        invocationMocker.addMatcher(arguments);
        invocationMocker.setStub(stub);

        return invocationMocker;
    }

    private class IsSameAsProxyStub extends CustomStub
    {
        public IsSameAsProxyStub() {
            super("returns whether equal to proxy");
        }

        public Object invoke( Invocation invocation ) throws Throwable {
            return new Boolean(invocation.parameterValues.get(0) == proxy());
        }
    }

    private class HashCodeStub extends CustomStub
    {
        public HashCodeStub() {
            super("returns hashCode for proxy");
        }

        public Object invoke( Invocation invocation ) throws Throwable {
            return new Integer(AbstractDynamicMock.this.hashCode());
        }
    }
}
