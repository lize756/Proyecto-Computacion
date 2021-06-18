package com.taller1.VarelaFanny.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;


@Component
public class CheckMeasurDelegate implements ICheckMeasurDelegate {

	private final String SERVER="http://localhost:8080/backapi/";
	private RestTemplate restTemplate;
	
	@Autowired
	public CheckMeasurDelegate() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public CheckMeasur GET_CheckMeasur(long measMeasId, long phychePhycheId) {
		System.out.println("Llegue get delegate");
		CheckMeasur checkmeasur = restTemplate.getForObject(SERVER+"checkMeasurs/"+measMeasId+"/"+phychePhycheId, CheckMeasur.class);
		return checkmeasur;
	}

	@Override
	public List<CheckMeasur> GET_CheckMeasurs() {
		try{
			return Arrays.asList(restTemplate.getForObject(SERVER+"checkMeasurs", CheckMeasur[].class));
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public CheckMeasur POST_CheckMeasur(CheckMeasur checkMeasur) {
		System.out.println("Llegue delegate");
		return restTemplate.postForEntity(SERVER+"checkMeasurs", checkMeasur, CheckMeasur.class).getBody();
	}

	@Override
	public void PUT_CheckMeasur(CheckMeasur CheckMeasur) {
		restTemplate.put(SERVER+"checkMeasurs", CheckMeasur, CheckMeasur.class);	
		
	}

	@Override
	public void DELETE_CheckMeasur(CheckMeasur CheckMeasur) {
		restTemplate.delete(SERVER+"checkMeasurs/",CheckMeasur, CheckMeasur.class);
		
	}

	@Override
	public CheckMeasur GET_CheckMeasur(CheckMeasurPK id) {
		// TODO Auto-generated method stub
		return null;
	}

}
