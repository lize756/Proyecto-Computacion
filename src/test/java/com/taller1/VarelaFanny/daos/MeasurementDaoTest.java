package com.taller1.VarelaFanny.daos;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Visit;

@SpringBootTest
public class MeasurementDaoTest {

	@Autowired
	private MeasurementDao measurDao;
	
	private Measurement measur;
	private Measurement measur1;
	
	@BeforeEach
	public void setUp() {
		
		String nameMeasur = "nameMeasur";
		String nameMeasur1 = "nameMeasur1";
		
		String measDescription = "MeasDescription";
		String measDescription1 = "MeasDescription1";
		
		String measUnit = "MeasUnit";
		String measUnit1 = "MeasUnit1";
		
		BigDecimal measMaxthreshold = new BigDecimal(8028);
		BigDecimal measMinthreshold = new BigDecimal(1222);
		
		BigDecimal measMaxthreshold1 = new BigDecimal(18873);
		BigDecimal measMinthreshold1 = new BigDecimal(12332);
		
		measur = new Measurement();
		measur.setMeasName(nameMeasur);
		measur.setMeasDescription(measDescription);
		measur.setMeasUnit(measUnit);
		measur.setMeasMaxthreshold(measMaxthreshold);
		measur.setMeasMinthreshold(measMinthreshold);

		measur1 = new Measurement();
		measur1.setMeasName(nameMeasur1);
		measur1.setMeasDescription(measDescription1);
		measur1.setMeasUnit(measUnit1);
		measur1.setMeasMaxthreshold(measMaxthreshold1);
		measur1.setMeasMinthreshold(measMinthreshold1);
				
	}
	
	@Test
	@Transactional
	public void saveMeasurementTest() {
		assertFalse(measurDao.isSaved(measur));
		measurDao.save(measur);
		assertTrue(measurDao.isSaved(measur));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		measurDao.save(measur);
		measur.setMeasDescription("MeasDescription 2");
		measurDao.update(measur);
		assertEquals("MeasDescription 2", measurDao.findById(measur.getMeasId()).getMeasDescription());
	}
	
	@Test
    @Transactional
    public void notUpdateTest(){
        Measurement measurEdit =new Measurement();
        measurEdit.setMeasDescription("measDescription2");
        measurEdit.setMeasUnit("measUnit2");

        measurDao.save(measur);
        assertEquals(measur, measurDao.findById(measur.getMeasId()));

        measurDao.update(measurEdit);
        assertNotSame(measurEdit, measurDao.findById(measur.getMeasId()));
    }
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		measurDao.save(measur);
		assertTrue(measurDao.isSaved(measur));
		measurDao.delete(measur);
		assertFalse(measurDao.isSaved(measur));
		
	}
	
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void noDeleteTest() {
		measurDao.save(measur);
		assertTrue(measurDao.isSaved(measur));
		measurDao.delete(measur1);
		assertTrue(measurDao.isSaved(measur));
		
	}
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByDescriptionTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		String description = measur.getMeasDescription();
		
		List<Measurement> lmeasur = measurDao.findByDescription(description);
		assertTrue(lmeasur.contains(measur));
		assertFalse(lmeasur.contains(measur1));
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByDescriptionTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		String description = "MeasDescription2";
		
		List<Measurement> lmeasur = measurDao.findByDescription(description);
		assertTrue(lmeasur.isEmpty());
		assertFalse(lmeasur.contains(measur1));
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByThresholdTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		BigDecimal measMaxthresholdP = new BigDecimal(8100);
		BigDecimal measMinthresholdP = new BigDecimal(1122);
		
		List<Measurement> lmeasur = measurDao.findByThreshold(measMinthresholdP, measMaxthresholdP);
		assertFalse(lmeasur.isEmpty());
		assertTrue(lmeasur.contains(measur));
		assertFalse(lmeasur.contains(measur1));
		
	}
	
	/**
     * cuando ningun threshold se encuentra en el rango
    */
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByThresholdTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		BigDecimal measMaxthresholdP = new BigDecimal(7000);
		BigDecimal measMinthresholdP = new BigDecimal(13000);
		
		List<Measurement> lmeasur = measurDao.findByThreshold(measMinthresholdP, measMaxthresholdP);
		assertTrue(lmeasur.isEmpty());
		assertFalse(lmeasur.contains(measur));
		assertFalse(lmeasur.contains(measur1));
		
	}
	
	/**
     * cuando el rango de threshold es mayor que el threshold minimo
    */
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByThresholdTest1() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		BigDecimal measMaxthresholdP = new BigDecimal(8100);
		BigDecimal measMinthresholdP = new BigDecimal(13000);
		
		List<Measurement> lmeasur = measurDao.findByThreshold(measMinthresholdP, measMaxthresholdP);
		assertTrue(lmeasur.isEmpty());
		assertFalse(lmeasur.contains(measur));
		assertFalse(lmeasur.contains(measur1));
		
	}
	
	/**
     * cuando el rango de threshold es menor que el threshold maximo
    */
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void notFindByThresholdTest2() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		BigDecimal measMaxthresholdP = new BigDecimal(7000);
		BigDecimal measMinthresholdP = new BigDecimal(1122);
		
		List<Measurement> lmeasur = measurDao.findByThreshold(measMinthresholdP, measMaxthresholdP);
		assertTrue(lmeasur.isEmpty());
		assertFalse(lmeasur.contains(measur));
		assertFalse(lmeasur.contains(measur1));
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		List<Measurement> lmeasur = measurDao.findAll();
		
		assertFalse(lmeasur.isEmpty());
		assertTrue(lmeasur.contains(measur));
		assertTrue(lmeasur.contains(measur1));
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		Measurement measurp = measurDao.findById(measur.getMeasId());
		assertEquals(measur, measurp);
		
		Measurement measurp1 = measurDao.findById(measur1.getMeasId());
		assertEquals(measur1, measurp1);
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void deleteAllTest() {
		measurDao.save(measur);
		measurDao.save(measur1);
		
		List<Measurement> lmeasur = measurDao.findAll();
		assertFalse(lmeasur.isEmpty());
		assertTrue(lmeasur.contains(measur));
		
		measurDao.deleteAll();
		lmeasur = measurDao.findAll();
		assertTrue(lmeasur.isEmpty());
		assertFalse(lmeasur.contains(measur));
			
	}
	

}
