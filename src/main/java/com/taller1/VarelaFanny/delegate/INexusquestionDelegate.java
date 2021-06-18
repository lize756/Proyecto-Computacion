package com.taller1.VarelaFanny.delegate;

import java.util.List;

import com.taller1.VarelaFanny.model.Nexusquestion;

public interface INexusquestionDelegate {

	public Nexusquestion GET_Nexusquestion(long id);
	public List<Nexusquestion> GET_Nexusquestions();
	public Nexusquestion POST_Nexusquestion(Nexusquestion nexusquestion);
	public void PUT_Nexusquestion(Nexusquestion nexusquestion);
	public List<Nexusquestion> findByPoll(long nexpollId);
	public void DELETE_Nexusquestion(Nexusquestion nexusquestion);
	
}
