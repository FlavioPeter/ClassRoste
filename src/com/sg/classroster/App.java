/**
 * 
 */
package com.sg.classroster;

import java.io.IOException;

import com.sg.classroster.controller.ClassRosterController;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

/**
 * @author Flavio Silva
 *
 */
public class App {

	public static void main(String[] args) throws IOException {
		UserIO myIo = new UserIOConsoleImpl();
		ClassRosterView myView = new ClassRosterView(myIo);
		ClassRosterDao myDao = new ClassRosterDaoFileImpl();
		ClassRosterController controller = new ClassRosterController(myDao, myView);
		controller.run();
	}
}
