package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Assignment;
import beans.Course;
import beans.Student;


public class StudentDao {
	private DBconnection dBconnection;
    private String sql;
    private List<Object> params;
    private List<Student> studentList ;
    private Student student;
    private List<Object> obj;
    
    int classID;
    int studentID;
    
    
    public StudentDao() {
        dBconnection = new DBconnection();
        dBconnection.getConnection();
    }
    
    public List<Student> getStudentlist(int classID) {
    	sql = "select * from students where id in (select studentid from enrollments where classID = ?)";
  //  	sql = "select firstName, lastName,BUID from student,enrollment where student.id = enrollment.studentID and enrollment.classID = ? "
    	params = new ArrayList<>();
    	params.add(classID);
    	
    	try {
    		studentList = dBconnection.findMoreRefResult(sql, params, Student.class);
            if(studentList != null) {
            	return studentList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
    
	public Student getLatest() {
		sql = "select * from students order by id DESC limit 1";
//    	params = new ArrayList<>();
//    	params.add(assignID);
		student = new Student();
    	try {
    		student = dBconnection.findSimpleRefResult(sql, null, Student.class);
            if(!student.equals(null)) {
            	return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
	}
	
	public Student getStudent(int id) {
		// TODO Auto-generated method stub
		sql = "select * from students where id = ?";
    	params = new ArrayList<>();
    	params.add(id);
    	student = new Student();
    	
    	try {
    		student = dBconnection.findSimpleRefResult(sql, params, Student.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return student;
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

	public boolean updateTotal(Double total, int uniqueId) {
		// TODO Auto-generated method stub
		sql = "update students set total=? where id=?";
		params = new ArrayList<>();
		params.add(total);
		params.add(uniqueId);
		
		try {
			return dBconnection.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateLetter(String letter, int uniqueId) {
		// TODO Auto-generated method stub
		sql = "update students set letterGrade=? where id=?";
		params = new ArrayList<>();
		params.add(letter);
		params.add(uniqueId);
		
		try {
			return dBconnection.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public Double getTotal(int studentID) {
		// TODO Auto-generated method stub
		Double total = 0.0;
		sql = "select total from students where id = ?";
		params = new ArrayList<>();
		params.add(studentID);
		try {
			total = (Double)dBconnection.findSimpleResult(sql, params).get("total");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return total;
	}
	
	public boolean addStudent(Student s) {
		sql = "insert into students (BUID, firstname, lastname, major, status) values (?,?,?,?,?)";
		params = new ArrayList<>();
		params.add(s.getBUID());
		params.add(s.getFirstName());
		params.add(s.getLastName());
		params.add(s.getMajor());
		params.add(s.getStatus());
		
		try {
			return dBconnection.updateByPreparedStatement(sql, params);
					
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	public int getIDbyBUID(String buid) {
		// TODO Auto-generated method stub
		sql = "select id from students where buid =?";
		params = new ArrayList<>();
		params.add(buid);
		int id = 0;
		try {
			id = (int) dBconnection.findSimpleResult(sql, params).get("id");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean removeStudent(String buid) {
		// TODO Auto-generated method stub
		sql = "delete from students where buid=?";
		params = new ArrayList<>();
		params.add(buid);
		try{
			return dBconnection.updateByPreparedStatement(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
