package com.taller1.VarelaFanny.daos;


import java.util.List;
import com.taller1.VarelaFanny.model.Nexuspoll;


public interface INexuspollDao {
	
	public void save(Nexuspoll entity);
	public boolean isSaved(Nexuspoll entity);
	public void update(Nexuspoll entity);
	public void delete(Nexuspoll entity);
	
	public List<Nexuspoll> findByInstution(Long instInstId);
	
	public List<Nexuspoll> findAll();
	public void deleteAll();
	public Nexuspoll findById(Long nexpollId);

}
