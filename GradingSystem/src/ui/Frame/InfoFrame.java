package ui.Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import ui.Panels.CourseTitlePanel;
import ui.Panels.WeightForm;
import utils.sqlConnection;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;

import beans.Assignment;
import beans.Course;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.InputMethodEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class InfoFrame extends JFrame{
	private String[] dbColumn = {"NAME", "WG", "WU", "CATEGORY", "ID" };

	private CourseTitlePanel panel = new CourseTitlePanel();
	private Course course;
	private String classID = "";
	private String classTerm = "";
	private String classYear = "";
	private String className = "";
	private Float classCredit = null;
	private int classUniqueID = 0;
	private Connection connection = null;

	private String title = "";
	
	public InfoFrame() {
		setSize(715,626);
		
		initCourseInfoFromStart();
		
		if(!classID.equals(null) && !classYear.equals(null) && !classTerm.equals(null)) {
			title = classID + "_" + classYear + classTerm;
		}
		setTitle(title);
		
		initForm();
		init();
	}
	
//	public InfoFrame() {
//		setSize(715,626);
//		
//		initCourseInfoFromStart();
//		
////		if(!classID.equals(null) && !classYear.equals(null) && !classTerm.equals(null)) {
////			title = classID + "_" + classYear + classTerm;
////		}
//		setTitle(title);
//		
//		initForm();
//		init();
//	}
	
	private void initCourseInfoFromStart() {
		// TODO Auto-generated method stub

		//(String ID, String NAME, String YEAR, String TERM, String CREDIT
		String selectedClass = StartFrame.getSelectedClass();
		if (selectedClass.equals(null)){
			JOptionPane.showMessageDialog(null, "Please select a template to start!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!selectedClass.equals("Empty template")) {
			//select template created before
			classID = selectedClass;
	    	String query="select CourseName, Year, Term, Credit, ID from CLASS where CourseID = '" + classID + "';";
			connection=sqlConnection.dbConnection();// connect to database
			
			try {
				PreparedStatement pst;
				pst = connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				while(rs.next()) {
					className = rs.getString(1);
					classYear = rs.getString(2);
					classTerm = rs.getString(3);
					classCredit = rs.getFloat(4);
					classUniqueID = rs.getInt(5);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		} else {
			
		}
		course = new Course(className, classID, classTerm, classYear, classCredit, classUniqueID);
		CourseTitlePanel.initClassData(course);
		WeightForm.initClassData(course);
		panel = new CourseTitlePanel();
		panel.setBounds(6, 0, 703, 191);
		getContentPane().add(panel);
	}
	
	
	private void initForm() {
		// TODO:implement this
		WeightForm form = new WeightForm();
		form.setBounds(6, 193, 703, 280);
		getContentPane().setLayout(null);
		getContentPane().add(form);
	}

	private void init() {
		// TODO Auto-generated method stub
		JPanel button = new JPanel();
		button.setBounds(6, 476, 688, 117);
		getContentPane().add(button);
		getContentPane().setLayout(null);
		
		JButton updatebtn = new JButton("Update");
		updatebtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/upFolder.gif")));
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Successfully upadated!", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				Connection c = sqlConnection.dbConnection();
			    Statement stmt = null;
			    try {
					stmt = c.createStatement();
					
					ArrayList<Assignment> updateData = WeightForm.getData();
					for(int i = 0; i < updateData.size(); i++) {
						// NAME
						String sql = "UPDATE " + classID + " SET NAME = '" + updateData.get(i).getName() + "' where ID=" + (i + 1) + ";";
						stmt.executeUpdate(sql);
						
						//WG
						sql = "UPDATE " + classID + " SET WG = " + updateData.get(i).getWeightGraduate() + " where ID=" + (i + 1) + ";";
						stmt.executeUpdate(sql);
						
						//WU
						sql = "UPDATE " + classID + " SET WU = " + updateData.get(i).getWeightUndergraduate() + " where ID=" + (i + 1) + ";";
						stmt.executeUpdate(sql);
						
						//CATEGORY
						sql = "UPDATE " + classID + " SET CATEGORY = '" + updateData.get(i).getCategory() + "' where ID=" + (i + 1) + ";";
						stmt.executeUpdate(sql);
					}
					stmt.close();
				    c.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.err.println(e1.getClass().getName() + ": " + e1.getMessage() );
				     System.exit(0);
					e1.printStackTrace();
				}
			    System.out.println("Records created successfully");
				}
			});
		updatebtn.setBounds(434, 7, 101, 29);
		button.add(updatebtn);
		
		//TODO
		JButton savebtn = new JButton("Save as Template");
		savebtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Successfully saved as template!", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		savebtn.setBounds(302, 48, 187, 29);
		button.add(savebtn);
		
		JButton addbtn = new JButton("Add");
		addbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFrame af = new AddFrame();
				af.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				af.setLocation(x, y);
				af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		addbtn.setBounds(304, 7, 101, 29);
		button.add(addbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose.gif")));
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbtn.setBounds(581, 89, 101, 29);
		button.add(cancelbtn);
		
		JButton start = new JButton("Start/Continue");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				mf.setLocation(x, y);
			}
		});
		start.setBackground(new Color(204, 255, 204));
		start.setIcon(new ImageIcon(InfoFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		start.setForeground(new Color(0, 102, 51));
		start.setBounds(518, 48, 164, 29);
		button.add(start);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(WeightForm.isEmpty()) {
					//TODO: Create a pop-up window to let user add assignments
//					JOptionPane.showMessageDialog(null, "Empty form!", "Error", JOptionPane.ERROR_MESSAGE);
					String[] options = { "Add Assignments", "Cancel" }; 
					int choice = JOptionPane.showOptionDialog(null, "Empty form!", "Error", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, options, options[0]); 
					if(choice == 0) {
						AddFrame df = new AddFrame();
						df.setVisible(true);
						int x = getLocation().x;
						int y = getLocation().y;
						df.setLocation(x, y);
						df.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					} else {
						dispose();
					}
				} else {
					DelFrame df = new DelFrame();
					df.setVisible(true);
					int x = getLocation().x;
					int y = getLocation().y;
					df.setLocation(x, y);
					df.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				}
			}
		});
		delete.setIcon(new ImageIcon(InfoFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		delete.setBounds(565, 7, 117, 29);
		button.add(delete);
	}

}
