package de.sqlcoach.db.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.db.entities.Scenario;

/**
 * Class extends methods to manipulate Scenario Entity
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class DBScenario extends DBBase implements DBScenarioService {
	private static final Logger LOG = LoggerFactory.getLogger(DBScenario.class);
	private static final String ENTITYNAME = Scenario.class.getSimpleName();
	private static final String SEQUENCENAME = "S_SCENARIO";

	private void setAppUserNames(List<Scenario> scenarios) {
		String appUserNameTmp = null;
		for (Scenario scenario : scenarios) {
			if(null != scenario.getAppUser().getFirstname()	&& null != scenario.getAppUser().getLastname()) {
				appUserNameTmp = scenario.getAppUser().getFirstname() + " " + scenario.getAppUser().getLastname();
			}

			if (scenario.getAppUser().getTitle() != null) {
				appUserNameTmp = scenario.getAppUser().getTitle() + " " + appUserNameTmp;
			}

			scenario.setAppUserName(appUserNameTmp);
		}
	}

	private void setAppUserName(Scenario scenario) {
		String appUserNameTmp = null;
		if (null != scenario.getAppUser()) {
			if(null != scenario.getAppUser().getFirstname()	&& null != scenario.getAppUser().getLastname()) {
				appUserNameTmp = scenario.getAppUser().getFirstname() + " " + scenario.getAppUser().getLastname();
			}
			
			if (scenario.getAppUser().getTitle() != null) {
				appUserNameTmp = scenario.getAppUser().getTitle() + " " + appUserNameTmp;
			}

			scenario.setAppUserName(appUserNameTmp);
		}
	}

	@Override
	public List<Scenario> getAll() {
		String strQuery = "SELECT s FROM " + ENTITYNAME + " s ORDER BY s.id";
		Query query = getEntityManager().createQuery(strQuery);
		List<Scenario> scenarios = findByQuery(query);
		if (null != scenarios) {
			setAppUserNames(scenarios);
		}

		LOG.info("Query: {} \nSize: {}", strQuery, scenarios.size());
		return scenarios;
	}

	@Override
	public Scenario get(Long id) {
		LOG.info("get ENTER id= {} ", id);

		String strQuery = "SELECT s FROM " + ENTITYNAME + " s WHERE s.id=:id";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("id", id);
		Scenario scenario = findByQuerySingleResult(query);
		if (null != scenario) {
			setAppUserName(scenario);
		}

		LOG.info("Query: {} ", strQuery);
		return scenario;
	}

	@Override
	public Scenario getByDescription(String description) {
		String strQuery = "SELECT s FROM " + ENTITYNAME + " s WHERE s.description=:description";
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("description", description);
		Scenario scenario = findByQuerySingleResult(query);
		setAppUserName(scenario);

		LOG.info("Query: {} \nDescription: {}", description);
		return scenario;
	}

	@Override
	public void insert(Scenario scenario) {
		scenario.setId(generateNextId(SEQUENCENAME));
		scenario.setDateCreate(new Date());
		scenario.setDateLastMod(new Date());
		super.insertT(scenario);
	}

	@Override
	public Scenario update(Scenario scenario) {
		Scenario scenarioTmp = this.get(scenario.getId());
		scenario.setDateCreate(scenarioTmp.getDateCreate());
		scenario.setDateLastMod(new Date());
		return super.updateT(scenario);
	}

	@Override
	public void delete(Scenario scenario) {
		super.deleteT(scenario);
	}
}
