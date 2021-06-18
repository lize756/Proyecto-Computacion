package com.taller1.VarelaFanny.delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.model.Measurement;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MeasurementDelegateTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private MeasurementDelegate measurDelegate;
	
	private final String SERVER="http://localhost:8080/backapi/";
	
	private Measurement measur1;
	private Measurement measur2;
		
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void GET_MeasurementTest() {
		measur1 = new Measurement();
		measur2 = new Measurement();
		int id = (int) measur1.getMeasId();
		
		when(restTemplate.getForObject(SERVER+"measurements/"+id, Measurement.class)).thenReturn(measur1);
        assertNotNull(measurDelegate.GET_Measurement((long)id));
	}
	
	@Test
	public void GET_MeasurementsTest() {
		measur1 = new Measurement();
		measur2 = new Measurement();
		Measurement[] measurs = {measur1, measur2};
		
		when(restTemplate.getForObject(SERVER+"measurements/",Measurement[].class)).thenReturn(measurs);

        List<Measurement> measurList= Arrays.asList(measurs);

        assertEquals(measurDelegate.GET_Measurements(),measurList);
		
	}
	
	@Test
	public void POST_MeasurementTest() {
		measur1 = new Measurement();
		when(restTemplate.postForEntity(SERVER+"measurements/", measur1, Measurement.class)).thenReturn(new ResponseEntity<>(measur1, HttpStatus.OK));

        assertEquals(measurDelegate.POST_Measurement(measur1), measur1);
	}
	
	@Test
	public void PUT_MeasurementTest() {
		measur1 = new Measurement();
		measurDelegate.PUT_Measurement(measur1);
		verify(restTemplate, times(1)).put(ArgumentMatchers.eq(SERVER+"measurements/"),ArgumentMatchers.eq(measur1), ArgumentMatchers.eq(Measurement.class));
		
	}
	

	@Test
	public void DELETE_Measurement() {
		measur1 = new Measurement();
		
		Mockito.doNothing().when(restTemplate).delete(SERVER+"measurements/", measur1, Measurement.class);
        measurDelegate.DELETE_Measurement(measur1);

        verify(restTemplate, Mockito.times(1)).delete(ArgumentMatchers.eq(SERVER+"measurements/"),ArgumentMatchers.eq(measur1), ArgumentMatchers.eq(Measurement.class));
		
	}
	
	

}
