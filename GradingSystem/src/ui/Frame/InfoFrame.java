package ui.Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ui.Panels.WeightForm;
import ui.Panels.WeightGrad;
import ui.Panels.WeightPanel;
import ui.Panels.WeightUG;
import utils.CourseTitlePanel;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class InfoFrame extends JFrame{
	public InfoFrame() {
		getContentPane().setLayout(new GridLayout(3,1));
		setSize(700,600);
//		WeightGrad wg = new WeightGrad();
//		WeightUG wu = new WeightUG();
		WeightForm form = new WeightForm();
		CourseTitlePanel panel = new CourseTitlePanel();
		getContentPane().add(panel);
		getContentPane().add(form);
//		getContentPane().add(wg);
//		getContentPane().add(wu);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoFrame frame = new InfoFrame();
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
}
