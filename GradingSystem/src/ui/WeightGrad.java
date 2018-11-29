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
import javax.swing.UIManager;

public class WeightGrad extends WeightPanel{
	
	public WeightGrad() {
		super();
		JLabel lblNewLabel = new JLabel("Weights for Graduate Students");
		lblNewLabel.setFont(UIManager.getFont("TitledBorder.font"));
		add(lblNewLabel, BorderLayout.NORTH);
	}
	
	
}