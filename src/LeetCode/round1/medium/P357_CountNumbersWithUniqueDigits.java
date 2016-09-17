package LeetCode.round1.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 160917
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 */
public class P357_CountNumbersWithUniqueDigits {

	
	
	/**
	 * Not AC: String + set, correct answer, but timeout at n=7.
	 */
	public int countNumbersWithUniqueDigits_StringAndSet_fail(int n) {
		int res = 0;
		for (int i = 0; i < Math.pow(10, n); i++) {
			if(isUnique(i))
				res ++;
			else
				System.out.print(i + " ");
		}
        return res;
    }
	private boolean isUnique(int n){
		String s = n + "";
		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < s.length(); i++) 
			set.add(s.charAt(i));
		return set.size() == s.length();
	}
	
	public static void main(String[] args) {
		P357_CountNumbersWithUniqueDigits p = new P357_CountNumbersWithUniqueDigits();
	}

}
