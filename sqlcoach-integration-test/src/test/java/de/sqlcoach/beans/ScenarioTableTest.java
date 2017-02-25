package de.sqlcoach.beans;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.jdbc.ScenarioTableBeanJDBC;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.remoteEJB.DBRemoteEJBClient;
import de.sqlcoach.remoteEJB.ModulName;

public class ScenarioTableTest {
	private DBScenarioTableService scenarioTableJPA = null;
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService scenarioTableJDBC = null;

	public DBScenarioTableService getJPA() {
		if (null == scenarioTableJPA) {
			scenarioTableJPA = DBRemoteEJBClient.getEJB(DBScenarioTableService.class.getName(), DBScenarioTableService.BEANNAME, ModulName.JPA);
		}
		return scenarioTableJPA;
	}

	public de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService getJDBC() {
		if (null == scenarioTableJDBC) {
			scenarioTableJDBC = DBRemoteEJBClient.getEJB(de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService.class.getName(), ScenarioTableBeanJDBC.class.getSimpleName(), ModulName.JDBC);
		}
		return scenarioTableJDBC;
	}
	
	/**
	 * TODO
	 * scenarioTableJDBC liefert null, sollte mehrere Ergebnisse liefern
	 * muesste auch in jpa auf List geaendert werden, falls benutzt 
	 */
	@Test
	public void testGet() {
//		List<ScenarioTable> scenarioTables = getJPA().getAll();
//		Long id = scenarioTables.get(0).getScenario().getId();
//		ScenarioTable scenarioTableJPA = getJPA().get(id);
//		de.sqlcoach.model.ScenarioTable scenarioTableJDBC = getJDBC().get(id.intValue()); 
//
//		assertEquals(scenarioTableJPA.getDateCreate(), scenarioTableJDBC.getDatecreate());
//		assertEquals(scenarioTableJPA.getDateLastMod(), scenarioTableJDBC.getDatelastmod());
//		assertEquals(scenarioTableJPA.getScenario().getId(), Long.valueOf(scenarioTableJDBC.getScenarioId()));
//		assertEquals(scenarioTableJPA.getScenarioTable(), scenarioTableJDBC.getScenarioTable());
	}
	
	@Test
	public void testGetByScenarioId() {
		List<ScenarioTable> scenarioTables = getJPA().getAll();
		Long id = scenarioTables.get(0).getScenario().getId();
		List<ScenarioTable> scenarioTablesJPA = getJPA().getByScenarioId(id);
		List<de.sqlcoach.model.ScenarioTable> scenarioTablesJDBC = getJDBC().getByScenarioId(id.toString());
 
		assertEquals(scenarioTablesJPA.size(), scenarioTablesJDBC.size());
	}
	
	@Test
	public void testGetAll() {
		List<ScenarioTable> scenarioTablesJPA = getJPA().getAll();
		List<de.sqlcoach.model.ScenarioTable> scenarioTablesJDBC = getJDBC().getAll();
 
		assertEquals(scenarioTablesJPA.size(), scenarioTablesJDBC.size());
	}
}
