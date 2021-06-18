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

import com.taller1.VarelaFanny.model.Visit;

import junit.framework.AssertionFailedError;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class VisitDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private VisitDelegate vistDelegate;
	
	private final String SERVER="http://localhost:8080/backapi/";
	
	private Visit visit;
	private Visit visit2;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void GET_VisitTest() {
		visit = new Visit();
		visit2 = new Visit();	
		int id = (int) visit.getVisitId();
		
		when(restTemplate.getForObject(SERVER+"visits/"+id, Visit.class)).thenReturn(visit);
        assertNotNull(vistDelegate.GET_Visit((long)id));
		
	}
	
	
	@Test
	public void GET_VisitsTest() {
		visit = new Visit();
		visit2 = new Visit();	
		Visit[] visits = {visit, visit2};
		
		when(restTemplate.getForObject(SERVER+"visits",Visit[].class)).thenReturn(visits);

        List<Visit> visList= Arrays.asList(visits);
        assertEquals(vistDelegate.GET_Visits(),visList);
		
	}
	
	@Test
	public void POST_VisitTest() {
		visit = new Visit();
		when(restTemplate.postForEntity(SERVER+"visits", visit, Visit.class)).thenReturn(new ResponseEntity<>(visit, HttpStatus.OK));

        assertEquals(vistDelegate.POST_Visit(visit), visit);
		
	}
	
	@Test
	public void PUT_VisitTest() {
		
		visit = new Visit();
		vistDelegate.PUT_Visit(visit);
		verify(restTemplate, times(1)).put(ArgumentMatchers.eq(SERVER+"visits"),ArgumentMatchers.eq(visit), ArgumentMatchers.eq(Visit.class));
		
	}
	
	@Test
	public void DELETE_VisitTest() {
		visit = new Visit();
		Mockito.doNothing().when(restTemplate).delete(SERVER+"visits/", visit, Visit.class);
        vistDelegate.DELETE_Visit(visit);

        verify(restTemplate, Mockito.times(1)).delete(ArgumentMatchers.eq(SERVER+"visits/"),ArgumentMatchers.eq(visit), ArgumentMatchers.eq(Visit.class));
		
	}
	
	

}
