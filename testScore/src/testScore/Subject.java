package testScore;

public class Subject
{
	private int score;
	
	public Subject() { this(-1); }	
	public Subject(int score) { this.score = score; }
	
	public void setScore(int score) { this.score = score; }
	
	public int getScore() { return this.score; }
}