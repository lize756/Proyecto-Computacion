package com.taller1.VarelaFanny.mocks;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.management.RuntimeErrorException;

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
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.services.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private InstitutionRepository institutionRepository;
	
	@InjectMocks
	private PersonServiceImpl personService;
	
	private static final long ID_INSTITUTION = 123456789;
	private static final String NAME_INSTITUTION = "Universidad ICESI";
	private Person person;
	private Institution institution;
	
	@BeforeEach
	public void setUp1() {
		MockitoAnnotations.initMocks(this);
		person = new Person();
		person.setPersId(987654321);
		institution = new Institution();
		
	}
	
	@Nested
	class createPerson {
		
		@DisplayName("Agregar persona sin nombre.")
		@Test
		public void createPersonWithoutNameTest() throws Exception {
			institution.setInstId(ID_INSTITUTION);
			institution.setInstName(NAME_INSTITUTION);
			person.setPersName(null);
			person.setPersLastname("Apellido");
			person.setInstitution(institution);
			
			when(institutionRepository.findById(person.getInstitution().getInstId())).thenReturn(Optional.of(institution));
			
			assertThrows(RuntimeErrorException.class,() ->{
				personService.createPerson(person);
				//verify(personRepository).save(person);
			});			
		}
		
		@DisplayName("Agregar persona sin apellido")
		@Test
		public void createPersonWithoutLastNameTest() throws Exception {
			institution.setInstId(ID_INSTITUTION);
			institution.setInstName(NAME_INSTITUTION);
			person.setPersName("Nombre");
			person.setPersLastname(null);
			person.setInstitution(institution);

			
			when(institutionRepository.findById(person.getInstitution().getInstId())).thenReturn(Optional.of(institution));

			
			assertThrows(RuntimeErrorException.class,() ->{
				personService.createPerson(person);
				verify(personRepository).save(person);
			});	
		}
		
		@DisplayName("Agregar persona, pero el instituto no existe.")
		@Test
		public void createPersonButInstituteDoesntExistTest() {
			person.setPersName("Nombre");
			person.setPersLastname("Apellido");
			person.setInstitution(institution);
			
			when(institutionRepository.findById(institution.getInstId())).thenReturn(Optional.ofNullable(null));
		
			assertThrows(RuntimeErrorException.class,() ->{
				personService.createPerson(person);
			});				
		}
		
		@DisplayName("Agregar persona example")
		@Test
		public void createPersonExampleTest() {
			institution.setInstId(ID_INSTITUTION);
			institution.setInstName(NAME_INSTITUTION);
			person.setPersName("Nombre");
			person.setPersLastname("Apellido");
			person.setInstitution(institution);
			when(institutionRepository.findById(person.getInstitution().getInstId())).thenReturn(Optional.of(institution));
			when(personRepository.save(person)).thenReturn(person);
			
			try {
				assertNotNull(personService.createPerson(person));
			} catch (Exception e) {
				fail();
			}				
		}
		
	}
	
	@Nested
	class updatePerson{
		
		@DisplayName("Editar persona, pero la persona es null.")
		@Test
		public void editPersonButParamIsNullTest() {		
			assertThrows(RuntimeErrorException.class,() ->{
				personService.updatePerson(null);
			});
		}
		
		@DisplayName("Editar persona, pero la persona no existe.")
		@Test
		public void editPersonButItDoesntExistTest() {	
			when(personRepository.findById(person.getPersId())).thenThrow(RuntimeErrorException.class);
			assertThrows(RuntimeErrorException.class,() ->{
				personService.updatePerson(person);
				//verify(personRepository).save(person);
			});
		}
		
		@DisplayName("Editar persona existente")
		@Test
		public void editPersonAndItExistTest() {	
			when(personRepository.findById(person.getPersId())).thenReturn(Optional.of(person));
			try {
				assertEquals(personService.updatePerson(person), person);
			}catch(Exception e) {
				fail();
			}
			
		}
		
		
	}
	

}
