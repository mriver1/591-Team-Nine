package ui.Frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.border.LineBorder;

import ui.Panels.GradingPanel;

import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame implements ActionListener{
	GradingPanel grad = new GradingPanel();
	private final JButton savebtn = new JButton("Save");
	private final JButton cancelbtn = new JButton("Cancel");
	private final JButton newbtn = new JButton("New");
	private final JButton logoutbtn = new JButton("Log out");
	private final JButton viewbtn = new JButton("View/Modify Course Information");
	private final JLabel lblNewLabel_1 = new JLabel("(Note: Click the ID to view the information of the student)");
	public MainFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Grade Form");
		lblNewLabel.setBounds(20, 47, 117, 16);
		getContentPane().add(lblNewLabel);
		grad.setBounds(20, 73, 858, 637);
		getContentPane().add(grad);
		savebtn.setBounds(599, 738, 117, 29);
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save the project
				//rename? default name?
			}
		});
		getContentPane().add(savebtn);
		cancelbtn.setBounds(741, 738, 117, 29);
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//return to the preview page to select the template or start a new one
				//pop-up window to make sure she would like to cancel the pj without save
			}
		});
		getContentPane().add(cancelbtn);
		newbtn.setBounds(20, 6, 117, 29);
		newbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//turn to the interface: create the pj (select the template or create a new one)
			}
		});
		getContentPane().add(newbtn);
		logoutbtn.setBounds(175, 6, 117, 29);
		logoutbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//go to the login page
				dispose();
				Login login = new Login();
				login.setVisible(true);
				login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
			
		});
		getContentPane().add(logoutbtn);
		viewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoFrame info = new InfoFrame();
				info.setVisible(true);
				int x = getLocation().x;
				int y = getLocation().y;
				info.setLocation(x, y);
				info.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		viewbtn.setBounds(330, 6, 291, 29);
		
		getContentPane().add(viewbtn);
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(116, 47, 429, 16);
		
		getContentPane().add(lblNewLabel_1);
	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setSize(978, 878);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
}
