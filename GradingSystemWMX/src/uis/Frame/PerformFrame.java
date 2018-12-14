package uis.Frame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import beans.Student;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class PerformFrame extends JFrame{
	private static HashMap<String, Integer> letter;
	private static List<Double> totalList = new ArrayList<>();
	
	public PerformFrame(List<Student> studentList) {
		setSize(800,800);
		letter = new HashMap<>();
		letter.put("A", 0);
		letter.put("B", 0);
		letter.put("C", 0);
		letter.put("D", 0);
		
		for(Student student : studentList) {
			String l = student.getLetterGrade();
			if(letter.containsKey(l)) {
				letter.put(l, letter.get(l) + 1);
			}else {
				letter.put(l, 1);
			}

			
			totalList.add(student.getTotal());
		}
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(4, 8, 100, 10));
		
		JLabel lblNewLabel = new JLabel("Highest Score");
		panel_1.add(lblNewLabel);
		
		 Collections.sort(totalList);
		 Double highest = totalList.get(totalList.size() - 1);
		JLabel lblNewLabel_1 = new JLabel(highest.toString());
		panel_1.add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Lowest Score");
		panel_1.add(lblNewLabel_3);
		
		Double lowest = totalList.get(0);
		JLabel lblNewLabel_2 = new JLabel(lowest.toString());
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Average");
		panel_1.add(lblNewLabel_4);
		
		Double all = 0.0;
		for(Double d : totalList) {
			all += d;
		}
		Double average = all / totalList.size();
		JLabel lblNewLabel_5 = new JLabel(average.toString());
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Median");
		panel_1.add(lblNewLabel_6);
		
		Double median = totalList.get(totalList.size() / 2);
		JLabel lblNewLabel_7 = new JLabel(median.toString());
		panel_1.add(lblNewLabel_7);
		
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
               "Student Performance",
               "Letter Grade",
               "Amount",
               dataset,
               PlotOrientation.VERTICAL,
               true,
               false,
               false);
		CategoryPlot plot=chart.getCategoryPlot();
        CategoryAxis domainAxis=plot.getDomainAxis();
		JPanel panel = new ChartPanel(chart,true);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
	}
	
    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(letter.get("A"), "Grade" ,"A");
        dataset.addValue(letter.get("B"), "Grade" ,"B");
        dataset.addValue(letter.get("C"), "Grade" ,"C");
        dataset.addValue(letter.get("D"), "Grade" ,"D");
        
        return dataset;
}

	
}
