/**
 * 
 */
package com.sg.classroster.service;

import java.io.IOException;
import java.util.List;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;

/**
 * @author Flavio Silva
 *
 */
public interface ClassRosterServiceLayer {
	
	void createStudent(Student student) throws
			ClassRosterDuplicateIdException,
			ClassRosterDataValidationException,
			ClassRosterPersistenceException;
	
	List<Student> getAllStudents() throws 
			ClassRosterPersistenceException;
	
	Student getStudent(String studentId) throws
			ClassRosterPersistenceException;
	
	Student removeStudent(String sstudentId) throws
			ClassRosterPersistenceException, IOException;
	
}
