package com.taller1.VarelaFanny.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Institutioncampus;
import com.taller1.VarelaFanny.repositories.InstitutioncampusRepository;

@Service
public class InstitutioncampusServiceImpl implements InstitutioncampusService{

	private InstitutioncampusRepository instCampusRepository;
	
	@Autowired
	public InstitutioncampusServiceImpl( InstitutioncampusRepository instCampusRepository) {
		this.instCampusRepository = instCampusRepository;
	}
	
		@Override
	public void deleteInstitutioncampus(Institutioncampus instCampus) throws Exception {
		instCampusRepository.delete(instCampus);
	}
	
	@Override
	public Optional<Institutioncampus> findById(long id) {
		return instCampusRepository.findById(id);
	}

	@Override
	public Iterable<Institutioncampus> findAll() {
		return instCampusRepository.findAll();
	}

	@Override
	public Institutioncampus saveInstitutioncampus(Institutioncampus instCampus) throws Exception {
		instCampusRepository.save(instCampus);
		return instCampusRepository.findById(instCampus.getInstcamId()).get();
	}

}
