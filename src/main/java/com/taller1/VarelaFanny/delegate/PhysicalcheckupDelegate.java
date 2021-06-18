package com.taller1.VarelaFanny.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.Physicalcheckup;

@Component
public class PhysicalcheckupDelegate implements IPhysicalcheckupDelegate{

	private final String SERVER="http://localhost:8080/backapi/";
	private RestTemplate restTemplate;
	
	@Autowired
	public PhysicalcheckupDelegate() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Physicalcheckup GET_Physicalcheckup(long id) {
		Physicalcheckup phys = restTemplate.getForObject(SERVER+"physicalcheckups/"+id, Physicalcheckup.class);
		return phys;
	}

	@Override
	public List<Physicalcheckup> GET_Physicalcheckups() {
		try{
			return Arrays.asList(restTemplate.getForObject(SERVER+"physicalcheckups", Physicalcheckup[].class));
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public Physicalcheckup POST_Physicalcheckup(Physicalcheckup phys) {
		return restTemplate.postForEntity(SERVER+"physicalcheckups", phys, Physicalcheckup.class).getBody();
	}

	@Override
	public void PUT_Physicalcheckup(Physicalcheckup phys) {
		restTemplate.put(SERVER+"physicalcheckups", phys, Physicalcheckup.class);	
	}

	@Override
	public void DELETE_Physicalcheckup(Physicalcheckup phys) {
		restTemplate.delete(SERVER+"physicalcheckups/", phys, Physicalcheckup.class);
	}

}
