/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock;

import java.util.Collection;

import junit.framework.TestCase;

import org.jmock.core.Constraint;
import org.jmock.core.DynamicMock;
import org.jmock.core.InvocationMatcher;
import org.jmock.core.Stub;
import org.jmock.core.Verifiable;
import org.jmock.core.constraint.And;
import org.jmock.core.constraint.Constraints;
import org.jmock.core.constraint.HasProperty;
import org.jmock.core.constraint.HasPropertyWithValue;
import org.jmock.core.constraint.HasToString;
import org.jmock.core.constraint.IsArrayContaining;
import org.jmock.core.constraint.IsCloseTo;
import org.jmock.core.constraint.IsCollectionContaining;
import org.jmock.core.constraint.IsCompatibleType;
import org.jmock.core.constraint.IsEqual;
import org.jmock.core.constraint.IsIn;
import org.jmock.core.constraint.IsInstanceOf;
import org.jmock.core.constraint.IsMapContaining;
import org.jmock.core.constraint.IsNot;
import org.jmock.core.constraint.IsSame;
import org.jmock.core.constraint.Or;
import org.jmock.core.constraint.StringContains;
import org.jmock.core.constraint.StringEndsWith;
import org.jmock.core.constraint.StringStartsWith;
import org.jmock.core.matcher.InvocationMatchers;
import org.jmock.core.stub.Stubs;
import org.jmock.util.JMockUtils;

/**
 * A base class for tests that use <a href="http://www.mockobjects.com">Mock
 * Objects</a>. This class provides methods for creating mock objects and
 * expectations and automatically verifying mock objects after the test has run
 * but before the test fixture has been torn down.
 * 
 * @since 1.0.0
 */
public abstract class AbstractMockObjectTestCase extends TestCase
{
   public static final Constraint ANYTHING = Constraints.ANYTHING;
   public static final Constraint NOT_NULL = Constraints.NOT_NULL;
   public static final Constraint NULL = Constraints.NULL;

   public AbstractMockObjectTestCase()
   {
   }

   public AbstractMockObjectTestCase( String name )
   {
      super( name );
   }

   public And and( Constraint left, Constraint right )
   {
      return Constraints.and( left, right );
   }

   public IsArrayContaining arrayContaining( boolean element )
   {
      return Constraints.arrayContaining( new Boolean( element ) );
   }

   public IsArrayContaining arrayContaining( byte element )
   {
      return Constraints.arrayContaining( new Byte( element ) );
   }

   public IsArrayContaining arrayContaining( char element )
   {
      return Constraints.arrayContaining( new Character( element ) );
   }

   public IsArrayContaining arrayContaining( Constraint elementConstraint )
   {
      return Constraints.arrayContaining( elementConstraint );
   }

   public IsArrayContaining arrayContaining( double element )
   {
      return Constraints.arrayContaining( new Double( element ) );
   }

   public IsArrayContaining arrayContaining( float element )
   {
      return Constraints.arrayContaining( new Float( element ) );
   }

   public IsArrayContaining arrayContaining( int element )
   {
      return Constraints.arrayContaining( new Integer( element ) );
   }

   public IsArrayContaining arrayContaining( long element )
   {
      return Constraints.arrayContaining( new Long( element ) );
   }

   public IsArrayContaining arrayContaining( Object element )
   {
      return Constraints.arrayContaining( eq( element ) );
   }

   public IsArrayContaining arrayContaining( short element )
   {
      return Constraints.arrayContaining( new Short( element ) );
   }

   public void assertThat( boolean actual, Constraint constraint )
   {
      Assert.assertThat( new Boolean( actual ), constraint );
   }

   public void assertThat( byte actual, Constraint constraint )
   {
      Assert.assertThat( new Byte( actual ), constraint );
   }

   public void assertThat( char actual, Constraint constraint )
   {
      Assert.assertThat( new Character( actual ), constraint );
   }

   public void assertThat( double actual, Constraint constraint )
   {
      Assert.assertThat( new Double( actual ), constraint );
   }

   public void assertThat( float actual, Constraint constraint )
   {
      Assert.assertThat( new Float( actual ), constraint );
   }

   public void assertThat( int actual, Constraint constraint )
   {
      Assert.assertThat( new Integer( actual ), constraint );
   }

   public void assertThat( long actual, Constraint constraint )
   {
      Assert.assertThat( new Long( actual ), constraint );
   }

   public void assertThat( Object actual, Constraint constraint )
   {
      Assert.assertThat( actual, constraint );
   }

   public void assertThat( short actual, Constraint constraint )
   {
      Assert.assertThat( new Short( actual ), constraint );
   }

   public InvocationMatcher atLeastOnce()
   {
      return InvocationMatchers.atLeastOnce();
   }

   public InvocationMatcher atMostOnce()
   {
      return InvocationMatchers.atMostOnce();
   }

   public IsCollectionContaining collectionContaining( Constraint elementConstraint )
   {
      return Constraints.collectionContaining( elementConstraint );
   }

   public IsCollectionContaining collectionContaining( Object element )
   {
      return Constraints.collectionContaining( eq( element ) );
   }

   public IsCompatibleType compatibleType( Class baseType )
   {
      return Constraints.compatibleType( baseType );
   }

   public StringContains contains( String substring )
   {
      return Constraints.stringContains( substring );
   }

   /**
    * Calculates
    * 
    * @param mockedType
    * @return
    */
   public String defaultMockNameForType( Class mockedType )
   {
      return getMockTester().defaultMockNameForType( mockedType );
   }

   public Stub doAll( Stub stub1, Stub stub2 )
   {
      return Stubs.doAll( new Stub[] { stub1, stub2 } );
   }

   public Stub doAll( Stub stub1, Stub stub2, Stub stub3 )
   {
      return Stubs.doAll( new Stub[] { stub1, stub2, stub3 } );
   }

   public Stub doAll( Stub stub1, Stub stub2, Stub stub3, Stub stub4 )
   {
      return Stubs.doAll( new Stub[] { stub1, stub2, stub3, stub4 } );
   }

   public Stub doAll( Stub[] stubs )
   {
      return Stubs.doAll( stubs );
   }

   public StringEndsWith endsWith( String substring )
   {
      return Constraints.endsWith( substring );
   }

   public IsEqual eq( boolean operand )
   {
      return Constraints.eq( new Boolean( operand ) );
   }

   public IsEqual eq( byte operand )
   {
      return Constraints.eq( new Byte( operand ) );
   }

   public IsEqual eq( char operand )
   {
      return Constraints.eq( new Character( operand ) );
   }

   public IsEqual eq( double operand )
   {
      return Constraints.eq( new Double( operand ) );
   }

   public IsCloseTo eq( double operand, double error )
   {
      return Constraints.isCloseTo( operand, error );
   }

   public IsEqual eq( float operand )
   {
      return Constraints.eq( new Float( operand ) );
   }

   public IsEqual eq( int operand )
   {
      return Constraints.eq( new Integer( operand ) );
   }

   public IsEqual eq( long operand )
   {
      return Constraints.eq( new Long( operand ) );
   }

   public IsEqual eq( Object operand )
   {
      return Constraints.eq( operand );
   }

   public IsEqual eq( short operand )
   {
      return Constraints.eq( new Short( operand ) );
   }

   public InvocationMatcher exactly( int expectedCount )
   {
      return InvocationMatchers.exactly( expectedCount );
   }

   public HasProperty hasProperty( String propertyName )
   {
      return Constraints.hasProperty( propertyName );
   }

   public HasPropertyWithValue hasProperty( String propertyName, Constraint expectation )
   {
      return Constraints.hasProperty( propertyName, expectation );
   }

   public IsInstanceOf isA( Class operandClass )
   {
      return Constraints.isInstanceOf( operandClass );
   }

   public IsIn isIn( Collection collection )
   {
      return Constraints.isIn( collection );
   }

   public IsIn isIn( Object[] array )
   {
      return Constraints.isIn( array );
   }

   public IsMapContaining mapContaining( Constraint keyConstraint, Constraint valueConstraint )
   {
      return Constraints.mapContaining( keyConstraint, valueConstraint );
   }

   public IsMapContaining mapContaining( Object key, Object value )
   {
      return Constraints.mapContaining( eq( key ), eq( value ) );
   }

   public IsMapContaining mapWithKey( Constraint keyConstraint )
   {
      return Constraints.mapWithKey( keyConstraint );
   }

   public IsMapContaining mapWithKey( Object key )
   {
      return Constraints.mapWithKey( eq( key ) );
   }

   public IsMapContaining mapWithValue( Constraint valueConstraint )
   {
      return Constraints.mapWithValue( valueConstraint );
   }

   public IsMapContaining mapWithValue( Object value )
   {
      return Constraints.mapWithValue( eq( value ) );
   }

   /**
    * Creates a mock object that mocks the given type. The mock object is named
    * after the type; the exact name is calculated by
    * {@link #defaultMockNameForType}.
    * 
    * @param mockedType The type to be mocked.
    * @return A {@link Mock} object that mocks <var>mockedType</var>.
    */
   public Mock mock( Class mockedType )
   {
      return getMockTester().mock( mockedType, defaultMockNameForType( mockedType ) );
   }

   /**
    * Creates a mock object that mocks the given type and is explicitly given a
    * name. The mock object is named after the type; the exact name is
    * calculated by {@link #defaultMockNameForType}.
    * 
    * @param mockedType The type to be mocked.
    * @param roleName The name of the mock object
    * @return A {@link Mock} object that mocks <var>mockedType</var>.
    */
   public Mock mock( Class mockedType, String roleName )
   {
      return getMockTester().mock( mockedType, roleName );
   }

   public InvocationMatcher never()
   {
      return InvocationMatchers.never( "not expected" );
   }

   public InvocationMatcher never( String errorMessage )
   {
      return InvocationMatchers.never( "not expected (" + errorMessage + ")" );
   }

   public Object newDummy( Class dummyType )
   {
      return getMockTester().newDummy( dummyType );
   }

   public Object newDummy( Class dummyType, String name )
   {
      return getMockTester().newDummy( dummyType, name );
   }

   public Object newDummy( String name )
   {
      return getMockTester().newDummy( name );
   }

   public IsNot not( Constraint c )
   {
      return Constraints.not( c );
   }

   public InvocationMatcher once()
   {
      return InvocationMatchers.once();
   }

   public Stub onConsecutiveCalls( Stub stub1, Stub stub2 )
   {
      return Stubs.onConsecutiveCalls( new Stub[] { stub1, stub2 } );
   }

   public Stub onConsecutiveCalls( Stub stub1, Stub stub2, Stub stub3 )
   {
      return Stubs.onConsecutiveCalls( new Stub[] { stub1, stub2, stub3 } );
   }

   public Stub onConsecutiveCalls( Stub stub1, Stub stub2, Stub stub3, Stub stub4 )
   {
      return Stubs.onConsecutiveCalls( new Stub[] { stub1, stub2, stub3, stub4 } );
   }

   public Stub onConsecutiveCalls( Stub[] stubs )
   {
      return Stubs.onConsecutiveCalls( stubs );
   }

   public Or or( Constraint left, Constraint right )
   {
      return Constraints.or( left, right );
   }

   public void registerToVerify( Verifiable verifiable )
   {
      getMockTester().registerToVerify( verifiable );
   }

   public Stub returnIterator( Collection collection )
   {
      return Stubs.returnIteratorStub( collection );
   }

   public Stub returnIterator( Object[] array )
   {
      return Stubs.returnIteratorStub( array );
   }

   public Stub returnValue( boolean result )
   {
      return Stubs.returnValue( new Boolean( result ) );
   }

   public Stub returnValue( byte result )
   {
      return Stubs.returnValue( new Byte( result ) );
   }

   public Stub returnValue( char result )
   {
      return Stubs.returnValue( new Character( result ) );
   }

   public Stub returnValue( double result )
   {
      return Stubs.returnValue( new Double( result ) );
   }

   public Stub returnValue( float result )
   {
      return Stubs.returnValue( new Float( result ) );
   }

   public Stub returnValue( int result )
   {
      return Stubs.returnValue( new Integer( result ) );
   }

   public Stub returnValue( long result )
   {
      return Stubs.returnValue( new Long( result ) );
   }

   public Stub returnValue( Object o )
   {
      return Stubs.returnValue( o );
   }

   public Stub returnValue( short result )
   {
      return Stubs.returnValue( new Short( result ) );
   }

   public void runBare() throws Throwable
   {
      setUp();
      try{
         runTest();
         getMockTester().verify();
      }
      finally{
         tearDown();
      }
   }

   public IsSame same( Object operand )
   {
      return Constraints.isSame( operand );
   }

   public StringStartsWith startsWith( String substring )
   {
      return Constraints.startsWith( substring );
   }

   public StringContains stringContains( String substring )
   {
      return Constraints.stringContains( substring );
   }

   public Stub throwException( Throwable throwable )
   {
      return Stubs.throwException( throwable );
   }

   public HasToString toString( Constraint toStringConstraint )
   {
      return Constraints.toString( toStringConstraint );
   }

   public void unregisterToVerify( Verifiable verifiable )
   {
      getMockTester().unregisterToVerify( verifiable );
   }

   public void verify()
   {
      getMockTester().verify();
   }

   protected abstract MockTester getMockTester();

   protected DynamicMock newCoreMock( Class mockedType, String roleName )
   {
      return getMockTester().newCoreMock( mockedType, roleName );
   }
}