package LeetCode.round1.easy;
/**
 * 160918
Given a column title as appear in an Excel sheet, return its corresponding column number.
For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
 */
public class P171_ExcelSheetColumnNumber {

	//AC: 3ms
	public int titleToNumber(String s) {
		int sum = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			int bit = (s.charAt(i) - (int) 'A') + 1;
			int n = s.length() - i;
			for (int j = 1; j < n; j++)
				bit *= 26;
			sum += bit;
		}
		return sum;
	}

	public static void main(String[] args) {
		P171_ExcelSheetColumnNumber p = new P171_ExcelSheetColumnNumber();
		System.out.println(p.titleToNumber("NTP"));
	}

}
