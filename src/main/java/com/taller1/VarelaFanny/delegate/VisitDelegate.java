package com.taller1.VarelaFanny.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Visit;

@Component
public class VisitDelegate implements IVisitDelegate {
	
	private final String SERVER="http://localhost:8080/backapi/";
	private RestTemplate restTemplate;
	
	@Autowired
	public VisitDelegate() {
		this.restTemplate = new RestTemplate();
	}
	
	@Override
	public Visit GET_Visit(long id) {
		Visit visit = restTemplate.getForObject(SERVER+"visits/"+id, Visit.class);
		return visit;
	}

	@Override
	public List<Visit> GET_Visits() {
		try{
			return Arrays.asList(restTemplate.getForObject(SERVER+"visits", Visit[].class));
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public Visit POST_Visit(Visit visit) {
		return restTemplate.postForEntity(SERVER+"visits", visit, Visit.class).getBody();
	}

	@Override
	public void PUT_Visit(Visit visit) {
		System.out.println("putdelegate");
		restTemplate.put(SERVER+"visits", visit, Visit.class);
		
	}

	@Override
	public void DELETE_Visit(Visit visit) {
		restTemplate.delete(SERVER+"visits/",visit, Visit.class);
	}

}
