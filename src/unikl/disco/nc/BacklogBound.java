/*
 * This file is part of the Disco Deterministic Network Calculator v2.2.6 "Hydra".
 *
 * Copyright (C) 2005 - 2007 Frank A. Zdarsky
 * Copyright (C) 2011 - 2016 Steffen Bondorf
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

package unikl.disco.nc;

import java.util.ArrayList;

import unikl.disco.curves.Curve;
import unikl.disco.curves.ArrivalCurve;
import unikl.disco.curves.ServiceCurve;
import unikl.disco.network.Flow;
import unikl.disco.network.Network;
import unikl.disco.network.Server;
import unikl.disco.numbers.Num;

/**
 * 
 * @author Frank A. Zdarsky
 * @author Steffen Bondorf
 *
 */
public class BacklogBound {
	public static Num derive( ArrivalCurve arrival_curve, ServiceCurve service_curve ) {
		if ( service_curve.equals( ServiceCurve.createNullService() )
				&& !arrival_curve.equals( ArrivalCurve.createNullArrival() ) ) {
			return Num.createPositiveInfinity();
		}
		
		if ( arrival_curve.equals( ArrivalCurve.createNullArrival() ) 
				|| service_curve.equals( ServiceCurve.createZeroDelayInfiniteBurst() ) ) {
			return Num.createZero();
		} else {
			if( arrival_curve.getSustainedRate().greater( service_curve.getSustainedRate() ) ) { // Only if the service curve is
				return Num.createPositiveInfinity();
			}
		}
		
		// The computeInflectionPoints based method does not work for 
		// single rate service curves (without latency)
		// in conjunction with token bucket arrival curves
		// because their common inflection point is in zero, 
		// where the arrival curve is 0.0 by definition.
		// This leads to a vertical deviation of 0 the arrival curve's burst
		// (or infinity which is already handled by the first if-statement)
		
		// Solution: 
		// Start with the burst as minimum vertical deviation
		
		Num result = arrival_curve.fLimitRight( Num.getZero() );
				
		ArrayList<Num> xcoords = Curve.computeInflectionPointsX( arrival_curve, service_curve );	
		for( int i = 0; i < xcoords.size(); i++ ) {
			Num ip_x = ( (Num) xcoords.get( i ) );

			Num backlog = Num.sub( arrival_curve.f( ip_x ), service_curve.f( ip_x ) );
			result = Num.max( result, backlog );
		}
		return result;
	}
	
	public static double derivePmooSinkTreeTbRl( Network tree, Server root) throws Exception {
		double bound = 0.0;
		double sum_T = 0.0;
		
		for ( Flow f : tree.getFlows( root ) ) {			
			sum_T = 0.0;
			for ( Server s : f.getSubPath( f.getSource(), root ).getServers() ) {
				sum_T = sum_T + s.getServiceCurve().getLatency().doubleValue();
			}
			bound += f.getArrivalCurve().getBurst().doubleValue() + f.getArrivalCurve().getSustainedRate().doubleValue() * sum_T;
		}		
		return bound;
	}
}
