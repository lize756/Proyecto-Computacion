package com.taller1.VarelaFanny.services;

import java.util.Optional;

import com.taller1.VarelaFanny.model.Nexusquestion;

public interface NexusquestionService {
	
	public Nexusquestion createNexusquestion(Nexusquestion nexusquestion);
	public Nexusquestion updateNexusquestion(Nexusquestion nexusquestion);
	public boolean isSaved(Nexusquestion nexusquestion);
	public Iterable<Nexusquestion> findAll();
	public void delete(Nexusquestion nexusquestion);
	public Optional<Nexusquestion> findById(long id);
	public Iterable<Nexusquestion> findByPoll(Long nexpollId);

}
