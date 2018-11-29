package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Admin;
import utils.LoginUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel panelLogin;
	private JTextField edtUsername;
	private JTextField edtPassword;
	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);

		JLabel labelUsername = new JLabel("Username");
		labelUsername.setBounds(83, 77, 80, 16);
		panelLogin.add(labelUsername);

		edtUsername = new JTextField();
		edtUsername.setBounds(175, 72, 130, 26);
		panelLogin.add(edtUsername);
		edtUsername.setColumns(10);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(83, 119, 61, 16);
		panelLogin.add(labelPassword);

		edtPassword = new JTextField();
		edtPassword.setBounds(175, 114, 130, 26);
		panelLogin.add(edtPassword);
		edtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(51, 219, 117, 29);
		panelLogin.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(227, 219, 117, 29);
		panelLogin.add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("Hello Professor!");
		lblNewLabel.setBounds(51, 25, 188, 16);
		panelLogin.add(lblNewLabel);

		


		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (edtUsername.getText() == null || edtUsername.getText().length() <= 0) {
					JOptionPane.showMessageDialog(frame, "Please enter user name", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (edtPassword.getText() == null || edtPassword.getText().length() <= 0) {
					JOptionPane.showMessageDialog(frame, "Please enter password", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				LoginUtils loginUtils = new LoginUtils();
				String username = edtUsername.getText().trim();
				String password = edtPassword.getText().trim();
				Admin admin = new Admin(username, password);

				String result = loginUtils.login(admin);

				if (!result.equals("SuccessfullyLogin")) {
					JOptionPane.showMessageDialog(frame, result, "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					// new mainBoard();
					frame.dispose();
				}
			}
		});

		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (edtUsername.getText() == null || edtUsername.getText().length() <= 0) {
					JOptionPane.showMessageDialog(frame, "Please enter user name", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (edtPassword.getText() == null || edtPassword.getText().length() <= 0) {
					JOptionPane.showMessageDialog(frame, "Please enter password", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				LoginUtils loginUtils = new LoginUtils();
				String username = edtUsername.getText().trim();
				String password = edtPassword.getText().trim();
				Admin admin = new Admin(username, password);

				String result = loginUtils.register(admin);

				if (!result.equals("Registered")) {
					JOptionPane.showMessageDialog(frame, result, "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					frame.dispose();
				}

			}
		});
	}
}
