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
package de.sqlcoach.db.jpa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.beans.DBConnectionService;
import de.sqlcoach.db.entities.Scenario;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.db.executionPlan.Facade;
import de.sqlcoach.util.DBViewResultSet;
import de.sqlcoach.util.MetaTable;
import de.sqlcoach.util.MetaTableColumn;
import de.sqlcoach.util.ViewResultSet;

public class DBMetaData extends DBConnectionBase implements DBConnectionService{
	private static final Logger LOG = LoggerFactory.getLogger(DBMetaData.class);
	private static final String PRIMARY_KEY_TABLE_NAME = "PKTABLE_NAME";
	private static final String PRIMARY_KEY_COLUMN_NAME = "PKCOLUMN_NAME";
	
	/**
	 * Helper for getDatabaseProductName(String dataSourceName)
	 * 
	 * @param connection
	 * @return String
	 * @throws SQLException
	 */
	private String getDatabaseProductName(Connection connection) throws SQLException {
		return connection.getMetaData().getDatabaseProductName();
	}
	
	/**
	 * Deliver selected scenario database product name (for example: Oracle)
	 * 
	 * @param dataSourceName
	 * @return String
	 */
	@Override
	public String getDatabaseProductName(Scenario scenario) {
		String databaseProductName = "";

		EntityManager entityManager = createConnection(scenario);

		try (Connection connection = getConnection(entityManager)) {
			databaseProductName = getDatabaseProductName(connection);
		} catch (SQLException e) {
			LOG.error("getDatabaseProductName: " + e);
		}

		return databaseProductName;
	}
	
	/**
	 * Deliver selected scenario database product version
	 * 
	 * @param dataSourceName
	 * @return String
	 */
	@Override
	public String getDatabaseProductVersion(Scenario scenario) {
		String databaseProductVersion = "";

		EntityManager entityManager = createConnection(scenario);
		try (Connection connection = getConnection(entityManager)) {
			databaseProductVersion = connection.getMetaData().getDatabaseProductVersion();
		} catch (SQLException e) {
			LOG.error("getDatabaseProductVersion: " + e);
		}

		return databaseProductVersion;
	}
	
	/**
	 * Execute query and get metadata from selected scenario database. 
	 * Deliver results in table form for training.jsp
	 * 
	 * @param query
	 * @param dataSourceName
	 * @return ViewResultSet
	 */
	@Override
	public ViewResultSet get(String query, Scenario scenario) throws SQLException {
		EntityManager entityManager = createConnection(scenario);
		ViewResultSet viewResultSet = null;
		try (Connection connection = getConnection(entityManager)) {
			viewResultSet = DBViewResultSet.get(connection, query);
		} catch (SQLException e) {
			LOG.error("get: " + e);
		}

		return viewResultSet;
	}
	
	/**
	 * Execute query and get metadata from execution plan at selected scenario database. 
	 * Deliver results in table form for training.jsp
	 * 
	 * @param query
	 * @param dataSourceName
	 * @return ViewResultSet
	 */
	@Override
	public ViewResultSet getExplainPlan(String query, Scenario scenario) {
		ViewResultSet viewResultSet = null;
		EntityManager entityManager = createConnection(scenario);
		Facade facade = new Facade();
		try (Connection connection = getConnection(entityManager)) {
			String dataBaseProductName = getDatabaseProductName(connection);
			viewResultSet = facade.explain(connection, query, dataBaseProductName);
		} catch (SQLException e) {
			LOG.error("getExplainPlan: " + e);
		}

		return viewResultSet;
	}
	
	/**
	 * Helper for readAllTables(String dataSourceName)
	 * 
	 * @param connection
	 * @return List<MetaTable>
	 * @throws SQLException
	 */
	private List<MetaTable> readAllTables(Connection connection) throws SQLException {
		if (LOG.isInfoEnabled())
			LOG.info("readAllTables, ");
		DatabaseMetaData metadata = connection.getMetaData();
		final List<MetaTable> metaTableCol = new ArrayList<MetaTable>();

		if (LOG.isInfoEnabled()) {
			LOG.info("metadata.getUserName()=" + metadata.getUserName());
		}

		final String[] names = { "TABLE" };
		final ResultSet tableNames = metadata.getTables(null, metadata.getUserName(), "%", names);

		while (tableNames.next()) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Pre Check: tableNames.getString(\"TABLE_NAME\")=" + tableNames.getString("TABLE_NAME"));
			}
			final String oracleHelper = tableNames.getString("TABLE_NAME");
			if (oracleHelper.indexOf("$") == -1) {
				if (LOG.isInfoEnabled()) {
					LOG.info("tableNames.getString(\"TABLE_NAME\")=" + tableNames.getString("TABLE_NAME"));
				}
				final MetaTable metaTable = new MetaTable();
				metaTable.setName(tableNames.getString("TABLE_NAME"));
				metaTable.setColumnCol(readTableColumns(connection, metaTable.getName()));

				metaTableCol.add(metaTable);
			}
		}
		tableNames.close();

		return metaTableCol;
	}

	/**
	 * Deliver metadata in tablename form from selected scenario database 
	 * 
	 * @param dataSourceName
	 * @return List<MetaTable>
	 */
	@Override
	public List<MetaTable> readAllTables(Scenario scenario) {
		EntityManager entityManager = createConnection(scenario);
		List<MetaTable> metaTableCol = new ArrayList<MetaTable>();

		try (Connection connection = getConnection(entityManager)) {
			metaTableCol = readAllTables(connection);
		} catch (SQLException e) {
			LOG.error("readAllTables: " + e);
		}

		return metaTableCol;
	}
	
	/**
	 * Helper for readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, String dataSourceName)
	 * 
	 * @param scenarioTableCol
	 * @param connection
	 * @return List<MetaTable>
	 * @throws SQLException
	 */
	private List<MetaTable> readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, Connection connection)
			throws SQLException {
		if (LOG.isInfoEnabled())
			LOG.info("readByScenarioTableCol, scenarioTableCol size: " + scenarioTableCol.size());

		List<MetaTable> metaTableCol = new ArrayList<MetaTable>();

		for (ScenarioTable scenarioTable : scenarioTableCol) {
			final MetaTable metaTable = new MetaTable();
			metaTable.setName(scenarioTable.getScenarioTable());

			metaTable.setColumnCol(readTableColumns(connection, metaTable.getName()));
			metaTableCol.add(metaTable);
		}
		
		return metaTableCol;
	}

	/**
	 * Get metadata from selected table  
	 * 
	 * @param scenarioTableCol
	 * @param dataSourceName
	 * @return List<MetaTable>
	 */
	@Override
	public List<MetaTable> readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, Scenario scenario) {
		EntityManager entityManager = createConnection(scenario);
		List<MetaTable> metaTableCol = new ArrayList<MetaTable>();

		try (Connection connection = getConnection(entityManager)) {
			metaTableCol = readByScenarioTableCol(scenarioTableCol, connection);
		} catch (SQLException e) {
			LOG.error("readByScenarioTableCol: " + e);
		}

		return metaTableCol;
	}
	
	/**
	 * Get primary keys from selected scenario database 
	 * 
	 * @param connection
	 * @param table
	 * @return HashMap<String, String>
	 * @throws SQLException
	 */
	private HashMap<String, String> getPkMap(Connection connection, String table) throws SQLException {
		HashMap<String, String> pk_map = new HashMap<String, String>();
		try (final ResultSet primaryKeys = connection.getMetaData().getPrimaryKeys(connection.getCatalog(), connection.getMetaData().getUserName(), table)) {
			while (primaryKeys.next()) {
				String pk_name = primaryKeys.getString("COLUMN_NAME");
				if (LOG.isDebugEnabled())
					LOG.debug("primaryKey= " + pk_name);
				pk_map.put(pk_name, pk_name);
			}
		}
		return pk_map;
	}

	/**
	 * Get primary keys from selected scenario database
	 * 
	 * @param connection
	 * @param table
	 * @param fkValue
	 * @return
	 * @throws SQLException
	 */
	private HashMap<String, String> getFkData(Connection connection, String table, String fkValue) throws SQLException {
		final HashMap<String, String> fk_col_map = new HashMap<String, String>();

		try (final ResultSet foreignKeys = connection.getMetaData().getImportedKeys(connection.getCatalog(),
				connection.getMetaData().getUserName(), table)) {
			while (foreignKeys.next()) {
				String fk_key = foreignKeys.getString("FKCOLUMN_NAME");
				String fk_value = foreignKeys.getString(fkValue);
				if (LOG.isDebugEnabled())
					LOG.debug("readTableColumns foreignKey key= " + fk_key + " value= " + fk_value);
				fk_col_map.put(fk_key, fk_value);
			}
		}

		return fk_col_map;
	}

	/**
	 * Helper for readTableColumns(Connection connection, String table)
	 * 
	 * @param connection
	 * @param table
	 * @param pk_map
	 * @param fk_tab_map
	 * @param fk_col_map
	 * @return
	 * @throws SQLException
	 */
	private List<MetaTableColumn> getMetaTableColumns(Connection connection, String table, HashMap<String, String> pk_map,
			HashMap<String, String> fk_tab_map, HashMap<String, String> fk_col_map) throws SQLException {
		List<MetaTableColumn> metaTableColumnCol = new ArrayList<MetaTableColumn>();

		try (final ResultSet columns = connection.getMetaData().getColumns(connection.getCatalog(),
				connection.getMetaData().getUserName(), table, "%")) {
			while (columns.next()) {
				MetaTableColumn metaTableColumn = new MetaTableColumn();
				metaTableColumn.setName(columns.getString("COLUMN_NAME"));
				metaTableColumn.setDatatype(columns.getString("TYPE_NAME"));
				metaTableColumn.setDatasize(columns.getInt("COLUMN_SIZE"));
				metaTableColumn.setDigits(columns.getInt("DECIMAL_DIGITS"));
				int nullable = columns.getInt("NULLABLE");
				metaTableColumn.setNullable(nullable == 1);

				metaTableColumnCol.add(metaTableColumn);

				String col_name = metaTableColumn.getName();

				if (pk_map.containsKey(col_name)) {
					metaTableColumn.setPrimaryKey(true);
				}

				String fk_col_val = fk_col_map.get(col_name);
				if (fk_col_val != null) {
					metaTableColumn.setForeignKeyColumnName(fk_col_val);
					metaTableColumn.setForeignKeyTableName(fk_tab_map.get(col_name));
				}
			}
		}

		return metaTableColumnCol;
	}

	/**
	 * Collect all metadata from selected scenario database
	 * 
	 * @param connection
	 * @param table
	 * @return List<MetaTableColumn>
	 * @throws SQLException
	 */
	private List<MetaTableColumn> readTableColumns(Connection connection, String table) throws SQLException {
		if (LOG.isInfoEnabled()) {
			LOG.info("readTableColumns ENTER table=" + table);
			LOG.info("readTableColumns  catalog=" + connection.getCatalog() + " user=" + connection.getMetaData().getUserName());
		}
		
		table = table.toUpperCase();
		List<MetaTableColumn> metaTableColumnCol = new ArrayList<MetaTableColumn>();

		HashMap<String, String> pk_map = getPkMap(connection, table);
		HashMap<String, String> fk_tab_map = getFkData(connection, table, PRIMARY_KEY_TABLE_NAME);
		HashMap<String, String> fk_col_map = getFkData(connection, table, PRIMARY_KEY_COLUMN_NAME);

		metaTableColumnCol = getMetaTableColumns(connection, table, pk_map, fk_tab_map, fk_col_map);

		return metaTableColumnCol;
	}

	/**
	 * do nothing, @see DBConnectionBean(module: sqlcoach-ejb)
	 */
	@Override
	public void testConnection(Scenario scenario) {
	}
}
