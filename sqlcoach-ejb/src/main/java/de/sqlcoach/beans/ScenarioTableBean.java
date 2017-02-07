package de.sqlcoach.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.sqlcoach.db.jpa.DBScenarioTable;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
@Remote(DBScenarioTableService.class)
public class ScenarioTableBean extends DBScenarioTable implements DBScenarioTableService {

	@PersistenceContext(unitName = "applicationdb")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
}
