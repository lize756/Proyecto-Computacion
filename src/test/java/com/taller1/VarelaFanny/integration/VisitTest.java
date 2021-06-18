package com.taller1.VarelaFanny.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.taller1.VarelaFanny.daos.PersonDao;
import com.taller1.VarelaFanny.daos.PhysicalcheckupDao;
import com.taller1.VarelaFanny.model.Institutioncampus;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.services.PersonService;
import com.taller1.VarelaFanny.services.PhysicalcheckupService;
import com.taller1.VarelaFanny.services.VisitService;

@SpringBootTest
public class VisitTest {
	
	@Autowired
	private VisitService vistService;
		
	@Autowired
	private PersonDao persDao;
	
	@Autowired
	private PhysicalcheckupDao phyDao;
	
	private Visit visit;
	private Visit visitA;
	private Person person;
	private Person person1;
	private Physicalcheckup phys;
	private Physicalcheckup phys1;	
	private Physicalcheckup phys2;	
	private Institutioncampus institutioncampus;
	private Institutioncampus institutioncampus1;
	
	@BeforeEach
	public void setUp1() {
		String namePerson = "namePerson";
		String nameInstitutionCampus = "nameInstitutionCampus";

		String namePerson1 = "namePerson1";
		String nameInstitutionCampus1 = "nameInstitutionCampus1";
		
		Date phycheDate = new GregorianCalendar(2021, Calendar.MARCH, 10).getTime();
		Date phycheDate1 = new GregorianCalendar(2021, Calendar.JULY, 3).getTime();
		Date phycheDate2 = new GregorianCalendar(2021, Calendar.JUNE, 5).getTime();
		
		Date entranceDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		Date entranceDate1 = new GregorianCalendar(2021, Calendar.APRIL, 5).getTime();
		String visitDetail = "visit Detail";
		String visitDetail1 = "visit DetailA";
		Date endDate = new GregorianCalendar(2021, Calendar.DECEMBER, 3).getTime();
		Date endDate1 = new GregorianCalendar(2021, Calendar.NOVEMBER, 15).getTime();
		
		person = new Person();
		person.setPersName(namePerson);
		
		person1 = new Person();
		person1.setPersName(namePerson1);
		
		phys = new Physicalcheckup();
		phys.setPhycheDate(phycheDate);
		phys.setVisit(visit);
		
		phys1 = new Physicalcheckup();
		phys1.setPhycheDate(phycheDate1);
		phys1.setVisit(visitA);
		
		phys2 = new Physicalcheckup();
		phys2.setPhycheDate(phycheDate2);
		phys2.setVisit(visit);
		
		institutioncampus = new Institutioncampus();
		institutioncampus.setInstcamName(nameInstitutionCampus);
		
		institutioncampus1 = new Institutioncampus();
		institutioncampus1.setInstcamName(nameInstitutionCampus1);
		
		
		visit = new Visit();
		visit.setVisitDetail(visitDetail);
		visit.setVisitEntrancedate(entranceDate);
		visit.setInstitutioncampus(institutioncampus);
		visit.setPerson(person);
		
		
		visitA = new Visit();
		visitA.setVisitDetail(visitDetail1);
		visitA.setVisitEntrancedate(entranceDate1);
		visitA.setVisitExitdate(endDate1);
		visitA.setInstitutioncampus(institutioncampus1);
		visitA.setPerson(person1);
		
		List<Physicalcheckup> lPhys = new ArrayList<>();
		lPhys.add(phys);
		lPhys.add(phys1);
		lPhys.add(phys2);
		
		List<Physicalcheckup> lPhys1 = new ArrayList<>();
		lPhys1.add(phys1);
		lPhys1.add(phys2);
		
		visit.setPhysicalcheckups(lPhys);
		visitA.setPhysicalcheckups(lPhys1);
	}
	
			
	@Test
	@Transactional
	public void saveVisitTest() {
		persDao.save(person1);
		persDao.save(person);
		
		assertFalse(vistService.isSaved(visit));
		vistService.createVisit(visit);
        assertTrue(vistService.isSaved(visit));	
	}
	
	@Test
	@Transactional
	public void notSaveVisitPersonNullTest() {
		persDao.save(person1);
		persDao.save(person);
		
		assertFalse(vistService.isSaved(visit));
		visit.setPerson(null);
		vistService.createVisit(visit);
        assertFalse(vistService.isSaved(visit));	
	}
	
	@Test
	@Transactional
	public void notSaveVisitCampusNullTest() {
		persDao.save(person1);
		persDao.save(person);
		
		assertFalse(vistService.isSaved(visit));
		visit.setInstitutioncampus(null);
		vistService.createVisit(visit);
        assertFalse(vistService.isSaved(visit));	
	}
	
	@Test
	@Transactional
	public void notSaveVisitEntranceDateNullTest() {
		persDao.save(person1);
		persDao.save(person);
		
		assertFalse(vistService.isSaved(visit));
		visit.setVisitEntrancedate(null);
		vistService.createVisit(visit);
        assertFalse(vistService.isSaved(visit));	
	}
	
	@Test
	@Transactional
	public void notSaveVisitDetailShortTest() {
		persDao.save(person1);
		persDao.save(person);
		
		assertFalse(vistService.isSaved(visit));
		visit.setVisitDetail("hola");
		vistService.createVisit(visit);
        assertFalse(vistService.isSaved(visit));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		persDao.save(person1);
		persDao.save(person);
		
		vistService.createVisit(visit);
		visit.setVisitDetail("visitDetail 2");
		vistService.updateVisit(visit);
		assertEquals("visitDetail 2", vistService.findById(visit.getVisitId()).get().getVisitDetail());
	}
	
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		persDao.save(person1);
		persDao.save(person);
		
		vistService.createVisit(visit);
		assertTrue(vistService.isSaved(visit));
		vistService.deleteVisit(visit);
		assertFalse(vistService.isSaved(visit));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		
		persDao.save(person1);
		persDao.save(person);
		
		vistService.createVisit(visit);
		Visit visit1 = vistService.findById(visit.getVisitId()).get();
		
		assertEquals(visit, visit1);
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		vistService.createVisit(visit);
		vistService.createVisit(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		List<Visit> lvist = new ArrayList<>();
		
		vistService.findAll().iterator().forEachRemaining(lvist::add);
						
		assertFalse(lvist.isEmpty());
		assertTrue(lvist.size() == 2);	
	}

	
	
	

	

}
