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

import com.taller1.VarelaFanny.model.Physicalcheckup;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PhysicalcheckupDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	private final String SERVER="http://localhost:8080/backapi/";

	@InjectMocks
	private PhysicalcheckupDelegate physDelegate;
	
	private Physicalcheckup phys;
	private Physicalcheckup phys1;
	
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void GET_PhysicalCheckupTest() {
		phys = new Physicalcheckup();
		phys1 = new Physicalcheckup();
		int id = (int) phys.getPhycheId();
		
		when(restTemplate.getForObject(SERVER+"physicalcheckups/"+id, Physicalcheckup.class)).thenReturn(phys);
        assertNotNull(physDelegate.GET_Physicalcheckup((long)id));
		
	}
	
	@Test
	public void GET_PhysicalCheckupsTest() {
		phys = new Physicalcheckup();
		phys1 = new Physicalcheckup();
		Physicalcheckup[] physs = {phys, phys1};
		
		when(restTemplate.getForObject(SERVER+"physicalcheckups",Physicalcheckup[].class)).thenReturn(physs);

        List<Physicalcheckup> physList= Arrays.asList(physs);

        assertEquals(physDelegate.GET_Physicalcheckups(), physList);
	}
	
	@Test
	public void POST_PhysicalCheckupTest() {
		phys = new Physicalcheckup();
		when(restTemplate.postForEntity(SERVER+"physicalcheckups", phys, Physicalcheckup.class)).thenReturn(new ResponseEntity<>(phys, HttpStatus.OK));

        assertEquals(physDelegate.POST_Physicalcheckup(phys), phys);
	}
	
	@Test
	public void PUT_PhysicalCheckupTest() {
		phys = new Physicalcheckup();
		physDelegate.PUT_Physicalcheckup(phys);
		verify(restTemplate, times(1)).put(ArgumentMatchers.eq(SERVER+"physicalcheckups"),ArgumentMatchers.eq(phys), ArgumentMatchers.eq(Physicalcheckup.class));
		
	}
	
	@Test
	public void DELETE_PhysicalCheckupTest() {
		phys = new Physicalcheckup();
		Mockito.doNothing().when(restTemplate).delete(SERVER+"physicalcheckups/", phys, Physicalcheckup.class);
		physDelegate.DELETE_Physicalcheckup(phys);
		verify(restTemplate, Mockito.times(1)).delete(ArgumentMatchers.eq(SERVER+"physicalcheckups/"),ArgumentMatchers.eq(phys), ArgumentMatchers.eq(Physicalcheckup.class));
	}
	
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
