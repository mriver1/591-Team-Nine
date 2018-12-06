package ui.Panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import beans.Course;

public class CourseTitlePanel extends JPanel{
	private static JTextField name;
	private static JTextField id;
	private static JTextField term;
	private static JTextField instructor;
	private static JTextField year;
	private static JTextField credit;
	
	private static Course courseInfo;
	private static String nameStr = "";
	private static String IDStr = "";
	private static String termStr = "";
	private static String yearStr = "";
	private static String creditStr = "";
	private final static String prof = "Christine Papadakis-Kanaris";
	
	public CourseTitlePanel() {
		setLayout(new GridLayout(3, 4, 5, 2));
		
		JLabel label = new JLabel("Course Name");
		add(label);
		
		name = new JTextField();
		name.setColumns(10);
		name.setText(nameStr);
		add(name);
		
		JLabel label_1 = new JLabel("CourseID");
		add(label_1);
		
		id = new JTextField();
		id.setColumns(10);
		id.setText(IDStr);
		add(id);
		
		JLabel label_2 = new JLabel("Term");
		add(label_2);
		
		term = new JTextField();
		term.setColumns(10);
		term.setText(termStr);
		add(term);
		
		JLabel label_3 = new JLabel("Instructor");
		add(label_3);
		
		instructor = new JTextField();
		instructor.setColumns(10);
		instructor.setText(prof);
		add(instructor);
		
		JLabel label_4 = new JLabel("Year");
		add(label_4);
		
		year = new JTextField();
		year.setColumns(10);
		year.setText(yearStr);
		add(year);
		
		JLabel label_5 = new JLabel("Credit");
		add(label_5);
		
		credit = new JTextField();
		credit.setColumns(10);
		credit.setText(creditStr);
		add(credit);
		
	}
	
	public static void initClassData (Course course) {
		courseInfo = course;
				
		IDStr = course.getClassID();
		nameStr = course.getClassName();
		yearStr = course.getClassYear();
		termStr = course.getClassTerm();
		creditStr = "" + course.getClassCredit();
	}

	public static Course getCourseInfo() {
		return courseInfo;
	}
}
