package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;

public class MainFrame extends JFrame{
	GradingPanel grad = new GradingPanel();
	WeightPanel weightG = new WeightGrad();
	WeightPanel weightUG = new WeightUG();
	CourseTitlePanel course = new CourseTitlePanel();
	private final JButton savebtn = new JButton("Save");
	private final JButton tempbtn = new JButton("Save as template");
	private final JButton cancelbtn = new JButton("Cancel");
	private final JButton newbtn = new JButton("New");
	private final JButton logoutbtn = new JButton("Log out");
	public MainFrame() {
		getContentPane().setLayout(null);
		course.setBounds(0, 36, 858, 143);
		getContentPane().add(course);
		weightG.setBounds(0, 178, 858, 157);
		getContentPane().add(weightG);
		weightUG.setBounds(0, 334, 858, 157);
		getContentPane().add(weightUG);
		
		JLabel lblNewLabel = new JLabel("Grade Form");
		lblNewLabel.setBounds(0, 503, 193, 16);
		getContentPane().add(lblNewLabel);
		grad.setBounds(0, 529, 858, 181);
		getContentPane().add(grad);
		savebtn.setBounds(473, 738, 117, 29);
		
		getContentPane().add(savebtn);
		tempbtn.setBounds(591, 738, 149, 29);
		
		getContentPane().add(tempbtn);
		cancelbtn.setBounds(741, 738, 117, 29);
		
		getContentPane().add(cancelbtn);
		newbtn.setBounds(6, 6, 117, 29);
		
		getContentPane().add(newbtn);
		logoutbtn.setBounds(155, 6, 117, 29);
		
		getContentPane().add(logoutbtn);
	
	}
}
