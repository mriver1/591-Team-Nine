package ui.Panels;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import beans.StudentInfo;
import ui.Frame.StudentProfileCopy;
import ui.Panels.GradingPanel.MyTableModel;
import utils.sqlConnection;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class GradingPanel extends JPanel {
    private boolean DEBUG = false;
    private String[] assignmentName;
    private MyTableModel mm = new MyTableModel();
	public static HashMap<String, StudentInfo> fakeStudents = new HashMap<>();
	
	private ArrayList<String> columnNames = new ArrayList<>();
	private ArrayList<Object[]> data = new ArrayList<>();
	
    public GradingPanel() {
        super(new GridLayout(1,0));
//       
//        initCol();
//        initStudent();
        
        fakeStudents.put("U79852612", new StudentInfo(-1, "Miranda", "Rivera", "U79852612", "Computer Science", false));
		fakeStudents.put("U08352066", new StudentInfo(0, "Mengxi", "Wang", "U08352066", "Electrical and Computer Engineering", true));
    		
        JTable MyTableModel = new JTable(new MyTableModel());
        
        for(int i = 0; i < MyTableModel.getColumnCount(); i++) {
        	MyTableModel.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        MyTableModel tableModel = (MyTableModel) MyTableModel.getModel();
        MyTableModel.setPreferredScrollableViewportSize(new Dimension(500, 70));
        MyTableModel.setFillsViewportHeight(true);

        //add cell selection event
        MyTableModel.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = MyTableModel.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
              String selectedData = null;

              int selectedRow = MyTableModel.getSelectedRow();
              int selectedColumn = MyTableModel.getSelectedColumn();
              if(selectedColumn == 0) {
                  selectedData = (String) MyTableModel.getValueAt(selectedRow, selectedColumn);
                  if(fakeStudents.containsKey(selectedData)) {
                	  StudentInfo s = fakeStudents.get(selectedData);
                	  StudentProfileCopy frame = new StudentProfileCopy(s);
                	  frame.setVisible(true);
                	  frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                  }else {
                	  JOptionPane.showMessageDialog(null, "Can't find the information of this student!", "Error", JOptionPane.ERROR_MESSAGE);
                  }
              }else {
            	  JOptionPane.showMessageDialog(null, "Click the student ID to view the student information!", "Error", JOptionPane.ERROR_MESSAGE);
              }
            }

          });
        JScrollPane scrollPane = new JScrollPane(MyTableModel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    /*
    private void initCol() {
    	assignmentName = mm.getAssignmentList();
		columnNames.add("ID");
		columnNames.add("First Name");
		columnNames.add("Last Name");
		for(int i = 0; i < assignmentName.length; i++) {
			columnNames.add(assignmentName[i]);
		}
		columnNames.add("Total");
		columnNames.add("Graduate");
    }
    
    private void initStudent() {
    	// TODO Auto-generated method stub
		//student info init
		Connection connection = null;
		String query="select * from STUDENT";
		connection=sqlConnection.dbConnection();// connect to database
		try {
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Object[] row = new Object[columnNames.size()];
				for(int j = 0; j < row.length; j++) {
					if(row.equals(null)) {
						break;
					}
					row[j] = rs.getString(j + 1);
				}
				data.add(row);
//				System.out.println (rs.getString("Category"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

*/
    class MyTableModel extends AbstractTableModel {

    	
		private String[] columnNames = {"ID",
										"First Name",
                                        "Last Name",
                                        "Assignment1",
                                        "Assignment2",
                                        "Assignment3",
                                        "Assignment4",
                                        "Assignment5",
                                        "Total",
                                        "Graduate"};
                                 
        private Object[][] data = {
	    {
	    	"U79852612",
	    "Miranda", 
	    "Rivera",
	     new Float(90.0), 
	     new Float(90.0), 
	     new Float(90.0), 
	     new Float(90.0),
	     new Float(0.0),
	     new Float(90.0),
	     new Boolean(false)},
	    {
	    	 "U08352066",
	    	 "Mengxi", 
	 	    "Wang",
	 	     new Float(90.0), 
	 	     new Float(90.0), 
	 	     new Float(90.0), 
	 	     new Float(90.0),
	 	     new Float(0.0),
	 	     new Float(90.0),
	 	     new Boolean(true)}
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public String[] getAssignmentList() {
			// TODO Auto-generated method stub
			return null;
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
        GradingPanel newContentPane = new GradingPanel();
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


