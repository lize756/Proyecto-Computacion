package com.taller1.VarelaFanny.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Visit;

@Repository
public class PersonDao implements IPersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Person entity) {
		entityManager.persist(entity);	
	}

	@Override
    public boolean isSaved(Person entity) {
        return entityManager.contains(entity);
    }
	
	@Override
	public void update(Person entity) {
		entityManager.merge(entity);	
	}

	@Override
	public void delete(Person entity) {
		entityManager.remove(entity);
	}
	
	@Override
	public List<Person> findByDate(Date start, Date end) {
		String jpql = "SELECT a FROM Person a INNER JOIN Visit v On (a.persId = v.person.persId) AND (v.visitEntrancedate BETWEEN : start AND :end OR v.visitExitdate BETWEEN :start AND :end) ORDER BY v.visitEntrancedate ASC";
		List<Person> list = entityManager.createQuery(jpql).setParameter("start", start,TemporalType.DATE).setParameter("end", end,TemporalType.DATE).getResultList();
		return list;	
	}
	
	@Override
	public List<Person> findAll() {
		String jpql = "SELECT a FROM Person a";
		List<Person> list = entityManager.createQuery(jpql).getResultList();
		return list;
	}

	@Override
	public void deleteAll() {
		entityManager.createQuery("DELETE from Physicalcheckup").executeUpdate();
		entityManager.createQuery("DELETE from Visit").executeUpdate();
		String jpql = "DELETE FROM Person";
		entityManager.createQuery(jpql).executeUpdate();
		
	}

	@Override
	public Person findById(Long persId) {
		return entityManager.find(Person.class, persId);
	}

	

}
