package de.sqlcoach.beans;
import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.DBScenarioTableService;
import de.sqlcoach.db.entities.ScenarioTable;

public class ScenarioTableTest {
	
	@Test
	public void test() {
		ScenarioTable scenarioTableJPA = null;
		de.sqlcoach.model.ScenarioTable scenarioTableJDBC = null;
		
		List<ScenarioTable> scenarioTablesJPA = null;
		List<de.sqlcoach.model.ScenarioTable> scenarioTablesJDBC = null;
		
		if(null != lookUpScenarioTableJPA() && null != lookUpScenarioTableJDBC()) {
//			scenarioTableJPA = lookUpScenarioTableJPA().get(0);
			scenarioTableJDBC = lookUpScenarioTableJDBC().get(0);
			scenarioTableJDBC.toString().equals(scenarioTableJPA.toString());
			
			String id = "";
//			scenarioTablesJPA = lookUpScenarioTableJPA().getByScenarioId(id);
			scenarioTablesJDBC = lookUpScenarioTableJDBC().getByScenarioId(id);
			scenarioTablesJDBC.toString().equals(scenarioTablesJPA.toString());
			
			scenarioTablesJPA = lookUpScenarioTableJPA().getAll();
			scenarioTablesJDBC = lookUpScenarioTableJDBC().getAll();
			scenarioTablesJDBC.toString().equals(scenarioTablesJPA.toString());
		}
	}
	
	private DBScenarioTableService lookUpScenarioTableJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioTableService lookUpScenarioTableJDBC() {
		return null;
	}

}
