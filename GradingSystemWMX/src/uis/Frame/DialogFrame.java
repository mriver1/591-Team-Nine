package uis.Frame;


import javax.swing.*;

import beans.Assignment;
import daos.AssignmentDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DialogFrame extends JFrame{
	private Assignment assignAdd = new Assignment();
	private AssignmentDao assignDao = new AssignmentDao();
	private int classID;
	
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
	//652 260
	
	public DialogFrame (int classID) {
		setSize(652, 260);
		JPanel panel = new JPanel();
		
		this.classID = classID;
		panel.setBounds(6, 476, 688, 236);
		getContentPane().add(panel);
		panel.setLayout(null);
		
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
		
		JLabel lblNewLabel = new JLabel("Comment");
		lblNewLabel.setBounds(6, 126, 117, 16);
		panel.add(lblNewLabel);
		
		JEditorPane comment = new JEditorPane();
		comment.setBounds(6, 146, 271, 86);
		panel.add(comment);
		
		JButton addbtn = new JButton("Add");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.equals(null) || category.equals(null) || wg.equals(null) || wu.equals(null)) {
					JOptionPane.showMessageDialog(null, "Missing data in required fields!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					assignAdd.setAssignName(name.getText());
					assignAdd.setWeightG(Double.valueOf(wg.getText()));
					assignAdd.setWeightU(Double.valueOf(wu.getText()));
					assignAdd.setComment(comment.getText());
					assignAdd.setAssignCat((String)category.getSelectedItem());
					assignAdd.setClassID(classID);
					
					boolean flag = assignDao.insertAssign(assignAdd);
					if(!flag) {
						JOptionPane.showMessageDialog(null, "Add failed!", "Error", JOptionPane.ERROR_MESSAGE);
					} else{
						JOptionPane.showMessageDialog(null, "Add Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
					dispose();
				}
			}
		});
		addbtn.setBounds(388, 175, 117, 29);
		panel.add(addbtn);
		
		
	}
}
