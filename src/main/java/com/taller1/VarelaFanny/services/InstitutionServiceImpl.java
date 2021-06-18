package com.taller1.VarelaFanny.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Institution;
import com.taller1.VarelaFanny.model.Userr;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;


@Service
public class InstitutionServiceImpl implements InstitutionService {

	public InstitutionRepository institutionRepo;
	
	public InstitutionServiceImpl(InstitutionRepository institutionRepo) {
		this.institutionRepo = institutionRepo;
	}

	@Override
	public Institution createInstitution(Institution institution) {
		if (institution != null) 
			institutionRepo.save(institution);
		
		return institutionRepo.findById(institution.getInstId()).get();
	}

	@Override
	public Institution updateInstitution(Institution institution) {
		
		if (institution != null) 
			institutionRepo.save(institution);
		
		return institutionRepo.findById(institution.getInstId()).get();
		
	}

	@Override
	public void deleteInstitution(long id) {
		
		institutionRepo.deleteById(id);
	}
	
	@Override
	public Optional<Institution> findById(long id) {
		return institutionRepo.findById(id);
	}

	@Override
	public Iterable<Institution> findAll() {
		return institutionRepo.findAll();
	}
	
	
}
