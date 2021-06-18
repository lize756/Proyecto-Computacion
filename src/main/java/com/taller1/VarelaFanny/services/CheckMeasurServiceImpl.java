package com.taller1.VarelaFanny.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.daos.ICheckMeasurDao;
import com.taller1.VarelaFanny.daos.IMeasurementDao;
import com.taller1.VarelaFanny.daos.IPhysicalcheckupDao;
import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.repositories.CheckMeasurRepository;
import com.taller1.VarelaFanny.repositories.MeasurementRepository;
import com.taller1.VarelaFanny.repositories.PhysicalcheckupRepository;


@Service
public class CheckMeasurServiceImpl implements CheckMeasurService {

	public CheckMeasurRepository checkMeasurRepository;
	public PhysicalcheckupRepository physicalcheckupRepository;
	public MeasurementRepository measurementRepository;
	public IPhysicalcheckupDao  physDao;
	public IMeasurementDao measurDao;
	public ICheckMeasurDao checkDao;
	
	@Autowired
	public CheckMeasurServiceImpl(IMeasurementDao measurDao,IPhysicalcheckupDao  physDao,CheckMeasurRepository checkMeasurRepository, PhysicalcheckupRepository physicalcheckupRepository, MeasurementRepository measurementRepository, ICheckMeasurDao checkDao) {
		this.checkMeasurRepository = checkMeasurRepository;
		this.physicalcheckupRepository = physicalcheckupRepository;
		this.measurementRepository = measurementRepository;
		this.measurDao = measurDao;
		this.physDao = physDao;
		this.checkDao = checkDao;
	}

	@Transactional
	@Override
	public CheckMeasur createCheckMeasur(CheckMeasur checkMeasur) {
		
		if(checkMeasur != null && checkMeasur.getMeasurement() != null && checkMeasur.getPhysicalcheckup() != null
				&& checkMeasur.getMeasvalue() != null) {
			Long idMeasur = checkMeasur.getMeasurement().getMeasId();
			Long idPhys = checkMeasur.getPhysicalcheckup().getPhycheId();
			
			if(physDao.findById(idPhys) != null && measurDao.findById(idMeasur) != null) {
				checkMeasur.setId(new CheckMeasurPK(idMeasur,idPhys));
				checkDao.save(checkMeasur);
				return checkMeasur;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public CheckMeasur updateCheckMeasur(CheckMeasur checkMeasur) {
		
		if(checkMeasur != null && checkMeasur.getMeasurement() != null && checkMeasur.getPhysicalcheckup() != null 
				&& checkMeasur.getMeasvalue() != null) {
			Long idMeasur = checkMeasur.getMeasurement().getMeasId();
			Long idPhys = checkMeasur.getPhysicalcheckup().getPhycheId();
			
			if(physDao.findById(idPhys) != null && measurDao.findById(idMeasur) != null) {
				if(checkDao.findById(checkMeasur.getId()) != null) {
					checkDao.update(checkMeasur);
					return checkMeasur;
				}
			}
			
		}
		
		return null;
	}
	
	@Transactional
	@Override
	public Iterable<CheckMeasur> findAll(){
		return checkMeasurRepository.findAll();
	}
	
	@Transactional
	@Override
	public Optional<CheckMeasur> findById(CheckMeasurPK id){
		return Optional.of(checkDao.findById(id));
	}
	
	@Transactional
	@Override
	public void deleteCheckMeasur(CheckMeasur checkMeasur) {
		if(checkMeasur != null) {
			checkDao.delete(checkMeasur);
		}
		
	}
	
}
