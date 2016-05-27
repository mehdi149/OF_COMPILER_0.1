/*
 * This file is part of the Disco Deterministic Network Calculator v2.2.6 "Hydra".
 *
 * Copyright (C) 2005 - 2007 Frank A. Zdarsky
 * Copyright (C) 2013 - 2016 Steffen Bondorf
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

package unikl.disco.curves;

import java.util.ArrayList;
import java.util.List;

import unikl.disco.nc.CalculatorConfig;
import unikl.disco.numbers.Num;

/**
 * 
 * @author Frank A. Zdarsky
 * @author Steffen Bondorf
 *
 */
public class ArrivalCurve extends Curve {
	public ArrivalCurve() {
		super();
	}
	
	public ArrivalCurve( Curve curve ) {
		super( curve );

		makeArrivalCurve();
		
		if( CalculatorConfig.ARRIVAL_CURVE_CHECKS && !isWideSenseIncreasing() ) { // too strong requirement: !isConcave()
			System.out.println( toString() );
			throw new RuntimeException( "Arrival curves can only be created from wide-sense increasing functions." );
		}
	}
	
	public ArrivalCurve( int segment_count ) {
		super( segment_count );
	}
	
	public ArrivalCurve( String arrival_curve_str ) throws Exception {
		if( arrival_curve_str == null || arrival_curve_str.isEmpty() || arrival_curve_str.length() < 9 ) { // Smallest possible string: {(0,0),0}
			throw new RuntimeException( "Invalid string representation of a service curve." );
		}
		
		initializeCurve( arrival_curve_str );
		makeArrivalCurve();
		
		if( CalculatorConfig.ARRIVAL_CURVE_CHECKS && !isWideSenseIncreasing() ) { // too strong requirement: !isConcave()
			System.out.println( toString() );
			throw new RuntimeException( "Arrival curves can only be created from wide-sense increasing functions." );
		}
	}

	private void makeArrivalCurve() {
		if ( getSegment(0).y.greater( Num.getZero() ) ) {
			LinearSegment[] segments_new = new LinearSegment[segments.length+1];
			segments_new[0] = LinearSegment.createNullSegment();
			
			System.arraycopy( segments, 0, segments_new, 1, segments.length );
			segments_new[1].leftopen = true;
			
			this.segments = segments_new;
		}
	}
	
	/**
	 * Creates a null arrival curve.
	 * 
	 * @return a <code>Curve</code> instance
	 */
	public static ArrivalCurve createNullArrival() {
		ArrivalCurve ac_result = new ArrivalCurve();
		ac_result.initializeNullCurve();
		
		return ac_result;
	}
	
	/**
	 * Creates a burst curve with zero delay.
	 * 
	 * @return a <code>Curve</code> instance
	 */
	public static ArrivalCurve createZeroDelayInfiniteBurst() {
		ArrivalCurve ac_result = new ArrivalCurve();
		ac_result.initializeZeroDelayInfiniteBurst();
		
		return ac_result;
	}

	/**
	 * Creates a new token bucket curve.
	 * 
	 * @param rate the rate
	 * @param burst the burstiness
	 * @return a <code>Curve</code> instance
	 */
	public static ArrivalCurve createTokenBucket( Num rate, Num burst ) {
		ArrivalCurve ac_result = new ArrivalCurve();
		ac_result.initializeTokenBucket( rate, burst );

		ac_result.token_buckets = new ArrayList<Curve>();
		ac_result.token_buckets.add( ac_result.copy() );
		ac_result.is_token_bucket = true;
		
		return ac_result;
	}

	/**
	 * Creates a new token bucket curve.
	 * 
	 * @param rate the rate
	 * @param burst the burstiness
	 * @return a <code>Curve</code> instance
	 */
	public static ArrivalCurve createTokenBucket( double rate, double burst ) {
		ArrivalCurve ac_result = new ArrivalCurve();
		ac_result.initializeTokenBucket( rate, burst );
		
		ac_result.token_buckets = new ArrayList<Curve>();
		ac_result.token_buckets.add( ac_result.copy() );
		ac_result.is_token_bucket = true;
		
		return ac_result;
	}

	/**
	 * Creates a new curve from a list of token bucket curves.
	 * 
	 * @param token_buckets a list of token bucket curves
	 * @return a <code>Curve</code> instance
	 */
	@Deprecated
	public static ArrivalCurve createFromTokenBuckets( List<Curve> token_buckets ) {
		ArrivalCurve ac_result = new ArrivalCurve();
		ac_result.initializeCurve( Curve.createFromTokenBuckets( token_buckets ) );
		
		return ac_result;
	}
	
	public Num getBurst() {
		if( segments.length > 1 ) {		// Arrival curves pass through the origin
			return segments[1].y.copy();
		} else {						// rate functions have burst 0
			return Num.createZero();
		}
		// Old code working with any kind of curve
//		return fLimitRight( Num.getZero() );
	}
	
	public ArrivalCurve copy() {
		ArrivalCurve ac_copy = new ArrivalCurve();
		ac_copy.initializeCurve( this );
		
		return ac_copy;
	}

	public static ArrivalCurve add( ArrivalCurve arrival_curve_1, ArrivalCurve arrival_curve_2 ) {
		return new ArrivalCurve( Curve.add( arrival_curve_1, arrival_curve_2 ) );
	}

	public static ArrivalCurve add( ArrivalCurve arrival_curve_1, Num dy ) {
		return new ArrivalCurve( Curve.add( arrival_curve_1, dy ) );
	}

	public static ArrivalCurve min( ArrivalCurve arrival_curve_1, ArrivalCurve arrival_curve_2 ) {
		return new ArrivalCurve( Curve.min( arrival_curve_1, arrival_curve_2 ) );
	}
	
	/**
	 * Returns a copy of this curve that is shifted to the right by <code>dx</code>,
	 * i.e. g(x) = f(x-dx).
	 * 
	 * @param dx the offset to shift the curve.
	 * @return the shifted curve.
	 */
	public ArrivalCurve shiftRight( Num dx ) {
		return new ArrivalCurve( Curve.shiftRight( this, dx ) );
	}
	
	/**
	 * Returns a copy of this curve that is shifted to the left by <code>dx</code>,
	 * i.e. g(x) = f(x+dx). Note that the new curve is clipped at the y-axis so
	 * that in most cases <code>c.shiftLeftClipping(dx).shiftRight(dx) != c</code>!
	 * 
	 * @param dx the offset to shift the curve.
	 * @return the shifted curve.
	 */
	@Deprecated
	public ArrivalCurve shiftLeftClipping( Num dx ) {
		return new ArrivalCurve( Curve.shiftLeftClipping( this, dx ) );
	}
	
	@Override
	public boolean equals( Object obj ) {
		return ( obj instanceof ArrivalCurve ) && super.equals( obj );
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Returns a string representation of this curve.
	 * 
	 * @return the curve represented as a string.
	 */
	@Override
	public String toString() {
		return "AC" + super.toString();
	}
}
