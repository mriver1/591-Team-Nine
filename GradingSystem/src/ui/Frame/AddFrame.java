package ui.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import beans.Assignment;
import beans.Course;
import ui.Panels.CourseTitlePanel;
import ui.Panels.WeightForm;
import utils.sqlConnection;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale.Category;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddFrame extends JFrame{
	private JTextField name;
	private JTextField wg;
	private JTextField wu;
	private JComboBox category;
	private String[] categories= {
			"Homework",
			"Midterm",
			"Project",
			"Final",
			"Extra",
			"Attendence",
			"Quiz"
		};
	private Course course = CourseTitlePanel.getCourseInfo();
	private String classID = course.getClassID();
	private String classTerm = course.getClassTerm();
	private String classYear = course.getClassYear();
	private String className = course.getClassName();
	private Float classCredit = course.getClassCredit();
	private int classUniqueID = course.getUniqueID();
	private Assignment addedData;
	
	public AddFrame () {
		setSize(652, 260);
		JPanel panel = new JPanel();
		
		
		panel.setBounds(6, 476, 688, 236);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton addbtn = new JButton("Add");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.equals(null) || category.equals(null) || wg.equals(null) || wu.equals(null)) {
					JOptionPane.showMessageDialog(null, "Missing data in required fields!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					addedData.setName(name.getText());
					addedData.setWeighG(Float.valueOf(wg.getText()));
					addedData.setWeightU(Float.valueOf(wu.getText()));
					addedData.setCategory(category.getSelectedItem().toString());
					
					JOptionPane.showMessageDialog(null, "New assignment has been successfully added!", "Info", JOptionPane.INFORMATION_MESSAGE);
					WeightForm.addData(addedData);
					//update in db
					Connection c = sqlConnection.dbConnection();;
				    Statement stmt = null;
				    try {
						stmt = c.createStatement();
						String sql = "INSERT INTO " + classID +" (NAME,WG,WU,CATEGORY) OUTPUT INSERTED.ID" + 
								"VALUES ( '" + addedData.getName() + "'," 
								+ addedData.getWeightGraduate() + "," + addedData.getWeightUndergraduate() + ",'"
								+ addedData.getCategory() + "');";
						
						//+ (WeightForm.getData().size() + 1) +
						stmt.executeUpdate(sql);
						stmt.close();
					    c.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					     System.exit(0);
						e1.printStackTrace();
					}
					
				    System.out.println("Records created successfully");
					dispose();
				}
			}
		});
		addbtn.setBounds(388, 175, 117, 29);
		panel.add(addbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbtn.setBounds(528, 175, 117, 29);
		panel.add(cancelbtn);
		
		JLabel lblAssignmentName = new JLabel("Assignment Name");
		lblAssignmentName.setBounds(6, 35, 125, 16);
		panel.add(lblAssignmentName);
		
		name = new JTextField();
		name.setName("name");
		name.setColumns(10);
		name.setBounds(147, 31, 130, 26);
		panel.add(name);
		
		JLabel label_1 = new JLabel("Weight (Graduate)");
		label_1.setBounds(6, 81, 117, 16);
		panel.add(label_1);
		
		wg = new JTextField();
		wg.setName("weightG");
		wg.setColumns(10);
		wg.setBounds(147, 80, 130, 26);
		panel.add(wg);
		
		JLabel label_2 = new JLabel("Category");
		label_2.setBounds(329, 33, 61, 16);
		panel.add(label_2);
		
		category = new JComboBox(categories);
		category.setBounds(502, 30, 143, 29);
		panel.add(category);
		
		JLabel label_3 = new JLabel("Weight (Undergraduate)");
		label_3.setBounds(329, 85, 152, 16);
		panel.add(label_3);
		
		wu = new JTextField();
		wu.setColumns(10);
		wu.setBounds(505, 80, 143, 26);
		panel.add(wu);
		
	}
}
