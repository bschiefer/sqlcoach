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
package de.sqlcoach.beans.jdbc;

import java.util.List;

import javax.ejb.Stateless;

import de.sqlcoach.beans.jdbc.interfaces.DBScenarioService;
import de.sqlcoach.db.jdbc.DBScenario;
import de.sqlcoach.model.Scenario;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 */
@Stateless
public class ScenarioBeanJDBC extends BaseBeanJDBC implements DBScenarioService {

	@Override
	public Scenario get(String id) {
		return DBScenario.get(getConnection(), id);
	}

	@Override
	public Scenario getByDescription(String description) {
		return DBScenario.getByDescription(getConnection(), description);
	}

	@Override
	public List<Scenario> getAll() {
		return DBScenario.getAll(getConnection());
	}

	@Override
	public int add(Scenario model) {
		return DBScenario.add(getConnection(), model);
	}

	@Override
	public int update(Scenario model) {
		return DBScenario.update(getConnection(), model);
	}

	@Override
	public int delete(Scenario model) {
		return DBScenario.delete(getConnection(), model);
	}
}
