package com.taller1.VarelaFanny.integration;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.model.Institution;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;
import com.taller1.VarelaFanny.services.MeasurementService;

@SpringBootTest
public class MeasuremenTest {

	@Autowired
	private MeasurementService measurService;
	
	@Autowired
	private InstitutionRepository instRepo;
	
	private Measurement measur;
	private Measurement measur1;
	
	private Institution inst;
	private Institution inst1;
	
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
		
		inst = new Institution();
		inst.setInstName("nameInst");
		
		inst1 = new Institution();
		inst.setInstName("nameInst1");
		
		measur = new Measurement();
		measur.setMeasName(nameMeasur);
		measur.setMeasDescription(measDescription);
		measur.setMeasUnit(measUnit);
		measur.setMeasMaxthreshold(measMaxthreshold);
		measur.setMeasMinthreshold(measMinthreshold);
		measur.setInstitution(inst);

		measur1 = new Measurement();
		measur1.setMeasName(nameMeasur1);
		measur1.setMeasDescription(measDescription1);
		measur1.setMeasUnit(measUnit1);
		measur1.setMeasMaxthreshold(measMaxthreshold1);
		measur1.setMeasMinthreshold(measMinthreshold1);
		measur1.setInstitution(inst1);			
	}
	
	@Test
	@Transactional
	public void saveMeasurementTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measurService.createMeasurement(measur);
		assertTrue(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measurService.createMeasurement(null);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementInstNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setInstitution(null);
		measurService.createMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementDescriptionNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasDescription("");
		measurService.createMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementNameNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasName("");
		measurService.createMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementUnitNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasUnit("");
		measurService.createMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementMaxthresholdNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasMaxthreshold(null);
		measurService.createMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notSaveMeasurementMinthresholdNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasMinthreshold(null);
		measurService.createMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void updateTest() {
		instRepo.save(inst);
		measurService.createMeasurement(measur);
		measur.setMeasDescription("MeasDescription 2");
		measurService.updateMeasurement(measur);
		assertEquals("MeasDescription 2", measurService.findById(measur.getMeasId()).get().getMeasDescription());
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measurService.updateMeasurement(null);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementInstNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setInstitution(null);
		measurService.updateMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementDescriptionNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasDescription("");
		measurService.updateMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementNameNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasName("");
		measurService.updateMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementUnitNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasUnit("");
		measurService.updateMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementMaxthresholdNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasMaxthreshold(null);
		measurService.updateMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional
	public void notUpdateMeasurementMinthresholdNullTest() {
		instRepo.save(inst);
		assertFalse(measurService.isSaved(measur));
		measur.setMeasMinthreshold(null);
		measurService.updateMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional(propagation= Propagation.REQUIRED)
	public void deleteTest() {
		measurService.createMeasurement(measur);
		assertTrue(measurService.isSaved(measur));
		measurService.deleteMeasurement(measur);
		assertFalse(measurService.isSaved(measur));
	}
	
	@Test
	@Transactional(propagation= Propagation.REQUIRED)
	public void findByIdTest() {
		instRepo.save(inst);
		instRepo.save(inst1);
		measurService.createMeasurement(measur);
		measurService.createMeasurement(measur1);
		
		Measurement measurp = measurService.findById(measur.getMeasId()).get();
		assertEquals(measur, measurp);
		
		Measurement measurp1 = measurService.findById(measur1.getMeasId()).get();
		assertEquals(measur1, measurp1);
		
	}
	
	@Test
    @Transactional(propagation= Propagation.REQUIRED)
	public void findAllTest() {
		instRepo.save(inst);
		instRepo.save(inst1);
		
		measurService.createMeasurement(measur);
		measurService.createMeasurement(measur1);
		
		List<Measurement> lmeasur = new ArrayList<>();
		
		measurService.findAll().iterator().forEachRemaining(lmeasur::add);
						
		assertFalse(lmeasur.isEmpty());
	}


}
