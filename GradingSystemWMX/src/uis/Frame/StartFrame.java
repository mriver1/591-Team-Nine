package uis.Frame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import beans.Course;
import daos.CourseDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StartFrame extends JFrame{
	private String selectedClass = "Empty template";
	private Course course; 
	private String[] showList;
	private CourseDao courseDao = new CourseDao();
	private List<String> classID;
	private List<Course> courseList;
	
	private String id;
	private String year;
	private String term;
	
	
	public StartFrame(Course newCourse) {
		
		
	}
	public StartFrame() {
		getContentPane().setBackground(new Color(128, 0, 0));
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(450,300);
		
		JLabel lblNewLabel = new JLabel("Select the template to start...");
		lblNewLabel.setForeground(new Color(255, 248, 220));
		lblNewLabel.setFont(new Font("Toppan Bunkyu Gothic", Font.PLAIN, 18));
		lblNewLabel.setBackground(new Color(128, 0, 0));
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		
		initData();
		JList list = new JList(showList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(list);
		
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list.getSelectedIndices();
                ListModel<String> listModel = list.getModel();
                for (int index : indices) {
                	selectedClass = listModel.getElementAt(index);
                	setSelectedClass(selectedClass);
                    System.out.println("Selected: " + index + " = " + listModel.getElementAt(index));
                }
                System.out.println();
            }


        });
		

		list.setSelectedIndex(0);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton confirmbtn = new JButton("Confirm");
		confirmbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();			
				
				String selectID = "";
				String selecteTerm = "";
				String selecteYear = "";
				
				selectedClass = (String)list.getSelectedValue();
				System.out.println(selectedClass);
				if(!selectedClass.equals("Empty template")) {
					
					String[] selected = selectedClass.split("_");
					selectID = selected[0];
					String rest = selected[1];
					selecteYear = rest.substring(0, 4);
					selecteTerm = rest.substring(4);
				} 
				InfoFrame info = new InfoFrame(selectID, selecteYear, selecteTerm);
				info.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				info.setLocation(x, y);
				
			}

		});
		
		panel.add(confirmbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login lf = new Login();
				lf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				lf.setLocation(x, y);
			}
		});
		panel.add(cancelbtn);
	}

	
	protected void setSelectedClass(String selectedClass2) {
		// TODO Auto-generated method stub
		this.selectedClass = selectedClass2;
		
	}
	private void initData() {
		// TODO Auto-generated method stub
		courseList = courseDao.courseImport();
		showList = new String[courseList.size() + 1];
		showList[0] = "Empty template";
		for(int i = 1; i < showList.length; i++) {
			String id = courseList.get(i - 1).getClassID();
			String term = courseList.get(i - 1).getClassTerm();
			String year = courseList.get(i - 1).getClassYear();
			showList[i] = id + "_" + year + term;
		}
	}

	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StartFrame frame = new StartFrame();
//					frame.setSize(702,621);;
//					frame.setLocationRelativeTo(null);
//					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

}
