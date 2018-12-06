package ui.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

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
import javax.swing.table.DefaultTableModel;

import beans.Assignment;
import beans.Course;
import beans.StudentInfo;
import net.proteanit.sql.DbUtils;
import ui.Frame.InfoFrame;
import ui.Frame.StudentProfileCopy;
import ui.Panels.GradingPanel.MyTableModel;
import utils.sqlConnection;


public class WeightForm extends JPanel{
	private static boolean DEBUG = false;
	private static boolean emptyFlag = false;
	
	private static Course course;
	private static String classID;
	private static String classTerm;
	private static String classYear;
	private static String className;
	private static Float classCredit;
	private static int classUniqueID;	
	private static String[] categories;
	
	private static ArrayList<Assignment>data = new ArrayList<>();
	private Connection connection = null;
	private static String[] columnNames = {
			"Assignment Name",
			"Weight(Graduate)",
			"Weight(Undergraduate)",
			"Category"
		};
	
	public WeightForm() {
        super(new GridLayout(1,0));
        initData();
        init();
	}
	
    private void initData() {
		// TODO Add data from corresponding table
		
    	String query="select * from '" + classID + "';";
		connection=sqlConnection.dbConnection();// connect to database
		
		try {
			PreparedStatement pst;
			pst = connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				Assignment assign = new Assignment();
				assign.setName(rs.getString(1));
				assign.setWeighG(rs.getFloat(2));
				assign.setWeightU(rs.getFloat(3));
				assign.setCategory(rs.getString(4));;
				data.add(assign);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(data);
	}

	private void init() {
		// TODO Auto-generated method stub
		JTable MyTableModel = new JTable(new MyTableModel());
		MyTableModel tableModel = (MyTableModel) MyTableModel.getModel();
		
		
    	MyTableModel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    	MyTableModel.setAutoCreateColumnsFromModel(false);
    	MyTableModel.setAutoCreateRowSorter(true);
        for(int i = 0; i < MyTableModel.getColumnCount(); i++) {
        	MyTableModel.getColumnModel().getColumn(0).setPreferredWidth(200);
        }
        tableModel.setRowCount( 0 );
        
        MyTableModel.setPreferredScrollableViewportSize(new Dimension(688, 284));
        MyTableModel.setFillsViewportHeight(false);
        
//        MyTableModel.addTableModelListener(new )
        MyTableModel.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = MyTableModel.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
              Object selectedData = null;

              int selectedRow = MyTableModel.getSelectedRow();
              int selectedColumn = MyTableModel.getSelectedColumn();
              selectedData = MyTableModel.getValueAt(selectedRow, selectedColumn);
              Object[] show = new Object[4]; 
              for(int i = 0; i < 4; i ++) {
            	  //selectedRow, i
            	  show[i] = MyTableModel.getValueAt(selectedRow, i);
              }
              
//              InfoFrame.showdata(show);
              switch (selectedColumn) {
              	case 0:
              		String inputValue = JOptionPane.showInputDialog(null, "Please input the assignment name: ", "Assignment name"); 
              		if(inputValue.equals(null)) {
              			String[] options = { "OK", "CANCEL" }; 
              			int choice = JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", 
              			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
              			null, options, options[0]); 
              		} else {
                  	MyTableModel.setValueAt(inputValue, selectedRow, 0);
              	}
              		break;
              	case 1:
              		inputValue = JOptionPane.showInputDialog(null, "Please input the weight for graduate student: ", ""); 
              		if(inputValue.equals(null)) {
              			Object[] options = { "OK", "CANCEL" }; 
              			JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", 
              			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
              			null, options, options[0]); 
              		} else {
                  		MyTableModel.setValueAt(inputValue, selectedRow, 1);
              		}
              		break;
              	case 2:
              		inputValue = JOptionPane.showInputDialog(null, "Please input the weight for undergraduate student: ", ""); 
              		MyTableModel.setValueAt(inputValue, selectedRow, 2);
              		if(inputValue.equals(null)) {
              			Object[] options = { "OK", "CANCEL" }; 
              			JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", 
              			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
              			null, options, options[0]); 
              		} else {
                  		MyTableModel.setValueAt(inputValue, selectedRow, 2);
              		}
              		break;
              	case 3: 
              		Object selectedValue = JOptionPane.showInputDialog(null, "Choose the assignment category", 
              				"Assignment Category", JOptionPane.INFORMATION_MESSAGE, null, categories, categories[0]);
              		if(selectedValue.equals(null)) {
              			Object[] options = { "OK", "CANCEL" }; 
              			JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", 
              			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
              			null, options, options[0]); 
              		} else {
                  		MyTableModel.setValueAt(selectedValue, selectedRow, 3);
              		}
              		break;
              }
            }

          });
        
        JScrollPane scrollPane = new JScrollPane(MyTableModel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add the scroll pane to this panel.
        add(scrollPane);

	}

	public static ArrayList<Assignment> getData() {
    	return data;
    }
    
    public static void setData(ArrayList<Assignment> updateData) {
    	data = updateData;
    }
    
    public static void addData(Assignment addData) {
    	data.add(addData);
    }
    
    public static boolean isEmpty() {
    	if(data.equals(null) || data.size() == 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
	
	  static class MyTableModel extends DefaultTableModel {

		public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            switch(col) {
        		case 0:
        			return data.get(row).getName();
        		case 1:
        			return data.get(row).getWeightGraduate();
        		case 2:
        			return data.get(row).getWeightUndergraduate();
        		case 3:
        			return data.get(row).getWeightUndergraduate();
        		default:
        			return null;
            }
        }

//        public Class getColumnClass(int c) {
//            return getValueAt(0, c).getClass();
//        }

        public boolean isCellEditable(int row, int col) {
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
        
        //update
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
            switch(col) {
            	case 0:
            		data.get(row).setName((String)value);
            		break;
            	case 1:
            		data.get(row).setWeighG((Float)value);
            		break;
            	case 2:
            		data.get(row).setWeightU((Float)value);
            		break;
            	case 3:
            		data.get(row).setCategory((String)value);
            		break;
            }
            
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
                    System.out.print("  " + data.get(i).printInfo().get(j));
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
//
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.length;
		}
		
		public String[] getAssignmentList() {
			// TODO Auto-generated method stub
			String[] nameList = new String[getRowCount()];
			for(int i = 0; i < nameList.length; i++) {
				nameList[i] = (String) getValueAt(i, 0);
			}
			return nameList;
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
	public static void addAssignment(Assignment addAssign) {
		// TODO Auto-generated method stub
		data.add(addAssign);
	}

	public static ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<>();
		for(Assignment assign : data) {
			names.add(assign.getName());
		}
		return names;
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public static void delete(ArrayList<String> delItems) {
		// TODO Auto-generated method stub		
		for(String name : delItems) {
			for(Assignment assign : data) {
				if(name.equals(assign.getName())) {
					data.remove(assign);
					break;
				}
			}
		}
	}
	
	public static void update() {
		
	}

	public static WeightForm getWeightForm() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void initClassData(Course classInfo) {
		// TODO Auto-generated method stub
		course = classInfo;
		classID = course.getClassID();
		className = course.getClassName();
		classYear = course.getClassYear();
		classTerm = course.getClassTerm();
		classCredit = course.getClassCredit();
		classUniqueID = course.getUniqueID();
	}
}
