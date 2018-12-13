package uis.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import beans.Assignment;
import daos.AssignmentDao;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CatUpFrame extends JFrame{
	private static String[] categories= {
			"Homework",
			"Midterm",
			"Project",
			"Final",
			"Extra",
			"Attendence",
			"Quiz"
		};
	private AssignmentDao assignmentDao = new AssignmentDao();
	private boolean flag = false;
	private Assignment assign;
	public CatUpFrame(Assignment selectAssign) {
		getContentPane().setLayout(null);
		setSize(300, 163);
		
		assign = selectAssign;
		JLabel lblNewLabel = new JLabel("Please select new category:");
		lblNewLabel.setBounds(6, 6, 238, 16);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox(categories);
		comboBox.setBounds(6, 25, 232, 27);
		getContentPane().add(comboBox);
		
		JButton updatebtn = new JButton("Update");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = assignmentDao.updateCategory(assign.getAssignID(), (String)comboBox.getSelectedItem());
				
				if(!flag) {
					JOptionPane.showMessageDialog(null, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Update Success!", "Info", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		updatebtn.setBounds(6, 60, 117, 29);
		getContentPane().add(updatebtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.setBounds(127, 60, 117, 29);
		getContentPane().add(cancelbtn);
		
		
	}
}
