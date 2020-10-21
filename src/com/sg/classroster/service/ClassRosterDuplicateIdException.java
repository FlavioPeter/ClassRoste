/**
 * 
 */
package com.sg.classroster.service;

/**
 * @author Flavio Silva
 *
 */
public class ClassRosterDuplicateIdException extends Exception {
	
	public ClassRosterDuplicateIdException(String message){
		super(message);
	}
	
	public ClassRosterDuplicateIdException(String message, Throwable cause) {
		super(message, cause);
	}
}
