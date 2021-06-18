package com.taller1.VarelaFanny.daos;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.VarelaFanny.model.Visit;

@Repository
public class VisitDao implements IVisitDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Visit entity) {
		entityManager.persist(entity);	
	}
	
	@Override
    public boolean isSaved(Visit entity) {
        return entityManager.contains(entity);
    }


	@Override
	public void update(Visit entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Visit entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Visit> findByPerson(Long persId) {
		String jpql = "SELECT a FROM Visit a WHERE a.person.persId = '" + persId + "'";
		List<Visit> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Visit> findByEntrancedate(Date visitEntrancedate) {
		String jpql ="SELECT a FROM Visit a WHERE a.visitEntrancedate =: visitEntrancedate";
		List<Visit> list = entityManager.createQuery(jpql).setParameter("visitEntrancedate", visitEntrancedate,TemporalType.DATE).getResultList();
		return list;
	}

	@Override
	public List<Visit> findByExitdate(Date visitExitdate) {
		String jpql = "SELECT a FROM Visit a WHERE a.visitExitdate =: visitExitdate";		
		List<Visit> list = entityManager.createQuery(jpql).setParameter("visitExitdate", visitExitdate).getResultList();
		return list;
	}
	
	@Override
	public List<Visit> findByDateAndCheck(Date date) {
		//AND a.physicalcheckups.size >= 2
		String jpql ="SELECT a FROM Visit a WHERE a.visitEntrancedate =: date ";
		List<Visit> list = entityManager.createQuery(jpql).setParameter("date", date).getResultList();
		return list;
	}


	@Override
	public List<Visit> findAll() {
		String jpql = "SELECT a FROM Visit a";
		List<Visit> list = entityManager.createQuery(jpql).getResultList();
		
		return list;
	}

	@Override
	public Visit findById(Long visitId) {
		return entityManager.find(Visit.class, visitId);
	}

	@Override
	public void deleteAll() {
		String jpql = "DELETE FROM Visit";
		entityManager.createQuery("DELETE from Physicalcheckup").executeUpdate();
		entityManager.createQuery("DELETE from Visit").executeUpdate();
			
	}

}
