

public class StudentInfo{
	private long uniqueID;
	private String name;
	private String ID;
	private String major;
	private String status; 
	
	public StudentInfo(long uniqueID, String name, String ID, String major, String status) {
		this.name = name;
		this.ID = ID;
		this.major = major;
		this.status = status; 
		this.uniqueID = uniqueID; //database unique identifier to identify column 
}
	
	public String getName() {
		return name; 
	}
	public String getID() {
		return ID;
	}
	public String getMajor() {
		return major;
	}
	
	public String getStatus() {
		return status;
	}
	public long getUniqueID() {
		return uniqueID;
	}
}
