package com.taller1.VarelaFanny.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.daos.PersonDao;
import com.taller1.VarelaFanny.daos.PhysicalcheckupDao;
import com.taller1.VarelaFanny.daos.VisitDao;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.repositories.VisitRepository;
import com.taller1.VarelaFanny.services.PhysicalcheckupService;

@SpringBootTest
class PhysicalcheckupTest {
	
	@Autowired
	private PhysicalcheckupService phyService;
	
	@Autowired
	private VisitDao vistDao;
	
	@Autowired
	private PersonDao persDao;
	
	
	private Physicalcheckup phycheck;
	private Physicalcheckup phycheck1;
	private Visit visit;
	private Visit visit1;
	private Person pers;
	private Person pers1;
	
	@BeforeEach
	public void setUp() {
		Date phycheDate = new Date(System.currentTimeMillis());
		Date phycheDate1 = new GregorianCalendar(2021, Calendar.JULY, 3).getTime();
		
		String visitDetail = "visit Detail";
		String visitDetail1 = "visit Detail1";
		Date entranceDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		
		pers = new Person();
		pers.setPersName("nameperson");
		
		pers1 = new Person();
		pers1.setPersName("nameperson1");
		
		visit = new Visit();
		visit1 = new Visit();
		visit.setVisitDetail(visitDetail);
		visit.setVisitEntrancedate(entranceDate);
		
		visit1.setVisitDetail(visitDetail1);
		visit1.setVisitEntrancedate(entranceDate);
		
		phycheck = new Physicalcheckup();
		phycheck1 = new Physicalcheckup();
		phycheck.setPhycheDate(phycheDate);
		phycheck.setVisit(visit);
		phycheck.setPerson(pers);
		
		phycheck1.setPhycheDate(phycheDate1);
		phycheck1.setVisit(visit1);
		phycheck1.setPerson(pers1);
		
	}
	
	@Test
	@Transactional
	public void savePhycheckTest(){
		persDao.save(pers);
		vistDao.save(visit);
		assertFalse(phyService.isSaved(phycheck));
		phyService.createPhysicalcheckupService(phycheck);
		assertTrue(phyService.isSaved(phycheck));	
	}
	
	@Test
	@Transactional
	public void notSavePhycheckTest(){
		persDao.save(pers);
		vistDao.save(visit);
		assertFalse(phyService.isSaved(phycheck));
		phyService.createPhysicalcheckupService(null);
		assertFalse(phyService.isSaved(phycheck));	
	}
	
	@Test
	@Transactional
	public void notSavePhycheckTest1(){
		persDao.save(pers);
		vistDao.save(visit);
		assertFalse(phyService.isSaved(phycheck));
		phycheck.setPerson(null);
		phyService.createPhysicalcheckupService(phycheck);
		assertFalse(phyService.isSaved(phycheck));	
	}
	
	@Test
	@Transactional
	public void notSavePhycheckTest2(){
		persDao.save(pers);
		vistDao.save(visit);
		assertFalse(phyService.isSaved(phycheck));
		phycheck.setVisit(null);
		phyService.createPhysicalcheckupService(phycheck);
		assertFalse(phyService.isSaved(phycheck));	
	}
	
	@Test
	@Transactional
	public void notSavePhycheckTest3(){
		persDao.save(pers);
		vistDao.save(visit);
		assertFalse(phyService.isSaved(phycheck));
		phycheck.setPhycheDate(null);
		phyService.createPhysicalcheckupService(phycheck);
		assertFalse(phyService.isSaved(phycheck));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		persDao.save(pers);
		vistDao.save(visit);
		phyService.createPhysicalcheckupService(phycheck);
		Date phycheDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		phycheck.setPhycheDate(phycheDate);
		phyService.updatePhysicalcheckupService(phycheck);
		assertEquals(phycheDate, phyService.findById(phycheck.getPhycheId()).get().getPhycheDate());
	
	}
	

	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notUpdateTest() {
		persDao.save(pers);
		vistDao.save(visit);
		phyService.createPhysicalcheckupService(phycheck);
		Date phycheDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		phycheck.setPhycheDate(null);
		phyService.updatePhysicalcheckupService(phycheck);
		assertNotSame(phycheDate, phyService.findById(phycheck.getPhycheId()).get().getPhycheDate());
	}
	
	@Test
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		persDao.save(pers);
		vistDao.save(visit);
		phyService.createPhysicalcheckupService(phycheck);
		assertTrue(phyService.isSaved(phycheck));
		phyService.delete(phycheck);
		assertFalse(phyService.isSaved(phycheck));
	}
	
	@Test
	@Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		
		persDao.save(pers);
		persDao.save(pers1);
		vistDao.save(visit);
		vistDao.save(visit1);
		phyService.createPhysicalcheckupService(phycheck);
		phyService.createPhysicalcheckupService(phycheck1);
		
		Physicalcheckup pycheck = phyService.findById(phycheck1.getPhycheId()).get();
		assertEquals(phycheck1, pycheck);
		assertNotEquals(phycheck, pycheck);
	
	}
	
	@Test
	@Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		
		phyService.createPhysicalcheckupService(phycheck);
		phyService.createPhysicalcheckupService(phycheck1);
		
		persDao.save(pers);
		persDao.save(pers1);
		vistDao.save(visit);
		vistDao.save(visit1);
		
		List<Physicalcheckup> pycheck = new ArrayList<>();
		
		phyService.findAll().iterator().forEachRemaining(pycheck::add);
		assertFalse(pycheck.isEmpty());
		
		
	}
	
//	
//	@Test
//    @Transactional
//    public void notUpdateTest(){
//        Physicalcheckup physEdit = new Physicalcheckup();
//        physEdit.setPhycheDate(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
//
//        phyDao.save(phycheck);
//        assertEquals(phycheck, phyDao.findById(phycheck.getPhycheId()));
//
//        phyDao.update(physEdit);
//        assertNotSame(physEdit, phyDao.findById(phycheck.getPhycheId()));
//    }
//	

//	
//	@Test
//	@Transactional
//	public void notDeleteTest(){
//		phyDao.save(phycheck);
//		assertTrue(phyDao.isSaved(phycheck));
//		phyDao.delete(phycheck1);
//		assertTrue(phyDao.isSaved(phycheck));
//	}
//	
//	@Test
//    @Transactional(propagation= Propagation.REQUIRED)
//	public void findByVisitTest() {	
//		phyDao.save(phycheck);
//		vistDao.save(visit);
//		
//		Visit vist = phycheck.getVisit();
//		List<Physicalcheckup> pycheck = phyDao.findByVisit(vist.getVisitId());
//		assertTrue(pycheck.contains(phycheck));
//	}
//	
//	@Test
//    @Transactional(propagation= Propagation.REQUIRED)
//	public void notFindByVisitTest() {	
//		phyDao.save(phycheck);
//		vistDao.save(visit);
//		vistDao.save(visit1);
//		
//		List<Physicalcheckup> pycheck = phyDao.findByVisit(visit1.getVisitId());
//		assertTrue(pycheck.isEmpty());
//		assertFalse(pycheck.contains(phycheck));
//	}
//	
//	@Test
//    @Transactional(propagation= Propagation.REQUIRED)
//	public void findByDateTest() {
//		phyDao.save(phycheck);
//		phyDao.save(phycheck1);
//		
//		vistDao.save(visit);
//		vistDao.save(visit1);
//		
//		Date phyDate = phycheck.getPhycheDate();
//		List<Physicalcheckup> pycheck = phyDao.findByDate(phyDate);
//		
//		assertTrue(pycheck.contains(phycheck));
//		assertFalse(pycheck.contains(phycheck1));
//		
//	}
//	
//	@Test
//    @Transactional(propagation= Propagation.REQUIRED)
//	public void notFindByDateTest() {
//		phyDao.save(phycheck);
//		phyDao.save(phycheck1);
//		
//		vistDao.save(visit);
//		vistDao.save(visit1);
//		
//		Date phyDate = new GregorianCalendar(2021, Calendar.AUGUST, 15).getTime();
//		List<Physicalcheckup> pycheck = phyDao.findByDate(phyDate);
//		assertTrue(pycheck.isEmpty());
//		assertFalse(pycheck.contains(phycheck));
//		
//		
//	}
//	
//	@Test
//    @Transactional(propagation= Propagation.REQUIRED)
//	public void findAllTest() {
//		
//		phyDao.save(phycheck);
//		phyDao.save(phycheck1);
//		
//		vistDao.save(visit);
//		vistDao.save(visit1);
//		
//		List<Physicalcheckup> pycheck = phyDao.findAll();
//		assertFalse(phyDao.findAll().isEmpty());
//		assertTrue(pycheck.contains(phycheck));
//		assertTrue(pycheck.contains(phycheck1));
//		
//	}
//	

//	
//	@Test
//    @Transactional(propagation= Propagation.REQUIRED)
//	public void deleteAllTest() {
//		phyDao.save(phycheck);
//		phyDao.save(phycheck1);
//		
//		vistDao.save(visit);
//		vistDao.save(visit1);
//		
//		List<Physicalcheckup> pycheck = phyDao.findAll();
//		assertTrue(pycheck.contains(phycheck));
//		phyDao.deleteAll();
//		
//		pycheck = phyDao.findAll();
//		assertTrue(phyDao.findAll().isEmpty());
//		assertFalse(pycheck.contains(phycheck));
//		
//		
//	}

	

}
