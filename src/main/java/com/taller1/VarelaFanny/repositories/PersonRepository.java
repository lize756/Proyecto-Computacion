package com.taller1.VarelaFanny.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{

}
