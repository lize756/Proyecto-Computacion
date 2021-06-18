package com.taller1.VarelaFanny.daos;

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

import com.taller1.VarelaFanny.model.Institutioncampus;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;

@SpringBootTest
public class VisitDaoTest {
	
	@Autowired
	private VisitDao vistDao;
		
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
		visit.setPerson(person);
		
		
		visitA = new Visit();
		visitA.setVisitDetail(visitDetail1);
		visitA.setVisitEntrancedate(entranceDate1);
		visitA.setVisitExitdate(endDate1);
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
		assertFalse(vistDao.isSaved(visit));
		vistDao.save(visit);
        assertTrue(vistDao.isSaved(visit));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		vistDao.save(visit);
		visit.setVisitDetail("visitDetail 2");
		vistDao.update(visit);
		assertEquals("visitDetail 2", vistDao.findById(visit.getVisitId()).getVisitDetail());
	}
	
	@Test
    @Transactional
    public void notUpdateTest(){
        Visit vistEdit =new Visit();
        vistEdit.setVisitDetail("visitDetail2");
        vistEdit.setVisitEntrancedate(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());

        vistDao.save(visit);
        assertEquals(visit, vistDao.findById(visit.getVisitId()));

        vistDao.update(vistEdit);
        assertNotSame(vistEdit, vistDao.findById(visit.getVisitId()));
    }
	
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		vistDao.save(visit);
		assertTrue(vistDao.isSaved(visit));
		vistDao.delete(visit);
		assertFalse(vistDao.isSaved(visit));
	}
	
	@Test
	@Transactional
	public void notDeleteTest(){
		vistDao.save(visit);
		assertTrue(vistDao.isSaved(visit));
		vistDao.delete(visitA);
		assertTrue(vistDao.isSaved(visit));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		vistDao.save(visit);
		Visit visit1 = vistDao.findById(visit.getVisitId());
		
		assertEquals(visit, visit1);
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		List<Visit> lvist = vistDao.findAll();		
		assertFalse(vistDao.findAll().isEmpty());
		assertTrue(lvist.contains(visit));	
		assertTrue(lvist.contains(visitA));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByPersonTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		Person personV = visit.getPerson();
		List<Visit> pvist = vistDao.findByPerson(personV.getPersId());
		assertTrue( pvist.contains(visit));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByPersonTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		Person personV = visitA.getPerson();
		List<Visit> pvist = vistDao.findByPerson(personV.getPersId());
		assertFalse( pvist.contains(visit));	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByEntrancedateTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		Date dateEntrance = new GregorianCalendar(2021, Calendar.OCTOBER, 1).getTime();
		List<Visit> dvist = vistDao.findByEntrancedate(dateEntrance);
		
		assertFalse( dvist.contains(visit));	
	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByEntrancedateTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		Date dateEntrance = visit.getVisitEntrancedate();
		List<Visit> dvist = vistDao.findByEntrancedate(dateEntrance);
		
		assertTrue( dvist.contains(visit));	
	
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByExitdateTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		Date dateEnd = visitA.getVisitExitdate();
		List<Visit> dvist = vistDao.findByExitdate(dateEnd);
		assertTrue(dvist.contains(visitA));		
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByExitdateTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		Date dateEnd =  new GregorianCalendar(2021, Calendar.JANUARY, 3).getTime();
		List<Visit> dvist = vistDao.findByExitdate(dateEnd);
		assertTrue(dvist.isEmpty());		
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByDateAndCheck() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		phyDao.save(phys);
		phyDao.save(phys1);
		phyDao.save(phys2);
		
		Date date = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		List<Visit> lvist = vistDao.findByDateAndCheck(date);
		assertFalse(lvist.isEmpty());
		assertTrue(lvist.contains(visit));
		
	}
	
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteAllTest() {
		vistDao.save(visit);
		vistDao.save(visitA);
		
		persDao.save(person);
		persDao.save(person1);
		
		List<Visit> lvist = vistDao.findAll();
		assertTrue(lvist.contains(visit));
		
		vistDao.deleteAll();
		
		lvist = vistDao.findAll();
		assertFalse(lvist.contains(visit));
		assertTrue(lvist.isEmpty());
		
	}
	
	
	
	
	
	

	

}
