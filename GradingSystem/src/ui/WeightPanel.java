package ui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class WeightPanel extends JPanel{
	private JTextField hw;
	private JTextField mid;
	private JTextField finalEx;
	private JTextField pj;
	private JTextField extra;
	private JTextField hwID;
	private JTextField midID;
	private JTextField finalID;
	private JTextField pjID;
	private JTextField extraID;
	
	private String[] categories= {
		"Homework",
		"Midterm",
		"Project",
		"Final",
		"Extra"
	};

	public WeightPanel() {
		setLayout(new BorderLayout());	

		initWeight();
		
		initButton();
	}
	
	
	public void initWeight() {
		JPanel panel = new JPanel(new GridLayout(6, 3, 1, 0));
		this.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("Assignment ID");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Category");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("Weight");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		hwID = new JTextField();
		hwID.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(hwID);
		hwID.setColumns(10);
		
		JComboBox comboBox = new JComboBox(categories);
		panel.add(comboBox);
		
		hw = new JTextField();
		hw.setHorizontalAlignment(SwingConstants.CENTER);
		hw.setText("0.15");
		panel.add(hw);
		hw.setColumns(10);
		
		midID = new JTextField();
		midID.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(midID);
		midID.setColumns(10);
		
		comboBox = new JComboBox(categories);
		panel.add(comboBox);
		
		mid = new JTextField();
		mid.setHorizontalAlignment(SwingConstants.CENTER);
		mid.setText("0.20");
		panel.add(mid);
		mid.setColumns(10);
		
		finalID = new JTextField();
		finalID.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(finalID);
		finalID.setColumns(10);
		
		comboBox = new JComboBox(categories);
		panel.add(comboBox);
		
		finalEx = new JTextField();
		finalEx.setHorizontalAlignment(SwingConstants.CENTER);
		finalEx.setText("0.40");
		panel.add(finalEx);
		finalEx.setColumns(10);
		
		pjID = new JTextField();
		pjID.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(pjID);
		pjID.setColumns(10);
		
		comboBox = new JComboBox(categories);
		panel.add(comboBox);
		
		pj = new JTextField();
		pj.setHorizontalAlignment(SwingConstants.CENTER);
		pj.setText("0.30");
		panel.add(pj);
		pj.setColumns(10);
		
		extraID = new JTextField();
		extraID.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(extraID);
		extraID.setColumns(10);
		
		comboBox = new JComboBox(categories);
		panel.add(comboBox);
		
		extra = new JTextField();
		extra.setHorizontalAlignment(SwingConstants.CENTER);
		extra.setText("0.05");
		panel.add(extra);
		extra.setColumns(10);
	}
	
	public void initButton() {
		JPanel panel = new JPanel(new BorderLayout());
		this.add(panel, BorderLayout.SOUTH);
		
		JButton saveBtn = new JButton("Save");
		panel.add(saveBtn, BorderLayout.EAST);
		
	}
}
