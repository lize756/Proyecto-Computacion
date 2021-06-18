package com.taller1.VarelaFanny.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;
import com.taller1.VarelaFanny.model.Measurement;


@Repository
public class MeasurementDao implements IMeasurementDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Measurement entity) {
		entityManager.persist(entity);
	}
	
	@Override
    public boolean isSaved(Measurement entity) {
        return entityManager.contains(entity);
    }

	@Override
	public void update(Measurement entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Measurement entity) {
		entityManager.remove(entity);
	}

	@Override
	public List<Measurement> findByDescription(String measDescription) {
		String jpql = "SELECT a FROM Measurement a WHERE a.measDescription = '" + measDescription + "'";
		List<Measurement> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public List<Measurement> findByThreshold(BigDecimal measMinthreshold, BigDecimal measMaxthreshold) {
		final String jpql = "SELECT a FROM Measurement a WHERE a.measMaxthreshold <= :measMaxthreshold AND a.measMinthreshold >= :measMinthreshold";
		return entityManager.createQuery(jpql).setParameter("measMaxthreshold", measMaxthreshold).setParameter("measMinthreshold", measMinthreshold).getResultList();
	}

	@Override
	public List<Measurement> findAll() {
		String jpql = "SELECT a FROM Measurement a";
		List<Measurement> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public Measurement findById(Long measId) {
		return entityManager.find(Measurement.class, measId);
	}

	@Override
	public void deleteAll() {
		String jpql =  "DELETE FROM Measurement";
		entityManager.createQuery(jpql).executeUpdate();
	}

}
