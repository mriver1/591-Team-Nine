package ui.Frame;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import beans.Assignment;
import beans.Course;
import ui.Panels.CourseTitlePanel;
import ui.Panels.WeightForm;
import utils.sqlConnection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DelFrame extends JFrame{
	ArrayList<String> del = new ArrayList<>();
	private Course course = CourseTitlePanel.getCourseInfo();
	private String classID = course.getClassID();
	private String classTerm = course.getClassTerm();
	private String classYear = course.getClassYear();
	private String className = course.getClassName();
	private Float classCredit = course.getClassCredit();
	private int classUniqueID = course.getUniqueID();
	
	public DelFrame() {
		setSize(300, 300);
		ArrayList<Assignment> data = WeightForm.getData();
		String[] names = WeightForm.getNames().toArray(new String[data.size()]);
				
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Select the record to delete...");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JList list = new JList(names);
		getContentPane().add(list, BorderLayout.CENTER);
		 list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 list.setPreferredSize(new Dimension(200, 100));

		final JList<String> nameList = new JList<String>();
		nameList.setListData(names);
		
		list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list.getSelectedIndices();
                ListModel<String> listModel = list.getModel();
//                setDelItems(listModel);
                for (int index : indices) {
                	del.add(listModel.getElementAt(index));
                    System.out.println("Selected: " + index + " = " + listModel.getElementAt(index));
                }
                System.out.println();
            }
        });
		
		 list.setSelectedIndex(0);
		 
		 JPanel buttonGroup = new JPanel();
		 getContentPane().add(buttonGroup, BorderLayout.SOUTH);
		 
		 JButton delbtn = new JButton("Delete");
		 delbtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		dispose();
		 		//TODO: update database
		 		//DELETE FROM COMPANY WHERE ID = 7;
		 		WeightForm.delete(del);
		 		Connection c = sqlConnection.dbConnection();;
			    Statement stmt = null;
			    try {
					stmt = c.createStatement();
					for(String item : del) {
						String sql = "DELETE FROM " + classID + " WHERE NAME = '";
						sql += item;
						sql += "'";
						stmt.executeUpdate(sql);
					}
					stmt.close();
				    c.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
				     System.exit(0);
					e1.printStackTrace();
				}
		 	}
		 });
		 buttonGroup.add(delbtn);
		 
		 JButton cancelbtn = new JButton("Cancel");
		 cancelbtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		dispose();
		 	}
		 });
		 buttonGroup.add(cancelbtn);
		
	}
}
