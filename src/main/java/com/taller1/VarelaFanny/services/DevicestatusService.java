package com.taller1.VarelaFanny.services;

import com.taller1.VarelaFanny.model.Devicestatus;

public interface DevicestatusService {
 
	public Devicestatus createDevicestatus(Devicestatus devicestatus);
	public Devicestatus updateDevicestatus(Devicestatus devicestatus);
	public void deleteDevicestatus(Long id);
}
