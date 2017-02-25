package de.sqlcoach.ejb;

import java.util.List;

import javax.ejb.Local;

import de.sqlcoach.db.entities.Scenario;

@Local
public interface TestJPAEjbLocal {
	public List<Scenario> getAllEntries();
	public String getTest();
}