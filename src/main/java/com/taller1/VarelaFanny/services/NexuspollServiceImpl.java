package com.taller1.VarelaFanny.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.daos.NexuspollDao;
import com.taller1.VarelaFanny.model.Nexuspoll;

@Service
public class NexuspollServiceImpl implements NexuspollService {

	private NexuspollDao nexusDao;
	
	@Autowired
	public NexuspollServiceImpl(NexuspollDao nexusDao) {
		this.nexusDao = nexusDao;
	}
	
	@Transactional
	@Override
	public Nexuspoll createNexuspoll(Nexuspoll nexuspoll) {
		if(nexuspoll!= null) {
			nexusDao.save(nexuspoll);
			System.out.println("guarde nexos");
			return nexuspoll;
		}
		return null;
	}

	@Transactional
	@Override
	public Nexuspoll updateNexuspoll(Nexuspoll nexuspoll) {
		if(nexuspoll!= null) {
			nexusDao.update(nexuspoll);
			return nexuspoll;
		}
		return null;
	}

	@Transactional
	@Override
	public boolean isSaved(Nexuspoll nexuspoll) {	
		return nexusDao.isSaved(nexuspoll);
	}

	@Transactional
	@Override
	public Iterable<Nexuspoll> findAll() {
		return nexusDao.findAll();
	}

	@Transactional
	@Override
	public void delete(Nexuspoll nexuspoll) {
		if(nexuspoll!= null) {
			nexusDao.delete(nexuspoll);
		}
	}

	@Transactional
	@Override
	public Optional<Nexuspoll> findById(long id) {
		return Optional.of(nexusDao.findById(id));
	}

	@Transactional
	@Override
	public Iterable<Nexuspoll> findByInstution(Long instInstId) {
		return nexusDao.findByInstution(instInstId);
	}

}
