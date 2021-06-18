package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Institutioncampus;

public interface InstitutioncampusService {
	
	public Institutioncampus saveInstitutioncampus(Institutioncampus instCampus) throws Exception;
	public void deleteInstitutioncampus(Institutioncampus instCampus) throws Exception;
	public Optional<Institutioncampus> findById(long id);
	public Iterable<Institutioncampus> findAll();
}
