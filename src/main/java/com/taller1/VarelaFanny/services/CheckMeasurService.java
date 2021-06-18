package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Measurement;

public interface CheckMeasurService {

	public CheckMeasur createCheckMeasur(CheckMeasur checkMeasur);
	public CheckMeasur updateCheckMeasur(CheckMeasur checkMeasur);
	public Iterable<CheckMeasur> findAll();
	public Optional<CheckMeasur> findById(CheckMeasurPK id);
	public void deleteCheckMeasur(CheckMeasur checkMeasur);
	
}
