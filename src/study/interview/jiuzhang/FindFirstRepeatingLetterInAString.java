package study.interview.jiuzhang;

import java.util.HashMap;
import java.util.Map;

/**
 * [《Amazon HackerRank OA 面经》](http://www.jiuzhang.com/qa/748/) 170529  
 * Find first repeating letter in a string. 比如输入“abcba”, 返回“a”
 */
public class FindFirstRepeatingLetterInAString {
	public static char find(String s){
		if(s == null || s.length() == 0)
			return ' ';
		int minIdx = Integer.MAX_VALUE;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(map.containsKey(c)){	//a dup found
				if(map.get(c) < minIdx)
					minIdx = map.get(c);
			}
			else
				map.put(c, i);
		}
		return minIdx == Integer.MAX_VALUE ? ' ': s.charAt(minIdx);
	}
	
	public static void main(String[] args) {
		System.out.println(find("abbcca"));	//a
	}
}
