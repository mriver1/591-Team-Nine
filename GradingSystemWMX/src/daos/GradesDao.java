package daos;

import java.util.ArrayList;
import java.util.List;
import beans.Course;
import beans.Assignment;
import beans.Grade;
import beans.Student;

import daos.AssignmentDao;
import daos.CourseDao;
import daos.StudentDao;
import daos.DBconnection;

public class GradesDao {
	private List<Grade> gradeList;
//	private Float grade;
	private String sql;
    private List<Object> params;
    private DBconnection dBconnection;
    public GradesDao(){
        dBconnection = new DBconnection();
        dBconnection.getConnection();
    }
    
    
    public Double getGrade(int studentID, int classID, int assignID) {
    	sql = "select grade from grades where studentID=? and classID=? and assignID=?";
    	params = new ArrayList<>();
    	params.add(studentID);
    	params.add(classID);
    	params.add(assignID);
    	Double grade = 0.0;
    	try {
    		grade = (Double) dBconnection.findSimpleResult(sql, params).get("grade");
    	} catch(Exception e) {
			e.printStackTrace();
    	}
    	return grade;
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

	public int getGradeID(int studentID, int classID, int assignID) {
		sql = "select id from grades where studentID=? and classID=? and assignID=?";
		params = new ArrayList<>();
		params.add(studentID);
		params.add(classID);
		params.add(assignID);
		
		int id = 0;
		try {
			id = (int) dBconnection.findSimpleResult(sql, params).get("id");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean updateTable(int studentID, int classUniqueID, int assignID) {
		// TODO Auto-generated method stub
		sql = "insert into grades (studentid,classid,assignid,grade) values (?,?,?,0.0)";
		params = new ArrayList<>();
		params.add(studentID);
		params.add(classUniqueID);
		params.add(assignID);
//		params.add(null);

		try {
			return dBconnection.updateByPreparedStatement(sql, params);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	
	}

	public boolean updateGrade(Double grade, int studentID, int classID, int assignID) {
		sql = "update grades set grade=? where studentID=? and classID=? and assignID=?";
		params = new ArrayList<>();
		
		params.add(grade);
		params.add(studentID);
		params.add(classID);
		params.add(assignID);

		try {
			return dBconnection.updateByPreparedStatement(sql, params);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getGradeByID(int gradeID) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean removeGrade(int sid, int classUniqueID) {
		// TODO Auto-generated method stub
		sql = "delete from grades where studentid=? and classid = ?";
		params = new ArrayList<>();
		params.add(sid);
		params.add(classUniqueID);
		
		try{
			return dBconnection.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
