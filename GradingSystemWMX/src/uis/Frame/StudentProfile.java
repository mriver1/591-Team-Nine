package uis.Frame;

import java.util.*;
import java.util.List;

import beans.Assignment;
import beans.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentProfile extends JFrame {

	private JPanel contentPane;
	private JLabel lblStudentProfileName;
	private JLabel lblStudentProfileId;
	private JLabel lblMajor;
	private JLabel lblStatus;
	private JLabel lblStudentProfile;
	private JLabel lblUxxxxxxx;
	private JLabel lblComputerScience;
	private JLabel lblUndergraduate;
	private JLabel lblComments;

	
	private String name = ""; 
	private String ID = ""; 
	private String major = ""; 
	private String status = ""; 
	private JTable table;

	private Vector<String> columnNames = new Vector<String>(){{
		add("Assignment");
		add("Weight");
		add("Grade");
		add("Comment");
	}};
	private Vector<Vector<Object>> data = new Vector<>();
	/**
	 * 
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public StudentProfile(Student s, List<Assignment> assignList) {
		
		initData(s, assignList);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblStudentProfileName = new JLabel("Student Name: ");
		lblStudentProfileName.setBounds(6, 5, 96, 16);
		contentPane.add(lblStudentProfileName);
		
		lblStudentProfile = new JLabel(name); // change to student.Name
		lblStudentProfile.setBounds(133, 5, 200, 16);
		contentPane.add(lblStudentProfile);
		
		lblStudentProfileId = new JLabel("Student ID: ");
		lblStudentProfileId.setBounds(6, 26, 96, 16);
		contentPane.add(lblStudentProfileId);
		
		lblUxxxxxxx = new JLabel(ID); // change to student.Id
		lblUxxxxxxx.setBounds(133, 26, 200, 16);
		contentPane.add(lblUxxxxxxx);
		
		lblMajor = new JLabel("Major: ");
		lblMajor.setBounds(6, 47, 96, 16);
		contentPane.add(lblMajor);
		
		lblComputerScience = new JLabel(major); // change to student.Major
		lblComputerScience.setBounds(133, 47, 200, 16);
		contentPane.add(lblComputerScience);
		
		lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(6, 68, 96, 16);
		contentPane.add(lblStatus);
		
		lblUndergraduate = new JLabel(status); // change to student.Status
		lblUndergraduate.setBounds(133, 68, 200, 16);
		contentPane.add(lblUndergraduate);
		
		lblComments = new JLabel("Comments");
		lblComments.setBounds(293, 5, 74, 16);
		contentPane.add(lblComments);
		
		JTextPane txtpnComment = new JTextPane();
		txtpnComment.setBounds(293, 26, 133, 101);
		contentPane.add(txtpnComment);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(176, 246, 96, 26);
		contentPane.add(btnSave);
		
		
		JTable info = new JTable(data, columnNames);
		//contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(18, 135, 408, 101);
		info.setFillsViewportHeight(true);
		contentPane.add(scrollPane);
	}

	private void initData(Student s, List<Assignment> assignList) {
		// TODO Auto-generated method stub
		this.name = s.getFirstName() + s.getLastName();
		this.ID = s.getBUID();
		this.major = s.getMajor();
		if(s.getStatus()) {
			this.status = "Graduate";
		}else {
			this.status = "Undergraduate";
		}
		for(int i = 0; i < assignList.size(); i++) {
			Vector<Object> row = new Vector<>();
			Assignment a = assignList.get(i);
			row.add(a.getAssignName());
			if (this.status.equals("Graduate")) {
				row.add(a.getWeightG());
			}else {
				row.add(a.getWeightU());
			}
			
			//TODO GRADE
			row.add(null);			
		}
	}
}

