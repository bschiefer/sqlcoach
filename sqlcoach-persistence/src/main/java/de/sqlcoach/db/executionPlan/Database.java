package de.sqlcoach.db.executionPlan;

import java.sql.Connection;

import de.sqlcoach.util.ViewResultSet;

/**
 * Parent Database class with explain, execute methods for all childs 
 * 
 * @author Michael Paulus
 * @version 1.0
 */
public abstract class Database {
	public abstract ViewResultSet explain (String query, Connection cn);
	public abstract ViewResultSet execute (String query, Connection cn);
}
