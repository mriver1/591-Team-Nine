package ui.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import beans.Course;
import ui.Panels.CourseTitlePanel;
import ui.Panels.WeightForm;
import utils.sqlConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StartFrame extends JFrame{
	private ArrayList<Course> classList = new ArrayList<>(); 
	private ArrayList<String> showList = new ArrayList<>();
	private Course course;
	private String[] arr;
	private static JList list;
	private static String selectedClass;
	public StartFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Select the template to start...");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		initData();
		list = new JList(arr);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(list);
		initList(list);
		
		list.setSelectedIndex(0);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton confirmbtn = new JButton("Confirm");
		confirmbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InfoFrame info = new InfoFrame();
				info.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				info.setLocation(x, y);
				
			}
		});
		
		panel.add(confirmbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login lf = new Login();
				lf.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				lf.setLocation(x, y);
			}
		});
		panel.add(cancelbtn);
	}

	private void initList(JList list) {
		// TODO Auto-generated method stub
		final JList<String> classNames = new JList<String>();
		classNames.setListData(arr);
		
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list.getSelectedIndices();
                ListModel<String> listModel = list.getModel();
                for (int index : indices) {
                	selectedClass = listModel.getElementAt(index);
                    System.out.println("Selected: " + index + " = " + selectedClass);
                }
                System.out.println();
            }
        });
	}

	private void initData() {
		// TODO Auto-generated method stub
		Connection c = sqlConnection.dbConnection();
	    String query="select * from CLASS";
		try {
			PreparedStatement pst;
			pst = c.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				//CourseID, CourseNAME, Year, Term, Credit
				course = new Course();
				course.setClassID(rs.getString(1));
				course.setClassName(rs.getString(2));
				course.setClassYear(rs.getString(3));
				course.setClassTerm(rs.getString(4));
				course.setClassCredit(rs.getFloat(5));
				course.setUniqueID(rs.getInt(6));
				classList.add(course);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		showList.add("Empty template");
		for(int i = 1; i < classList.size() + 1; i++) {
			showList.add((String) classList.get(i - 1).getClassID());
		}
		
		arr = showList.toArray(new String[showList.size()]);
	}
	
	static String getSelectedClass() {
		// TODO Auto-generated method stub
		return selectedClass;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setSize(702,621);;
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
