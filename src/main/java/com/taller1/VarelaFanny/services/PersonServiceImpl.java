package com.taller1.VarelaFanny.services;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.daos.PersonDao;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;
import com.taller1.VarelaFanny.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

	private PersonRepository personRepository;
	private PersonDao persDao;
	private InstitutionRepository institutionRepository;
	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, PersonDao persDao, InstitutionRepository institutionRepository ) {
		this.personRepository = personRepository;
		this.persDao = persDao;
		this.institutionRepository = institutionRepository;
	}
	
	
	@Override
	public Person createPerson(Person person) throws Exception {
		
		if(person.getPersName() == null || person.getPersLastname() == null) {
			throw new RuntimeErrorException(null,"La persona no tiene asociado un nombre o un apellido.");
		}else {	
			Person p = personRepository.save(person);
			return p;
		}
	}
	
	public Person createPersonExample(Person person) throws Exception {
		
		if(person.getPersName() == null || person.getPersLastname() == null) {
			throw new RuntimeErrorException(null,"La persona debe tener asociado un nombre y un apellido.");
		}else {
			Person p = personRepository.save(person);
			return p;
		}
	}

	@Override
	public Person readPerson(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updatePerson(Person person) throws Exception {
		// TODO Auto-generated method stub
		
		if(person == null) {
			throw new RuntimeErrorException(null,"El valor ingresado por parámetro es null.");
		}else {
			Person p = personRepository.findById(person.getPersId()).get();
			
			if(p == null) {
				throw new RuntimeErrorException(null,"La persona no existe.");
			}else {
				personRepository.save(person);
				return person;
			}
		}
	}

	@Override
	public void deletePerson(long id) throws Exception {
		// TODO Auto-generated method stub
		personRepository.delete(personRepository.findById(id).get());
	}
	
	@Override
	public Iterable<Person> findAll(){
		return personRepository.findAll();
	}
	
	@Override
	public Optional<Person> findById(long id){
		return personRepository.findById(id);
	}
	

}
