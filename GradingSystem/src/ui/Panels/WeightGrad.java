package ui.Panels;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class WeightGrad  extends JPanel{
	
	public WeightGrad() {
		JLabel lblNewLabel = new JLabel("Weights for Graduate Students");
		lblNewLabel.setFont(UIManager.getFont("TitledBorder.font"));
		add(lblNewLabel, BorderLayout.NORTH);
		
		WeightForm form = new WeightForm();
		add(lblNewLabel, BorderLayout.CENTER);
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));
        //Create and set up the content pane.
        WeightGrad newContentPane = new WeightGrad();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
}