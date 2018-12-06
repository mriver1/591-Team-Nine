package beans;

import java.util.ArrayList;

public class StudentInfo{
	private long uniqueID;
	private String firstName;
	private String lastName;
	private String ID;
	private String major;
	private Boolean status; 
	
	public StudentInfo(long uniqueID, String firstName, String lastName, String ID, String major, Boolean status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
		this.major = major;
		this.status = status; 
		this.uniqueID = uniqueID; //database unique identifier to identify column 
}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getName() {
		return firstName + lastName; 
	}
	public String getID() {
		return ID;
	}
	public String getMajor() {
		return major;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public long getUniqueID() {
		return uniqueID;
	}
	
}
