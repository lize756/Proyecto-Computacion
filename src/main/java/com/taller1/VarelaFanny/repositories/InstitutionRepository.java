package com.taller1.VarelaFanny.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Institution;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long>{

}
