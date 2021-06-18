package com.taller1.VarelaFanny.delegate;

import java.util.List;

import com.taller1.VarelaFanny.model.Nexuspoll;

public interface INexuspollDelegate {
	
	public Nexuspoll GET_Nexuspoll(long id);
	public List<Nexuspoll> GET_Nexuspolls();
	public Nexuspoll POST_Nexuspoll(Nexuspoll nexuspoll);
	public void PUT_Nexuspoll(Nexuspoll nexuspoll);
	public List<Nexuspoll> findByinstitution(long instInstId);
	public void DELETE_Nexuspoll(Nexuspoll nexuspoll);

}
