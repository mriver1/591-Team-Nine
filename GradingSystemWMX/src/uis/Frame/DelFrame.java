package uis.Frame;


import daos.AssignmentDao;
import daos.DBconnection;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import beans.Assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DelFrame extends JFrame{
	private AssignmentDao assignDao = new AssignmentDao();
	private Assignment assignDel = new Assignment();
	private int uniqueID;
	private String[] names;
	ArrayList<String> del = new ArrayList<>();
	public DelFrame(int uniqueID, String[] names) {
		setSize(300, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.names = names;
		this.uniqueID = uniqueID;
		
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
                }
            }
        });
		
		 list.setSelectedIndex(0);
		 
		 JPanel buttonGroup = new JPanel();
		 getContentPane().add(buttonGroup, BorderLayout.SOUTH);
		 
		 JButton delbtn = new JButton("Delete");
		 delbtn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		boolean flag = false;
		 		for(String name : del) {
		 			 flag = assignDao.delAssign(uniqueID, name);
		 		}
		 		if(!flag) {
					JOptionPane.showMessageDialog(null, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Update Success! Please click 'Refresh'.", "Info", JOptionPane.INFORMATION_MESSAGE);

				}
		 		dispose();
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
