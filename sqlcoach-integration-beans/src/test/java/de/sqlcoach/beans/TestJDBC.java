package de.sqlcoach.beans;

//@RunWith(Arquillian.class)
public class TestJDBC {

//	http://stackoverflow.com/questions/11041418/classnotfoundexception-oracle-jdbc-driver-oracledriver-only-in-servlet-using-ec
//	http://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
//	http://stackoverflow.com/questions/13785508/simple-jdbc-connection-test-with-junit
//	https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
//	http://stackoverflow.com/questions/10089957/explicite-local-ejb-not-injected-with-arquillian
//	http://stackoverflow.com/questions/17806480/arquillian-fails-to-inject-dependencies-after-the-first-test-class
//	https://docs.jboss.org/arquillian/reference/1.0.0.Alpha1/en-US/html_single/#examples.ejb
//	http://www.adam-bien.com/roller/abien/entry/how_to_unit_test_ejb
//	http://stackoverflow.com/questions/27515201/integration-tests-with-ejb-jpa-and-dbunit
//	http://www.oracle.com/technetwork/articles/java/integrationtesting-487452.html
//	http://java-abwaschbar.blogspot.de/2011/02/arquillian-ejb31-unit-testing-with-jta.html
//	http://stackoverflow.com/questions/6469751/testing-an-ejb-with-junit
//	https://www.innoq.com/blog/gs/2008/03/howto_test_ejb3_with_junit_out.html
//	http://arquillian.org/blog/2014/06/11/Testing-with-Aliens-How-to-use-Arquillian-to-test-your-JPA-type-converter/
//	http://arquillian.org/guides/testing_java_persistence/

	
	
	//	@Deployment
//	public static JavaArchive createDeployment() {
//		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test2.jar");
//		jar
//		.addClass(AppUser.class)
//		.addClass(AppStatisticsBeanJDBC.class)
//		.addClass(DBAppStatisticService.class)
//		.addClass(DBAppStatistic.class)
//		.addClass(BaseBeanJDBC.class)
//		.addClass(de.sqlcoach.model.AppUser.class)
//		.addClass(de.sqlcoach.model.AppStatistic.class)
//		.addClass(de.sqlcoach.model.AppStatisticSuccessFail.class)
//		.addClass(de.sqlcoach.db.jdbc.DBConnection.class);
////		jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//		jar.addAsManifestResource("persistence.xml", ArchivePaths.create("persistence.xml"));
////		jar.addAsManifestResource("db.properties", ArchivePaths.create("db.properties"));
////		jar.addAsManifestResource("server.xml", ArchivePaths.create("server.xml"));
//		// jar.addAsManifestResource("ejb-jar.xml", "ejb-jar.xml");
//		System.out.println(jar.toString(true));
//
//		return jar;
//	}
//	
//	@EJB
//	DBAppStatisticService dbAppStatisticService;
//	
//	@Test
//	public void testJDBC() {
//		System.out.println("MPA dbAppStatisticService.get: " + dbAppStatisticService.get(0));
//	}
}