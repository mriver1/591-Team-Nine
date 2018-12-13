package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Assignment;
import beans.Course;

public class AssignmentDao {
	
	private DBconnection dBconnection;
    private String sql;
    private List<Object> params;
    private List<Assignment> assignList;
    private Assignment assignment;
    
	public AssignmentDao() {
        dBconnection = new DBconnection();
        dBconnection.getConnection();
    }
	
	//query assignment list information for a specific course
	public List<Assignment> assignList(int classID) {
		assignList = new ArrayList<>();
		sql = "select * from assignment where classID=?";
    	params = new ArrayList<>();
    	params.add(classID);
    	try {
    		assignList = dBconnection.findMoreRefResult(sql, params, Assignment.class);
            if(assignList != null) {
            	return assignList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
	}
	
	//query a specific assignment information
	public Assignment assignInfo(String assignID, String classID) {
		sql = "select * from course where assignID=? and classID=?";
    	params = new ArrayList<>();
    	params.add(assignID);
    	params.add(classID);

    	try {
    		assignment = dBconnection.findSimpleRefResult(sql, params, Assignment.class);
            if(!assignment.equals(null)) {
            	return assignment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
	}
	

	public Assignment assignInfo(int assignID) {
		sql = "select * from assignment where assignID=?;";
    	params = new ArrayList<>();
    	params.add(assignID);
    	
    	try {
    		assignment = dBconnection.findSimpleRefResult(sql, params, Assignment.class);
            if(!assignment.equals(null)) {
            	return assignment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
	}

	public Assignment getLatest() {
		sql = "select * from assignment order by assignID DESC limit 1";
//    	params = new ArrayList<>();
//    	params.add(assignID);
    	try {
    		assignment = dBconnection.findSimpleRefResult(sql, null, Assignment.class);
            if(!assignment.equals(null)) {
            	return assignment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
	}


	
	public int getID(int selectedColumn, String selectedData, int classid) {
		String col = null;
		Integer assignId = -1;
		switch(selectedColumn) {
		case 0:
			col = "assignName";
			break;
		case 1:
			col = "weightG";
			break;
		case 2:
			col = "weightU";
			break;
		case 3:
			col = "Category";
			break;
		case 4:
			col = "comments";
			break;
		}
		sql = "select assignId from assignment where "+ col +"=? and classID=?";
    	params = new ArrayList<>();
    	params.add(selectedData);
    	params.add(classid);
    	try {
    		assignId = (Integer) dBconnection.findSimpleResult(sql, params).get("assignID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return assignId;
	}
	
	//update the assignmentName
	public boolean updateName(int assignID, String assignName) {
		sql = "update assignment set assignName= ? where assignID= ?";
		params = new ArrayList<>();
		params.add(assignName);
		params.add(assignID);
		
		boolean flag = false;
		try {
			flag = dBconnection.updateByPreparedStatement(sql, params);	
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return flag;
	}
	
	//update WG
	public boolean updateWG(int assignID, Double wg) {
		sql = "update assignment set weightG=? where assignID=?;";
		params = new ArrayList<>();
		params.add(wg);
		params.add(assignID);
		
		boolean flag = false;
		try {
            flag = dBconnection.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return flag;
	}
	
	//updateWU
	public boolean updateWU(int assignID, Double wu) {
		sql = "update assignment set weightU=? where assignID=?;";
		params = new ArrayList<>();
		params.add(wu);
		params.add(assignID);
		
		boolean flag = false;
		try {
            flag = dBconnection.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return flag;
	}
	
	
	//update category
	public boolean updateCategory(int assignID, String category) {
		sql = "update assignment set category=? where assignID=?;";
		params = new ArrayList<>();
		params.add(category);
		params.add(assignID);
		
		try {
            return dBconnection.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return false;
	}
	
	//update comment
	public boolean updateComments(int assignID, String comments) {
		sql = "update assignment set comments=? where assignID=?;";
		params = new ArrayList<>();
		params.add(comments);
		params.add(assignID);
		
		boolean flag = false;
		try {
            flag = dBconnection.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return flag;
	}
	
	public boolean insertAssign(Assignment assignAdd) {
		// TODO Auto-generated method stub
		String assignName = assignAdd.getAssignName();
		Double weightG = assignAdd.getWeightG();
		Double weightU = assignAdd.getWeightU();
		String category = assignAdd.getAssignCat();
		String comments = assignAdd.getComment();
		int classID = assignAdd.getClassID();
		params = new ArrayList<>();
		System.out.println(assignName);
		sql = "insert into assignment(assignName, weightG, weightU, category, comments, classID) values(?,?,?,?,?,?)";
		params.add(assignName);
		params.add(weightG);
		params.add(weightU);
		params.add(category);
		params.add(comments);
		params.add(classID);
		
		boolean flag = false;
		try {
            flag = dBconnection.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return flag;
	}
	

	public boolean delAssign(int uniqueID, String name) {
		// TODO Auto-generated method stub
		sql = "delete from assignment where classID=? and assignName=?";
		params = new ArrayList<>();
		params.add(uniqueID);
		params.add(name);
		
		System.out.println("delete name:" + name);
		boolean flag = false;
		try {
            flag = dBconnection.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return flag;
	}

	
	@Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (dBconnection != null) {
            dBconnection.releaseConn();
            dBconnection = null;
        }

        System.out.println(this.getClass().toString() + "destroyed.");
    }

	public int getIDbyName(int classUniqueID, String assignName) {
		// TODO Auto-generated method stub
		sql = "select assignID from assignment where classID=? and assignName= '" + assignName + "'";
		params = new ArrayList<>();
		params.add(classUniqueID);
		int gradeid = 0;
		try {
			gradeid = (int)dBconnection.findSimpleResult(sql, params).get("assignID");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return gradeid;
	}
	
}
