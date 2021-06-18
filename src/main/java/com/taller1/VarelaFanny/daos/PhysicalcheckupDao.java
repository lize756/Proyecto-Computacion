package com.taller1.VarelaFanny.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;
import com.taller1.VarelaFanny.model.Physicalcheckup;

@Repository
public class PhysicalcheckupDao implements IPhysicalcheckupDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Physicalcheckup entity) {
		entityManager.persist(entity);
	}
	
	@Override
    public boolean isSaved(Physicalcheckup entity) {
        return entityManager.contains(entity);
    }


	@Override
	public void update(Physicalcheckup entity) {
		entityManager.merge(entity);	
	}

	@Override
	public void delete(Physicalcheckup entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public List<Physicalcheckup> findByVisit(Long visitId) {
		String jpql = "SELECT a FROM Physicalcheckup a WHERE a.visit.visitId = '"+ visitId +"'" ;
		List<Physicalcheckup> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Physicalcheckup> findByDate(Date phycheDate) {
		String jpql = "SELECT a FROM Physicalcheckup a WHERE a.phycheDate =: phycheDate" ;
		List<Physicalcheckup> list = entityManager.createQuery(jpql).setParameter("phycheDate", phycheDate,TemporalType.DATE).getResultList();
		return list;
	}

	@Override
	public List<Physicalcheckup> findAll() {
		String jpql = "SELECT a FROM Physicalcheckup a";
		List<Physicalcheckup> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public Physicalcheckup findById(Long phycheId) {
		return entityManager.find(Physicalcheckup.class, phycheId);
	}

	@Override
	public void deleteAll() {
		String jpql = "DELETE FROM Physicalcheckup";
		entityManager.createQuery(jpql).executeUpdate();
		
	}

}
