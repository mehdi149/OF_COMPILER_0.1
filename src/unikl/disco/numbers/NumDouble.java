/*
 * This file is part of the Disco Deterministic Network Calculator v2.2.6 "Hydra".
 *
 * Copyright (C) 2014 - 2016 Steffen Bondorf
 *
 * disco | Distributed Computer Systems Lab
 * University of Kaiserslautern, Germany
 *
 * http://disco.cs.uni-kl.de
 *
 *
 * The Disco Deterministic Network Calculator (DiscoDNC) is free software;
 * you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; 
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */
 
package unikl.disco.numbers;

import unikl.disco.nc.CalculatorConfig.NumClass;

/**
 * Wrapper class around double;
 *
 * @author Steffen Bondorf
 *
 */
public class NumDouble extends Num {
	protected final NumClass num_class = NumClass.DOUBLE;
	
	private double value = 0.0;
	
	private static final double EPSILON = Double.parseDouble("5E-10");

	public static final NumDouble POSITIVE_INFINITY = createPositiveInfinity();
	public static final NumDouble NEGATIVE_INFINITY = createNegativeInfinity();
	public static final NumDouble NaN = createNaN();
	public static final NumDouble ZERO = createZero();
	
	private NumDouble() {}
	
	public NumDouble( double value ) {
		this.value = value;
		checkInftyNaN();
	}
	
	public NumDouble( String num_str ) throws Exception {
		if( num_str.equals( "Infinity" ) ) {
			value = Double.POSITIVE_INFINITY;
		} else {
			value = Num.parse( num_str ).doubleValue();
			checkInftyNaN();
		}
	}
	
	public NumDouble( int num ) {
		value = (double)num;
		checkInftyNaN();
	}
	
	public NumDouble( int num, int den ) {
		value = ((double)num) / ((double)den);
		checkInftyNaN();
	}
	
	public NumDouble( NumDouble num ) {
		value = num.value;
	}

	protected NumDouble( SpecialValue indicator ) {
		switch (indicator) {
			case POSITIVE_INFINITY:
				instantiatePositiveInfinity();
				break;
			case NEGATIVE_INFINITY:
				instantiateNegativeInfinity();
				break;
			case NaN:
				instantiateNaN();
				break;
			case ZERO:
				instantiateZero();
				break;
		}
	}
	
	private void checkInftyNaN() {
		// See Java documentation on parsing Doubles. There are rounding errors up to Math.ulp(Double.MAX_VALUE)/2.
		if( value >= Double.MAX_VALUE ){
			isNaN = false;
			isPosInfty = true;
			isNegInfty = false;
			return;
		}
		if ( Math.abs( value - Double.NEGATIVE_INFINITY ) < EPSILON ) {
			isNaN = false;
			isPosInfty = false;
			isNegInfty = true;
			return;
		}
		if( Double.isNaN( value ) ){
			isNaN = true;
			isPosInfty = false;
			isNegInfty = false;
			return;
		}
	}
	
	public static NumDouble createPositiveInfinity() {
		NumDouble pos_infty = new NumDouble();
		pos_infty.instantiatePositiveInfinity();
		return pos_infty;
	}
	
	private void instantiatePositiveInfinity() {
		value = Double.POSITIVE_INFINITY;
		isNaN = false;
		isPosInfty = true;
		isNegInfty = false;
	}

	public static NumDouble createNegativeInfinity() {
		NumDouble neg_infty = new NumDouble();
		neg_infty.instantiateNegativeInfinity();
		return neg_infty;
	}

	private void instantiateNegativeInfinity() {
		value = Double.NEGATIVE_INFINITY;
		isNaN = false;
		isPosInfty = false;
		isNegInfty = true;
	}
	
	public static NumDouble createNaN() {
		NumDouble nan = new NumDouble();
		nan.instantiateNaN();
		return nan;
	}
	
	private void instantiateNaN() {
		value = Double.NaN;
		isNaN = true;
		isPosInfty = false;
		isNegInfty = false;
	}
	
	public static NumDouble createZero() {
		NumDouble zero = new NumDouble();
		zero.instantiateZero();
		return zero;
	}
	
	private void instantiateZero() {
		value = 0.0;
		isNaN = false;
		isPosInfty = false;
		isNegInfty = false;
	}
	
	protected static NumDouble createEpsilon() {
        return new NumDouble( EPSILON );
	}
	
	protected static NumDouble add( NumDouble num1, NumDouble num2 ) {
		return new NumDouble( num1.doubleValue() + num2.doubleValue() );
	}
	
	protected static NumDouble sub( NumDouble num1, NumDouble num2 ) {
		double result = num1.doubleValue() - num2.doubleValue();
		if( Math.abs( result ) <= EPSILON ) {
			result = 0;
		}
		return new NumDouble( result );
	}
	
	protected static NumDouble mult( NumDouble num1, NumDouble num2 ) {
		return new NumDouble( num1.doubleValue() * num2.doubleValue() );
	}

	protected static NumDouble div( NumDouble num1, NumDouble num2 ) {
		return new NumDouble( num1.doubleValue() / num2.doubleValue() );
	}

	protected static NumDouble diff( NumDouble num1, NumDouble num2 ) {
		return new NumDouble( Math.max( num1.doubleValue(), num2.doubleValue() )
										- Math.min( num1.doubleValue(), num2.doubleValue() ) );	
	}

	protected static NumDouble max( NumDouble num1, NumDouble num2 ) {
		return new NumDouble( Math.max( num1.doubleValue(), num2.doubleValue() ) );
	}

	protected static NumDouble min( NumDouble num1, NumDouble num2 ) {
		return new NumDouble( Math.min( num1.doubleValue(), num2.doubleValue() ) );
	}
	
	protected static NumDouble abs( NumDouble num ) {
		return new NumDouble( Math.abs( num.doubleValue() ) );
	}

	protected static NumDouble negate( NumDouble num ) {
	    return new NumDouble( num.doubleValue() * -1 );
	}

	public boolean greater( Num num2 ) {
		if( this.isNaN || num2.isNaN ){
			return false;
		}
		
		if( num2.isPosInfty ){
			return false;
		}
		if( this.isPosInfty ){
			return true;
		}
		
		if( this.isNegInfty ){
			return false;
		}
		if( num2.isNegInfty ){
			return true;
		}
		
		return value > num2.doubleValue();
	}

	public boolean ge( Num num2 ) {
		if( this.isNaN || num2.isNaN ){
			return false;
		}
		
		if( this.isPosInfty ){
			return true;
		}
		if( num2.isPosInfty ){
			return false;
		}

		if( num2.isNegInfty ){
			return true;
		}
		if( this.isNegInfty ){
			return false;
		}
		
		return value >= num2.doubleValue();
	}

	public boolean less( Num num2 ) {
		if( this.isNaN || num2.isNaN ){
			return false;
		}

		if( this.isPosInfty ){
			return false;
		}
		if( num2.isPosInfty ){
			return true;
		}
		
		if( num2.isNegInfty ){
			return false;
		}
		if( this.isNegInfty ){
			return true;
		}
		
		return value < num2.doubleValue();
	}

	public boolean le( Num num2 ) {
		if( this.isNaN || num2.isNaN ){
			return false;
		}

		if( num2.isPosInfty ){
			return true;
		}
		if( this.isPosInfty ){
			return false;
		}

		if( this.isNegInfty ){
			return true;
		}
		if( num2.isNegInfty ){
			return false;
		}
		
		return value <= num2.doubleValue();
	}
	
	@Override
	public double doubleValue() {
	    return value;
	}

	@Override
	public Num copy() {
		if ( this.isNaN ) {
    		return createNaN();
    	}
    	if ( this.isPosInfty ) {
    		return createPositiveInfinity();
    	}
    	if ( this.isNegInfty ) {
			return createNegativeInfinity();
		}
    	
		return new NumDouble( value );
	}
	
	@Override
	public boolean equals( double num2 ) {
		if( Double.isNaN( num2 ) ){
			return this.isNaN;
		}
		if( num2 == Double.POSITIVE_INFINITY ){
			return this.isPosInfty;
		}
		if( num2 == Double.NEGATIVE_INFINITY ){
			return this.isNegInfty;
		}
		
		if( Math.abs( value - num2 ) <= EPSILON ) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean equals( NumDouble num2 ) {
		return equals( num2.value );
	}

	@Override
	public boolean equals( Object num2 ) {
		if( num2 == null ){
			return true;
		}
		
		NumDouble num2_Num;
		try {
			num2_Num = (NumDouble) num2;
			return equals( num2_Num.value );
		} catch( ClassCastException e ) {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Double.valueOf(value).hashCode();
	}
	
	@Override
	public String toString(){
		if ( this.isNaN ) {
    		return "NaN";
    	}
    	if ( this.isPosInfty ) {
    		return "Infinity";
    	}
    	if ( this.isNegInfty ) {
			return "-Infinity";
		}
    	
		return Double.toString( value );
	}
}
