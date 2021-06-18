package com.taller1.VarelaFanny.services;

import com.taller1.VarelaFanny.model.Devicetype;

public interface DevicetypeService {
	
	public Devicetype createDevicetype(Devicetype devicetype);
	public Devicetype updateDevicetype(Devicetype devicetype);
	public void deleteDevicetype(long id);
	
}
