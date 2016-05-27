package struct;

import unikl.disco.curves.MaxServiceCurve;
import unikl.disco.curves.ServiceCurve;

public class ServiceCurves {
	
	public ServiceCurve min_service;
	public MaxServiceCurve max_service;
	
	public ServiceCurves(ServiceCurve min_service , MaxServiceCurve max_service){
		this.min_service=min_service;
		this.max_service = max_service;
	}
	

}
