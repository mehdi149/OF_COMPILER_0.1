/*
 * This file is part of the Disco Deterministic Network Calculator v2.2.6 "Hydra".
 *
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

package unikl.disco.nc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Steffen Bondorf
 *
 */
public class AnalysisConfig {
	public static enum MuxDiscipline { SERVER_LOCAL, GLOBAL_ARBITRARY, GLOBAL_FIFO }

	public static enum GammaFlag { SERVER_LOCAL, GLOBALLY_ON, GLOBALLY_OFF }

	private MuxDiscipline multiplexing_discipline = MuxDiscipline.SERVER_LOCAL;

	private GammaFlag use_gamma = GammaFlag.SERVER_LOCAL;

	private GammaFlag use_extra_gamma = GammaFlag.SERVER_LOCAL;

	public static enum ArrivalBoundMethod
	{ PBOO_PER_HOP, PBOO_CONCATENATION, PMOO,
		PER_FLOW_SFA, PER_FLOW_PMOO,
		PMOO_SINKTREE_TBRL, PMOO_SINKTREE_TBRL_CONV, PMOO_SINKTREE_TBRL_CONV_TBRL, PMOO_SINKTREE_TBRL_HOMO }

	private Set<ArrivalBoundMethod> arrival_bound_methods = new HashSet<ArrivalBoundMethod>( Collections.singleton( ArrivalBoundMethod.PBOO_CONCATENATION ) );

	private boolean remove_duplicate_arrival_bounds = true;

	private boolean tbrl_convolution = false;
	
	private boolean tbrl_deconvolution = false;
	
	private boolean ab_consider_tfa_nodeBacklog = false;
	
	public AnalysisConfig() {}
	
	public AnalysisConfig( MuxDiscipline multiplexing_discipline,
							GammaFlag use_gamma,
							GammaFlag use_extra_gamma,
							Set<ArrivalBoundMethod> arrival_bound_methods,
							boolean remove_duplicate_arrival_bounds,
							boolean tbrl_convolution,
							boolean tbrl_deconvolution,
							boolean ab_consider_tfa_nodeBacklog ) {
		this.multiplexing_discipline = multiplexing_discipline;
		this.use_gamma = use_gamma;
		this.use_extra_gamma = use_extra_gamma;
		this.arrival_bound_methods = new HashSet<ArrivalBoundMethod>( arrival_bound_methods );
		
		this.remove_duplicate_arrival_bounds = remove_duplicate_arrival_bounds;
		this.tbrl_convolution = tbrl_convolution;
		this.tbrl_deconvolution = tbrl_deconvolution;
		this.ab_consider_tfa_nodeBacklog = ab_consider_tfa_nodeBacklog;
	}

	public MuxDiscipline multiplexingDiscipline() {
		return multiplexing_discipline;
	}
	
	public void setMultiplexingDiscipline( MuxDiscipline mux_discipline ) {
		multiplexing_discipline = mux_discipline;
	}	
	
	public GammaFlag useGamma() {
		return use_gamma;
	}
	
	public void setUseGamma( GammaFlag use_gamma_flag ) {
		use_gamma = use_gamma_flag;
	}
	
	public GammaFlag useExtraGamma() {
		return use_extra_gamma;
	}
	
	public void setUseExtraGamma( GammaFlag use_extra_gamma_flag ) {
		use_extra_gamma = use_extra_gamma_flag;
	}
	
	public void defaultArrivalBoundMethods() {
		clearArrivalBoundMethods();
		arrival_bound_methods.add( ArrivalBoundMethod.PBOO_CONCATENATION );
	}
	
	public void clearArrivalBoundMethods() {
		arrival_bound_methods.clear();
	}
	
	public void setArrivalBoundMethod( ArrivalBoundMethod arrival_bound_method ) {
		clearArrivalBoundMethods();
		arrival_bound_methods.add( arrival_bound_method );
	}
	
	public Set<ArrivalBoundMethod> arrivalBoundMethods() {
		return new HashSet<ArrivalBoundMethod>( arrival_bound_methods );
	}

	public void setArrivalBoundMethods( Set<ArrivalBoundMethod> arrival_bound_methods_set ) {
		clearArrivalBoundMethods();
		arrival_bound_methods.addAll( arrival_bound_methods_set );
	}

	public void addArrivalBoundMethod( ArrivalBoundMethod arrival_bound_method ) {
		arrival_bound_methods.add( arrival_bound_method );
	}
	
	public void addArrivalBoundMethods( Set<ArrivalBoundMethod> arrival_bound_methods_set ) {
		arrival_bound_methods.addAll( arrival_bound_methods_set );
	}
	
	public boolean removeDuplicateArrivalBounds() {
		return remove_duplicate_arrival_bounds;
	}
    
	public void setRemoveDuplicateArrivalBounds( boolean remove_duplicate_arrival_bounds_flag ) {
		remove_duplicate_arrival_bounds = remove_duplicate_arrival_bounds_flag;
	}
	
	public boolean tbrlConvolution() {
		return tbrl_convolution;
	}
	
	public void setUseTbrlConvolution( boolean optimized_code_path ) {
		tbrl_convolution = optimized_code_path;
	}
	
	public boolean tbrlDeconvolution() {
		return tbrl_deconvolution;
	}
	
	public void setUseTbrlDeconvolution( boolean optimized_code_path ) {
		tbrl_deconvolution = optimized_code_path;
	}
	
	public boolean abConsiderTFANodeBacklog() {
		return ab_consider_tfa_nodeBacklog;
	}
	
	public void setAbConsiderTFANodeBacklog( boolean consider_backlog_bound ) {
		ab_consider_tfa_nodeBacklog = consider_backlog_bound;
	}
	
	/**
	 * 
	 * Returns a deep copy of this analysis configuration.
	 * 
	 * @return The copy.
	 */
	public AnalysisConfig copy() { // deep copy as primitive data types are copied by value
		return new AnalysisConfig( multiplexing_discipline,
									use_gamma,
									use_extra_gamma,
									arrival_bound_methods,
									remove_duplicate_arrival_bounds,
									tbrl_convolution,
									tbrl_deconvolution,
									ab_consider_tfa_nodeBacklog );
	}
	
	@Override
	public String toString() {
		StringBuffer func_test_str = new StringBuffer();

		func_test_str.append( multiplexingDiscipline().toString() );
		func_test_str.append( ", " );
		func_test_str.append( arrivalBoundMethods().toString() );

		if( removeDuplicateArrivalBounds() ) {
			func_test_str.append( ", " );
			func_test_str.append( "remove duplicate ABs" );
		}
		if( tbrlConvolution() ) {
			func_test_str.append( ", " );
			func_test_str.append( "TbRl Conv" );
		}
		if( tbrlDeconvolution() ) {
			func_test_str.append( ", " );
			func_test_str.append( "TbRl Deconv" );
		}
		
		return func_test_str.toString();
	}
}