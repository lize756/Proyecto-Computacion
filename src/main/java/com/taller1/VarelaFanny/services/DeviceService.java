package com.taller1.VarelaFanny.services;

import com.taller1.VarelaFanny.model.Device;

public interface DeviceService {
	
	public Device createDevice(Device device);
	public void deleteDevice(long id);
	public Device updateDevice(Device device);

}
