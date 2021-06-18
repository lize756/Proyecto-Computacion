package com.taller1.VarelaFanny.daos;

import java.util.List;


import com.taller1.VarelaFanny.model.Nexusquestion;

public interface INexusquestionDao {
	
	public void save(Nexusquestion entity);
	public boolean isSaved(Nexusquestion entity);
	public void update(Nexusquestion entity);
	public void delete(Nexusquestion entity);
	
	public List<Nexusquestion> findByPoll(Long nexpollId);
	
	public List<Nexusquestion> findAll();
	public void deleteAll();
	public Nexusquestion findById(Long nexquesId);

}
