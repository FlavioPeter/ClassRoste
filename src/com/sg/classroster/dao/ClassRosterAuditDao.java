/**
 * 
 */
package com.sg.classroster.dao;

/**
 * @author Flavio Silva
 *
 */
public interface ClassRosterAuditDao {
	
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
	
}
