package com.taller1.VarelaFanny.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.Measurement;

@Component
public class MeasurementDelegate implements IMeasurementDelegate {

	
	private final String SERVER="http://localhost:8080/backapi/";
	private RestTemplate restTemplate;
	
	@Autowired
	public MeasurementDelegate() {
		this.restTemplate = new RestTemplate();
		
	}
	
	@Override
	public Measurement GET_Measurement(long id) {
		Measurement measur = restTemplate.getForObject(SERVER+"measurements/"+id, Measurement.class);
		return measur;
	}

	@Override
	public List<Measurement> GET_Measurements() {
		
		try{
			return Arrays.asList(restTemplate.getForObject(SERVER+"measurements/", Measurement[].class));
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public Measurement POST_Measurement(Measurement measur) {
		System.out.println("Llegue delegate");
		return restTemplate.postForEntity(SERVER+"measurements/", measur, Measurement.class).getBody();
	}

	@Override
	public void PUT_Measurement(Measurement measur) {
		restTemplate.put(SERVER+"measurements/", measur, Measurement.class);	
	}

	@Override
	public void DELETE_Measurement(Measurement measur) {
		restTemplate.delete(SERVER+"measurements/",measur, Measurement.class);
	}

	
	
}
