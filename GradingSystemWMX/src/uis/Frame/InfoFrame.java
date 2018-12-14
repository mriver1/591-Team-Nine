package uis.Frame;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import beans.Assignment;
import beans.Course;
import beans.Student;
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
	private static JTextField instructor;
	private static JTextField year;
	private static JTextField credit;
	
	private static JTable table;
	private static JPanel assignInfo;
	
	private CourseDao courseDao = new CourseDao();
	private Course course;
	private String className = "";
	private String classID = "";
	private String classTerm = "";
	private String classYear = "";
	private Float classCredit = (float) 0.0; 
	private int uniqueID;
	
	String title = "";

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
	private static String[] terms = {"SPRING", "FALL", "SUMMER", "WINTER"};
	private  Vector<String> columnNames = new Vector<String>() {{
	add("Assignment Name");
	add("Weight(Graduate)");
	add("Weight(Undergraduate)");
	add("Category");
	add("Comment");
	}};
	private JTextField nametv;
	private JTextField wgtv;
	private JTextField wutv;

	
	public InfoFrame(String classid, String classyear, String classterm) {
		setSize(800, 501);	
		if(classid.equals("") || classyear.equals("") || classterm.equals("")) {
			title = "Empty template";
		} else {
			classID = classid;
			classYear = classyear;
			classTerm = classterm;
			title = classID + "_" + classYear + classTerm;
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
		}
		

		
		//System.out.println(formData.get(formData.size() - 1));
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
		
		JComboBox comboBox = new JComboBox(terms);
		comboBox.setSelectedItem(classterm.toUpperCase().toString());
		courseInfo.add(comboBox);
		
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
		
		DefaultTableModel tableModel = new DefaultTableModel(formData, columnNames);
		table = new JTable(tableModel);
//	    table = new JTable(formData, columnNames);
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
	    
	    JPanel panel_1 = new JPanel();
	    FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
	    flowLayout.setAlignment(FlowLayout.TRAILING);
	    getContentPane().add(panel_1, BorderLayout.SOUTH);
	    
	    JButton refreshbtn = new JButton("Refresh");
	    panel_1.add(refreshbtn);
	    refreshbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/upFolder.gif")));
	    
	    JButton save = new JButton("Save as template");
	    panel_1.add(save);
	    
	    JButton startbtn = new JButton("Start/Continue");
	    panel_1.add(startbtn);
	    startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dispose();
				List<Assignment> list = assignDao.assignList(uniqueID);
				Double totalWeightG = 0.0;
				Double totalWeightU = 0.0;
				for(Assignment ass : list) {
					totalWeightG += ass.getWeightG();
					totalWeightU += ass.getWeightU();
				}
				if(totalWeightG == 1.0 && totalWeightU == 1.0) {
					if(!title.equals("Empty template")) {
						MainFrame mf = new MainFrame(uniqueID);
						mf.setVisible(true);
						int x = getLocation().x;
						int y = getLocation().y;
						mf.setLocation(x, y);
					}else {
						JOptionPane.showMessageDialog(null, "Please save this new class first", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "The total weight is not 1.0, please modify assignments!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
	    startbtn.setBackground(new Color(204, 255, 204));
	    startbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
	    startbtn.setForeground(new Color(0, 102, 51));
	    
	    JButton cancelbtn = new JButton("Cancel");
	    panel_1.add(cancelbtn);
	    cancelbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose.gif")));
	    cancelbtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    save.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
//	    		dispose();
	    		Course newCourse = new Course();
	    		Float newCredit = Float.parseFloat(credit.getText().toString());
	    		String newID = id.getText().toString();
	    		String newName = name.getText().toString();
	    		String newTerm = comboBox.getSelectedItem().toString();
	    		String newYear = year.getText().toString();
	    		
	    		if(newCredit != null && !newName.equals(null) && !newTerm.equals(null) && !newYear.equals(null)) {
	    			newCourse.setClassCredit(newCredit);
		    		newCourse.setClassID(newID);
		    		newCourse.setClassName(newName);
		    		newCourse.setClassTerm(newTerm);
		    		newCourse.setClassYear(newYear);
		    		
		    		//update db
		    		if(title.equals("_") || title.equals("Empty template")) {
		    			Boolean flag = courseDao.addClass(newID, newCredit, newName, newTerm, newYear);
		    			uniqueID = courseDao.getuniqueID(newID, newTerm, newYear);
		    			setUniqueID(uniqueID);
//		    			Boolean flag1 = assignDao.addAssign(uniqueID);
		    			if(flag) {
		    				JOptionPane.showMessageDialog(null, "Success!", "Info", JOptionPane.INFORMATION_MESSAGE);	
		    				title = newID + "_" + newYear + newTerm;
		    				setT(title);
		    				setTitle(title);
		    			}else {
		    				JOptionPane.showMessageDialog(null, "Fail!", "Warning", JOptionPane.WARNING_MESSAGE);
		    			}
		    		//update assign

		    		} 

	    		}else {
	    			JOptionPane.showMessageDialog(null, "Please fill all the fields above!", "Missing fields", JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    		
	    	}
	    });
	    refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String note = "Update fail!";
				if(!title.equals("Empty template")) {
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
				}else {
					JOptionPane.showMessageDialog(null, "Please save this new class first", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				}
			});

	    JPanel panel = new JPanel();
//	    assignInfo.add(panel, BorderLayout.SOUTH);
	    getContentPane().add(panel, BorderLayout.WEST);
	    panel.setLayout(new GridLayout(12, 5, 0, 0));
	    
	    JLabel lblNewLabel = new JLabel("Assignment Name");
	    panel.add(lblNewLabel);
	    
	    nametv = new JTextField();
	    panel.add(nametv);
	    nametv.setColumns(10);
	    
	    JLabel lblNewLabel_1 = new JLabel("Weight(Graduate)");
	    panel.add(lblNewLabel_1);
	    
	    wgtv = new JTextField();
	    panel.add(wgtv);
	    wgtv.setColumns(10);
	    
	    JLabel lblNewLabel_2 = new JLabel("Weight(Undergraduate)");
	    panel.add(lblNewLabel_2);
	    
	    wutv = new JTextField();
	    panel.add(wutv);
	    wutv.setColumns(10);
	    
	    JLabel lblNewLabel_3 = new JLabel("Category");
	    panel.add(lblNewLabel_3);
	    
	    JComboBox catselect = new JComboBox(categories);
	    panel.add(catselect);
	    JLabel lblNewLabel_4 = new JLabel("Comment");
	    panel.add(lblNewLabel_4);
	    
	    JTextArea commenttv = new JTextArea();
	    panel.add(commenttv);
	   
	    
	    JButton addbtn = new JButton("Add");
	    addbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
	    addbtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String s = (String)catselect.getSelectedItem();
	    		Object []rowValues = {nametv.getText(),wgtv.getText(),wutv.getText(),
						s, commenttv.getText()};
				Assignment newAssign = new Assignment();
				newAssign.setAssignName(nametv.getText());
				newAssign.setWeightG(Double.parseDouble(wgtv.getText()));
				newAssign.setWeightU(Double.parseDouble(wutv.getText()));
				newAssign.setAssignCat(s);
				newAssign.setComment(commenttv.getText());
				newAssign.setClassID(uniqueID);
				
				boolean flag = assignDao.insertAssign(newAssign);
				int id = assignDao.getIDbyName(uniqueID, newAssign.getAssignName());
	    		newAssign.setAssignID(id);
//				boolean flag1= enrollDao.updateEnroll(classUniqueID, studentID);
				
	    		if(!title.equals("Empty template")) {
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
				}else {
					JOptionPane.showMessageDialog(null, "Please save this new class first", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				if(!flag) {
					JOptionPane.showMessageDialog(null, "Add failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Add Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
                nametv.setText("");
                wgtv.setText("");
                wutv.setText("");
                commenttv.setText("");
                catselect.setSelectedIndex(0);
				
	    	}
	    });
	    
	    panel.add(addbtn);
	    
	    
	    
	    JButton delbtn = new JButton("Delete");
	    delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!title.equals("Empty template")) {
//					 int selectedRow = table.getSelectedRow();
//		                if(selectedRow != -1) 
//		                {
//		                	String aname = (String) tableModel.getValueAt(selectedRow, 0);              	
//		                    tableModel.removeRow(selectedRow);
//		                    int aid = assignDao.getIDbyName(uniqueID, aname);
//		                    boolean flag = assignDao.delAssign(uniqueID, aname);
////		                    boolean flag1 = studentDao.removeStudent(buid);
////		                    boolean flag2 = enrollDao.removeEnroll(sid, classUniqueID);
////		    				boolean flag3 = gradesDao.removeGrade(sid, classUniqueID);
//		    				
//		    				if(!flag) {
//		    					JOptionPane.showMessageDialog(null, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
//		    				}else {
////		    					update();
//		    					JOptionPane.showMessageDialog(null, "Delete Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
//		    				}
//		                   
//		                }
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
					assignList = assignDao.assignList(uniqueID);
					String[] names = new String[assignList.size()];
					for(int i=0;i<names.length;i++){
					  String name= assignList.get(i).getAssignName();
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
				}else {
					JOptionPane.showMessageDialog(null, "Please save this new class first", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	    
	    
	    delbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
	    panel.add(delbtn);
	    
//	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//	    table.addMouseListener(new MouseAdapter(){
//	        public void mouseClicked(MouseEvent e){
//	            int selectedRow = table.getSelectedRow(); 
//	            Object asn = tableModel.getValueAt(selectedRow, 0);
//	            Object aswg = tableModel.getValueAt(selectedRow, 1);
//	            Object aswu = tableModel.getValueAt(selectedRow, 2);
//	            Object asc = tableModel.getValueAt(selectedRow, 4);
//	            Object c = tableModel.getValueAt(selectedRow, 3);
//	            nametv.setText(asn.toString());
//	            wgtv.setText(aswg.toString());
//	            wutv.setText(aswu.toString());
//	            commenttv.setText(asc.toString());
//	            catselect.setSelectedItem(c.toString());
//	            
//	        }
//	    });
//	    
//	    
	    
	    
	}
	
	


	protected void setUniqueID(int uniqueID2) {
		// TODO Auto-generated method stub
		this.uniqueID = uniqueID2;
	}


	protected void setT(String title2) {
		// TODO Auto-generated method stub
		this.title = title2;
	}
}
	