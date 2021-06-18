package com.taller1.VarelaFanny.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Device;
import com.taller1.VarelaFanny.repositories.DeviceRepository;


@Service
public class DeviceServiceImpl implements DeviceService {

	public DeviceRepository deviceRepo;
	
	@Autowired
	public DeviceServiceImpl(DeviceRepository deviceRepo) {
		this.deviceRepo = deviceRepo;
	}
	
	@Override
	public Device createDevice(Device device) {
		
		if(device != null)
			deviceRepo.save(device);
		
		return deviceRepo.findById(device.getDevId()).get();
	}
	
	@Override
	public Device updateDevice(Device device) {
		if(device != null)
			deviceRepo.save(device);
		
		return deviceRepo.findById(device.getDevId()).get();
	}

	@Override
	public void deleteDevice(long id) {
		deviceRepo.deleteById(id);
	}

	

}
