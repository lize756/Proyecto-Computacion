package com.taller1.VarelaFanny.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Visit;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

}
