package uis.Frame;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import beans.Assignment;
import beans.Course;
import daos.AssignmentDao;
import daos.CourseDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InfoFrame extends JFrame{
	private static JTextField name;
	private static JTextField id;
	private static JTextField term;
	private static JTextField instructor;
	private static JTextField year;
	private static JTextField credit;
	
	private static JTable table;
	private static JPanel assignInfo;
	
	private CourseDao courseDao = new CourseDao();
	private Course course;
	private String className = null;
	private String classID = null;
	private String classTerm = null;
	private String classYear = null;
	private Float classCredit = null; 
	private int uniqueID;

	private final static String prof = "Christine Papadakis-Kanaris";
	
	private AssignmentDao assignDao = new AssignmentDao();
	private static List<Assignment> assignList = new ArrayList<>();

	private static Vector<Vector<String>> formData = new Vector<>();
	private static String[] categories= {
			"Homework",
			"Midterm",
			"Project",
			"Final",
			"Extra",
			"Attendence",
			"Quiz"
		};
	private static Vector<String> columnNames = new Vector<String>() {{
		getContentPane().add("Assignment Name");
		getContentPane().add("Weight(Graduate)");
		getContentPane().add("Weight(Undergraduate)");
		getContentPane().add("Category");
		getContentPane().add("Comment");
	}};

	
	public InfoFrame(String classid, String classyear, String classterm) {
		setSize(800, 501);	
		
		classID = classid;
		classYear = classyear;
		classTerm = classterm;
		uniqueID = courseDao.getuniqueID(classID, classTerm, classYear);
		course = courseDao.getCourse(uniqueID);
		className = course.getClassName();
		classCredit = course.getClassCredit();
		
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
		
		System.out.println(formData.get(formData.size() - 1));
		String title = null;
		if(!classID.equals(null) && !classYear.equals(null) && !classTerm.equals(null)) {
			title = classID + "_" + classYear + classTerm;
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
		
		name = new JTextField();
		courseInfo.add(name);
		name.setColumns(10);
		name.setText(className);
		
		JLabel label_1 = new JLabel("CourseID");
		label_1.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_1);
		
		id = new JTextField();
		courseInfo.add(id);
		id.setColumns(10);
		id.setText(classID);
		
		JLabel label_2 = new JLabel("Term");
		label_2.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_2);
		
		term = new JTextField();
		courseInfo.add(term);
		term.setColumns(10);
		term.setText(classTerm);
		
		JLabel label_3 = new JLabel("Instructor");
		label_3.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_3);
		
		instructor = new JTextField();
		courseInfo.add(instructor);
		instructor.setColumns(10);
		instructor.setText(prof);
		
		JLabel label_4 = new JLabel("Year");
		label_4.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_4);
		
		year = new JTextField();
		courseInfo.add(year);
		year.setColumns(10);
		year.setText(classYear);
		
		JLabel label_5 = new JLabel("Credit");
		label_5.setForeground(new Color(255, 248, 220));
		courseInfo.add(label_5);
		
		credit = new JTextField();
		courseInfo.add(credit);
		credit.setColumns(10);
		credit.setText("" + classCredit);
		
		
	    table = new JTable(formData, columnNames);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    table.setFillsViewportHeight(true);
	    
	    //set properties of table
	    table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		int assignID = 0;
                Object selectedData = null;
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();
                
                selectedData = table.getValueAt(selectedRow, selectedColumn);
                assignID = assignDao.getID(selectedColumn, (String)selectedData, uniqueID);
                String colName = columnNames.get(selectedColumn);
                Assignment selectAssign = new Assignment();
                selectAssign = assignDao.assignInfo(assignID);
                
                if(selectedColumn != 3) {
                    UpdateFrame uf = new UpdateFrame(selectAssign, colName);
            		uf.setVisible(true);
    				int x = getLocation().x;
    				int y = getLocation().y;
    				uf.setLocation(x, y);
    				uf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
                }else {
                	CatUpFrame cf = new CatUpFrame(selectAssign);
                	cf.setVisible(true);
    				int x = getLocation().x;
    				int y = getLocation().y;
    				cf.setLocation(x, y);
    				cf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
                }
                
              }
        });
        
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
//	    assignInfo.add(panel, BorderLayout.SOUTH);
	    getContentPane().add(panel, BorderLayout.SOUTH);
	    panel.setLayout(new GridLayout(1, 0, 0, 0));
	    
	    JButton refreshbtn = new JButton("Refresh");
	    refreshbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/upFolder.gif")));
	    refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String note = "Update fail!";
				assignList = assignDao.assignList(uniqueID);
				formData.clear();
				for(int i = 0; i < assignList.size(); i++) {
					Vector<String> row = new Vector<>();
					row.add(assignList.get(i).getAssignName());
					row.add("" + assignList.get(i).getWeightG());
					row.add("" +assignList.get(i).getWeightU());
					row.add(assignList.get(i).getAssignCat());
					row.add(assignList.get(i).getComment());
					
					formData.add(row);
				}
				jsp.validate();
				jsp.repaint(); 
				JOptionPane.showMessageDialog(null, "Successfully upadated!", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			});
	    panel.add(refreshbtn);
	    
	    JButton addbtn = new JButton("Add");
	    addbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogFrame df = new DialogFrame(uniqueID);
				df.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				df.setLocation(x, y);
				df.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
	    panel.add(addbtn);
	    
	    JButton delbtn = new JButton("Delete");
	    delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(formData.isEmpty()) {
					//TODO: Create a pop-up window to let user add assignments
//					JOptionPane.showMessageDialog(null, "Empty form!", "Error", JOptionPane.ERROR_MESSAGE);
					String[] options = { "Add Assignments", "Cancel" }; 
					int choice = JOptionPane.showOptionDialog(null, "Empty form!", "Error", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, options, options[0]); 
					if(choice == 0) {
						DialogFrame df = new DialogFrame(uniqueID);
						df.setVisible(true);
						int x = getLocation().x;
						int y = getLocation().y;
						df.setLocation(x, y);
						df.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					} else {
						dispose();
					}
				} else {
					String[] names = new String[table.getRowCount()];
					for(int i=0;i<table.getRowCount();i++){
					  String name= (String)table.getValueAt(i, 0);
					  System.out.println(name);
					  names[i] = name;
					}
					DelFrame df = new DelFrame(uniqueID, names);
					df.setVisible(true);
					int x = getLocation().x;
					int y = getLocation().y;
					df.setLocation(x, y);
					df.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					
					
				}
			}
		});
	    
	    
	    delbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
	    panel.add(delbtn);
	    
	    JButton save = new JButton("Save as template");
	    panel.add(save);
	    
	    JButton startbtn = new JButton("Start/Continue");
	    startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();
				MainFrame mf = new MainFrame(uniqueID);
				mf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				mf.setLocation(x, y);
			}
		});
	    startbtn.setBackground(new Color(204, 255, 204));
	    startbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
	    startbtn.setForeground(new Color(0, 102, 51));
	    panel.add(startbtn);
	    
	    JButton cancelbtn = new JButton("Cancel");
	    cancelbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose.gif")));
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	    panel.add(cancelbtn);
	}
}
	