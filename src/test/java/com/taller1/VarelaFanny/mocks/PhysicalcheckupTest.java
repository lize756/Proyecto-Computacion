package com.taller1.VarelaFanny.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.repositories.PhysicalcheckupRepository;
import com.taller1.VarelaFanny.repositories.VisitRepository;
import com.taller1.VarelaFanny.services.PhysicalcheckupServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class PhysicalcheckupTest {

	@Mock
	private VisitRepository visitRepository;
	
	@Mock
	private PersonRepository persRepository;
	
	@Mock
	private PhysicalcheckupRepository physCheckRepository;
	
	@InjectMocks
	private PhysicalcheckupServiceImpl physicalServiceImpl;
	
	private Person pers;
	private Visit visit;
	private Physicalcheckup physCheck;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		String namePers = "namePers";
		
		long idVist = 93911302;
		long idPerson = 12345688;
		long idPhysCheck = 938374848;
		Date phycheDate = new Date(System.currentTimeMillis());
		
		pers = new Person();
		pers.setPersId(idPerson);
		pers.setPersName(namePers);
		
		visit = new Visit();
		visit.setVisitId(idVist);
		
		physCheck = new Physicalcheckup();
		physCheck.setPhycheId(idPhysCheck);
		physCheck.setPhycheDate(phycheDate);
		physCheck.setVisit(visit);
		physCheck.setPerson(pers);
				
	}
	
	@Nested
	class createPhysicalCheckup{
		
		@DisplayName("Agregar PhysicalCheckup sin problema.")
		@Test
		public void createPhysicalCheckup() {
			when(persRepository.findById(physCheck.getPerson().getPersId())).thenReturn(Optional.of(pers));
			when(visitRepository.findById(physCheck.getVisit().getVisitId())).thenReturn(Optional.of(visit));
			when(physCheckRepository.save(physCheck)).thenReturn(physCheck);
			
			assertNotNull(physicalServiceImpl.createPhysicalcheckupService(physCheck));
		}
		
		@DisplayName("Agregar PhysicalCheckup sin persona.")
		@Test
		public void notCreatePhysicalCheckup_PersonNull() {
			
			physCheck.setPerson(null);
			assertNull(physicalServiceImpl.createPhysicalcheckupService(physCheck));
		}
		
		@DisplayName("Agregar PhysicalCheckup sin visit.")
		@Test
		public void notCreatePhysicalCheckup_visitNull() {
			
			physCheck.setVisit(null);
			assertNull(physicalServiceImpl.createPhysicalcheckupService(physCheck));
		}
		
		@DisplayName("Agregar PhysicalCheckup sin PhycheDate.")
		@Test
		public void notCreatePhysicalCheckup_PhycheDateNull() {
			
			physCheck.setPhycheDate(null);
			assertNull(physicalServiceImpl.createPhysicalcheckupService(physCheck));
		}	
		
	}
	
	@Nested
	class updatePhysicalCheckup{
		
		@DisplayName("Editar PhysicalCheckup sin problema.")
		@Test
		public void updatetePhysicalCheckup() {
			
			Date phycheDate = physCheck.getPhycheDate();
			Date phycheDate2 = new Date(System.currentTimeMillis());
			physCheck.setPhycheDate(phycheDate2);
			
			when(persRepository.findById(physCheck.getPerson().getPersId())).thenReturn(Optional.of(pers));
			when(visitRepository.findById(physCheck.getVisit().getVisitId())).thenReturn(Optional.of(visit));
			when(physCheckRepository.findById(physCheck.getPhycheId())).thenReturn(Optional.of(physCheck));
			when(physCheckRepository.save(physCheck)).thenReturn(physCheck);
			
			assertNotNull(physicalServiceImpl.updatePhysicalcheckupService(physCheck));
			//assertNotEquals(phycheDate, physCheck.getPhycheDate());
		}
		
		
		@DisplayName("Editar PhysicalCheckup sin persona.")
		@Test
		public void notUpdatePhysicalCheckup_PersonNull() {
			
			physCheck.setPerson(null);
			assertNull(physicalServiceImpl.updatePhysicalcheckupService(physCheck));
		}
		
		@DisplayName("Editar PhysicalCheckup sin visit.")
		@Test
		public void notUpdatePhysicalCheckup_visitNull() {
			
			physCheck.setVisit(null);
			assertNull(physicalServiceImpl.updatePhysicalcheckupService(physCheck));
		}
		
		@DisplayName("Editar PhysicalCheckup sin PhycheDate.")
		@Test
		public void notUpdatePhysicalCheckup_PhycheDateNull() {
			
			physCheck.setPhycheDate(null);
			assertNull(physicalServiceImpl.updatePhysicalcheckupService(physCheck));
		}	
		
	}
	


}
