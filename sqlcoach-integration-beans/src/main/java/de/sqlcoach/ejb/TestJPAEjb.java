package de.sqlcoach.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.sqlcoach.db.entities.Scenario;

@Stateless
public class TestJPAEjb implements TestJPAEjbLocal {
	@PersistenceContext(unitName = "applicationdb")
	private EntityManager entityManager;
	
	public List<Scenario> getAllEntries() {
		return entityManager.createQuery("select s from Scenario s").getResultList();
	}
	
	public String getTest(){
		return "StringBla";
	}
}