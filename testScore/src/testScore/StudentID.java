package testScore;

public class StudentID {
    private String studentID;
    
    public StudentID() { this("N/A"); }
    public StudentID(String studentID) { this.studentID = studentID; }
    
    public String getStudentID() { return studentID; }
    
    public void setStudentID(String studentID) { this.studentID = studentID; }
}