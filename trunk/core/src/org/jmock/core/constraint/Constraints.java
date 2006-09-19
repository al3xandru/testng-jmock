/*
 * Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core.constraint;

import java.util.Collection;

import org.jmock.core.Constraint;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class Constraints
{
   public static final Constraint ANYTHING = new IsAnything();
   public static final Constraint NULL = new IsNull();
   public static final Constraint NOT_NULL = new IsNot( NULL );

   public static And and( Constraint left, Constraint right )
   {
      return new And( left, right );
   }

   public static Constraint anything()
   {
      return ANYTHING;
   }

   public static IsArrayContaining arrayContaining( boolean element )
   {
      return arrayContaining( new Boolean( element ) );
   }

   public static IsArrayContaining arrayContaining( byte element )
   {
      return arrayContaining( new Byte( element ) );
   }

   public static IsArrayContaining arrayContaining( char element )
   {
      return arrayContaining( new Character( element ) );
   }

   public static IsArrayContaining arrayContaining( Constraint elementConstraint )
   {
      return new IsArrayContaining( elementConstraint );
   }

   public static IsArrayContaining arrayContaining( double element )
   {
      return arrayContaining( new Double( element ) );
   }

   public static IsArrayContaining arrayContaining( float element )
   {
      return arrayContaining( new Float( element ) );
   }

   public static IsArrayContaining arrayContaining( int element )
   {
      return arrayContaining( new Integer( element ) );
   }

   public static IsArrayContaining arrayContaining( long element )
   {
      return arrayContaining( new Long( element ) );
   }

   public static IsArrayContaining arrayContaining( Object element )
   {
      return arrayContaining( eq( element ) );
   }

   public static IsArrayContaining arrayContaining( short element )
   {
      return arrayContaining( new Short( element ) );
   }

   public static IsCollectionContaining collectionContaining( Constraint elementConstraint )
   {
      return new IsCollectionContaining( elementConstraint );
   }

   public static IsCollectionContaining collectionContaining( Object element )
   {
      return collectionContaining( eq( element ) );
   }

   public static IsCompatibleType compatibleType( Class baseType )
   {
      return new IsCompatibleType( baseType );
   }

   public static StringContains contains( String substring )
   {
      return stringContains( substring );
   }

   public static StringEndsWith endsWith( String substring )
   {
      return new StringEndsWith( substring );
   }

   public static IsEqual eq( boolean operand )
   {
      return eq( new Boolean( operand ) );
   }

   public static IsEqual eq( byte operand )
   {
      return eq( new Byte( operand ) );
   }

   public static IsEqual eq( char operand )
   {
      return eq( new Character( operand ) );
   }

   public static IsEqual eq( double operand )
   {
      return eq( new Double( operand ) );
   }

   public static IsCloseTo eq( double operand, double error )
   {
      return new IsCloseTo( operand, error );
   }

   public static IsEqual eq( float operand )
   {
      return eq( new Float( operand ) );
   }

   public static IsEqual eq( int operand )
   {
      return eq( new Integer( operand ) );
   }

   public static IsEqual eq( long operand )
   {
      return eq( new Long( operand ) );
   }

   public static IsEqual eq( Object operand )
   {
      return new IsEqual( operand );
   }

   public static IsEqual eq( short operand )
   {
      return eq( new Short( operand ) );
   }

   public static HasProperty hasProperty( String propertyName )
   {
      return new HasProperty( propertyName );
   }

   public static HasPropertyWithValue hasProperty( String propertyName, Constraint expectation )
   {
      return new HasPropertyWithValue( propertyName, expectation );
   }

   public static IsInstanceOf isA( Class operandClass )
   {
      return new IsInstanceOf( operandClass );
   }

   public static IsIn isIn( Collection collection )
   {
      return new IsIn( collection );
   }

   public static IsIn isIn( Object[] array )
   {
      return new IsIn( array );
   }

   public static IsCloseTo isCloseTo( double operand, double error ){
      return new IsCloseTo(operand, error);
   }
   public static IsSame isSame( Object object ){
      return new IsSame(object);
   }
   public static IsInstanceOf isInstanceOf(Class operandClass){
      return new IsInstanceOf(operandClass);
   }

   public static IsMapContaining mapContaining( Constraint keyConstraint, Constraint valueConstraint )
   {
      return new IsMapContaining( keyConstraint, valueConstraint );
   }

   public static IsMapContaining mapContaining( Object key, Object value )
   {
      return new IsMapContaining( eq( key ), eq( value ) );
   }

   public static IsMapContaining mapWithKey( Constraint keyConstraint )
   {
      return new IsMapContaining( keyConstraint, ANYTHING );
   }

   public static IsMapContaining mapWithKey( Object key )
   {
      return mapWithKey( eq( key ) );
   }

   public static IsMapContaining mapWithValue( Constraint valueConstraint )
   {
      return new IsMapContaining( ANYTHING, valueConstraint );
   }

   public static IsMapContaining mapWithValue( Object value )
   {
      return mapWithValue( eq( value ) );
   }

   public static Constraint nil()
   {
      return NULL;
   }

   public static IsNot not( Constraint c )
   {
      return new IsNot( c );
   }

   public static Constraint notNil()
   {
      return NOT_NULL;
   }

   public static Or or( Constraint left, Constraint right )
   {
      return new Or( left, right );
   }

   public static IsSame same( Object operand )
   {
      return new IsSame( operand );
   }

   public static StringStartsWith startsWith( String substring )
   {
      return new StringStartsWith( substring );
   }

   public static StringContains stringContains( String substring )
   {
      return new StringContains( substring );
   }

   public static HasToString toString( Constraint toStringConstraint )
   {
      return new HasToString( toStringConstraint );
   }

   private Constraints()
   {

   }
}
