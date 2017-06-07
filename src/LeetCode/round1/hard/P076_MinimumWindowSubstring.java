package LeetCode.round1.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 170607
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class P076_MinimumWindowSubstring {

	/**
	 * 一次AC. 和之前Anagram的题目一样套路，用滑动窗口方法。
	 * 25.08%。  
	 */
	public String minWindow(String s, String t) {
		if(s == null || t == null || s.length() == 0 || t.length() == 0)
			return "";
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			int val = map.getOrDefault(c, 0);
			map.put(c, val + 1);
		}
		
		int left = 0, right = 0, counter = map.size();
		String res = "";
		while(right < s.length()){
			char c = s.charAt(right);
			if(map.containsKey(c)){
				int val = map.get(c) - 1;
				map.put(c, val);
				if(val == 0)
					counter --;
			}
			
			while(counter == 0){
				char c2 = s.charAt(left);
				if(map.containsKey(c2)){
					int val = map.get(c2) + 1;
					if(val > 0)
						counter ++;
					map.put(c2, val);
				}
				
				if(counter > 0){
					String sb = s.substring(left, right + 1);
					if(res.equals("")){
						res = sb;
					}else if(res.length() > sb.length()){
						res = sb;
					}
				}
				
				left ++;
			}
			
			right ++;
		}
		
		return res;
    }
	
	public static void main(String[] args) {
		P076_MinimumWindowSubstring p = new P076_MinimumWindowSubstring();
		System.out.println(p.minWindow("ADOBECODEBANC", "ABC"));
	}

}
