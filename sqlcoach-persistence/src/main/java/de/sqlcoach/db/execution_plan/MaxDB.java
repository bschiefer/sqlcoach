package de.sqlcoach.db.execution_plan;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqlcoach.util.DBViewResultSet;
import de.sqlcoach.util.ViewResultSet;

/**
 * MaxDB class create explain and execute statement for database Sap DB  
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public class MaxDB extends Database{

	private static final Logger LOG = LoggerFactory.getLogger(MaxDB.class);
	
	private static final String EXPLAIN = "explain ";
	
	public MaxDB() {
		LOG.debug("enter MaxDB");
	}
	
	//TODO ViewResult, evtl. in Fasade
	public ViewResultSet explain(String query, Connection cn) {
		ViewResultSet viewResultSet = null;
		String sqlString = EXPLAIN + query;
		LOG.info("MPA sqlString:" + sqlString);
	
		viewResultSet = execute(sqlString, cn);
		
		return viewResultSet;
	}

	public ViewResultSet execute(String query, Connection cn) {
		ViewResultSet viewResultSet = null;
		try {
			viewResultSet = DBViewResultSet.get(cn, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return viewResultSet;
	}
}
