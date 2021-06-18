package com.taller1.VarelaFanny.delegate;

import java.util.List;
import com.taller1.VarelaFanny.model.Visit;

public interface IVisitDelegate {

	public Visit GET_Visit(long id);
	public List<Visit> GET_Visits();
	public Visit POST_Visit(Visit visit);
	public void PUT_Visit(Visit visit);
	public void DELETE_Visit(Visit visit);
	
}
