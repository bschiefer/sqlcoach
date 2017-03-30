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
/*
 * Created on 09.11.2006 at 03:20:58
 *
 * Florian Moritz, Chistoph Gerstle
 *
 * Media Management and Production
 * University of Applied Sciences Kaiserslautern
 * License: LGPL - GNU Lesser General Public License - http://www.gnu.org/licenses/lgpl.html
 */

package de.sqlcoach.db.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.wildfly.security.manager.action.GetSystemPropertiesAction;

/**
 * The Class DBConnection. This class is a Wrapper for java.util.Connection
 * 
 * @author Bernhard Schiefer
 * @version 0.1
 */
public class DBConnection implements AutoCloseable {

	@Override
	public String toString() {
		return "[dataSource=" + dataSourceName + " connectionCnt=" + connectionCnt + "]";
	}

	/** The log. */
	private static final Log log = LogFactory.getLog(DBConnection.class);

	/** The PROPERTIE s_ FILE. */
	// private static final String PROPERTIES_FILE =
	// "src/de/fhkl/sqlcoach/properties/db.properties";
	private static final String PROPERTIES_FILE = "db.properties";

	/** The SQLCOAC h_ DBA. */
	public static final String SQLCOACH_DBA = "SQLCOACH_DBADS";

	/** The SQLCOAC h_ USER. */
	// public static String SQLCOACH_USER = "SQLUEBDS";

	/** The connection counter. */
	private static Map<String, Integer> connectionCounter = new HashMap<String, Integer>();

	private Connection cn; //
	private int connectionCnt; // Value of connectionCounter when created
	private String dataSourceName;

	/**
	 * Instantiates a new DB object.
	 */
	private DBConnection(Connection cn, int connectionCnt, String dataSourceName) {
		this.cn = cn;
		this.connectionCnt = connectionCnt;
		this.dataSourceName = dataSourceName;
	}

	/**
	 * provides a Connection object to handle database actions.
	 * 
	 * @return Connection
	 * 
	 * @throws Exception
	 *           *
	 * @throws NamingException
	 *           the naming exception
	 * @throws SQLException
	 *           the SQL exception
	 */
	public static DBConnection getConnection() throws NamingException, SQLException {
		// decide to use connection pool or a simple connection with a
		// properties file

		return getPoolConnection(SQLCOACH_DBA);
	}

	public static DBConnection getConnection(String dataSourceName) throws NamingException, SQLException {
		// decide to use connection pool or a simple connection with a
		// properties file
		return getPoolConnection(dataSourceName);
	}

	/**
	 * Gets the pool connection.
	 * 
	 * @return the pool connection
	 * 
	 * @throws NamingException
	 *           the naming exception
	 * @throws SQLException
	 *           the SQL exception
	 */
	protected static DBConnection getPoolConnection(String dataSourceName) throws NamingException, SQLException {
		final Context initCtx = new InitialContext();
		final Context envCtx = (Context) initCtx.lookup("java:comp/env");

		final String name = "jdbc/" + dataSourceName;

		DBConnection dbconn = null;

		Integer connectionCnt = DBConnection.connectionCounter.get(dataSourceName);
		if (connectionCnt == null)
			connectionCnt = 0;

		if (log.isInfoEnabled()) {
			log.info("getPoolConnection ENTER [dataSource=" + dataSourceName + " connectionCnt=" + connectionCnt + "]");
		}

		// final DataSource ds = (DataSource)envCtx.lookup(name);
		// if (ds == null) {
		// log.error ("getPoolConnection : No DataSource for "+ name+ " !");
		// return null;
		// } else {
		// if (log.isDebugEnabled())
		// log.debug("getPoolConnection : DataSource: "+ds.toString());
		// }

		// final Connection cn = ds.getConnection();
		final Connection cn = getSimpleConnection(dataSourceName);
		if (cn == null) { // Failed !
			log.error("getPoolConnection : No Connection for " + name + " ! (connectionCnt:" + connectionCnt + ")");
			return null;
		} else { // Success
			DBConnection.connectionCounter.put(dataSourceName, connectionCnt++);
			dbconn = new DBConnection(cn, connectionCnt, dataSourceName);
			if (log.isDebugEnabled())
				log.debug("getPoolConnection : Connection: " + cn.toString());
		}

		cn.setAutoCommit(false);

		// Set Oracle date format to YYYY-MM-DD
		// final java.sql.DatabaseMetaData dm = cn.getMetaData();
		// final String prodname = dm.getDatabaseProductName();
		// if (prodname.equalsIgnoreCase("Oracle")) { // Only for Oracle !!
		// try (Statement s = cn.createStatement()) {
		// s.execute("ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD'");
		// if (log.isDebugEnabled())
		// log.debug("getPoolConnection : ProductName=" + prodname + "\nALTER
		// SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD'");
		// } catch (SQLException e) {
		// log.warn("getPoolConnection : ProductName="+prodname+"\nALTER SESSION SET
		// NLS_DATE_FORMAT = 'YYYY-MM-DD': ", e);
		// }
		// } else {
		// if (log.isDebugEnabled())
		// log.debug("getPoolConnection : Kein Oracle cn= "+cn+ "
		// ProductName="+prodname);
		// }

		if (log.isInfoEnabled())
			log.info("getPoolConnection LEAVE dbconn=" + dbconn);

		return dbconn;
	}

	/**
	 * Gets the simple connection.
	 * 
	 * @return the simple connection
	 */
	public static Connection getSimpleConnection(String dataSourceName) {

		log.info("getSimpleConnection ENTER ");
		String driver = "com.sap.dbtech.jdbc.DriverSapDB";
		String url = "jdbc:sapdb://localhost/SQLCOACH";
		String user = "SQLCOACH_DBA";
		String password = "sqlcoach";

		Connection cn = null;
		// final Properties props = new Properties();
		// try {
		// props.load(new FileInputStream(PROPERTIES_FILE));
		// } catch (FileNotFoundException e) {
		// log.error("getSimpleConnection : FileNotFoundException: \n" + e);
		// } catch (IOException e) {
		// log.error("getSimpleConnection : IOException: \n" + e);
		// }

		try {
			Class.forName("com.sap.dbtech.jdbc.DriverSapDB").newInstance();
		} catch (InstantiationException e1) {
			log.error("getSimpleConnection : Class.forName InstantiationException:\n" + e1);
		} catch (IllegalAccessException e1) {
			log.error("getSimpleConnection : Class.forName IllegalAccessException:\n" + e1);
		} catch (ClassNotFoundException e1) {
			log.error("getSimpleConnection : Class.forName ClassNotFoundException:\n" + e1);
		}

		/**
		 * driver=com.sap.dbtech.jdbc.DriverSapDB
		 * url=jdbc:sapdb://localhost/sqlcoach user=SQLCOACH_DBA password=test
		 */

		// final String usr = props.getProperty("user");
		// final String pwd = props.getProperty("password");
		// final String url = props.getProperty("url");

		try {
			cn = DriverManager.getConnection(url, user, password);
			// cn = DriverManager.getConnection("jdbc: sapdb: // localhost/SQLCOACH");
			cn.setAutoCommit(false);
		} catch (Exception e) {
			log.error("getSimpleConnection : problems while establishing the connection:\n", e);
		}
//		int connectionCnt = connectionCounter.get(dataSourceName);
//		connectionCounter.put(dataSourceName, connectionCnt++);
		return cn;
	}

	/**
	 * Close connection.
	 * 
	 * @param cn
	 *          the cn
	 */
	@Override
	public void close() throws Exception {
		if (log.isInfoEnabled())
			log.info("close Connection ENTER cn=" + this);

		if (cn == null) {
			log.error("close Connection cn == NULL! this=" + this);
			return;
		}
		int connectionCnt = DBConnection.connectionCounter.get(dataSourceName);
		DBConnection.connectionCounter.put(dataSourceName, connectionCnt--);
		try {
			cn.close();
			cn = null;
		} catch (SQLException sqle) {
			log.error("close Connection() SQLException this=" + this + "\ncn=" + cn, sqle);
		}
	}

	public Connection getCn() {
		return cn;
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		if (log.isInfoEnabled())
			log.info("getMetaData ENTER cn=" + this);
		return cn.getMetaData();
	}

	public void commit() throws SQLException {
		if (log.isInfoEnabled())
			log.info("commit ENTER cn=" + this);
		cn.commit();
	}

	public void rollback() throws SQLException {
		if (log.isInfoEnabled())
			log.info("rollback ENTER cn=" + this);
		cn.rollback();
	}
}
