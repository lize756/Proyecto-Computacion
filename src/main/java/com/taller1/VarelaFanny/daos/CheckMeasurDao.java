package com.taller1.VarelaFanny.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;

@Repository
public class CheckMeasurDao implements ICheckMeasurDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(CheckMeasur entity) {
		entityManager.persist(entity);	
	}
	
	@Override
    public boolean isSaved(CheckMeasur entity) {
        return entityManager.contains(entity);
    }

	@Override
	public void update(CheckMeasur entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(CheckMeasur entity) {
		entityManager.remove(entity);		
	}

	@Override
	public List<CheckMeasur> findAll() {
		String jpql = "SELECT a FROM CheckMeasur a";
		List<CheckMeasur> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public CheckMeasur findById(CheckMeasurPK id) {
		return entityManager.find(CheckMeasur.class, id);
	}

	@Override
	public void deleteAll() {
		String jpql = "DELETE FROM CheckMeasur";
		entityManager.createQuery(jpql).executeUpdate();
		
	}

}
