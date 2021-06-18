package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Physicalcheckup;

public interface PhysicalcheckupService {

	public Physicalcheckup createPhysicalcheckupService(Physicalcheckup physicalcheckup);
	public Physicalcheckup updatePhysicalcheckupService(Physicalcheckup physicalcheckup);
	public boolean isSaved(Physicalcheckup physicalcheckup);
	public Iterable<Physicalcheckup> findAll();
	public void delete(Physicalcheckup physicalcheckup);
	public Optional<Physicalcheckup> findById(long id);
	
}
