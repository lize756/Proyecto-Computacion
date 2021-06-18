package com.taller1.VarelaFanny.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.daos.PersonDao;
import com.taller1.VarelaFanny.daos.PhysicalcheckupDao;
import com.taller1.VarelaFanny.daos.VisitDao;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.repositories.PhysicalcheckupRepository;
import com.taller1.VarelaFanny.repositories.VisitRepository;


@Service
public class PhysicalcheckupServiceImpl implements PhysicalcheckupService{

	private PhysicalcheckupRepository physicalcheckupRepository;
	private PhysicalcheckupDao phyDao;
	private VisitDao vistDao;
	private PersonDao persDao;
	private PersonRepository persRepository;
	private VisitRepository visitRepository;
	
	
	@Autowired
	public PhysicalcheckupServiceImpl(PhysicalcheckupRepository physicalcheckupRepository,PhysicalcheckupDao phyDao,PersonDao persDao,VisitDao vistDao,PersonRepository persRepository, VisitRepository visitRepository ) {
		this.physicalcheckupRepository = physicalcheckupRepository;
		this.persRepository = persRepository;
		this.phyDao = phyDao;
		this.vistDao = vistDao;
		this.persDao = persDao;
		this.visitRepository = visitRepository;
		
	}

	@Transactional
	@Override
	public Physicalcheckup createPhysicalcheckupService(Physicalcheckup physicalcheckup) {
		
		if(physicalcheckup != null && physicalcheckup.getPerson() != null && physicalcheckup.getVisit() != null && physicalcheckup.getPhycheDate() != null) {
			
			if(persDao.findById(physicalcheckup.getPerson().getPersId()) != null && vistDao.findById(physicalcheckup.getVisit().getVisitId()) != null) {
				
				phyDao.save(physicalcheckup);
				return physicalcheckup;		
			}	
		}		
		return null;
	}

	@Transactional
	@Override
	public Physicalcheckup updatePhysicalcheckupService(Physicalcheckup physicalcheckup) {
		
		if(physicalcheckup != null && physicalcheckup.getPerson() != null && physicalcheckup.getVisit() != null && physicalcheckup.getPhycheDate() != null) {
			
			if(persDao.findById(physicalcheckup.getPerson().getPersId()) != null && vistDao.findById(physicalcheckup.getVisit().getVisitId()) != null) {
				
				if(phyDao.findById(physicalcheckup.getPhycheId())!= null) {
					phyDao.update(physicalcheckup);
					return physicalcheckup;	
				}
					
			}
		}		
				
		return null;
	}
	
	@Transactional
	@Override
    public boolean isSaved(Physicalcheckup physicalcheckup) {
        return phyDao.isSaved(physicalcheckup);
    }
	
	@Override
	public Iterable<Physicalcheckup> findAll(){
		return phyDao.findAll();
	}
	
	@Override
	public Optional<Physicalcheckup> findById(long id){
		return Optional.of(phyDao.findById(id));
	}

	@Override
	public void delete(Physicalcheckup physicalcheckup) {
		if(physicalcheckup!= null) {
			phyDao.delete(physicalcheckup);
		}
		
	}
	
}
