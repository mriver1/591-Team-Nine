package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Student;

public class EnrollDao {
	private DBconnection dBconnection;
    private String sql;
    private List<Object> params;
    private List<Student> studentList ;
    private Student student;
    private List<Object> obj;
    
//    int classID;
//    int studentID;
    
    public EnrollDao() {
        dBconnection = new DBconnection();
        dBconnection.getConnection();
    }
    
    
    public boolean updateEnroll(int classID, int studentID) {
    	sql = "insert into enrollments (studentid, classid) values (?,?)";
    	params = new ArrayList<>();
    	params.add(studentID);
    	params.add(classID);
    	
    	try {
    		return dBconnection.updateByPreparedStatement(sql, params);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return false;
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


	public boolean removeEnroll(int sid, int classUniqueID) {
		// TODO Auto-generated method stub
		sql = "delete from enrollments where studentid=? and classid=?";
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
