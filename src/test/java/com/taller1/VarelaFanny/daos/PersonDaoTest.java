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

import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Visit;

@SpringBootTest
class PersonDaoTest {
	
	@Autowired
	private PersonDao persDao;
	
	@Autowired
	private VisitDao vistDao;
	
	private Person person1;
	private Person person;
	private Visit visit;
	private Visit visit1;
	
	@BeforeEach
	public void setUp1() {
		String namePerson = "namePerson";
		String namePerson1 = "namePerson1";
		
		
		Date entranceDate = new GregorianCalendar(2021, Calendar.OCTOBER, 3).getTime();
		Date entranceDate1 = new GregorianCalendar(2021, Calendar.NOVEMBER, 5).getTime();
		String visitDetail = "visit Detail";
		String visitDetail1 = "visit DetailA";
		Date endDate = new GregorianCalendar(2021, Calendar.DECEMBER, 3).getTime();
		Date endDate1 = new GregorianCalendar(2021, Calendar.DECEMBER, 15).getTime();
		
		person = new Person();
		person.setPersName(namePerson);
		
		
		person1 = new Person();
		person1.setPersName(namePerson1);
	
		
		visit = new Visit();
		visit.setVisitDetail(visitDetail);
		visit.setVisitEntrancedate(entranceDate);
		visit.setVisitExitdate(endDate);
		visit.setPerson(person);
		
		
		visit1 = new Visit();
		visit1.setVisitDetail(visitDetail1);
		visit1.setVisitEntrancedate(entranceDate1);
		visit1.setVisitExitdate(endDate1);
		visit1.setPerson(person1);
		
		
		List<Visit> lvist = new ArrayList<>();
		lvist.add(visit);
		lvist.add(visit1);
		person.setVisits(lvist);
	}
	

	@Test
	@Transactional
	public void savePersonTest() {
		assertFalse(persDao.isSaved(person));
		persDao.save(person);
		assertTrue(persDao.isSaved(person));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		persDao.save(person);
		person.setPersName("new name");
		persDao.update(person);
		assertEquals("new name", persDao.findById(person.getPersId()).getPersName());	
	}
	
	@Test
    @Transactional
    public void notUpdateTest(){
        Person persEdit =new Person();
        persEdit.setPersName("name2");
       
        persDao.save(person);
        assertEquals(person, persDao.findById(person.getPersId()));

        persDao.update(persEdit);
        assertNotSame(persEdit, persDao.findById(person.getPersId()));
    }
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		persDao.save(person);
		assertTrue(persDao.isSaved(person));
		persDao.delete(person);
		assertFalse(persDao.isSaved(person));
		
	}
	
	@Test
	@Transactional
	public void notDeleteTest(){
		persDao.save(person);
		assertTrue(persDao.isSaved(person));
		persDao.delete(person1);
		assertTrue(persDao.isSaved(person));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByDateTest() {
		persDao.save(person);
		persDao.save(person1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
				
		Date start = new GregorianCalendar(2021, Calendar.OCTOBER, 2).getTime();
		Date end = new GregorianCalendar(2021, Calendar.DECEMBER, 1).getTime();
		
		List<Person> lPers = persDao.findByDate(start, end);
		assertFalse(lPers.isEmpty());
		assertTrue(lPers.contains(person));
			
	}

	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByDateTest() {
		persDao.save(person);
		persDao.save(person1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
				
		Date start = new GregorianCalendar(2021, Calendar.FEBRUARY, 2).getTime();
		Date end = new GregorianCalendar(2021, Calendar.APRIL, 1).getTime();
		
		List<Person> lPers = persDao.findByDate(start, end);
		assertTrue(lPers.isEmpty());
		assertFalse(lPers.contains(person));
			
	}
	
	/**
     * cuando solo la fecha final se encuentra dentro del rango de busqueda
    */
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByDateTest1() {
		persDao.save(person);
		persDao.save(person1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
				
		Date start = new GregorianCalendar(2021, Calendar.OCTOBER, 2).getTime();
		Date end = new GregorianCalendar(2021, Calendar.DECEMBER, 1).getTime();
		
		List<Person> lPers = persDao.findByDate(start, end);
		assertFalse(lPers.isEmpty());
		assertTrue(lPers.contains(person));
			
	}
	
	/**
     * cuando solo la fecha inicial se encuentra dentro del rango de busqueda
    */
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByDateTest2() {
		persDao.save(person);
		persDao.save(person1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
				
		Date start = new GregorianCalendar(2021, Calendar.OCTOBER, 4).getTime();
		Date end = new GregorianCalendar(2021, Calendar.DECEMBER, 20).getTime();
		
		List<Person> lPers = persDao.findByDate(start, end);
		assertFalse(lPers.isEmpty());
		assertTrue(lPers.contains(person));
			
	}
	
	
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		persDao.save(person);
		persDao.save(person1);
		
		Person pers1 = persDao.findById(person1.getPersId());
		assertEquals(person1, pers1);
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		
		persDao.save(person);
		persDao.save(person1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
		
		List<Person> lPers = persDao.findAll();
		assertFalse(lPers.isEmpty());
		assertTrue(lPers.contains(person));
		assertTrue(lPers.contains(person1));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteAllTest() {

		persDao.save(person);
		persDao.save(person1);
		
		vistDao.save(visit);
		vistDao.save(visit1);
		
		List<Person> lPers = persDao.findAll();
		assertTrue(lPers.contains(person));
		
		persDao.deleteAll();
		
		lPers = persDao.findAll();
		assertFalse(lPers.contains(person));
		assertTrue(lPers.isEmpty());
		
	}

}
