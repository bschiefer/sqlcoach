/*******************************************************************************
 * This file is part of SQLCoach.
 *
 * SQLCoach is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SQLCoach is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.sqlcoach.beans;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.util.MetaTable;
import de.sqlcoach.util.ViewResultSet;

/**
 * Local Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public interface DBConnectionService {
	public static final String BEANNAME = "DBConnectionBean";
	public void testConnection(Scenario scenario);
	public List<Object[]> executeQuery(String sqlString, Scenario scenario);
	public ViewResultSet get(String query, Scenario scenario) throws SQLException;
	public ViewResultSet getExplainPlan(String query, Scenario scenario);
	public List<MetaTable> readAllTables(Scenario scenario);
	public List<MetaTable> readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, Scenario scenario);
	public String getDatabaseProductName(Scenario scenario);
	public String getDatabaseProductVersion(Scenario scenario);
}
