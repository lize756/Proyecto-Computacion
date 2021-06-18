package com.taller1.VarelaFanny.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.Nexuspoll;
import com.taller1.VarelaFanny.model.Nexusquestion;

@Component
public class NexusquestionDelegate implements INexusquestionDelegate{

	private final String SERVER="http://localhost:8080/backapi/";
	private RestTemplate restTemplate;
	
	@Autowired
	public NexusquestionDelegate() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Nexusquestion GET_Nexusquestion(long id) {
		Nexusquestion Nexusquestion = restTemplate.getForObject(SERVER+"nexusquestions/"+id, Nexusquestion.class);
		return Nexusquestion;
	}

	@Override
	public List<Nexusquestion> GET_Nexusquestions() {
		try{
			return Arrays.asList(restTemplate.getForObject(SERVER+"nexusquestions", Nexusquestion[].class));
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public Nexusquestion POST_Nexusquestion(Nexusquestion nexusquestion) {
		return restTemplate.postForEntity(SERVER+"nexusquestions", nexusquestion, Nexusquestion.class).getBody();
	}

	@Override
	public void PUT_Nexusquestion(Nexusquestion nexusquestion) {
		System.out.println("put delegate");
		restTemplate.put(SERVER+"nexusquestions", nexusquestion, Nexusquestion.class);
	}

	@Override
	public List<Nexusquestion> findByPoll(long nexpollId) {
		try {
			return Arrays.asList(restTemplate.getForObject(SERVER +"nexusquestions/" +"poll?nexpollId="+ nexpollId, Nexusquestion[].class));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public void DELETE_Nexusquestion(Nexusquestion nexusquestion) {
		restTemplate.delete(SERVER+"nexusquestions/",nexusquestion, Nexusquestion.class);
	}

}
