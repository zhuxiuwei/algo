package LeetCode.round1.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 160917 countNumbersWithUniqueDigits_StringAndSet_fail, 161120 AC pass.
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 */
public class P357_CountNumbersWithUniqueDigits {

	/**
	 * One time AC: 0ms.
	 * 
	 * DP problem.
	 * General idea:
	 * f(0) = 1;
	 * f(1) = 9 + f(0) = 10;
	 * f(2) = 9 * 9 + f(1);
	 * f(3) = 8 * 9 * 9 + f(2);
	 * ...
	 * f(10) = 1*2*3*4*5*6*7*8*9*9 + f(9);
	 * f(11) = f(10);
	 * f(12) = f(10);
	 * ...
	 * 
	 */
	public int countNumbersWithUniqueDigits(int n) {
		if(n == 0)
			return 1;
		
		int res[] = new int[10];
		res[0] = 1;
		for (int i = 1; i < 10; i++) {
			res[i] = 9;
			for (int j = 0; j < i - 1; j++) 
				res[i] *= 9 - j; 
			res[i] += res[i - 1];
			if(n == i)
				return res[i];
		}
		return res[9];
    }
	
	/**
	 * Not AC: String + set, correct answer, but timeout at n=7.
	 */
	public int countNumbersWithUniqueDigits_StringAndSet_fail(int n) {
		int res = 0;
		for (int i = 0; i < Math.pow(10, n); i++) {
			if(isUnique(i))
				res ++;
//			else
//				System.out.print(i + " ");
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
		System.out.println(p.countNumbersWithUniqueDigits_StringAndSet_fail(1));
		System.out.println(p.countNumbersWithUniqueDigits_StringAndSet_fail(2));
		System.out.println(p.countNumbersWithUniqueDigits_StringAndSet_fail(3));
		System.out.println(p.countNumbersWithUniqueDigits_StringAndSet_fail(4));
		System.out.println(p.countNumbersWithUniqueDigits_StringAndSet_fail(10));
		
		System.out.println(p.countNumbersWithUniqueDigits(1));
		System.out.println(p.countNumbersWithUniqueDigits(2));
		System.out.println(p.countNumbersWithUniqueDigits(3));
		System.out.println(p.countNumbersWithUniqueDigits(4));
		System.out.println(p.countNumbersWithUniqueDigits(10));
		System.out.println(p.countNumbersWithUniqueDigits(11));
	}

}
