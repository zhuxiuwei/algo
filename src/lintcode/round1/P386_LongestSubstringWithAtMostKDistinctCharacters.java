package lintcode.round1;

import java.util.HashMap;
import java.util.Map;

/**
 * 170613 Medium
Given a string s, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = "eceba", k = 3, T is "eceb" which its length is 4.

Challenge: O(n), n is the size of the string s.
 */
public class P386_LongestSubstringWithAtMostKDistinctCharacters {
	/**
	 * 总体顺利。
	 * ！！！！！！！！注意一个bug!!!!!!!!!!!
     * @param s : A string
     * @return : The length of the longest substring 
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
    	if(s == null || s.length() == 0 || k <= 0)
    		return 0;
    	int max = 0;
    	int left = 0, right = 0, counter = k;
    	Map<Character, Integer>  map = new HashMap<Character, Integer>();
    	
    	while(right < s.length()){
    		char c = s.charAt(right);
    		if(!map.containsKey(c)){
    			counter --;
    			map.put(c, 1);
    		}else{
    			int v = map.get(c) + 1;
    			map.put(c, v);
    		}
    		
    		if(counter >= 0){	//！！！！注意bug: 不能写成 == 0， 会对["ab", 10]这种case错误返回0.
    			if(right - left + 1 > max){
    				max = right - left + 1;
    			}
    		}
    		
    		while(counter < 0){
    			char c2 = s.charAt(left);
    			int v = map.get(c2) - 1;
    			if(v == 0){
    				map.remove(c2);
    				counter ++;
    			}else{
    				map.put(c2, v);
    			}
    			left ++;
    		}
    		right ++;
    	}
    	
    	return max;
    }
}
