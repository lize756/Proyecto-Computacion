package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Userr;

public interface PersonService {
	
	public Person createPerson(Person person) throws Exception;
	public Person readPerson(long id) throws Exception;
	public Person updatePerson(Person person) throws Exception;
	public void deletePerson(long id) throws Exception;
	public Optional<Person> findById(long id);
	public Iterable<Person> findAll();
	
	

}
