package com.taller1.VarelaFanny.delegate;

import java.util.List;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;

public interface ICheckMeasurDelegate {

	public CheckMeasur GET_CheckMeasur(CheckMeasurPK id);
	public List<CheckMeasur> GET_CheckMeasurs();
	public CheckMeasur POST_CheckMeasur(CheckMeasur checkMeasur);
	public void PUT_CheckMeasur(CheckMeasur CheckMeasur);
	public void DELETE_CheckMeasur(CheckMeasur CheckMeasur);
	CheckMeasur GET_CheckMeasur(long measMeasId, long phychePhycheId);
	
}
