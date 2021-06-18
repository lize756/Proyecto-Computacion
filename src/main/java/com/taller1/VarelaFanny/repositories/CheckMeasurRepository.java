package com.taller1.VarelaFanny.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;

@Repository
public interface CheckMeasurRepository extends CrudRepository<CheckMeasur, CheckMeasurPK> {

}
