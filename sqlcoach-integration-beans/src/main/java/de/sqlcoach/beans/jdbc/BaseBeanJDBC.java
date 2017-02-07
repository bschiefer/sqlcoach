package de.sqlcoach.beans.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import de.sqlcoach.db.jdbc.DBConnection;

public abstract class BaseBeanJDBC {
	protected Connection getConnection(){
		DBConnection cn = null;
		try {
			cn = DBConnection.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn.getCn();
	}
}
