package com.taller1.VarelaFanny.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Userr;

@Repository
public interface UserrRepository extends CrudRepository<Userr, Long> {
	
	//List<Userr> findByName(String name);
	List<Userr> findByUserName(String userName);

}
