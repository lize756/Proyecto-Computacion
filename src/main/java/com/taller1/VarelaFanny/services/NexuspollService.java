package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Nexuspoll;

public interface NexuspollService {
	
	public Nexuspoll createNexuspoll(Nexuspoll nexuspoll);
	public Nexuspoll updateNexuspoll(Nexuspoll nexuspoll);
	public boolean isSaved(Nexuspoll nexuspoll);
	public Iterable<Nexuspoll> findAll();
	public void delete(Nexuspoll nexuspoll);
	public Optional<Nexuspoll> findById(long id);
	public Iterable<Nexuspoll> findByInstution(Long instInstId);

}
