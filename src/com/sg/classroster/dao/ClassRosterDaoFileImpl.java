/**
 * 
 */
package com.sg.classroster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.classroster.dto.Student;

/**
 * @author Flavio Silva
 *
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {
	
	public static final String ROSTER_FILE = "roster.txt";
	public static final String DELIMITER = "::";
	
	private Map<String, Student> students = new HashMap<>();
	
	private Student unmarshallStudent(String studentAsText) {
		// studentAsText is expecting a line read in from our file.
	    // For example, it might look like this:
	    // 1234::Ada::Lovelace::Java-September1842
	    //
	    // We then split that line on our DELIMITER - which we are using as ::
	    // Leaving us with an array of Strings, stored in studentTokens.
	    // Which should look like this:
	    // ______________________________________
	    // |    |   |        |                  |
	    // |1234|Ada|Lovelace|Java-September1842|
	    // |    |   |        |                  |
	    // --------------------------------------
	    //  [0]  [1]    [2]         [3]
		String[] studentTokens = studentAsText.split(DELIMITER);
		
		// Given the pattern above, the student Id is in index 0 of the array.
		String studentId = studentTokens[0];
		
		// Which we can then use to create a new Student object to satisfy
		// the requirements of the student constructor.
		Student studentFromFile = new Student(studentId);
		
		// However, there are 3 remaining tokens that need to be set into the
		// new student object. Do this manually by using the appropriate setters.
		
		// Index 1 - FirstName
		studentFromFile.setFirstName(studentTokens[1]);
		
		// Index 2 - LastName
		studentFromFile.setLastName(studentTokens[2]);
		
		// Index 3 - Cohort
		studentFromFile.setCohort(studentTokens[3]);
		
		// We have now created a student! Return it!
		return studentFromFile;
	}
	
	private void loadRoster() throws ClassRosterPersistenceException{
		Scanner scanner;
		
		try {
			// Create Scanner for reading the file
			scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
		}catch(FileNotFoundException e) {
			throw new ClassRosterPersistenceException("-_- Could not load roster data in memory.", e);
		}
		// currentLine hold the most recent line read from the file
		String currentLine;
		// currentStudent holds the most recent student unmarshalled
		Student currentStudent;
		// Go through ROSTER_FILE line by line, decoding each line into a
		// Student object by calling the unmarshallStudent method.
		// Process while we have more lines in the file
		while(scanner.hasNextLine()) {
			// get the next line in the file
			currentLine = scanner.nextLine();
			// unmarshall the line into a Student
			currentStudent = unmarshallStudent(currentLine);
			
			// We are going to use the student id as the map key for our student object.
			// Put currentStudent into the map using student id as the key
			students.put(currentStudent.getStudentId(), currentStudent);
		}
		// close scanner
		scanner.close();
	}
	
	private String marshallStudent(Student aStudent) {
		// We need to turn a Student object into a line of text for our file.
		// For example, we need an in memory object end up like this:
		// 4321::Charles::Babbage::Java-September1842
		
		// It's not a complicated process. Just get out each property,
		// and concatenate with our DELIMITER as a kind of spacer.
		
		// Start with the student id, since that's supposed to be first.
		String studentAsText = aStudent.getStudentId() + DELIMITER;
		
		// Add the rest of the properties in the correct order:
		
		// FirstName
		studentAsText += aStudent.getFirstName() + DELIMITER;
		
		// LastName
		studentAsText += aStudent.getLastName() + DELIMITER;
		
		// Cohort - don't forget to skip the DELIMITER here
		studentAsText += aStudent.getCohort();
		
		// We have now turned a student to text! Return it!
		return studentAsText;
	}
	
	private void writeRoster(Map <String, Student> students) throws ClassRosterPersistenceException, IOException {
        PrintWriter scanner;
        try {
            scanner = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException( "-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        for(Map.Entry<String, Student> thisStudent: students.entrySet()){
            scanner.println(marshallStudent(thisStudent.getValue()));
        }
        scanner.close();
    }
	
	@Override
	public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException, IOException {
		loadRoster();
		Student newStudent = students.put(studentId, student);
		writeRoster(students);
		return newStudent;
	}
	
	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException{
		loadRoster();
		return new ArrayList<Student>(students.values());
	}
	
	@Override
	public Student getStudent(String studentId) throws ClassRosterPersistenceException {
		loadRoster();
		return students.get(studentId);
	}
	
	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersistenceException, IOException {
		loadRoster();
		Student removedStudent = students.remove(studentId);
		writeRoster(students);
		return removedStudent;
	}
	
}
