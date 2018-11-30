package ui.Panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class WeightUG extends JPanel{
	public WeightUG() {
		JLabel lblNewLabel = new JLabel("Weights for Undergraduate Students");
		lblNewLabel.setFont(UIManager.getFont("TitledBorder.font"));
		add(lblNewLabel, BorderLayout.NORTH);
		
		WeightForm form = new WeightForm();
		add(lblNewLabel, BorderLayout.CENTER);
	}
	
}
