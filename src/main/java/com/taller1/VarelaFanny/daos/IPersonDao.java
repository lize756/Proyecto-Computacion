package com.taller1.VarelaFanny.daos;

import java.util.Date;
import java.util.List;

import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Visit;

public interface IPersonDao {
	
	public void save(Person entity);
	public boolean isSaved(Person entity);
	public void update(Person entity);
	public void delete(Person entity);
	
	public List<Person> findByDate(Date start, Date end);
	
	public List<Person> findAll();
	public void deleteAll();
	public Person findById(Long persId);
	
}
