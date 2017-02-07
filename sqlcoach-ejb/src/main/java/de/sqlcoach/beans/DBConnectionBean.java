package de.sqlcoach.beans;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.util.ViewResultSet;
import de.sqlcoach.db.entities.ScenarioTable;
import de.sqlcoach.db.execution_plan.Facade;
import de.sqlcoach.util.DBViewResultSet;
import de.sqlcoach.util.MetaTable;
import de.sqlcoach.util.MetaTableColumn;

/**
 * Stateless Bean serves as interface between persistence-modul and ejb-modul
 * 
 * @author Michael Paulus
 * @version 1.0
 * 
 *          https://developer.jboss.org/thread/228555
 */
@Stateless
@Remote(DBConnectionService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class DBConnectionBean implements DBConnectionService {

	private static final Logger LOG = LoggerFactory.getLogger(DBConnectionBean.class);
	private static final String PERSISTENCE_UNIT_NAME = "scenariodb";
	private static final String PRIMARY_KEY_TABLE_NAME = "PKTABLE_NAME";
	private static final String PRIMARY_KEY_COLUMN_NAME = "PKCOLUMN_NAME";

	private static Map<String, EntityManagerFactory> entityManagerFactories = new HashMap<>();
	
	/**
	 * Deliver connectionProperties to create EntityManagerFactory for scenario database
	 * (see scenario table)
	 * 
	 * @param jndiName
	 * @return Map<String, String>
	 */
	private Map<String, String> getConnectionProperties(String jndiName) {
		Map<String, String> connectionProperties = new HashMap<>();
		
		connectionProperties.put("javax.persistence.jtaDataSource", jndiName);
		connectionProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		// connectionProperties.put("hibernate.dialect",
		// "org.hibernate.dialect.SAPDBDialect");
		connectionProperties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
		
		return connectionProperties;
	}

	/**
	 * create EntityManagerFactory for scenario database
	 * 
	 * @param jndiName
	 * @return EntityManager
	 */
	private EntityManager createConnection(String jndiName) {
		Map<String, String> connectionProperties = new HashMap<>();
		connectionProperties = getConnectionProperties(jndiName);
		

		if (entityManagerFactories.containsKey(jndiName)) {
			LOG.info("createConnection entityManagerFactory exists with jta Datasource: " + jndiName);
		} else {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,
					connectionProperties);

			entityManagerFactories.put(jndiName, entityManagerFactory);
			LOG.info("createConnection entityManagerFactory does not exists and create with jta Datasource: " + jndiName);
		}

		return entityManagerFactories.get(jndiName).createEntityManager();
	}

	/**
	 * get connection for selected scenario database and execute query as native query 
	 * 
	 * @param sqlString
	 * @param jndiName
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeQuery(String sqlString, String jndiName) {
		List<Object[]> list = null;

		EntityManager entityManager = createConnection(jndiName);
		Query query = entityManager.createNativeQuery(sqlString);
		list = query.getResultList();

		return list;
	}

	/**
	 * Get Connection from selected scenario EntityManager 
	 * 
	 * @param entityManager
	 * @return Connection
	 */
	private Connection getConnection(EntityManager entityManager) {
		Session hibernateSession = entityManager.unwrap(Session.class);
		SessionImpl sessionImpl = (SessionImpl) hibernateSession;
		Connection connection = sessionImpl.connection();

		return connection;
	}

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
	public String getDatabaseProductName(String dataSourceName) {
		String databaseProductName = "";

		EntityManager entityManager = createConnection(dataSourceName);

		try (Connection connection = getConnection(entityManager)) {
			databaseProductName = getDatabaseProductName(connection);
		} catch (SQLException e) {
			e.printStackTrace();
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
	public String getDatabaseProductVersion(String dataSourceName) {
		String databaseProductVersion = "";

		EntityManager entityManager = createConnection(dataSourceName);
		try (Connection connection = getConnection(entityManager)) {
			databaseProductVersion = connection.getMetaData().getDatabaseProductVersion();
		} catch (SQLException e) {
			e.printStackTrace();
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
	public ViewResultSet get(String query, String dataSourceName) throws SQLException {
		EntityManager entityManager = createConnection(dataSourceName);
		ViewResultSet viewResultSet = null;
		try (Connection connection = getConnection(entityManager)) {
			viewResultSet = DBViewResultSet.get(connection, query);
		} catch (SQLException e) {
			e.printStackTrace();
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
	public ViewResultSet getExplainPlan(String query, String dataSourceName) {
		ViewResultSet viewResultSet = null;
		EntityManager entityManager = createConnection(dataSourceName);
		Facade facade = new Facade();
		try (Connection connection = getConnection(entityManager)) {
			String dataBaseProductName = getDatabaseProductName(connection);
			viewResultSet = facade.explain(connection, query, dataBaseProductName);
		} catch (SQLException e) {
			e.printStackTrace();
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
	public List<MetaTable> readAllTables(String dataSourceName) {
		EntityManager entityManager = createConnection(dataSourceName);
		List<MetaTable> metaTableCol = new ArrayList<MetaTable>();

		try (Connection connection = getConnection(entityManager)) {
			metaTableCol = readAllTables(connection);
		} catch (SQLException e) {
			e.printStackTrace();
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
	public List<MetaTable> readByScenarioTableCol(Collection<ScenarioTable> scenarioTableCol, String dataSourceName) {
		EntityManager entityManager = createConnection(dataSourceName);
		List<MetaTable> metaTableCol = new ArrayList<MetaTable>();

		try (Connection connection = getConnection(entityManager)) {
			metaTableCol = readByScenarioTableCol(scenarioTableCol, connection);
		} catch (SQLException e) {
			e.printStackTrace();
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
}
