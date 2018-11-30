package ui.Panels;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class ToolBar extends JPanel{
	public ToolBar() {
		
		initTool();
		
		
		
	}

	private void initTool() {
		setLayout(null);
		// TODO Auto-generated method stub
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 534, 22);
		this.add(toolBar);
		
		JButton newbtn = new JButton("New");
		toolBar.add(newbtn);
		
		JButton savebtn = new JButton("Save...");
		toolBar.add(savebtn);
		
		JButton helpbtn = new JButton("Help");
		toolBar.add(helpbtn);
		
		JButton logoutbtn = new JButton("Log out");
		toolBar.add(logoutbtn);
		

	}
	
}
