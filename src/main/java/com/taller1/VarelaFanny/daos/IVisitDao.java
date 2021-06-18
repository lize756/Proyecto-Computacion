package com.taller1.VarelaFanny.daos;

import java.util.Date;
import java.util.List;

import com.taller1.VarelaFanny.model.Visit;

public interface IVisitDao {
	
	public void save(Visit entity);
	public boolean isSaved(Visit entity);
	public void update(Visit entity);
	public void delete(Visit entity);
	
	public List<Visit> findByPerson(Long persId);
	public List<Visit> findByEntrancedate(Date visitEntrancedate);
	public List<Visit> findByExitdate(Date visitExitdate);
	public List<Visit> findByDateAndCheck(Date date);
	
	public List<Visit> findAll();
	public Visit findById(Long visitId);
	public void deleteAll();
	
	
}
