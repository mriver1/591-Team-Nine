package miranda;


import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class StudentProfileCopy extends JFrame {

	private JPanel contentPane;
	private JLabel lblStudentProfileName;
	private JLabel lblStudentProfileId;
	private JLabel lblMajor;
	private JLabel lblStatus;
	private JLabel lblStudentProfile;
	private JLabel lblUxxxxxxx;
	private JLabel lblComputerScience;
	private JLabel lblUndergraduate;
	private JLabel lblComments;
	private String name; 
	private String ID; 
	private String major; 
	private String status; 
	private JTable table;
	private String[] columnNames; 
	private Object[][] data;
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfo s = new StudentInfo(-1, "Miranda", "Rivera", "U79852612", "Computer Science", false);
					StudentProfileCopy frame = new StudentProfileCopy(s);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	//this must be searched on database and turned into a StudentInfo object before calling this class
	//Receive Student object and extract information
	public void GetStudent(StudentInfo s) {
		name = s.getName();
		ID = s.getID();
		major = s.getMajor();
		if(s.getStatus()) {
			status = "Graduate";
		}else {
			status = "Undergraduate";
		}
		//status = s.getStatus();
		//missing the assignment array etc. 
		LoadTableData(s);
		
	}
	
	
	/**database manager class 
	 * function that takes in id of column and takes student info and turns into StudentInfo class 
	 * function that takes in a student info and updates by overriding info for that student in database 
	*/
	
	
	//student must have assignment array 
	public void LoadTableData(StudentInfo s) {
		// TODO : Load the assignments from student
		columnNames = new String[]{"Assignment",
                "Weight",
                "Grade"};

		data = new Object[][] {
			{"Homework1", new Float(0.5), new Float(90.0)},
			{"Homework2", new Float(0.5), new Float(90.0)},
			{"Homework3", new Float(0.5), new Float(90.0)},
			{"Homework4", new Float(0.5), new Float(90.0)},
			{"Homework5", new Float(0.5), new Float(90.0)},
		};

	}
	
	
	/**
	 * Create the frame.
	 */
	public StudentProfileCopy(StudentInfo s) {
		GetStudent(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblStudentProfileName = new JLabel("Student Name: ");
		lblStudentProfileName.setBounds(6, 5, 96, 16);
		contentPane.add(lblStudentProfileName);
		
		lblStudentProfile = new JLabel(name); // change to student.Name
		lblStudentProfile.setBounds(133, 5, 200, 16);
		contentPane.add(lblStudentProfile);
		
		lblStudentProfileId = new JLabel("Student ID: ");
		lblStudentProfileId.setBounds(6, 26, 96, 16);
		contentPane.add(lblStudentProfileId);
		
		lblUxxxxxxx = new JLabel(ID); // change to student.Id
		lblUxxxxxxx.setBounds(133, 26, 200, 16);
		contentPane.add(lblUxxxxxxx);
		
		lblMajor = new JLabel("Major: ");
		lblMajor.setBounds(6, 47, 96, 16);
		contentPane.add(lblMajor);
		
		lblComputerScience = new JLabel(major); // change to student.Major
		lblComputerScience.setBounds(133, 47, 200, 16);
		contentPane.add(lblComputerScience);
		
		lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(6, 68, 96, 16);
		contentPane.add(lblStatus);
		
		lblUndergraduate = new JLabel(status); // change to student.Status
		lblUndergraduate.setBounds(133, 68, 200, 16);
		contentPane.add(lblUndergraduate);
		
		lblComments = new JLabel("Comments");
		lblComments.setBounds(293, 5, 74, 16);
		contentPane.add(lblComments);
		
		JTextPane txtpnComment = new JTextPane();
		txtpnComment.setBounds(293, 26, 133, 101);
		contentPane.add(txtpnComment);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(176, 246, 96, 26);
		contentPane.add(btnSave);
		
		table = new JTable(data, columnNames);
		table.setBounds(18, 135, 408, 101);
		//contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(18, 135, 408, 101);
		table.setFillsViewportHeight(true);
		contentPane.add(scrollPane);
		
		
	}
}


