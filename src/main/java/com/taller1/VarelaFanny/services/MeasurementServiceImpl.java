package com.taller1.VarelaFanny.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.daos.MeasurementDao;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;
import com.taller1.VarelaFanny.repositories.MeasurementRepository;


@Service
public class MeasurementServiceImpl implements MeasurementService {

	public MeasurementRepository measurementRepository;
	private MeasurementDao measurDao;
	public InstitutionRepository instRepository;
	
	
	@Autowired
	public MeasurementServiceImpl(MeasurementRepository measurementRepository, MeasurementDao measurDao,InstitutionRepository instRepository) {
		this.measurementRepository = measurementRepository;
		this.measurDao = measurDao;
		this.instRepository = instRepository;
	}

	@Transactional
	@Override
	public Measurement createMeasurement(Measurement measurement) {
		
		if(measurement != null && measurement.getInstitution() != null 
				&& !measurement.getMeasDescription().isEmpty() 
				&& !measurement.getMeasName().isEmpty() 
				&& !measurement.getMeasUnit().isEmpty() 
				&& measurement.getMeasMaxthreshold() != null 
				&& measurement.getMeasMinthreshold() != null) {
			if(instRepository.findById(measurement.getInstitution().getInstId()) != null) {
				measurDao.save(measurement);
				System.out.println("estoy guardando a: " + measurement.getMeasName());
				return measurement;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public Measurement updateMeasurement(Measurement measurement) {
		
		if(measurement != null && measurement.getInstitution() != null
				&& !measurement.getMeasDescription().isEmpty() 
				&& !measurement.getMeasName().isEmpty() 
				&& !measurement.getMeasUnit().isEmpty() 
				&& measurement.getMeasMaxthreshold() != null 
				&& measurement.getMeasMinthreshold() != null) {
			if(instRepository.findById(measurement.getInstitution().getInstId()) != null) {
				
				if(measurDao.findById(measurement.getMeasId()) != null) {
	                measurDao.update(measurement);
					return measurement;
				}
			}
		}
		
		return null;
	}
	
	@Transactional
	@Override
    public boolean isSaved(Measurement measur) {
        return measurDao.isSaved(measur);
    }
	
	@Transactional
	@Override
	public void deleteMeasurement(Measurement measur) {
		if(measur != null) {
			measurDao.delete(measur);
		}
	}
	
	@Transactional
	@Override
	public Iterable<Measurement> findAll(){
		return measurDao.findAll();
	}
	
	@Transactional
	@Override
	public Optional<Measurement> findById(long id){
		return Optional.of(measurDao.findById(id));
	}
	
	
}
