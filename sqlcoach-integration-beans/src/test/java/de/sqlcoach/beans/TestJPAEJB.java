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

import java.util.List;

import javax.ejb.EJB;

//import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.sqlcoach.beans.jdbc.AppStatisticsBeanJDBC;
import de.sqlcoach.beans.jdbc.BaseBeanJDBC;
import de.sqlcoach.beans.jdbc.interfaces.DBAppStatisticService;
import de.sqlcoach.db.entities.AppUser;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.jdbc.DBAppStatistic;
import de.sqlcoach.ejb.TestJPAEjb;
import de.sqlcoach.ejb.TestJPAEjbLocal;

@RunWith(Arquillian.class)
public class TestJPAEJB {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar");
		jar.addClass(TestJPAEjbLocal.class).addClass(TestJPAEjb.class)
		.addClass(Scenario.class)
		.addClass(AppUser.class)
		.addClass(AppStatisticsBeanJDBC.class)
		.addClass(DBAppStatisticService.class)
		.addClass(DBAppStatistic.class)
		.addClass(BaseBeanJDBC.class)
		.addClass(de.sqlcoach.model.AppUser.class)
		.addClass(de.sqlcoach.model.AppStatistic.class)
		.addClass(de.sqlcoach.model.AppStatisticSuccessFail.class)
		.addClass(de.sqlcoach.db.jdbc.DBConnection.class);
//		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		jar.addAsManifestResource("persistence.xml", ArchivePaths.create("persistence.xml"));
//		jar.addAsManifestResource("db.properties", ArchivePaths.create("db.properties"));
//		jar.addAsManifestResource("server.xml", ArchivePaths.create("server.xml"));
		// jar.addAsManifestResource("ejb-jar.xml", "ejb-jar.xml");
		System.out.println(jar.toString(true));

		return jar;
	}
	
//	@EJB(mappedName="java:global/test/TestJPAEjb!de.sqlcoach.ejb.TestJPAEjbLocal")
	@EJB
	TestJPAEjbLocal testJPAEjbLocal;
	
	@EJB
	DBAppStatisticService dbAppStatisticService;

	@Test
	public void testJPA() {
		System.out.println("MPA : " + testJPAEjbLocal.getTest());
		List<Scenario> scenarios = testJPAEjbLocal.getAllEntries();
		System.out.println("MPA scenario size: " + scenarios.size());
		for (Scenario scenario : scenarios) {
			System.out.println("MPA scenario: " + scenario);
		}
	}
	
	@Test
	public void testJDBC() {
//		System.out.println("MPA dbAppStatisticService.get: " + dbAppStatisticService.get(0));
	}
}