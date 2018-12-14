package Abandon;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import beans.Student;
import daos.EnrollDao;
import daos.StudentDao;
import uis.Frame.UpdateFrame.TextChanged;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class AddStudentFrame extends JFrame{
	private JPanel contentPane;
	private JLabel lblStudentProfileName;
	private JLabel lblStudentProfileId;
	private JLabel lblMajor;
	private JLabel lblComments;

	
	private String firstname = ""; 
	private String lastname = ""; 
	private String ID = ""; 
	private String major = ""; 
	private String status = ""; 
	private JTable table;

	private Student newStudent =  new Student();
	private StudentDao studentDao = new StudentDao();
	private EnrollDao enrollDao = new EnrollDao();
	private Vector<String> columnNames = new Vector<String>();
	private String[] statusList = {"Graduate", "Undergraduate"};
	private Vector<Vector<Object>> data = new Vector<>();
	private JTextField idtxt;
	private JTextField lastTxt;
	private JTextField majortxt;
	private JTextField firstTxt;
	private JComboBox comboBox;
	public AddStudentFrame(int classUniqueID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton addbtn = new JButton("Add");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student newStudent = new Student();
				newStudent.setBUID(ID);
				newStudent.setFirstName(firstname);
				newStudent.setLastName(lastname);
				newStudent.setMajor(major);
				status = (String)comboBox.getSelectedItem();
				if(status == "Graduate") {
					newStudent.setStatus(true);
				}else {
					newStudent.setStatus(false);
				}
				
				setStudent(newStudent);
				boolean flag = studentDao.addStudent(newStudent);
				int studentID = studentDao.getIDbyBUID(newStudent.getBUID());
				boolean flag1= enrollDao.updateEnroll(classUniqueID, studentID);
				
				if(!flag || !flag1) {
					JOptionPane.showMessageDialog(null, "Grade failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
					JOptionPane.showMessageDialog(null, "Grade Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		addbtn.setBounds(314, 184, 96, 26);
		contentPane.add(addbtn);
		
		
		columnNames.add("AssignMent");
		columnNames.add("Weight");
		columnNames.add("Grade");
		columnNames.add("Comment");
		JTable info = new JTable(data, columnNames);
		info.setFillsViewportHeight(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 398, 166);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(5, 2, 0, 0));
		
		lblStudentProfileName = new JLabel("First Name:");
		lblStudentProfileName.setBounds(6, 36, 96, 16);
		panel.add(lblStudentProfileName);
		
		firstTxt = new JTextField();
		panel.add(firstTxt);
		firstTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Last Name:");
		lblNewLabel.setBounds(6, 5, 89, 16);
		panel.add(lblNewLabel);
		
		lastTxt = new JTextField();
		panel.add(lastTxt);
		lastTxt.setColumns(10);
		
		lblStudentProfileId = new JLabel("BUID: ");
		lblStudentProfileId.setBounds(6, 59, 96, 16);
		panel.add(lblStudentProfileId);
		
		idtxt = new JTextField();
		idtxt.setBounds(114, 59, 130, 26);
		panel.add(idtxt);
		idtxt.setColumns(10);
		
		lblMajor = new JLabel("Major: ");
		lblMajor.setBounds(6, 87, 96, 16);
		panel.add(lblMajor);
		
		majortxt = new JTextField();
		panel.add(majortxt);
		majortxt.setColumns(10);
		

		
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(6, 111, 61, 16);
		panel.add(statusLabel);
		
		comboBox = new JComboBox(statusList);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbtn.setBounds(185, 183, 117, 29);
		contentPane.add(cancelbtn);
		
		//listener
		Document id = idtxt.getDocument();
		id.addDocumentListener(new TextChanged());
		
		Document fn = firstTxt.getDocument();
		fn.addDocumentListener(new TextChanged());
		
		Document ln = lastTxt.getDocument();
		ln.addDocumentListener(new TextChanged());
	
		Document m = majortxt.getDocument();
		m.addDocumentListener(new TextChanged());
		
	}
	
	protected void setStudent(Student newStudent) {
		// TODO Auto-generated method stub
		this.newStudent = newStudent;
	}
//
//	public Student getNew() {
//		return newStudent;
//	}
//	
	class TextChanged implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			firstname = firstTxt.getText().toString();
			lastname = lastTxt.getText().toString();
			ID = idtxt.getText().toString();
			major = majortxt.getText().toString();
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
