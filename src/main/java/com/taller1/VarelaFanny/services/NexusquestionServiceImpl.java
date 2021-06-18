package com.taller1.VarelaFanny.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.daos.INexusquestionDao;
import com.taller1.VarelaFanny.model.Nexusquestion;

@Service
public class NexusquestionServiceImpl implements NexusquestionService{

	private INexusquestionDao nexusDao;
	
	@Autowired
	public NexusquestionServiceImpl(INexusquestionDao nexusDao) {
		this.nexusDao = nexusDao;
	}

	@Transactional
	@Override
	public Nexusquestion createNexusquestion(Nexusquestion nexusquestion) {
		if(nexusquestion != null) {
			System.out.println("Estoy guardando ques");
			nexusDao.save(nexusquestion);
			return nexusquestion;
		}
		return null;
	}

	@Transactional
	@Override
	public Nexusquestion updateNexusquestion(Nexusquestion nexusquestion) {
		if(nexusquestion != null) {
			nexusDao.update(nexusquestion);
			return nexusquestion;
		}
		return null;
	}

	@Transactional
	@Override
	public boolean isSaved(Nexusquestion nexusquestion) {
		return nexusDao.isSaved(nexusquestion);
	}

	@Transactional
	@Override
	public Iterable<Nexusquestion> findAll() {
		return nexusDao.findAll();
	}

	@Transactional
	@Override
	public void delete(Nexusquestion nexusquestion) {
		if(nexusquestion != null) {
			nexusDao.delete(nexusquestion);
		}
	}

	@Transactional
	@Override
	public Optional<Nexusquestion> findById(long id) {
		return Optional.of(nexusDao.findById(id));
	}

	@Transactional
	@Override
	public Iterable<Nexusquestion> findByPoll(Long nexpollId) {
		return nexusDao.findByPoll(nexpollId);
	}

	

}
