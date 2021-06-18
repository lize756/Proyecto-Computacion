package com.taller1.VarelaFanny.daos;

import static org.junit.jupiter.api.Assertions.*;

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

import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;

@SpringBootTest
class PhysicalcheckupDaoTest {
	
	@Autowired
	private PhysicalcheckupDao phyDao;
	
	@Autowired
	private VisitDao vistDao;
	
	private Physicalcheckup phycheck;
	private Physicalcheckup phycheck1;
	private Visit visit;
	private Visit visit1;
	
	@BeforeEach
	public void setUp() {
		Date phycheDate = new Date(System.currentTimeMillis());
		Date phycheDate1 = new GregorianCalendar(2021, Calendar.JULY, 3).getTime();
		
		String visitDetail = "visit Detail";
		String visitDetail1 = "visit Detail1";
		Date entranceDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		
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
		
		phycheck1.setPhycheDate(phycheDate1);
		phycheck1.setVisit(visit1);
	}
	
	@Test
	@Transactional
	public void savePhycheckTest(){
		assertFalse(phyDao.isSaved(phycheck));
		phyDao.save(phycheck);
		assertTrue(phyDao.isSaved(phycheck));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		
		phyDao.save(phycheck);
		Date phycheDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		phycheck.setPhycheDate(phycheDate);
		phyDao.update(phycheck);
		assertEquals(phycheDate, phyDao.findById(phycheck.getPhycheId()).getPhycheDate());
	
	}
	
	@Test
    @Transactional
    public void notUpdateTest(){
        Physicalcheckup physEdit = new Physicalcheckup();
        physEdit.setPhycheDate(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());

        phyDao.save(phycheck);
        assertEquals(phycheck, phyDao.findById(phycheck.getPhycheId()));

        phyDao.update(physEdit);
        assertNotSame(physEdit, phyDao.findById(phycheck.getPhycheId()));
    }
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		
		phyDao.save(phycheck);
		assertTrue(phyDao.isSaved(phycheck));
		phyDao.delete(phycheck);
		assertFalse(phyDao.isSaved(phycheck));
	}
	
	@Test
	@Transactional
	public void notDeleteTest(){
		phyDao.save(phycheck);
		assertTrue(phyDao.isSaved(phycheck));
		phyDao.delete(phycheck1);
		assertTrue(phyDao.isSaved(phycheck));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByVisitTest() {	
		phyDao.save(phycheck);
		vistDao.save(visit);
		
		Visit vist = phycheck.getVisit();
		List<Physicalcheckup> pycheck = phyDao.findByVisit(vist.getVisitId());
		assertTrue(pycheck.contains(phycheck));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByVisitTest() {	
		phyDao.save(phycheck);
		vistDao.save(visit);
		vistDao.save(visit1);
		
		List<Physicalcheckup> pycheck = phyDao.findByVisit(visit1.getVisitId());
		assertTrue(pycheck.isEmpty());
		assertFalse(pycheck.contains(phycheck));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByDateTest() {
		phyDao.save(phycheck);
		phyDao.save(phycheck1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
		
		Date phyDate = phycheck.getPhycheDate();
		List<Physicalcheckup> pycheck = phyDao.findByDate(phyDate);
		
		assertTrue(pycheck.contains(phycheck));
		assertFalse(pycheck.contains(phycheck1));
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByDateTest() {
		phyDao.save(phycheck);
		phyDao.save(phycheck1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
		
		Date phyDate = new GregorianCalendar(2021, Calendar.AUGUST, 15).getTime();
		List<Physicalcheckup> pycheck = phyDao.findByDate(phyDate);
		assertTrue(pycheck.isEmpty());
		assertFalse(pycheck.contains(phycheck));
		
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		
		phyDao.save(phycheck);
		phyDao.save(phycheck1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
		
		List<Physicalcheckup> pycheck = phyDao.findAll();
		assertFalse(phyDao.findAll().isEmpty());
		assertTrue(pycheck.contains(phycheck));
		assertTrue(pycheck.contains(phycheck1));
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		
		phyDao.save(phycheck);
		phyDao.save(phycheck1);
		
		Physicalcheckup pycheck = phyDao.findById(phycheck1.getPhycheId());
		assertEquals(phycheck1, pycheck);
		assertNotEquals(phycheck, pycheck);
	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteAllTest() {
		phyDao.save(phycheck);
		phyDao.save(phycheck1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
		
		List<Physicalcheckup> pycheck = phyDao.findAll();
		assertTrue(pycheck.contains(phycheck));
		phyDao.deleteAll();
		
		pycheck = phyDao.findAll();
		assertTrue(phyDao.findAll().isEmpty());
		assertFalse(pycheck.contains(phycheck));
		
		
	}

	

}
