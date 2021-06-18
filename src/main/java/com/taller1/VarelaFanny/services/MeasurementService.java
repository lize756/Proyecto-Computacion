package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Visit;

public interface MeasurementService {

	public Measurement createMeasurement(Measurement measurement);
	public Measurement updateMeasurement(Measurement measurement);
	public boolean isSaved(Measurement measur);
	public void deleteMeasurement(Measurement measur);
	public Iterable<Measurement> findAll();
	public Optional<Measurement> findById(long id);
}

