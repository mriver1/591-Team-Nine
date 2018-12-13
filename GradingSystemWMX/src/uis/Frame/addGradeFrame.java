package uis.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import beans.Grade;
import daos.EnrollDao;
import daos.GradesDao;
import uis.Frame.UpdateFrame.TextChanged;

public class addGradeFrame extends JFrame{
	private JTextField content;
	private GradesDao gradeDao = new GradesDao();
//	private boolean flag = false;
	private int studentID;
	private int classID;
	private int assignID;
	
	private int gradeID;
	private static Double grade;
	public String update;
	public EnrollDao enrollDao;
	
	public addGradeFrame(String assignName, int studentID, int classUniqueID, int assignID) {
		getContentPane().setLayout(null);
		setSize(453, 145);
		classID = classUniqueID;
		this.studentID = studentID;
		this.assignID = assignID;
		//public int getGradeID(int studentID, int classID, int assignID) {
		
		this.gradeID = gradeDao.getGradeID(studentID, classUniqueID, assignID);
//		this.gradeID = gradeID;
		
		String title = "Add grade for assignment: " + assignName;
		setTitle(title);
		
		String note = "Please input grade:"; 

		JLabel lblNewLabel = new JLabel(note);
		lblNewLabel.setBounds(0, 0, 450, 16);
		getContentPane().add(lblNewLabel);
		//TODO

		content = new JTextField();
//		content.setToolTipText(gradeDao.getGradeByID(gradeID));
		content.setBounds(0, 28, 450, 26);

		
		//listener
		Document doc = content.getDocument();
		doc.addDocumentListener(new TextChanged());

		getContentPane().add(content);
		content.setColumns(10);
		
		JButton updateBtn = new JButton("Confirm");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grade = getUpdatedData();
				//int studentID, int classID, int assignID

				boolean flag = gradeDao.updateGrade(grade, studentID, classID, assignID);
				
				if(!flag) {
					JOptionPane.showMessageDialog(null, "Grade failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
					JOptionPane.showMessageDialog(null, "Grade Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		updateBtn.setBounds(92, 66, 117, 29);
		getContentPane().add(updateBtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelbtn.setBounds(232, 66, 117, 29);
		getContentPane().add(cancelbtn);
	}
	
	public static Double getUpdatedData() {
		// TODO Auto-generated method stub		
		return grade;
	}

	
	class TextChanged implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			grade = Double.parseDouble(content.getText().toString());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
