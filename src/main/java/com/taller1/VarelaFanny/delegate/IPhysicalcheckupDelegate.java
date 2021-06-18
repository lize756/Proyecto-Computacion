package com.taller1.VarelaFanny.delegate;

import java.util.List;

import com.taller1.VarelaFanny.model.Physicalcheckup;

public interface IPhysicalcheckupDelegate {
	
	public Physicalcheckup GET_Physicalcheckup(long id);
	public List<Physicalcheckup> GET_Physicalcheckups();
	public Physicalcheckup POST_Physicalcheckup(Physicalcheckup phys);
	public void PUT_Physicalcheckup(Physicalcheckup phys);
	public void DELETE_Physicalcheckup(Physicalcheckup phys);
	

}
