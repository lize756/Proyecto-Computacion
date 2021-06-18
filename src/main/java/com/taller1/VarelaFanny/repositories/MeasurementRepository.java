package com.taller1.VarelaFanny.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Measurement;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

}
