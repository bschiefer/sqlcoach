package de.sqlcoach.db.execution_plan;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.util.ViewResultSet;

/**
 * MySQL class create explain and execute statement for database MySQL  
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class MySQL extends Database {

	private static final Logger LOG = LoggerFactory.getLogger(MySQL.class);

	public MySQL() {
		LOG.debug("enter MySQL");
	}
	
	@Override
	public ViewResultSet explain(String query, Connection cn) {
		ViewResultSet viewResultSet = null;
		
		return viewResultSet;
	}

	@Override
	public ViewResultSet execute(String query, Connection cn) {
		ViewResultSet viewResultSet = null;
		
		return viewResultSet;
	}
}
