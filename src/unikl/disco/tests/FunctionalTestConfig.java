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

package unikl.disco.tests;

import java.util.Set;

import unikl.disco.nc.AnalysisConfig;
import unikl.disco.nc.CalculatorConfig;
import unikl.disco.nc.CalculatorConfig.NumClass;

public class FunctionalTestConfig extends AnalysisConfig {
	// Functional test specific parameters
	boolean define_multiplexing_globally;
	boolean console_output = false;
	
	// Calculator parameters
	boolean enable_checks = false;
	NumClass numbers;
	
	@SuppressWarnings("unused")
	private FunctionalTestConfig(){}
	
	public FunctionalTestConfig( Set<ArrivalBoundMethod> arrival_bound_methods,
									boolean remove_duplicate_arrival_bounds,
									boolean tbrl_convolution, 
									boolean tbrl_deconvolution,
									boolean define_multiplexing_globally,
									CalculatorConfig.NumClass numbers ) {
		
		super( MuxDiscipline.GLOBAL_ARBITRARY, // Not used, no influence yet.
				GammaFlag.GLOBALLY_OFF,        // Not used, no influence yet.
				GammaFlag.GLOBALLY_OFF,        // Not used, no influence yet.
				arrival_bound_methods,
				remove_duplicate_arrival_bounds,
				tbrl_convolution,
				tbrl_deconvolution,
				false );
		
		this.define_multiplexing_globally = define_multiplexing_globally;
		this.numbers = numbers;
	}
	
	public boolean fullConsoleOutput() { // false == Exceptions only
		return console_output;
	}
	
	@Override
	public String toString() {
		// AB, ab cache, convolve ABs, tbrl opt convolution, tbrl opt deconvolusion, global mux def
		String func_test_str = arrivalBoundMethods().toString();
		
		if( removeDuplicateArrivalBounds() ) {
			func_test_str += ", ";
			func_test_str += "Rem dupl ABs";
		}
		if( tbrlConvolution() ) {
			func_test_str += ", ";
			func_test_str += "TbRl Conv";
		}
		if( tbrlDeconvolution() ) {
			func_test_str += ", ";
			func_test_str += "TbRl Deconv";
		}
		if( define_multiplexing_globally ) {
			func_test_str += ", ";
			func_test_str += "MUX global";
		}

		func_test_str += ", " + numbers.toString();
		
		return func_test_str;
	}
}
