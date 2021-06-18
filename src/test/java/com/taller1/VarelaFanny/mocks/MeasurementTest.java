package com.taller1.VarelaFanny.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.taller1.VarelaFanny.model.Institution;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;
import com.taller1.VarelaFanny.repositories.MeasurementRepository;
import com.taller1.VarelaFanny.services.MeasurementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MeasurementTest {

	@Mock
	private MeasurementRepository measurRepository;
	
	@Mock
	private InstitutionRepository InstitutionRepository;
	
	@InjectMocks
	private MeasurementServiceImpl measurImpl;
	
	
	private Measurement measur;
	private Institution inst;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		String nameMeasur = "nameMeasur";
		String nameInst = "nameInst";
		
		long idMeasur =  93911334;
		long idInstitution =12345678; 
		
		
		String measDescription = "MeasDescription";
		String measUnit = "MeasUnit";
		BigDecimal measMaxthreshold = new BigDecimal(18288733);
		BigDecimal measMinthreshold = new BigDecimal(122233212);
		
		inst = new Institution();
		inst.setInstId(idInstitution);
		inst.setInstName(nameInst);
		
		measur = new Measurement();
		measur.setMeasId(idMeasur);
		measur.setMeasName(nameMeasur);
		measur.setMeasDescription(measDescription);
		measur.setMeasUnit(measUnit);
		measur.setMeasMaxthreshold(measMaxthreshold);
		measur.setMeasMinthreshold(measMinthreshold);
		measur.setInstitution(inst);
				
	}
	
	
	@Nested
	class createMeasurement{
		
		@DisplayName("Agregar measurement sin problema.")
		@Test
		public void createMeasurement() {
				
			when(InstitutionRepository.findById(measur.getInstitution().getInstId())).thenReturn(Optional.of(inst));
			
			when(measurRepository.save(measur)).thenReturn(measur);
		
			assertNotNull(measurImpl.createMeasurement(measur));
			System.out.println();
		}
		
		@DisplayName("Measurement sin Institution")
		@Test
		public void notCreateMeasurement_InstitutionNull() {
				
			measur.setInstitution(null);
			assertNull(measurImpl.createMeasurement(measur));
			
		}
		
		@DisplayName("Measurement sin MeasDescription")
		@Test
		public void notCreateMeasurement_MeasDescriptionNull() {
				
			measur.setMeasDescription("");
			assertNull(measurImpl.createMeasurement(measur));
			
		}
		
		@DisplayName("Measurement sin MeasName")
		@Test
		public void notCreateMeasurement_MeasNameNull() {
				
			measur.setMeasName("");
			assertNull(measurImpl.createMeasurement(measur));
			
		}
		
		@DisplayName("Measurement sin MeasUnit")
		@Test
		public void notCreateMeasurement_MeasUnitNull() {
				
			measur.setMeasUnit("");
			assertNull(measurImpl.createMeasurement(measur));
			
		}
		
		
		@DisplayName("Measurement sin MeasMaxthreshold")
		@Test
		public void notCreateMeasurement_MeasMaxthresholdNull() {
				
			measur.setMeasMaxthreshold(null);
			assertNull(measurImpl.createMeasurement(measur));
			
		}
		
		@DisplayName("Measurement sin MeasMinthreshold")
		@Test
		public void notCreateMeasurement_MeasMinthresholdNull() {
				
			measur.setMeasMinthreshold(null);;;
			assertNull(measurImpl.createMeasurement(measur));
			
		}
	}
	
	@Nested
	class updateMeasurement{
		
		@DisplayName("Editar measurement sin problema.")
		@Test
		public void updateMeasurement() {
				
			String measDescription = measur.getMeasDescription();
			String measDescription2 = "MeasDescription2";
			measur.setMeasDescription(measDescription2);
			
			when(InstitutionRepository.findById(measur.getInstitution().getInstId())).thenReturn(Optional.of(inst));
			when(measurRepository.findById(measur.getMeasId())).thenReturn(Optional.of(measur));
			when(measurRepository.save(measur)).thenReturn(measur);
			
		
			assertNotNull(measurImpl.createMeasurement(measur));
			assertNotEquals(measDescription, measur.getMeasDescription());
			System.out.println();
		}
		
		
		@DisplayName("Editar Measurement sin Institution")
		@Test
		public void notUpdateMeasurement_InstitutionNull() {
				
			measur.setInstitution(null);
			assertNull(measurImpl.updateMeasurement(measur));
			
		}
		
		@DisplayName("Editar Measurement sin MeasDescription")
		@Test
		public void notUpdateMeasurement_MeasDescriptionNull() {
				
			measur.setMeasDescription("");
			assertNull(measurImpl.updateMeasurement(measur));
			
		}
		
		@DisplayName("Editar Measurement sin MeasName")
		@Test
		public void notUpdateMeasurement_MeasNameNull() {
				
			measur.setMeasName("");
			assertNull(measurImpl.updateMeasurement(measur));
			
		}
		
		@DisplayName("Editar Measurement sin MeasUnit")
		@Test
		public void notUpdateMeasurement_MeasUnitNull() {
				
			measur.setMeasUnit("");
			assertNull(measurImpl.updateMeasurement(measur));
			
		}
		
		
		@DisplayName("Editar Measurement sin MeasMaxthreshold")
		@Test
		public void notCreateMeasurement_MeasMaxthresholdNull() {
				
			measur.setMeasMaxthreshold(null);
			assertNull(measurImpl.createMeasurement(measur));
			
		}
		
		@DisplayName("Editar Measurement sin MeasMinthreshold")
		@Test
		public void notUpdateMeasurement_MeasMinthresholdNull() {
				
			measur.setMeasMinthreshold(null);
			assertNull(measurImpl.updateMeasurement(measur));
			
		}
	}

}
