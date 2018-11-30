package utils;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class CourseTitlePanel extends JPanel{
	public CourseTitlePanel() {
		setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Course ID: CS591 D1");
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Course Name: Oriented-Object Programming");
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Term: 18Fall");
		add(lblNewLabel_3);
		
	}
	
}
