package com.taller1.VarelaFanny.daos;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;

@SpringBootTest
class CheckMeasurDaoTest {
	
	@Autowired
	private CheckMeasurDao checkMeasurDao;
	
	private CheckMeasur checkMeasur;
	private CheckMeasur checkMeasur1;
	private CheckMeasurPK checkMeasurPK;
	private CheckMeasurPK checkMeasurPK1;
	
	@BeforeEach
	public void setUp() {
		
		long idCheckMeasur = 546464768;
		long idCheckMeasur1 = 59064768;
		BigDecimal measvalue = new BigDecimal(23224242);
		BigDecimal measvalue1 = new BigDecimal(32264242);
		
		checkMeasurPK = new CheckMeasurPK();
		checkMeasurPK.setMeasMeasId(idCheckMeasur);
		
		checkMeasurPK1 = new CheckMeasurPK();
		checkMeasurPK1.setMeasMeasId(idCheckMeasur1);
		
		checkMeasur = new CheckMeasur();
		checkMeasur.setId(checkMeasurPK);
		checkMeasur.setMeasvalue(measvalue);
		
		checkMeasur1 = new CheckMeasur();
		checkMeasur1.setId(checkMeasurPK1);
		checkMeasur1.setMeasvalue(measvalue1);
			
	}
	
	@Test
	@Transactional
	public void saveCheckMeasurTest() {
		assertFalse(checkMeasurDao.isSaved(checkMeasur));
		checkMeasurDao.save(checkMeasur);
		assertTrue(checkMeasurDao.isSaved(checkMeasur));
	}
	
	/**@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		checkMeasurDao.save(checkMeasur);
		
		BigDecimal measvaluep = new BigDecimal(67224242);
		checkMeasur.setMeasvalue(measvaluep);
		checkMeasurDao.update(checkMeasur);
		assertEquals(measvaluep, checkMeasurDao.findById(checkMeasur.getId()).getMeasvalue());
		
	}**/
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		checkMeasurDao.save(checkMeasur);
		assertTrue(checkMeasurDao.isSaved(checkMeasur));
		checkMeasurDao.delete(checkMeasur);
		assertFalse(checkMeasurDao.isSaved(checkMeasur));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notDeleteTest() {
		checkMeasurDao.save(checkMeasur);
		assertTrue(checkMeasurDao.isSaved(checkMeasur));
		checkMeasurDao.delete(checkMeasur1);
		assertTrue(checkMeasurDao.isSaved(checkMeasur));
	}
	
	/**@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		checkMeasurDao.save(checkMeasur);
		checkMeasurDao.save(checkMeasur1);
		
		List<CheckMeasur> lcheck = checkMeasurDao.findAll();
		assertFalse(lcheck.isEmpty());
		assertTrue(lcheck.contains(checkMeasur1));
		assertTrue(lcheck.contains(checkMeasur));
		
	}**/
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		
	}

	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteAllTest() {
		
	}
}
