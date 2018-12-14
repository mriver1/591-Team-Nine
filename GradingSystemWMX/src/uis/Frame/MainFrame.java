package uis.Frame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import beans.Assignment;
import beans.Course;
import beans.Grade;
import beans.Student;
import daos.AssignmentDao;
import daos.CourseDao;
import daos.EnrollDao;
import daos.GradesDao;
import daos.StudentDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame implements ActionListener{
	private final JButton refreshbtn = new JButton("Refresh");
	private final JButton cancelbtn = new JButton("Cancel");
	private final JButton newbtn = new JButton("New");
	private final JButton logoutbtn = new JButton("Log out");
	private final JButton viewbtn = new JButton("View Course Information");
	private JTable studentform;
	private GradesDao gradesDao = new GradesDao();
	private Grade grade;
	
	private CourseDao courseDao = new CourseDao();
	private Course course = new Course();
	private int classUniqueID;
	private String className = null;
	private String classID = null;
	private String classTerm = null;
	private String classYear = null;
	private Float classCredit = null; 
	
	private AssignmentDao assignDao = new AssignmentDao();
	private Assignment assign = new Assignment();
	private List<Assignment> assignList = new ArrayList<>();
	
	private StudentDao studentDao = new StudentDao();
	private Student student = new Student();
	private List<Student> studentList = new ArrayList<>();
	
	private static Vector<Vector<Object>> studentData = new Vector<>();
	private static Vector<Object> column;
	private final JPanel panel_2 = new JPanel();
	private final JButton addbtn = new JButton("Add student");
	private final JButton delbtn = new JButton("Delete student");
	private final JPanel panel_3 = new JPanel();
	private final JButton performbtn = new JButton("View Performance");
	private final JPanel panel_1 = new JPanel();
	
	private EnrollDao enrollDao = new EnrollDao();
	private final JTextField viewID = new JTextField();
	private final JTextField viewFn = new JTextField();
	private JTextField viewLn = new JTextField();
	private JTextField viewMajor = new JTextField();
	String[] statusList = {"Graduate", "Undergraduate"};
	private JComboBox statusSelect = new JComboBox(statusList);
	private final JLabel lblNewLabel = new JLabel("BUID");
	private final JLabel lblNewLabel_1 = new JLabel("First Name");
	private final JLabel lblNewLabel_2 = new JLabel("Last Name");
	private final JLabel lblNewLabel_3 = new JLabel("Major");
	private final JLabel lblNewLabel_4 = new JLabel("Status");

	
	public MainFrame(int classUniqueID) {
		viewFn.setColumns(10);
		viewLn.setColumns(10);
		viewMajor.setColumns(10);
		statusSelect.setSelectedIndex(0);
		setSize(1000, 1000);
		
		this.classUniqueID = classUniqueID;
		course = courseDao.getCourse(classUniqueID);
		className = course.getClassName();
		classID = course.getClassID();
		classTerm = course.getClassTerm();
		classYear = course.getClassYear();
		classCredit = course.getClassCredit();
		
		assignList = assignDao.assignList(classUniqueID);
		String title = classID + "_" + classYear + classTerm + " Grading Form";
		setTitle(title);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		column = new Vector<>();
//		column.add("");
		column.add("BUID");
		column.add("FirstName");
		column.add("LastName");
		column.add("Major");
		column.add("isGraduate");
		for(Assignment assign : assignList) {
			column.add(assign.getAssignName());
		}
		column.add("Total");
		column.add("LetterGrade");

	    
	    studentList = studentDao.getStudentlist(classUniqueID);

	    for(Student student : studentList) {
	    	System.out.println(student.getFirstName());
		    Vector<Object> row = new Vector<>();
//		    row.add(new JButton());
	    	row.add(student.getBUID());
	    	row.add(student.getFirstName());
	    	row.add(student.getLastName());
	    	row.add(student.getMajor());
	    	if(student.getStatus()) {
	    		row.add(new Boolean(true));
	    	}else {
	    		row.add(new Boolean(false));
	    	}	 
	    	for(Assignment assign : assignList) {
	    		Double g = gradesDao.getGrade(student.getUniqueId(), classUniqueID, assign.getAssignID());
	    		System.out.println(g);
	    		if(g == null) {
		    		boolean flag = gradesDao.updateTable(student.getUniqueId(), classUniqueID, assign.getAssignID());
	    		}
	    		row.add(g);
	    		//row.add(new ImageIcon(MainFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
	    	}
	    	
	    	Double total = 0.0;
	    	String letter = "";
	    	try {
	    		total = studentDao.getTotal(student.getUniqueId());
	    		letter = getLetter(total);
	    	}catch(NullPointerException e) {
	    		e.printStackTrace();
	    		total = 0.0;
	    	}
    		row.add(total);
    		row.add(letter);
	    	studentData.add(row);
	    	
	    }
	    System.out.println(studentData);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(20, 3, 0, 0));		

		DefaultTableModel tableModel = new DefaultTableModel(studentData, column);
        studentform = new JTable(tableModel);
//		JTable studentform = new JTable(studentData,column);
	    studentform.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    	    
	    studentform.setPreferredSize(getPreferredSize());
	    studentform.setForeground(Color.BLACK);                   
	    studentform.setFont(new Font(null, Font.PLAIN, 14));     
	    studentform.setSelectionForeground(Color.DARK_GRAY);     
	    studentform.setSelectionBackground(Color.LIGHT_GRAY);   
	    studentform.setGridColor(Color.GRAY);     
	    studentform.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(studentform);
		scrollPane.setViewportView(studentform);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		studentform.setFillsViewportHeight(true);
		studentform.setCellSelectionEnabled(true);
		studentform.setSelectionBackground(Color.YELLOW);
		
		studentform.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentform.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int selectedRow = studentform.getSelectedRow(); 
                Object oaID = tableModel.getValueAt(selectedRow, 0);
                Object obFN = tableModel.getValueAt(selectedRow, 1);
                Object ocLN = tableModel.getValueAt(selectedRow, 2);
                Object odM = tableModel.getValueAt(selectedRow, 3);
                Object oeStatus = tableModel.getValueAt(selectedRow, 4);
                viewID.setText(oaID.toString());
                viewFn.setText(obFN.toString());
                viewLn.setText(ocLN.toString());
                viewMajor.setText(odM.toString());
                statusSelect.setSelectedItem(oeStatus.toString());
                
            }
        });
		
		//update
	    ListSelectionModel cellSelectionModel = studentform.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				boolean isAdjusting = e.getValueIsAdjusting(); 
				Object selectedData = null;
                int selectedRow = studentform.getSelectedRow();
                int selectedColumn = studentform.getSelectedColumn();
                Student s = new Student();
                selectedData = studentform.getValueAt(selectedRow, selectedColumn);
                System.out.println("selectedrow" + selectedRow);
                String buid = (String)studentform.getValueAt(selectedRow, 0);
            	int id = studentDao.getIDbyBUID(buid);
            	s = studentDao.getStudent(id);
                if(selectedColumn >= 5 && selectedColumn < assignList.size() + 5) {
        			int assignID = assignDao.getIDbyName(classUniqueID, (String)column.get(selectedColumn));
        			String assignName = (String)column.get(selectedColumn);
                	if(isAdjusting) {
                		addGradeFrame a = new addGradeFrame(assignName, id, classUniqueID, assignID);
                    	a.setVisible(true);
        				int x = getLocation().x;
        				int y = getLocation().y;
        				a.setLocation(x, y);
        				a.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 				
                	}        	
                	
                }else if(selectedColumn == column.indexOf("Total") || selectedColumn == column.indexOf("LetterGrade")) {

                	if(isAdjusting) {
                		
                		Double total = calculate(s, assignList);
                		studentform.setValueAt(total, selectedRow, column.indexOf("Total"));
//        				total = getUpdatedData();
        				boolean flag = studentDao.updateTotal(total, s.getUniqueId());
        				String letter = getLetter(total);
                		//TODO customize
                		studentform.setValueAt(letter, selectedRow, column.indexOf("LetterGrade"));
                		flag = studentDao.updateLetter(letter, s.getUniqueId());
                		if(!flag) {
                			JOptionPane.showMessageDialog(null, "Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                }
			}
        	
        });
        
        
		refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				JOptionPane.showMessageDialog(null, "Successfully upadated!", "Info", JOptionPane.INFORMATION_MESSAGE);
		
			}
			
		});
		
		panel.add(refreshbtn);
		panel.add(viewbtn);
		viewbtn.setBackground(new Color(210, 105, 30));
		panel.add(performbtn);
		panel.add(cancelbtn);
		lblNewLabel.setForeground(new Color(255, 248, 220));
		panel.add(lblNewLabel);
		panel.add(viewID);
		lblNewLabel_1.setForeground(new Color(255, 248, 220));
		panel.add(lblNewLabel_1);
		panel.add(viewFn);
		lblNewLabel_2.setForeground(new Color(255, 248, 220));
		panel.add(lblNewLabel_2);
		panel.add(viewLn);
		lblNewLabel_3.setForeground(new Color(255, 248, 220));
		panel.add(lblNewLabel_3);

		panel.add(viewMajor);
		lblNewLabel_4.setForeground(new Color(255, 248, 220));
		panel.add(lblNewLabel_4);

		panel.add(statusSelect);
//		viewID.setColumns(10);
		panel.add(addbtn);
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int selectedRow = studentform.getSelectedRow();
                if(selectedRow!=-1) 
                {
                	String buid = (String) tableModel.getValueAt(selectedRow, 0);
                	
//                    tableModel.removeRow(selectedRow);
                    int sid = studentDao.getIDbyBUID(buid);
                    boolean flag1 = studentDao.removeStudent(buid);
                    boolean flag2 = enrollDao.removeEnroll(sid, classUniqueID);
    				boolean flag3 = gradesDao.removeGrade(sid, classUniqueID);
    				
    				if(!flag1 || !flag2 || !flag3) {
    					JOptionPane.showMessageDialog(null, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
    				}else {
    					update();
    					JOptionPane.showMessageDialog(null, "Delete Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
    				}
                   
                }
			}
		});
		panel.add(delbtn);
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//return to the preview page to select the template or start a new one
				//pop-up window to make sure she would like to cancel the pj without save
				dispose();
				StartFrame sf = new StartFrame();
				sf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				sf.setLocation(x, y);
				sf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
			}
		});
		performbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerformFrame pf = new PerformFrame(studentList);
				pf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				pf.setLocation(x, y);
				pf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 	
			}
		});
		
		viewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();
				ViewInfoFrame vi = new ViewInfoFrame(course);
				vi.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				vi.setLocation(x, y);
				vi.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		panel_3.setBackground(new Color(139, 0, 0));
		getContentPane().add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
//		newbtn.setBackground(new Color(128, 0, 0));
		panel_3.add(newbtn);
//		logoutbtn.setBackground(new Color(210, 105, 30));
		panel_3.add(logoutbtn);
		
	
		//add student
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String)statusSelect.getSelectedItem();
				Boolean st = true;
				if(s == "Graduate") {
					st = true;
				}else {
					st = false;
				}
				Object []rowValues = {viewID.getText(),viewFn.getText(),viewLn.getText(),
						viewMajor.getText(), new Boolean(st)};
				Student newStudent = new Student();
				newStudent.setBUID(viewID.getText());
				newStudent.setFirstName(viewFn.getText());
				newStudent.setLastName(viewLn.getText());
				newStudent.setMajor(viewMajor.getText());
				newStudent.setStatus(st);
				
//				setStudent(newStudent);
				boolean flag = studentDao.addStudent(newStudent);
				int studentID = studentDao.getIDbyBUID(newStudent.getBUID());
				newStudent.setUniqueId(studentID);
				boolean flag1= enrollDao.updateEnroll(classUniqueID, studentID);
				
				update();
				if(!flag || !flag1) {
					JOptionPane.showMessageDialog(null, "Add failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Add Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
                viewID.setText("");
                viewFn.setText("");
                viewLn.setText("");
                viewMajor.setText("");
                statusSelect.setSelectedIndex(0);

			}
		});
		
		
		logoutbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//go to the login page
				dispose();
				Login login = new Login();
				login.setVisible(true);
				login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
			
		});
		newbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//turn to the interface: create the pj (select the template or create a new one)
				dispose();
				StartFrame sf = new StartFrame();
				sf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				sf.setLocation(x, y);
				sf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
	    		studentform.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  
	    		studentform.getTableHeader().setForeground(Color.DARK_GRAY);                
	    		studentform.getTableHeader().setResizingAllowed(true);              
	    		studentform.getTableHeader().setReorderingAllowed(false);
	
	}

	protected Double calculate(Student s, List<Assignment> assignList) {
		// TODO Auto-generated method stub
		Double total = 0.0;
		Double weight;
		for(Assignment assign : assignList) {
			if(s.getStatus()) {
				weight = assign.getWeightG();
			}else {
				weight = assign.getWeightU();
			}
			Double g = gradesDao.getGrade(s.getUniqueId(), classUniqueID, assign.getAssignID());
    		System.out.println(classUniqueID + s.getUniqueId() + " " + assign.getAssignID() + " " + g);
    		if(g != null) {
    			total += (g * weight);
    		}

		}
		return total;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
	
	public String getLetter(Double total) {
		String letter = null;
		//TODO customize
		if(total >= 90) {
			letter = "A";
    		
		}else if(total < 90 && total >=80){
			letter = "B";
			
		}else if(total < 80 && total >= 70) {
			letter = "C";
			
		}else {
			letter = "D";
		}
		
		return letter;
	}
	
	private void update(){
		studentList.clear();
		studentData.clear();
		studentList = studentDao.getStudentlist(classUniqueID);	

	    for(Student student : studentList) {
		    Vector<Object> row = new Vector<>();
	    	row.add(student.getBUID());
	    	row.add(student.getFirstName());
	    	row.add(student.getLastName());
	    	row.add(student.getMajor());
	    	if(student.getStatus()) {
	    		row.add(new Boolean(true));
	    	}else {
	    		row.add(new Boolean(false));
	    	}	 
	    	for(Assignment assign : assignList) {
	    		Double g = gradesDao.getGrade(student.getUniqueId(), classUniqueID, assign.getAssignID());
	    		if(g == null) {
		    		boolean flag = gradesDao.updateTable(student.getUniqueId(), classUniqueID, assign.getAssignID());
	    		}
	    		row.add(g);
	    	}
	    	
	    	Double total = 0.0;
	    	String letter = "";
	    	try {
	    		total = studentDao.getTotal(student.getUniqueId());
	    		letter = getLetter(total);
	    	}catch(NullPointerException e1) {
	    		e1.printStackTrace();
	    		total = 0.0;
	    	}
    		row.add(total);
    		row.add(letter);
	    	studentData.add(row);

	    }
    	DefaultTableModel tableModel = new DefaultTableModel(studentData, column);
    	studentform.setModel(tableModel);
    	studentform.setEnabled(true);
	}
}
