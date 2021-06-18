package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Institution;

public interface InstitutionService {

	public Institution createInstitution(Institution institution);
	public Institution updateInstitution(Institution institution);
	public void deleteInstitution(long id);
	public Optional<Institution> findById(long id);
	public Iterable<Institution> findAll();
}
