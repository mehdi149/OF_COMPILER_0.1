/*
 * This file is part of the Disco Deterministic Network Calculator v2.2.6 "Hydra".
 *
 * Copyright (C) 2005 - 2007 Frank A. Zdarsky
 * Copyright (C) 2008 - 2010 Andreas Kiefer
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

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import unikl.disco.curves.ServiceCurve;
import unikl.disco.curves.ArrivalCurve;
import unikl.disco.nc.AnalysisConfig.MuxDiscipline;
import unikl.disco.network.Flow;
import unikl.disco.network.Link;
import unikl.disco.network.Network;
import unikl.disco.network.Path;
import unikl.disco.network.Server;
import unikl.disco.network.Server.Multiplexing;
import unikl.disco.numbers.Num;
import unikl.disco.minplus.Convolution;
import unikl.disco.misc.SetUtils;

/**
 * 
 * @author Frank A. Zdarsky
 * @author Andreas Kiefer
 * @author Steffen Bondorf
 * 
 */
public class SeparateFlowAnalysis extends Analysis {
	@SuppressWarnings("unused")
	private SeparateFlowAnalysis() {}

	public SeparateFlowAnalysis( Network network ) {
		super( network );
		super.result = new SeparateFlowAnalysisResults();
	}

	public SeparateFlowAnalysis( Network network, AnalysisConfig configuration ) {
		super( network, configuration );
		super.result = new SeparateFlowAnalysisResults();
	}
	
	/**
	 * Performs a separated flow analysis for the <code>flow_of_interest</code>.
	 * 
	 * This analysis first blends out the flow of interest and then computes for
	 * each server along this flow's path the left-over service curve that
	 * results if all remaining flows crossing this server receive their maximum
	 * amount of service. Then all left-over service curves are concatenated to
	 * receive the end-to-end service curve from the perspective of the flow of
	 * interest.
	 * 
	 */
	public void performAnalysis( Flow flow_of_interest ) throws Exception {
		performAnalysis( flow_of_interest, flow_of_interest.getPath() );
	}
	
	public void performAnalysis( Flow flow_of_interest, Server server ) throws Exception {
		Path path_foi = flow_of_interest.getPath();
		if( !path_foi.getServers().contains( server ) ) {
			throw new Exception( "Given server is not on the flow of interest's path." );
		}
		
		performAnalysis( flow_of_interest, path_foi.getSubPath( server, server ) );
	}

	public void performAnalysis( Flow flow_of_interest, Path path ) throws Exception {
		((SeparateFlowAnalysisResults) result).betas_e2e = getServiceCurves( flow_of_interest, path, Collections.singleton( flow_of_interest ) );

		Num delay_bound__beta_e2e;
		Num backlog_bound__beta_e2e;
		
		result.delay_bound = Num.createPositiveInfinity();
		result.backlog_bound = Num.createPositiveInfinity();
		
		for( ServiceCurve beta_e2e : ((SeparateFlowAnalysisResults) result).betas_e2e ) {
			delay_bound__beta_e2e = DelayBound.deriveFIFO( flow_of_interest.getArrivalCurve(), beta_e2e ); // single flow of interest, i.e., fifo per micro flow holds
			if( delay_bound__beta_e2e.le( result.delay_bound ) ) {
				result.delay_bound = delay_bound__beta_e2e;
			}
			
			backlog_bound__beta_e2e = BacklogBound.derive( flow_of_interest.getArrivalCurve(), beta_e2e );
			if( backlog_bound__beta_e2e.le( result.backlog_bound ) ) {
				result.backlog_bound = backlog_bound__beta_e2e;
			}
		}
		
		result.succeeded = true;
	}

	protected Set<ServiceCurve> getServiceCurves( Flow flow_of_interest, Path path, Set<Flow> flows_to_serve ) throws Exception {
		Set<ServiceCurve> betas_lofoi_path = new HashSet<ServiceCurve>();
		Set<ServiceCurve> betas_lofoi_s;
		
		// This version iterates over the servers on the path and computes the the service curve set hop-by-hop
		// You could also call network.getFlowsPerServer( path, {foi} u {flows_to_serve} )
		// and use that result. That would be more abstract and in line with the PMOO analysis's way.
		
		// Convolve all left over service curves, server by server
		Link link_from_prev_s;
		Path foi_path = flow_of_interest.getPath();
		for ( Server server : path.getServers() ) {
			try {
				link_from_prev_s = network.findLink( foi_path.getPrecedingServer( server ), server );
			} catch (Exception e) {			// Reached the path's first server
				link_from_prev_s = null;	// reset to null
			}
			
			betas_lofoi_s = new HashSet<ServiceCurve>();
			
			Set<Flow> f_xfoi_server = network.getFlows( server );
			f_xfoi_server.removeAll( flows_to_serve );
			f_xfoi_server.remove( flow_of_interest );
			
			Set<Flow> f_xfoi_server_path = SetUtils.getIntersection( f_xfoi_server, network.getFlows( link_from_prev_s ) );
			
			// Convert f_xfoi_server to f_xfoi_server_offpath
			f_xfoi_server.removeAll( f_xfoi_server_path );
			
			ServiceCurve beta_lofoi = server.getServiceCurve();
			
			if ( f_xfoi_server.isEmpty() && f_xfoi_server_path.isEmpty() ) {
				betas_lofoi_s.add( beta_lofoi );
			} else {															// network, server, flows_to_bound, flows_lower_priority
				Set<ArrivalCurve> alpha_xfois_path = ArrivalBound.computeArrivalBounds( network, configuration, server, f_xfoi_server_path, flow_of_interest );
				Set<ArrivalCurve> alpha_xfois_offpath = ArrivalBound.computeArrivalBounds( network, configuration, server, f_xfoi_server, Flow.NULL_FLOW );
				
				Set<ArrivalCurve> alpha_xfois = new HashSet<ArrivalCurve>();
				for( ArrivalCurve arrival_curve_path : alpha_xfois_path ) {
					for ( ArrivalCurve arrival_curve_offpath : alpha_xfois_offpath ) {
						alpha_xfois.add( ArrivalCurve.add( arrival_curve_path, arrival_curve_offpath ) );
					}
				}
				
				// Calculate the left-over service curve for the flow of interest
				if( configuration.multiplexingDiscipline() == MuxDiscipline.GLOBAL_FIFO
					|| ( configuration.multiplexingDiscipline() == MuxDiscipline.SERVER_LOCAL && server.multiplexingDiscipline() == Multiplexing.FIFO ) )
				{
					for ( ArrivalCurve alpha_xfoi : alpha_xfois ) {
						betas_lofoi_s.add( LeftOverService.fifoMux( beta_lofoi, alpha_xfoi ) );
					}
				}
				else
				{
					for ( ArrivalCurve alpha_xfoi : alpha_xfois ) {
						betas_lofoi_s.add( LeftOverService.arbMux( beta_lofoi, alpha_xfoi ) );
					}
				}
				result.map__server__alphas.put( server, alpha_xfois );
			}
			((SeparateFlowAnalysisResults) result).map__server__betas_lo.put( server, betas_lofoi_s );
			
			betas_lofoi_path = Convolution.convolve_SCs_SCs( betas_lofoi_path, betas_lofoi_s, configuration.tbrlConvolution() );
		}
		return betas_lofoi_path;
	}

	public Set<ServiceCurve> getLeftOverServiceCurves() {
		return ((SeparateFlowAnalysisResults) result).betas_e2e;
	}
	
	public Map<Server,Set<ServiceCurve>> getServerLeftOverBetasMap(){
		return ((SeparateFlowAnalysisResults) result).map__server__betas_lo;
	}
	
	public String getServerLeftOverBetasMapString() {
		return ((SeparateFlowAnalysisResults) result).getServerLeftOverBetasMapString();
	}
}