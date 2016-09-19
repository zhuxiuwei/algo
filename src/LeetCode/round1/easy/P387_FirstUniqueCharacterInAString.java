package LeetCode.round1.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 160919
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:
s = "leetcode"
return 0.
s = "loveleetcode",
return 2.

Note: You may assume the string contain only lowercase letters.
 */

/**
 * 一次AC。
 * 注意要返回的是index，不是返回char。返回char的话更简单。
 */
public class P387_FirstUniqueCharacterInAString {

	/**
	 * AC: 30ms
	 * Time: O(N), space: 26x2 two-dimensional int array + O(n), O(n)
	 */
	public int firstUniqChar(String s) {
		Queue<Integer> q = new LinkedList<Integer>();
		int[][] count = new int[26][2];	//first dimension: character count. second dimension: first time appear index.
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int idx = c - 'a';
			count[idx][0]++;
			if(count[idx][0] == 1){
				q.add(i);
				count[idx][1] = i;
			}
			else if(count[idx][0] == 2)	
				q.remove(count[idx][1]);	//just need remove once.
		}
		//System.out.println(q);
        return q.size() == 0 ? -1: q.peek();
    }
	
	public static void main(String[] args) {
		P387_FirstUniqueCharacterInAString p = new P387_FirstUniqueCharacterInAString();
		System.out.println(p.firstUniqChar("leetcode"));
		System.out.println(p.firstUniqChar("loveleetcode"));
		System.out.println(p.firstUniqChar("lmssml"));
	}

}
