package com.taller1.VarelaFanny.daos;

import java.util.List;
import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;

public interface ICheckMeasurDao {
	
	public void save(CheckMeasur entity);
	 public boolean isSaved(CheckMeasur entity);
	public void update(CheckMeasur entity);
	public void delete(CheckMeasur entity);
	
	public List<CheckMeasur> findAll();
	public CheckMeasur findById(CheckMeasurPK id);
	public void deleteAll();

}
