package de.sqlcoach.beans;
import java.util.List;

import org.junit.Test;

import de.sqlcoach.beans.DBScenarioService;
import de.sqlcoach.db.entities.Scenario;

public class ScenarioTest {
	
	@Test
	public void test() {
		Scenario scenarioJPA = null;
		de.sqlcoach.model.Scenario scenarioJDBC = null;
		
		List<Scenario> scenariosJPA = null;
		List<de.sqlcoach.model.Scenario> scenariosJDBC = null;
		
		if(null != lookUpScenarioJPA() && null != lookUpScenarioJDBC()) {
			String id = "";
//			scenarioJPA = lookUpScenarioJPA().get(id);
			scenarioJDBC = lookUpScenarioJDBC().get(id);
			scenarioJDBC.toString().equals(scenarioJPA.toString());
			
			String description = "";
			scenarioJPA = lookUpScenarioJPA().getByDescription(description);
			scenarioJDBC = lookUpScenarioJDBC().getByDescription(description);
			scenarioJDBC.toString().equals(scenarioJPA.toString());
			
			scenariosJPA = lookUpScenarioJPA().getAll();
			scenariosJDBC = lookUpScenarioJDBC().getAll();
			scenariosJDBC.toString().equals(scenariosJPA.toString());
		}
	}
	
	private DBScenarioService lookUpScenarioJPA() {
		return null;
	}
	
	private de.sqlcoach.beans.jdbc.interfaces.DBScenarioService lookUpScenarioJDBC() {
		return null;
	}

}
