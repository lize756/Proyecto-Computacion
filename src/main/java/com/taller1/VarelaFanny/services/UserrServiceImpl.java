package com.taller1.VarelaFanny.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.taller1.VarelaFanny.model.Userr;
import com.taller1.VarelaFanny.repositories.UserrRepository;

public class UserrServiceImpl implements UserrService{

	private UserrRepository userrRepository;
	
	@Autowired
	public UserrServiceImpl(UserrRepository userrRepository) {
		this.userrRepository = userrRepository;
	}
	
	@Override
	public void save(Userr user) {
		userrRepository.save(user);
	}

	@Override
	public Optional<Userr> findById(long id) {
		return userrRepository.findById(id);
	}

	@Override
	public Iterable<Userr> findAll() {
		return userrRepository.findAll();
	}

	@Override
	public void delete(Userr user) {
		userrRepository.delete(user);
	}

	

}
