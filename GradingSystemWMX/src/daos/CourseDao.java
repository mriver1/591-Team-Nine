package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Course;
import beans.LoginUser;

public class CourseDao {
    private DBconnection dBconnection;
    private String sql;
    private List<Object> params;
    private List<Course> courseList;
    private Course course;
    private List<String> courseID = new ArrayList<>();
    
    public CourseDao() {
        dBconnection = new DBconnection();
        dBconnection.getConnection();
    }
    
    //query the course list
    public List<Course> courseImport() {
    	sql = "select * from course";
    	try {
    		courseList = dBconnection.findMoreRefResult(sql, params, Course.class);
            if(courseList != null) {
            	return courseList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }

    //query information for a specific course
    public Course courseInfo(String id) {
    	sql = "select * from course where classID=?";
    	params = new ArrayList<>();
    	params.add(id);
    	try {
    		course = dBconnection.findSimpleRefResult(sql, params, Course.class);
            if(course != null) {
            	return course;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }

    //query uniqueID for specific class
    public int getuniqueID(String classID, String classTerm, String classYear) {
    	sql = "select uniqueID from course where classID=? and classTerm=? and classYear=?";
    	params = new ArrayList<>();
    	params.add(classID);
    	params.add(classTerm);
    	params.add(classYear);
    	Integer uniqueID = -1;
    	try {
    		uniqueID = (Integer) dBconnection.findSimpleResult(sql, params).get("uniqueID");
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return uniqueID;
    }
    
    //query specific class based on uniqueID
    public Course getCourse(int uniqueID) {
    	sql = "select * from course where uniqueID=?";
    	params = new ArrayList<>();
    	params.add(uniqueID);
    	
    	course = new Course();
    	try {
    		if(!course.equals(null)) {
        		course = dBconnection.findSimpleRefResult(sql, params, Course.class);
    		}
    		return course;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return course;
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


}
