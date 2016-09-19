package LeetCode.round1.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 160919
Given two strings s and t, write a function to determine if t is an anagram of s.
For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.
Note: You may assume the string contains only lowercase alphabets.
Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?

注：anagram的含义： 只要s和t包含同样的字符集就行，如"gasdf" "sfgad"是anagram。
 */
public class P242_ValidAnagram {

	/**
	 * AC: 44ms
	 * Time:O(n) Space:O(n)
	 * Can support unicode characters.
	 */
	public boolean isAnagram_hash(String s, String t) {
		if(s.length() != t.length())
			return false;
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) 
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		for (int i = 0; i < t.length(); i++) {
			if(!map.containsKey(t.charAt(i)))
				return false;
			else{
				int v = map.get(t.charAt(i)) - 1;
				if(v > 0)
					map.put(t.charAt(i), v);
				else
					map.remove(t.charAt(i));
			}
		}
        return map.size() == 0;
    }
	
	/**
	 * AC: 6ms
	 * Time:O(n) Space:a 26-bit array.
	 * For lowercase alphabets only
	 */
	public boolean isAnagram_hash2(String s, String t) {
		if(s.length() != t.length())
			return false;
		
		int c[] = new int[26];
		for (int i = 0; i < s.length(); i++){
			int idx = s.charAt(i) - 'a';
			c[idx] ++;
		}
		for (int i = 0; i < t.length(); i++){
			int idx = t.charAt(i) - 'a';
			c[idx] --;
			if(c[idx] < 0)
				return false;
		}
		
		//check array if all 0.
		for (int i = 0; i < c.length; i++) {
			if(c[i] != 0)
				return false;
		}
		
        return true;
    }
	
	public static void main(String[] args) {

	}

}
