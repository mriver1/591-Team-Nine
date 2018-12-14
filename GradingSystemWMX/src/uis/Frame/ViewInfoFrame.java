package uis.Frame;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import beans.Assignment;
import beans.Course;
import daos.AssignmentDao;
import daos.CourseDao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewInfoFrame extends JFrame{
	private CourseDao courseDao = new CourseDao();
	private Course course;
	private String className = "";
	private String classID = "";
	private String classTerm = "";
	private String classYear = "";
	private Float classCredit = (float) 0.0; 
	private int uniqueID;
	private AssignmentDao assignDao = new AssignmentDao();
	private static List<Assignment> assignList = new ArrayList<>();
	String title = "";
	private static Vector<Vector<String>> formData = new Vector<>();
	
	private static Vector<String> columnNames = new Vector<String>() {{
		add("Assignment Name");
		add("Weight(Graduate)");
		add("Weight(Undergraduate)");
		add("Category");
		add("Comment");
	}};

	private final static String prof = "Christine Papadakis-Kanaris";
	public ViewInfoFrame(Course course) {
		
		setSize(800, 501);	
		this.course = course;
		this.className = course.getClassName();
		this.classID = course.getClassID();
		this.classTerm = course.getClassTerm();
		this.classYear = course.getClassYear();
		this.classCredit = course.getClassCredit();
		this.uniqueID = course.getUniqueID();
		
		assignList = assignDao.assignList(uniqueID);	
		for(int i = 0; i < assignList.size(); i++) {
			Vector<String> row = new Vector<>();
			row.add(assignList.get(i).getAssignName());
			row.add("" + assignList.get(i).getWeightG());
			row.add("" +assignList.get(i).getWeightU());
			row.add(assignList.get(i).getAssignCat());
			row.add(assignList.get(i).getComment());
			
			formData.add(row);
		}
		
		setTitle(title);

		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel courseInfo = new JPanel();
		courseInfo.setBackground(new Color(128, 0, 0));
		getContentPane().add(courseInfo, BorderLayout.NORTH);
		courseInfo.setLayout(new GridLayout(3, 4, 5, 2));
		
		JLabel label = new JLabel("Course Name");
		label.setForeground(new Color(255, 248, 220));
		courseInfo.add(label);
		
		JLabel name = new JLabel(className);
		name.setForeground(new Color(255, 248, 220));
		courseInfo.add(name);
		
		JLabel label_1 = new JLabel("CourseID");
		label_1.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_1);
		
		JLabel id = new JLabel(classID);
		id.setForeground(new Color(255, 248, 220));
		courseInfo.add(id);
		
		JLabel label_2 = new JLabel("Term");
		label_2.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_2);
		
		JLabel term = new JLabel(classTerm);
		term.setForeground(new Color(255, 248, 220));
		courseInfo.add(term);
		
		JLabel label_3 = new JLabel("Instructor");
		label_3.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_3);
		
		JLabel instructor = new JLabel(prof);
		instructor.setForeground(new Color(255, 248, 220));
		courseInfo.add(instructor);
		
		JLabel label_4 = new JLabel("Year");
		label_4.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_4);
		
		JLabel year = new JLabel(classYear);
		year.setForeground(new Color(255, 248, 220));
		courseInfo.add(year);
		
		JLabel label_5 = new JLabel("Credit");
		label_5.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_5);
		
		JLabel credit = new JLabel("" + classCredit);
		label_5.setForeground(new Color(255, 248, 220));
		courseInfo.add(credit);
		
	    JTable table = new JTable(formData, columnNames);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    table.setFillsViewportHeight(true);
	    
	    //set properties of table
	    table.setCellSelectionEnabled(false);
	    table.setPreferredSize(getPreferredSize());
	    table.setForeground(Color.BLACK);                   
	    table.setFont(new Font(null, Font.PLAIN, 14));     
	    table.setSelectionForeground(Color.DARK_GRAY);     
	    table.setSelectionBackground(Color.LIGHT_GRAY);   
	    table.setGridColor(Color.GRAY);     
	    
	    table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  
	    table.getTableHeader().setForeground(Color.DARK_GRAY);                
	    table.getTableHeader().setResizingAllowed(true);              
	    table.getTableHeader().setReorderingAllowed(false);
	    
	    table.setRowHeight(30);
	    JScrollPane jsp = new JScrollPane(table);
	    jsp.setViewportView(table);
	    getContentPane().add(jsp, BorderLayout.CENTER);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(128, 0, 0));
	    getContentPane().add(panel, BorderLayout.SOUTH);
	    
	    JButton btnNewButton = new JButton("Back");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);
	    panel.add(btnNewButton);
	}
	
	

}
