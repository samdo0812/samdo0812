package testScore;

public class ScoreManager
{
    StudentID studentID;
	Name name;
	Subject kor;
	Subject eng;
	Subject math;

	public ScoreManager()
	{
	    studentID = new StudentID();
        name      = new Name();
		kor       = new Subject();
		eng       = new Subject();
		math      = new Subject();
	}
	
	public ScoreManager(String studentID,String name, int kor, int eng, int math)
	{
        this.studentID = new StudentID(studentID);
        this.name      = new Name(name);
		this.kor       = new Subject(kor);
		this.eng       = new Subject(eng);
		this.math      = new Subject(math);
	}

    public String getStudentID() { return studentID.getStudentID(); }
    public String getName() { return name.getName(); }
	public int getKor()     { return kor.getScore(); }
	public int getEng()     { return eng.getScore(); }
	public int getMath()    { return math.getScore(); }

	public void setStudentID(String studentID) { this.studentID.setStudentID(studentID); }
	public void setName(String name)           { this.name.setName(name); }
	public void setKor(int kor)                { this.kor.setScore(kor); }
	public void setEng(int eng)                { this.eng.setScore(eng); }
	public void setMath(int math)              { this.math.setScore(math); }

	public int getSum()    { return kor.getScore() + eng.getScore() + math.getScore(); }
	public double getAvg() { return getSum() / 3.0; }
	
	public String toString()
	{
		return String.format("%10s %16s %8d %8d %8d %8d %10.2f", studentID.getStudentID(), name.getName(), kor.getScore(), eng.getScore(), math.getScore(), getSum(), getAvg());
	}
}
