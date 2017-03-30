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