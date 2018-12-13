package uis.Frame;



import beans.LoginUser;
import daos.LoginDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		panelLogin.setBackground(new Color(139, 0, 0));
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);

		JLabel labelUsername = new JLabel("Username");
		labelUsername.setForeground(new Color(255, 248, 220));
		labelUsername.setBounds(83, 77, 80, 16);
		panelLogin.add(labelUsername);

		edtUsername = new JTextField();
		edtUsername.setBounds(175, 72, 130, 26);
		panelLogin.add(edtUsername);
		edtUsername.setColumns(10);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setForeground(new Color(255, 248, 220));
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
		lblNewLabel.setFont(new Font("Toppan Bunkyu Midashi Gothic", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(255, 248, 220));
		lblNewLabel.setBounds(51, 6, 200, 35);
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

				LoginDao loginDao = new LoginDao();
				String username = edtUsername.getText().trim();
				String password = edtPassword.getText().trim();
				LoginUser user = new LoginUser(username, password);

				String result = loginDao.login(user);

				if (!result.equals("SuccessfullyLogin")) {
					JOptionPane.showMessageDialog(frame, result, "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					// new mainBoard();
					frame.dispose();
					StartFrame sf = new StartFrame();
					sf.setVisible(true);
					sf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

				LoginDao loginDao = new LoginDao();
				String username = edtUsername.getText().trim();
				String password = edtPassword.getText().trim();
				LoginUser admin = new LoginUser(username, password);

				String result = loginDao.register(admin);

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
