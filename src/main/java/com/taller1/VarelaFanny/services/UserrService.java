package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Userr;


public interface UserrService {

	public void save(Userr user);
	public void delete(Userr user);
	public Optional<Userr> findById(long id);
	public Iterable<Userr> findAll();

}
