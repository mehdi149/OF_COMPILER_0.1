/*
 * This file is part of the Disco Deterministic Network Calculator v2.2.6 "Hydra".
 *
 * Copyright (C) 2005 - 2007 Frank A. Zdarsky
 * Copyright (C) 2012 - 2016 Steffen Bondorf
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

import unikl.disco.numbers.Num;

/**
 * Class representing linear segments of a curve. A linear segments starts at
 * point (<code>x0</code>, <code>y0</code>) and continues to the right
 * with slope <code>grad</code>. If <code>leftopen</code> is
 * <code>true</code>, the point (<code>x0</code>,<code>y0</code>) is
 * excluded from the segment, otherwise is is included.
 *
 * @author Frank A. Zdarsky
 * @author Steffen Bondorf
 *
 */
public class LinearSegment{
	/** The x-coordinate of the linear segment's starting point. */
	protected Num x;

	/** The y-coordinate of the linear segment's starting point. */
	protected Num y;

	/** The gradient of the linear segment */
	protected Num grad;

	/** Whether the point (<code>x0</code>, <code>y0</code>) is part of the segment or not. */
	protected boolean leftopen;

	/**
	 * The default constructor.
	 */
	private LinearSegment() {
		x = Num.createZero();
		y = Num.createZero();
		grad = Num.createZero();
		leftopen = false;
	}
	
	public LinearSegment( String segment_str ) throws Exception {
		// Is this segment left-open?
		leftopen = false;
		switch ( segment_str.charAt( 0 ) ) {
			case '!':
				leftopen = true;
				segment_str = segment_str.substring( 1 );
				break;
			case '(':
				// Formatting seems to be ok (for now at least)
				break;
			default:
				throw new RuntimeException( "Invalid string representation of a linear segment." );
		}
		
		// Remove the bracket at the front
		segment_str = segment_str.substring( 1 ); 
		
		String[] xy_r = segment_str.split( "\\)," ); // ["x,y","grad"]
		if( xy_r.length != 2 ) {
			throw new RuntimeException( "Invalid string representation of a linear segment." );
		}
		
		String[] x_y = xy_r[0].split( "," );
		if( x_y.length != 2 ) {
			throw new RuntimeException( "Invalid string representation of a linear segment." );
		}
		
		x = Num.createNum( x_y[0] );
		y = Num.createNum( x_y[1] );
		grad = Num.createNum( xy_r[1] );
	}

	public static LinearSegment createNullSegment() {
		return LinearSegment.createHorizontalLine( 0.0 ); // X-axis
	}
	
	public static LinearSegment createHorizontalLine( double y ) {
		LinearSegment segment = new LinearSegment();
		segment.x = Num.createZero();
		segment.y = Num.createNum( y );
		segment.grad = Num.createZero();
		segment.leftopen = false;
		return segment;
	}
	
	public static LinearSegment getXAxis() {
		return createHorizontalLine( 0.0 );
	}
	
	/**
	 * A convenient constructor.
	 * 
	 * @param x The x-coordinate this segments starts at.
	 * @param y The y-coordinate this segments starts at.
	 * @param grad The segments gradient.
	 * @param leftopen Set the segment to be left-open.
	 */
	public LinearSegment( Num x, Num y, Num grad, boolean leftopen ) {
		this.x = x;
		this.y = y;
		this.grad = grad;
		this.leftopen = leftopen;
	}

	public Num getX() {
		return x.copy();
	}

	public void setX( Num x ) {
		this.x = x.copy();
	}
	
	public Num getY() {
		return y.copy();
	}
	
	public void setY( Num y ) {
		this.y = y.copy();
	}
	
	public Num getGrad() {
		return grad.copy();
	}
	
	public void setGrad( Num grad ) {
		this.grad = grad.copy();
	}
	
	public boolean isLeftopen() {
		return leftopen;
	}
	
	public void setIsLeftopen( boolean isLeftopen ) {
		this.leftopen = isLeftopen;
	}
	
	/**
	 * Helper creating a new segment starting at x that is the sum of the given getSegment.
	 * 
	 * @param s1 Segment 1.
	 * @param s2 Segment 2.
	 * @param x New x-coordinate the start at.
	 * @param leftopen Set the segment to be left-open.
	 * @return The new linear segment, pointwise sum of the given ones, starting in x.
	 */
	protected static LinearSegment add( LinearSegment s1, LinearSegment s2, Num x, boolean leftopen ) {
		LinearSegment result = LinearSegment.createHorizontalLine( 0.0 );
		result.x       = x;
		result.y       = Num.add( s1.f( x ), s2.f( x ) );
		result.grad     = Num.add( s1.grad, s2.grad );
		result.leftopen = leftopen;
		return result;
	}

	/**
	 * Helper creating a new segment starting at x that is the difference between the given getSegment.
	 * 
	 * @param s1 Segment 1.
	 * @param s2 Segment 2.
	 * @param x New x-coordinate the start at.
	 * @param leftopen Set the segment to be left-open.
	 * @return The new linear segment, pointwise difference of the given ones, i.e., s1 - s2, starting in x.
	 */
	public static LinearSegment sub( LinearSegment s1, LinearSegment s2, Num x, boolean leftopen ) {
		LinearSegment result = LinearSegment.createHorizontalLine( 0.0 );
		result.x       = x;
		result.y       = Num.sub( s1.f( x ), s2.f( x ) );
		result.grad     = Num.sub( s1.grad, s2.grad );
		result.leftopen = leftopen;
		return result;
	}

	/**
	 * Helper creating a new segment starting at x that is the minimum of the given getSegment.
	 * Note: Only valid if s1 and s2 do not intersect before the next IP.
	 * 
	 * @param s1 Segment 1.
	 * @param s2 Segment 2.
	 * @param x New x-coordinate the start at.
	 * @param leftopen Set the segment to be left-open.
	 * @param crossed Provides information if the segments intersect.
	 * @return The new linear segment, pointwise minimum of the given ones, starting in x.
	 */
	public static LinearSegment min( LinearSegment s1, LinearSegment s2, Num x, boolean leftopen, boolean crossed ) {
		Num f1_x = s1.f( x );
		Num f2_x = s2.f( x );

		LinearSegment result = LinearSegment.createHorizontalLine( 0.0 );
		result.x = x;
		if ( crossed || Num.abs( Num.sub( f1_x, f2_x ) ).less( Num.getEpsilon() ) ) {
			result.y   = f1_x;
			result.grad = Num.min( s1.grad, s2.grad );
		} else if ( f1_x.less( f2_x ) ) {
			result.y   = f1_x;
			result.grad = s1.grad;
		} else {
			result.y   = f2_x;
			result.grad = s2.grad;
		}
		result.leftopen = leftopen;
		return result;
	}

	/**
	 * Helper creating a new segment starting at x that is the maximum of the given segments.
	 * Note: Only valid if s1 and s2 do not intersect before the next IP.
	 * 
	 * @param s1 Segment 1.
	 * @param s2 Segment 2.
	 * @param x New x-coordinate the start at.
	 * @param leftopen Set the segment to be left-open.
	 * @param crossed Provides information if the segments intersect.
	 * @return The new linear segment, pointwise maximum of the given ones, starting in x.
	 */
	public static LinearSegment max( LinearSegment s1, LinearSegment s2, Num x, boolean leftopen, boolean crossed ) {
		Num f1_x = s1.f( x );
		Num f2_x = s2.f( x );

		LinearSegment result = LinearSegment.createHorizontalLine( 0.0 );
		result.x = x;
		if ( crossed || Num.abs( Num.sub( f1_x, f2_x ) ).less( Num.getEpsilon() ) ) {
			result.y   = f1_x;
			result.grad = Num.max( s1.grad, s2.grad );
		} else if ( f1_x.greater( f2_x ) ) {
			result.y   = f1_x;
			result.grad = s1.grad;
		} else {
			result.y   = f2_x;
			result.grad = s2.grad;
		}
		result.leftopen = leftopen;
		return result;
	}

	/**
	 * Returns a copy of this instance.
	 *
	 * @return a copy of this instance.
	 */
	public LinearSegment copy() {
		LinearSegment copy = new LinearSegment();
		if( this.x != null ) {
			copy.x = this.x.copy();			
		} else {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if( this.y != null ) {
			copy.y = this.y.copy();			
		} else {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if( this.grad != null ) {
			copy.grad = this.grad.copy();		
		} else {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		copy.leftopen = this.leftopen;
		return copy;
	}

	/**
	 * Returns the function value of this linear segment at the given
	 * x-coordinate. Note that there is no test whether the function is defined
	 * at this location, but simply returns the the value of the co-linear line.
	 *
	 * @param x the coordinate whose function value shall be returned
	 * @return the function value
	 */
	public Num f( Num x ) {
		return Num.add( Num.mult( Num.sub( x, this.x ), grad ), y );
	}

	/**
	 * Returns the x-coordinate at which a co-linear line through this segment
	 * intersects a co-linear line through the segment <code>other</code>.
	 *
	 * @param other the other segment
	 * @return the x-coordinate at which the segments cross or NaN of they are parallel
	 */
	public Num getXIntersectionWith( LinearSegment other ) {
		Num y1 = Num.sub( this.y, Num.mult( x, this.grad ) );
		Num y2 = Num.sub( other.y, Num.mult( other.x, other.grad ) );
		return Num.div( Num.sub( y2, y1 ), Num.sub( this.grad, other.grad ) ); // returns NaN if lines are parallel
	}
	
	@Override
	public boolean equals( Object obj ) {
		if ( obj == null || !( obj instanceof LinearSegment ) ) {
			return false;
		}
		
		LinearSegment other = (LinearSegment) obj;
		boolean result;
		result = this.x.equals( other.x );
		result = result && this.y.equals( other.y );
		result = result && this.grad.equals( other.grad );
		result = result && ( this.leftopen == other.leftopen );
		return result;
	}
	
	@Override
	public int hashCode() {
		return (int) x.hashCode() *
					 y.hashCode() *
					 grad.hashCode() *
					 Boolean.valueOf(leftopen).hashCode();
	}
	
	/**
	 * Returns a string representation of this linear segment.
	 * 
	 * @return the linear segment represented as a string.
	 */
	@Override
	public String toString() {
		String result = "";
		if ( this.leftopen ) {
			result = "!";
		}
		result += "(" + x.toString() + "," + y.toString() + ")," + grad.toString();
		
		return result;
	}
}
