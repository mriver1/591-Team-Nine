package uis.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

import beans.Assignment;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import daos.AssignmentDao;

public class UpdateFrame extends JFrame{
	private JTextField content;
	private AssignmentDao assignmentDao = new AssignmentDao();
//	private boolean flag = false;
	private static Assignment assign = new Assignment();
	public String update;
	public UpdateFrame(Assignment selectAssign, String colName) {
		getContentPane().setLayout(null);
		setSize(453, 145);
		assign = selectAssign;
		String title = "Update " + colName;
		setTitle(title);
//		System.out.println(assign.getAssignName());
		
		String note = "Please input new " + colName + " :"; 

		JLabel lblNewLabel = new JLabel(note);
		lblNewLabel.setBounds(0, 0, 450, 16);
		getContentPane().add(lblNewLabel);
		
		content = new JTextField();
		content.setBounds(0, 28, 450, 26);
		content.setToolTipText(colName);
		
		//listener
		Document doc = content.getDocument();
		doc.addDocumentListener(new TextChanged());

		getContentPane().add(content);
		content.setColumns(10);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				Assignment updateassign = new Assignment();
				switch(colName) {
				case "Assignment Name":
					flag = assignmentDao.updateName(assign.getAssignID(), update);
					updateassign.setAssignName(content.getText());
					break;
				case "Weight(Graduate)":
					flag = assignmentDao.updateWG(assign.getAssignID(), Double.valueOf(update));
					updateassign.setWeightG(Double.valueOf(content.getText()));
					break;
				case "Weight(Undergraduate)":
					flag = assignmentDao.updateWU(assign.getAssignID(), Double.valueOf(update));
					updateassign.setWeightU(Double.valueOf(content.getText()));
					break;
				case "Comment":
					flag = assignmentDao.updateComments(assign.getAssignID(), update);
					updateassign.setComment(content.getText());
					break;
				}
				
				setAssign(updateassign);
				dispose();
				if(!flag) {
					JOptionPane.showMessageDialog(null, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Update Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
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
	
	protected void setAssign(Assignment updateassign) {
		// TODO Auto-generated method stub
		assign = updateassign;
	}

	public static Assignment getUpdatedData() {
		// TODO Auto-generated method stub		
		return assign;
	}

	class TextChanged implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			update = content.getText().toString();
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


