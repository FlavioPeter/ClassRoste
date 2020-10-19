/**
 * 
 */
package com.sg.classroster.dao;

import java.io.IOException;
import java.util.List;

import com.sg.classroster.dto.Student;

/**
 * @author Flavio Silva
 *
 */
public interface ClassRosterDao {
	/**
	 * Adds the given student to the roster and associates it with the given
	 * student id. If there is already a student associated with the given
	 * student id it will return that student object, otherwise it will return null.
	 * 
	 * @param studentId id with which student is to be associated
	 * @param student student to be added to roster
	 * @return the Student object previously associated with the given
	 * student id if it exists, null otherwise.
	 */
	Student addStudent(String studentId, Student student) throws ClassRosterDaoException, IOException;
	
	/**
	 * Return a List of all students in the roster.
	 * 
	 * @return List containing all student in the roster.
	 */
	List<Student> getAllStudents() throws ClassRosterDaoException;
	
	/**
	 * Returns the student object associated with the given student id.
	 * Return null if no such student exists
	 * 
	 * @param studentId ID of the student to retrieve
	 * @return the Student object associated with the given student id.
	 * null if no such student exists
	 */
	Student getStudent(String studentId) throws ClassRosterDaoException;
	
	/**
	 * Removes from the roster the student associated with the given id.
	 * Returns the student object that is being removed or null if
	 * there is no student associated with the given id
	 * 
	 * @param studentId id of student to be removed
	 * @return Student object that was removed or null if no student
	 * was associated with the given student id
	 */
	Student removeStudent(String studentId) throws ClassRosterDaoException, IOException;
}
