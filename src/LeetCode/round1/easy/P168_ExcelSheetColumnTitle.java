package LeetCode.round1.easy;
/**
 * 160918
Given a positive integer, return its corresponding column title as appear in an Excel sheet.
For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 */
public class P168_ExcelSheetColumnTitle {

	/**
	 * AC: 0ms
	 * 一次AC。问题是白板写可能写不对，一边看leetcode test result，一边debug来做的。
	 */
	public String convertToTitle(int n) {
		int d = n / 26;
		int r = n % 26;
		
		if(n % 26 == 0){
			d--;
			r = 26;
		}
		
		char s2 = (char)((int)'A' + r - 1);
		if(d > 26){
			String left = convertToTitle(d);
			return left + s2;
		}else{
			char s1 = d == 0? ' ': (char)((int)'A' + d - 1);
	        return (s1 + "" +s2).trim();
		}
    }
	
	public static void main(String[] args) {
		P168_ExcelSheetColumnTitle p = new P168_ExcelSheetColumnTitle();
		System.out.println(p.convertToTitle(10000));
	}
}
