package com.taller1.VarelaFanny.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Devicestatus;
import com.taller1.VarelaFanny.repositories.DevicestatusRepository;


@Service
public class DevicestatusServiceImpl implements DevicestatusService{

	public DevicestatusRepository devicestatusRepo;
	
	@Autowired
	public DevicestatusServiceImpl(DevicestatusRepository devicestatusRepo) {
		this.devicestatusRepo = devicestatusRepo;
	}
	
	@Override
	public Devicestatus createDevicestatus(Devicestatus devicestatus) {
		
		if(devicestatus != null)
		devicestatusRepo.save(devicestatus);
		
		return devicestatusRepo.findById(devicestatus.getDevstatId()).get();
	}

	@Override
	public Devicestatus updateDevicestatus(Devicestatus devicestatus) {
		
		if(devicestatus != null)
			devicestatusRepo.save(devicestatus);
			
			return devicestatusRepo.findById(devicestatus.getDevstatId()).get();
	}

	@Override
	public void deleteDevicestatus(Long id) {
		
		devicestatusRepo.deleteById(id);
	}
	
	
	
}
