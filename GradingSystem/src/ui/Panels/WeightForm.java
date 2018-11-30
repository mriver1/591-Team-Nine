package ui.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import fengjun.sqlConnection;
import miranda.StudentInfo;
import miranda.StudentProfileCopy;
import net.proteanit.sql.DbUtils;
import ui.Panels.GradingPanel.MyTableModel;

public class WeightForm extends JPanel{
	private boolean DEBUG = false;
	private String[] categories= {
			"Homework",
			"Midterm",
			"Project",
			"Final",
			"Extra",
			"Attendence",
			"quiz"
		};
	private String[] columnNames = {
			"Assignment ID",
			"Weight(Graduate)",
			"Weight(Undergraduate)",
			"Category"
			};
	
	private Object[][] data = new Object[5][];
	
	private Connection connection = null;
	public WeightForm() {
			
        super(new GridLayout(1,0));
		String query="select * from coursecategory";
		connection=sqlConnection.dbConnection();// connect to database
		
		try {
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				Object[] row = new Object[4];
				for(int j = 0; j < row.length; j++) {
					row[j] = rs.getString(j + 1);
				}
				data[i] = row;
				i += 1;
				System.out.println (rs.getString("Category"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        JTable MyTableModel = new JTable(new MyTableModel());
    
        for(int i = 0; i < MyTableModel.getColumnCount(); i++) {
        	MyTableModel.getColumnModel().getColumn(0).setPreferredWidth(200);
        }
        
        MyTableModel tableModel = (MyTableModel) MyTableModel.getModel();
        MyTableModel.setPreferredScrollableViewportSize(new Dimension(500, 70));
        MyTableModel.setFillsViewportHeight(true);
        
        
        MyTableModel.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = MyTableModel.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
              String selectedData = null;

              int selectedRow = MyTableModel.getSelectedRow();
              int selectedColumn = MyTableModel.getSelectedColumn();
              
              switch (selectedColumn) {
              	case 0:
              		String inputValue = JOptionPane.showInputDialog(null, "Please input the assignment ID: ", "Assignment ID"); 
              		break;
              	case 1:
              		inputValue = JOptionPane.showInputDialog(null, "Please input the weight for graduate student: ", ""); 
              		break;
              	case 2:
              		inputValue = JOptionPane.showInputDialog(null, "Please input the weight for undergraduate student: ", ""); 
              		break;
              	case 3: 
              		Object selectedValue = JOptionPane.showInputDialog(null, "Choose the assignment category", 
              				"Assignment Category", JOptionPane.INFORMATION_MESSAGE, null, categories, categories[0]);
              		break;
              }
            }

          });
        
        
        JScrollPane scrollPane = new JScrollPane(MyTableModel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add the scroll pane to this panel.
        add(scrollPane);
	}
	
	class MyTableModel extends AbstractTableModel {
		
		public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }


        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
	}
	

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));
        //Create and set up the content pane.
        WeightForm newContentPane = new WeightForm();
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
