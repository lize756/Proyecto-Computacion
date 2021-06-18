package com.taller1.VarelaFanny.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.daos.PersonDao;
import com.taller1.VarelaFanny.daos.VisitDao;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.repositories.VisitRepository;


@Service
public class VisitServiceImpl implements VisitService {

	public VisitRepository visitRepository;
	public VisitDao visitDao;
	public PersonRepository persRepository;
	public PersonDao persDao;
	
	@Autowired
	public VisitServiceImpl(VisitRepository visitRepository, VisitDao visitDao ,PersonRepository persRepository, PersonDao persDao) {
		this.visitRepository = visitRepository;
		this.persRepository = persRepository;
		this.persDao = persDao;
		this.visitDao = visitDao;
	}

	@Transactional
	@Override
	public Visit createVisit(Visit visit) {
		
		System.out.println("Llegue");
		if(visit != null && visit.getPerson() != null && visit.getInstitutioncampus() != null) {
			
			if(visit.getVisitEntrancedate() != null && visit.getVisitDetail().length() >= 5 && persDao.findById(visit.getPerson().getPersId()) != null) {
				
				visitDao.save(visit);
				return visit;
			}
		}
		
		return null;
	}
	
	@Transactional
	@Override
    public boolean isSaved(Visit visit) {
        return visitDao.isSaved(visit);
    }

	@Transactional
	@Override
	public Visit updateVisit(Visit visit) {
		
		if(visit != null && visit.getPerson() != null && visit.getInstitutioncampus() != null) {
					
			long idPers = visit.getPerson().getPersId();		
			if(visit.getVisitEntrancedate() != null && visit.getVisitDetail().length() >= 5 && persDao.findById(idPers) != null) {
				
				if(visitDao.findById(visit.getVisitId()) != null) {
					visitDao.update(visit);	
					return visit;
				}
			}
		}
		
		return null;
	}
	
	@Transactional
	@Override
	public Iterable<Visit> findAll(){
		return visitDao.findAll();
	}
	
	@Transactional
	@Override
	public Optional<Visit> findById(long id){
		return  Optional.of(visitDao.findById(id));
	}

	@Transactional
	@Override
	public void deleteVisit(Visit visit) {
		if(visit != null) {
			visitDao.delete(visit);
		}
		
	}
	
}
