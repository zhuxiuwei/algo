package LeetCode.round1.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 170601
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
For example, Given s = “eceba” and k = 2,
RESULT is "ece" which its length is 3.
 */
public class P340_LongestSubstringWithAtMostKDistinctCharacters {

	/**
	 * 利用滑动窗口。
	 * ！！！！！！！注意1个小bug！！！！！！！！！
	 * @param s
	 * @param k
	 * @return
	 */
	public int lengthOfLongestSubstringKDistinct(String s, int k){
		if(s == null || s.length() == 0 || k <= 0)
			return 0;
		int max = Integer.MIN_VALUE, cur = 0;
		int left = 0, right = 0, counter = k;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		while(right < s.length()){
			char c = s.charAt(right);
			if(map.containsKey(c)){
				map.put(c, map.get(c) + 1);
				cur ++;
				if(cur > max)
					max = cur;
			}else{
				counter -- ;
				map.put(c, 1);
				cur ++;
				if(map.size() <= k){
					if(cur > max)
						max = cur;
				}else{
					while(counter < 0){
						char c2 = s.charAt(left);
						int val = map.get(c2) - 1;
						if(val == 0){
							map.remove(c2);
							counter ++;
						}else{
							map.put(c2, val);
						}
						left ++;
						cur --;
					}
				}
			}
			right ++;
		}
		return max;
	}
	
	public static void main(String[] args) {
		P340_LongestSubstringWithAtMostKDistinctCharacters p = new P340_LongestSubstringWithAtMostKDistinctCharacters();
		System.out.println(p.lengthOfLongestSubstringKDistinct("eceba", 2)); //3
		System.out.println(p.lengthOfLongestSubstringKDistinct("eceebbaaaaad", 4)); //11
		System.out.println(p.lengthOfLongestSubstringKDistinct("eceebbaaaaad", 3)); //9
		System.out.println(p.lengthOfLongestSubstringKDistinct("eceebbaaaaad", 2)); //7
		System.out.println(p.lengthOfLongestSubstringKDistinct("eceebbaaaaad", 1)); //5
	}

}
