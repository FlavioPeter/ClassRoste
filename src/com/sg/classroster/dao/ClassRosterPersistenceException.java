/**
 * 
 */
package com.sg.classroster.dao;

/**
 * @author Flavio Silva
 *
 */
public class ClassRosterPersistenceException extends Exception {
	
	public ClassRosterPersistenceException(String message) {
		super(message);
	}
	
	public ClassRosterPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}
