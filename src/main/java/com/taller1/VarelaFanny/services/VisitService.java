package com.taller1.VarelaFanny.services;

import java.util.Optional;
import com.taller1.VarelaFanny.model.Visit;

public interface VisitService {

	public Visit createVisit(Visit visit);
	public boolean isSaved(Visit visit);
	public Visit updateVisit(Visit visit);
	public void deleteVisit(Visit visit);
	public Optional<Visit> findById(long id);
	public Iterable<Visit> findAll();
}
