package com.taller1.VarelaFanny.delegate;

import java.util.List;

import com.taller1.VarelaFanny.model.Measurement;

public interface IMeasurementDelegate {

	public Measurement GET_Measurement(long id);
	public List<Measurement> GET_Measurements();
	public Measurement POST_Measurement(Measurement measur);
	public void PUT_Measurement(Measurement measur);
	public void DELETE_Measurement(Measurement measur);
	
}
