package com.taller1.VarelaFanny.delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Physicalcheckup;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CheckMeasurDelegateTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private CheckMeasurDelegate checkDelegate;
	
	private final String SERVER="http://localhost:8080/backapi/";
	
	private CheckMeasur check;
	private CheckMeasur check1;
	
	@Test
	public void GET_CheckMeasurTest() {
		check = new CheckMeasur();
		check1 = new CheckMeasur();
		
		Physicalcheckup physicalcheckup1 = new Physicalcheckup();
		Measurement measurement2 = new Measurement();
		check.setMeasurement(measurement2);
		check.setPhysicalcheckup(physicalcheckup1);
		
		Long idMeasur = check.getMeasurement().getMeasId();
		Long idPhys = check.getPhysicalcheckup().getPhycheId();
		check.setId(new CheckMeasurPK(idPhys,idMeasur));
		
		CheckMeasurPK id = check.getId();
		
		when(restTemplate.getForObject(SERVER+"checkMeasurs/"+idMeasur+"/"+idPhys, CheckMeasur.class)).thenReturn(check);
        assertNotNull(checkDelegate.GET_CheckMeasur(idMeasur, idPhys));
	}
	
	
	@Test
	public void GET_CheckMeasursTest() {
		check = new CheckMeasur();
		check1 = new CheckMeasur();
		CheckMeasur[] checks = {check, check1};
		
		when(restTemplate.getForObject(SERVER+"checkMeasurs", CheckMeasur[].class)).thenReturn(checks);
        List<CheckMeasur> checkList= Arrays.asList(checks);
        assertEquals(checkDelegate.GET_CheckMeasurs(),checkList);
	}
	
	@Test
	public void POST_CheckMeasurTest() {
		check = new CheckMeasur();
		when(restTemplate.postForEntity(SERVER+"checkMeasurs", check, CheckMeasur.class)).thenReturn(new ResponseEntity<>(check, HttpStatus.OK));

        assertEquals(checkDelegate.POST_CheckMeasur(check), check);
	}
	
	@Test
	public void PUT_CheckMeasurTest() {
		check = new CheckMeasur();
		checkDelegate.PUT_CheckMeasur(check);
		verify(restTemplate, times(1)).put(ArgumentMatchers.eq(SERVER+"checkMeasurs"),ArgumentMatchers.eq(check), ArgumentMatchers.eq(CheckMeasur.class));
		
	}
	
	@Test
	public void DELETE_CheckMeasurTest() {
		check = new CheckMeasur();
		
		Mockito.doNothing().when(restTemplate).delete(SERVER+"checkMeasurs/", check, CheckMeasur.class);
        checkDelegate.DELETE_CheckMeasur(check);

        verify(restTemplate, Mockito.times(1)).delete(ArgumentMatchers.eq(SERVER+"checkMeasurs/"),ArgumentMatchers.eq(check), ArgumentMatchers.eq(CheckMeasur.class));
		
	}

}
