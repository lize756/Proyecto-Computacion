package com.taller1.VarelaFanny.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
//import org.junit.runner.RunWith;

import com.taller1.VarelaFanny.model.Institutioncampus;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.repositories.VisitRepository;
import com.taller1.VarelaFanny.services.VisitServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class VisitTest {

	@Mock
	private PersonRepository persRepository;
	
	@Mock
	private VisitRepository visitRepository;
	
	@InjectMocks
	private VisitServiceImpl visitServiceImpl;

	private Person person;
	private Institutioncampus institutioncampus;
	private Visit visit;
	
	@BeforeEach
	public void setUp() {
	
		MockitoAnnotations.initMocks(this);
		
		String namePerson = "namePerson";
		String nameInstitutionCampus = "nameInstitutionCampus";
		
		long idVist = 93911301;
		long idPerson = 12345678;
		long idInstCampus = 87654321;
		
		Date entranceDate = new Date(System.currentTimeMillis());
		String visitDetail = "visit Detail";
		
		person = new Person();
		person.setPersId(idPerson);
		person.setPersName(namePerson);
		
		institutioncampus = new Institutioncampus();
		institutioncampus.setInstcamId(idInstCampus);
		institutioncampus.setInstcamName(nameInstitutionCampus);
		
		visit = new Visit();
		visit.setVisitId(idVist);
		visit.setVisitDetail(visitDetail);
		visit.setVisitEntrancedate(entranceDate);
		visit.setPerson(person);
		visit.setInstitutioncampus(institutioncampus);
		System.out.println(visit.getVisitEntrancedate().toString());
		
	}
	
	@Nested
	class createVisit{
		
		@Test
		public void createVisit() {
			when(persRepository.findById(visit.getPerson().getPersId())).thenReturn(Optional.of(person));
			when(visitRepository.save(visit)).thenReturn(visit);
			assertNotNull(visitServiceImpl.createVisit(visit));
			
		}
		
		
		@Test
		public void createVisit_PersonNull() {
			visit.setPerson(null);	
			assertNull(visitServiceImpl.createVisit(visit));
			
		}
		
		@Test
		public void createVisit_InstitutioncampusNull() {
			visit.setInstitutioncampus(null);	
			assertNull(visitServiceImpl.createVisit(visit));
			
		}
		
		@Test
		public void createVisit_VisitEntrancedateNull() {
			visit.setVisitEntrancedate(null);	
			assertNull(visitServiceImpl.createVisit(visit));
			
		}
		
		@Test
		public void createVisit_VisitDetailNull() {
			visit.setVisitDetail("Hola");	
			assertNull(visitServiceImpl.createVisit(visit));
			
		}
	}
	
	@Nested
	class updateVisit{
		
		@Test
		public void updateteVisit() {
			String visitDetail = visit.getVisitDetail();
			String visitDetail2 = "VisitDetail 2";
			
			visit.setVisitDetail(visitDetail2);
			when(persRepository.findById(visit.getPerson().getPersId())).thenReturn(Optional.of(person));
			when(visitRepository.findById(visit.getVisitId())).thenReturn(Optional.of(visit));
			when(visitRepository.save(visit)).thenReturn(visit);
			
			
			assertNotNull(visitServiceImpl.updateVisit(visit));
			assertNotEquals(visitDetail,visitServiceImpl.updateVisit(visit).getVisitDetail() );
			
		}
	
		
		@Test
		public void updateVisit_PersonNull() {
			visit.setPerson(null);	
			assertNull(visitServiceImpl.updateVisit(visit));
			
		}
		
		@Test
		public void updateVisit_InstitutioncampusNull() {
			visit.setInstitutioncampus(null);	
			assertNull(visitServiceImpl.updateVisit(visit));
			
		}
		
		@Test
		public void updateVisit_VisitEntrancedateNull() {
			visit.setVisitEntrancedate(null);	
			assertNull(visitServiceImpl.updateVisit(visit));
			
		}
		
		@Test
		public void updateVisit_VisitDetailNull() {
			visit.setVisitDetail("Hola");	
			assertNull(visitServiceImpl.updateVisit(visit));
			
		}
	}
}
