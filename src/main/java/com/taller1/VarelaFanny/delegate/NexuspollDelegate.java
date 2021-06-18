package com.taller1.VarelaFanny.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.Nexuspoll;

@Component
public class NexuspollDelegate implements INexuspollDelegate{

	private final String SERVER="http://localhost:8080/backapi/";
	private RestTemplate restTemplate;
	
	@Autowired
	public NexuspollDelegate() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Nexuspoll GET_Nexuspoll(long id) {
		Nexuspoll nexuspoll = restTemplate.getForObject(SERVER+"nexuspolls/"+id, Nexuspoll.class);
		return nexuspoll;
	}
	
	@Override
	public List<Nexuspoll> GET_Nexuspolls() {
		try{
			return Arrays.asList(restTemplate.getForObject(SERVER+"nexuspolls", Nexuspoll[].class));
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public Nexuspoll POST_Nexuspoll(Nexuspoll nexuspoll) {
		return restTemplate.postForEntity(SERVER+"nexuspolls", nexuspoll, Nexuspoll.class).getBody();
	}

	@Override
	public void PUT_Nexuspoll(Nexuspoll nexuspoll) {
		System.out.println("put delegate");
		restTemplate.put(SERVER+"nexuspolls", nexuspoll, Nexuspoll.class);
		
	}
	
	@Override
	public List<Nexuspoll> findByinstitution(long instInstId) {
		try {
			return Arrays.asList(restTemplate.getForObject(SERVER +"nexuspolls/" +"institution?instInstId="+ instInstId, Nexuspoll[].class));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	

	@Override
	public void DELETE_Nexuspoll(Nexuspoll nexuspoll) {
		restTemplate.delete(SERVER+"nexuspolls/",nexuspoll, Nexuspoll.class);
		
	}

}
