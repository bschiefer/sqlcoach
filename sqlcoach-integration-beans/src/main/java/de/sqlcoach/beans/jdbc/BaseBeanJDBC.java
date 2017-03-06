package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.sqlcoach.db.jdbc.DBConnection;

public abstract class BaseBeanJDBC {
	private final static Logger log = Logger.getLogger(BaseBeanJDBC.class);
	
	protected Connection getConnection(){
		DBConnection cn = null;
		try {
			cn = DBConnection.getConnection();
		} catch (NamingException e) {
			log.error("NamingException: " + e);
		} catch (SQLException e) {
			log.error("SQLException: " + e);
		}
		return cn.getCn();
	}
}
