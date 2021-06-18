package com.taller1.VarelaFanny.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Nexuspoll;


@Repository
public class NexuspollDao implements INexuspollDao{

	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public void save(Nexuspoll entity) {
		entityManager.persist(entity);	
	}

	@Override
	public boolean isSaved(Nexuspoll entity) {
		return entityManager.contains(entity);
	}

	@Override
	public void update(Nexuspoll entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(Nexuspoll entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public List<Nexuspoll> findByInstution(Long instInstId) {
		String jpql = "SELECT a FROM Nexuspoll a WHERE a.instInstId = '"+ instInstId +"'" ;
		List<Nexuspoll> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Nexuspoll> findAll() {
		String jpql = "SELECT a FROM Nexuspoll a";
		List<Nexuspoll> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public void deleteAll() {
		String jpql = "DELETE FROM Nexuspoll";
		entityManager.createQuery(jpql).executeUpdate();
		
	}

	@Override
	public Nexuspoll findById(Long nexpollId) {
		return entityManager.find(Nexuspoll.class, nexpollId);
	}

}
