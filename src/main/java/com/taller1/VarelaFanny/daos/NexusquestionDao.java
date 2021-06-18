package com.taller1.VarelaFanny.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Nexusquestion;

@Repository
public class NexusquestionDao implements INexusquestionDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Nexusquestion entity) {
		//System.out.println("Estoy en el dao");
		entityManager.persist(entity);
		
	}

	@Override
	public boolean isSaved(Nexusquestion entity) {
		return entityManager.contains(entity);
	}

	@Override
	public void update(Nexusquestion entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Nexusquestion entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Nexusquestion> findByPoll(Long nexpollId) {
		String jpql = "SELECT a FROM Nexusquestion a WHERE a.nexuspoll.nexpollId = '"+ nexpollId +"'" ;
		List<Nexusquestion> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Nexusquestion> findAll() {
		String jpql = "SELECT a FROM Nexusquestion a";
		List<Nexusquestion> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public void deleteAll() {
		String jpql = "DELETE FROM Nexusquestion";
		entityManager.createQuery(jpql).executeUpdate();
	}

	@Override
	public Nexusquestion findById(Long nexquesId) {
		return entityManager.find(Nexusquestion.class, nexquesId);
	}

	

}
