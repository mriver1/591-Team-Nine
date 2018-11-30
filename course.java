import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.sqlite.SQLiteConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;//i add a new import for Dbutils

public class course {

	private JFrame frame;
	private JTable table;
	private JTextField coursename;
	private JTextField courseid;
	private JTextField term;
	private JTextField instructor;
	private JTextField year;
	private JTextField credit;
	private JTextField catename;
	private JTextField weight1;
	private JTextField weight2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					course window = new course();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public course() {
		initialize();
		connection=sqlConnection.dbConnection();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 549, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 137, 477, 96);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Weight(M)", "Weight(U)", "Categary"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Float.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lable1 = new JLabel("Couse Name");
		lable1.setBounds(35, 38, 79, 14);
		frame.getContentPane().add(lable1);
		
		JLabel lable2 = new JLabel("CourseID");
		lable2.setBounds(35, 63, 64, 14);
		frame.getContentPane().add(lable2);
		
		JLabel lable3 = new JLabel("Term");
		lable3.setBounds(35, 88, 64, 14);
		frame.getContentPane().add(lable3);
		
		JLabel lblInstructor = new JLabel("Instructor");
		lblInstructor.setBounds(302, 35, 64, 14);
		frame.getContentPane().add(lblInstructor);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(302, 60, 64, 14);
		frame.getContentPane().add(lblYear);
		
		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setBounds(302, 85, 64, 14);
		frame.getContentPane().add(lblCredit);
		
		coursename = new JTextField();
		coursename.setBounds(109, 35, 128, 20);
		frame.getContentPane().add(coursename);
		coursename.setColumns(10);
		
		courseid = new JTextField();
		courseid.setColumns(10);
		courseid.setBounds(109, 60, 128, 20);
		frame.getContentPane().add(courseid);
		
		term = new JTextField();
		term.setColumns(10);
		term.setBounds(109, 85, 128, 20);
		frame.getContentPane().add(term);
		
		instructor = new JTextField();
		instructor.setColumns(10);
		instructor.setBounds(376, 32, 128, 20);
		frame.getContentPane().add(instructor);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(376, 57, 128, 20);
		frame.getContentPane().add(year);
		
		credit = new JTextField();
		credit.setColumns(10);
		credit.setBounds(376, 82, 128, 20);
		frame.getContentPane().add(credit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(35, 360, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(396, 360, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.setBounds(223, 360, 89, 23);
		frame.getContentPane().add(btnUpgrade);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(35, 262, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblWeight = new JLabel("Weight(master)");
		lblWeight.setBounds(35, 287, 99, 14);
		frame.getContentPane().add(lblWeight);
		
		JLabel lblWeightundergraduate = new JLabel("Weight(under)");
		lblWeightundergraduate.setBounds(302, 287, 89, 14);
		frame.getContentPane().add(lblWeightundergraduate);
		
		catename = new JTextField();
		catename.setBounds(109, 259, 128, 20);
		frame.getContentPane().add(catename);
		catename.setColumns(10);
		
		weight1 = new JTextField();
		weight1.setColumns(10);
		weight1.setBounds(109, 284, 128, 20);
		frame.getContentPane().add(weight1);
		
		weight2 = new JTextField();
		weight2.setColumns(10);
		weight2.setBounds(384, 284, 128, 20);
		frame.getContentPane().add(weight2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner.setBounds(237, 284, 36, 20);
		frame.getContentPane().add(spinner);
		
		JButton btnShowData = new JButton("Load Table");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from coursecategory";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setmodel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnShowData.setBounds(35, 395, 117, 29);
		frame.getContentPane().add(btnShowData);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Assignment", "Task", "Quiz", "Midterm", "Finalterm"}));
		comboBox.setBounds(384, 256, 128, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(310, 258, 61, 16);
		frame.getContentPane().add(lblCategory);
	}
}
