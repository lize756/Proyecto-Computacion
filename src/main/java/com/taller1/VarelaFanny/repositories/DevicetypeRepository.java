package com.taller1.VarelaFanny.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Devicetype;

@Repository
public interface DevicetypeRepository extends CrudRepository<Devicetype, Long>{

}
