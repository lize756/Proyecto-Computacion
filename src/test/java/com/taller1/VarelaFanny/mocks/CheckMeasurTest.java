package com.taller1.VarelaFanny.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.repositories.CheckMeasurRepository;
import com.taller1.VarelaFanny.repositories.MeasurementRepository;
import com.taller1.VarelaFanny.repositories.PhysicalcheckupRepository;
import com.taller1.VarelaFanny.services.CheckMeasurServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CheckMeasurTest {
	
	@Mock
	private CheckMeasurRepository checkMeasurRepository;
	
	@Mock
	private PhysicalcheckupRepository physicalcheckupRepository;
	
	@Mock
	private MeasurementRepository measurRepository;
	
	@InjectMocks
	private CheckMeasurServiceImpl checkMeasurImpl;
	
	private Measurement measur;
	private Physicalcheckup physCheckup;
	private CheckMeasur checkMeasur;
	private CheckMeasurPK checkMeasurPK;
	
	@BeforeEach
	public void setUp1() {
		MockitoAnnotations.initMocks(this);
		String nameMeasur = "nameMeasur";
		
		String nameCheckMeasur = "nameCheckMeasur";
		
		long idMeasur =  93911332;
		long idPhysCheckup =12345678; 
		long idCheckMeasur = 546464768;
		 BigDecimal measvalue = new BigDecimal(23224242);
		
		measur = new Measurement();
		measur.setMeasId(idMeasur);
		measur.setMeasName(nameMeasur);
		
		physCheckup = new Physicalcheckup();
		physCheckup.setPhycheId(idPhysCheckup);
		
		checkMeasurPK = new CheckMeasurPK();
		checkMeasurPK.setMeasMeasId(idCheckMeasur);
		
		checkMeasur = new CheckMeasur();
		checkMeasur.setId(checkMeasurPK);
		checkMeasur.setMeasvalue(measvalue);
		checkMeasur.setPhysicalcheckup(physCheckup);
		checkMeasur.setMeasurement(measur);	
		
	}
	
	@Nested
	class createCheckMeasurement{
	
		
		@DisplayName("Agregar CheckMeasurement sin problema.")
		@Test
		public void createCheckMeasurement() {
				
			when(measurRepository.findById(checkMeasur.getMeasurement().getMeasId())).thenReturn(Optional.of(measur));
			when(physicalcheckupRepository.findById(checkMeasur.getPhysicalcheckup().getPhycheId())).thenReturn(Optional.of(physCheckup));
			
			when(checkMeasurRepository.save(checkMeasur)).thenReturn(checkMeasur);
		
			assertNotNull(checkMeasurImpl.createCheckMeasur(checkMeasur));
			
		}
		
		@DisplayName("CheckMeasurement sin Physicalcheckup")
		@Test
		public void notCreatCheckMeasurement_PhysicalcheckupNull() {
				
			checkMeasur.setPhysicalcheckup(null);
			assertNull(checkMeasurImpl.createCheckMeasur(checkMeasur));
			
		}
		
		@DisplayName("CheckMeasurement sin Measurement")
		@Test
		public void notCreatCheckMeasurement_MeasurementNull() {
				
			checkMeasur.setMeasurement(null);
			assertNull(checkMeasurImpl.createCheckMeasur(checkMeasur));
			
		}
		
		@DisplayName("CheckMeasurement sin Measvalue")
		@Test
		public void notCreatCheckMeasurement_MeasvalueNull() {
				
			checkMeasur.setMeasvalue(null);
			assertNull(checkMeasurImpl.createCheckMeasur(checkMeasur));
			
		}
		
		
	}
		
	@Nested
	class updateteCheckMeasurement{
		
		@DisplayName("Editar CheckMeasurement sin problema.")
		@Test
		public void updateCheckMeasurement() {
			
			BigDecimal measvalue2 = new BigDecimal(23226542);
			BigDecimal measvalue = checkMeasur.getMeasvalue();
			
			checkMeasur.setMeasvalue(measvalue2);
				
			when(measurRepository.findById(checkMeasur.getMeasurement().getMeasId())).thenReturn(Optional.of(measur));
			when(physicalcheckupRepository.findById(checkMeasur.getPhysicalcheckup().getPhycheId())).thenReturn(Optional.of(physCheckup));
			when(checkMeasurRepository.findById(checkMeasur.getId())).thenReturn(Optional.of(checkMeasur));
			
			when(checkMeasurRepository.save(checkMeasur)).thenReturn(checkMeasur);
		
			assertNotNull(checkMeasurImpl.updateCheckMeasur(checkMeasur));
			assertNotEquals(measvalue, checkMeasur.getMeasvalue());
		
		}
		
		@DisplayName("Editar CheckMeasurement sin Physicalcheckup")
		@Test
		public void notUpdateCheckMeasurement_PhysicalcheckupNull() {
				
			checkMeasur.setPhysicalcheckup(null);
			assertNull(checkMeasurImpl.updateCheckMeasur(checkMeasur));
			
		}
		
		@DisplayName("Editar CheckMeasurement sin Measurement")
		@Test
		public void notUpdateCheckMeasurement_MeasurementNull() {
				
			checkMeasur.setMeasurement(null);
			assertNull(checkMeasurImpl.updateCheckMeasur(checkMeasur));
			
		}
		
		@DisplayName("Editar CheckMeasurement sin Measvalue")
		@Test
		public void notUpdateCheckMeasurement_MeasvalueNull() {
				
			checkMeasur.setMeasvalue(null);
			assertNull(checkMeasurImpl.updateCheckMeasur(checkMeasur));
			
		}
		
		
	}


}
