package LeetCode.round1.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 161011
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes(回文) that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input:
"abccccdd"
Output:
7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class P409_LongestPalindrome {

	/**
	 * Use hash. Time: O(n), space: O(n).
	 * AC: 23ms
	 */
	public int longestPalindrome(String s) {
		if(s == null || s.isEmpty())
			return 0;
		
		int res = 0;
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			m.put(c, m.getOrDefault(c, 0) + 1);
		}
		boolean hasSingle = false;
		for (int count: m.values()) {
			if(count % 2 != 0){
				hasSingle = true;
				res += (count - 1);
			}else
				res += count;
		}
        return hasSingle ? res + 1: res;
    }
	
	public static void main(String[] args) {
		P409_LongestPalindrome p = new P409_LongestPalindrome();
		System.out.println(p.longestPalindrome("abccccdd"));
	}

}
