import java.util.ArrayList;

public class StudentInfo{
	private long uniqueID;
	private String name;
	private String ID;
	private String major;
	private String status; 
	private Float totalGrade;
	private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	public StudentInfo(long uniqueID, String name, String ID, String major, String status) {
		this.name = name;
		this.ID = ID;
		this.major = major;
		this.status = status; 
		this.uniqueID = uniqueID; //database unique identifier to identify column 
		
		
}
	public Float getTotalGrade() {
		Float current=0f;
		int length = getNumAssignments(); 
		for (int x=0; x<length; x++) {
			Float grade = assignments.get(x).getGrade();
			Float weight = assignments.get(x).getWeight();
			current += (grade*weight);
			
		}
		return current;
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
	public int getNumAssignments() { 
		return (assignments.size());
	}
	
	public String getLetterGrade() {
		String letterGrade = "A";
		if(totalGrade<=95 && totalGrade>=90) {
			letterGrade = "A-";
		}
		else if (totalGrade>=87 && totalGrade<90) {
			letterGrade = "B+";
		}
		else if (totalGrade<87 && totalGrade>83) {
			letterGrade = "B";
		}
		else if (totalGrade>=80 && totalGrade<83) {
			letterGrade = "B-";
		}
		else if (totalGrade >= 77 && totalGrade<80 ) {
			letterGrade = "C+";
		}
		else if (totalGrade <77 && totalGrade>73) {
			letterGrade ="C";
		}
		else if(totalGrade<=73 && totalGrade>=70) {
			letterGrade ="C-";
			
		}
		else if(totalGrade <= 69 && totalGrade >= 60) {
			letterGrade = "D";
			
		}
		else if(totalGrade <60) {
			letterGrade = "F"; 
		}
		return letterGrade;
	}
}
