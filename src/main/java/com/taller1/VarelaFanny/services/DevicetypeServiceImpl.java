package com.taller1.VarelaFanny.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Devicetype;
import com.taller1.VarelaFanny.repositories.DevicetypeRepository;


@Service
public class DevicetypeServiceImpl implements DevicetypeService{

	public DevicetypeRepository devicetypeRepo;
	
	@Autowired
	public DevicetypeServiceImpl(DevicetypeRepository devicetypeRepo) {
		this.devicetypeRepo = devicetypeRepo;
	}
	
	@Override
	public Devicetype createDevicetype(Devicetype devicetype) {
		
		if(devicetype != null)
			devicetypeRepo.save(devicetype);
		
		return devicetypeRepo.findById(devicetype.getDevtypeId()).get();
	}

	@Override
	public Devicetype updateDevicetype(Devicetype devicetype) {
		
		if(devicetype != null)
			devicetypeRepo.save(devicetype);
		
		return devicetypeRepo.findById(devicetype.getDevtypeId()).get();
	}

	@Override
	public void deleteDevicetype(long id) {
		devicetypeRepo.deleteById(id);
	}

	
	
}
