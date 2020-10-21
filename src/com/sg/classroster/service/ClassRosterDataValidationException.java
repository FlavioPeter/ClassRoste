/**
 * 
 */
package com.sg.classroster.service;

/**
 * @author Flavio Silva
 *
 */
public class ClassRosterDataValidationException extends Exception{
	
	public ClassRosterDataValidationException(String message) {
		super(message);
	}
	
	public ClassRosterDataValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
