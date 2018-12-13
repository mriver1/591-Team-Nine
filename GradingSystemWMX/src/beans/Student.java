package beans;

public class Student{
	private int id;
	private String firstName;
	private String lastName;
	private String BUID;
	private String major;
	private Boolean status; 
	private double total = 0.0;
	private String letterGrade = null;
	
	
	public Student() {
		
	}
	
	public Student(String BUID, String firstName, String lastName, String major, 
			Boolean status, Double total, String letterGrade, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.major = major;
		this.status = status; 
		this.total = total;
		this.letterGrade = letterGrade;
		this.BUID = BUID; 
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	
	public String getLetterGrade() {
		return letterGrade;
	}
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	
	public int getUniqueId() {
		return id;
	}
	public void setUniqueId(int uniqueId) {
		this.id = uniqueId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBUID() {
		return BUID;
	}
	public void setBUID(String bUID) {
		BUID = bUID;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
