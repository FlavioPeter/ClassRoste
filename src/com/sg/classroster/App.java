/**
 * 
 */
package com.sg.classroster;

import java.io.IOException;

import com.sg.classroster.controller.ClassRosterController;
import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.service.ClassRosterServiceLayerImpl;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

/**
 * @author Flavio Silva
 *
 */
public class App {

	public static void main(String[] args) throws IOException {
		// Instatiate the UserIO implementation
		UserIO myIo = new UserIOConsoleImpl();
		// Instatiate the View and wire the UserIO implementation into it
		ClassRosterView myView = new ClassRosterView(myIo);
		// Instatiate DAO
		ClassRosterDao myDao = new ClassRosterDaoFileImpl();
		// Instatiate the Audit DAO
		ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
		// Instatiate the Service Layer and wire the DAO and Audit DAO into it 
		ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
		// Instatiate the Controller and wire the Service Layer into it
		ClassRosterController controller = new ClassRosterController(myService, myView);
		// Kick off the Controller
		controller.run();
	}
}
