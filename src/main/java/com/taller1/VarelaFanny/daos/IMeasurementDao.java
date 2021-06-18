package com.taller1.VarelaFanny.daos;

import java.math.BigDecimal;
import java.util.List;

import com.taller1.VarelaFanny.model.Measurement;

public interface IMeasurementDao {

	public void save(Measurement entity);
	public boolean isSaved(Measurement entity);
	public void update(Measurement entity);
	public void delete(Measurement entity);
	
	public List<Measurement> findByDescription(String measDescription);
	public List<Measurement> findByThreshold(BigDecimal measMinthreshold,BigDecimal measMaxthreshold);

	public List<Measurement> findAll();
	public Measurement findById(Long measId);
	public void deleteAll();
}
