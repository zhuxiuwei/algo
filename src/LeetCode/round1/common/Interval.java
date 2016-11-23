package LeetCode.round1.common;

public class Interval {
	public int start;
	public int end;
	public Interval() { start = 0; end = 0; }
	public Interval(int s, int e) { start = s; end = e; }
	public String toString(){
		return String.format("[%s,%s]", start, end);
	}
}