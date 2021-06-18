package com.taller1.VarelaFanny.daos;

import java.util.Date;
import java.util.List;

import com.taller1.VarelaFanny.model.Physicalcheckup;

public interface IPhysicalcheckupDao {

	public void save(Physicalcheckup entity);
	public boolean isSaved(Physicalcheckup entity);
	public void update(Physicalcheckup entity);
	public void delete(Physicalcheckup entity);
	
	public List<Physicalcheckup> findByVisit(Long visitId);
	public List<Physicalcheckup> findByDate(Date phycheDate);
	
	public List<Physicalcheckup> findAll();
	public Physicalcheckup findById(Long phycheId);
	public void deleteAll();
		
}
