package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 170529
 * @author Zhu Xiuwei
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"
Output:
[0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"
Output:
[0, 1, 2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class P438_FindAllAnagramsInAString {

	/**
	 * 使用滑动窗口。滑动窗口有一套特定的解决思路，参考：https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
	 * 思路绕，自己想不出来这个思路。
	 * AC: 77ms,24%
	 */
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new LinkedList<>();
        if(p.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        int begin = 0, end = 0;
        
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);	//map里的值可以为负的。
                if(map.get(c) == 0) counter--;
            }
            end++;
            
            while(counter == 0){	//找到了，下一步定位合理的begin
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == p.length()){
                    result.add(begin);
                }
                begin++;
            }
            
        }
        return result;
    }
	
	/**
	 * 最“朴实”的办法，会导致最后超长的case时间超时。
	 * ！！！！！有两个注意点。！！！！
	 */
	public List<Integer> findAnagrams_timeout(String s, String p) {
		List<Integer> res = new ArrayList<>();
	    if (s == null || s.length() == 0 || p == null || p.length() == 0) return res;
		Map<Character, Integer> map = prepareMap(p);
		boolean in = false, mapChanged = false;
		int last = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(map.containsKey(c)){
				mapChanged = true;
				if(!in){
					in = true;
					last = i;
				}
				int val = map.get(c) - 1;
				if(val == 0)
					map.remove(c);
				else
					map.put(c, val);
				if(map.size() == 0){	//a matched Anagrams found.
					res.add(last);
					i = last ;	//!!!!!!!!!!!!注意，for循环会做i++,所以这里赋值 i= last
					in = false;
					map = prepareMap(p);
					mapChanged = false;
				}
			}else{
				in = false;
				if(mapChanged){	//restore map
					map = prepareMap(p);
					mapChanged = false;
					i = last;	//!!!!!!注意，i从last + 1的位置开始回溯。因为for循环会做i++,所以这里赋值 i= last
				}
			}
				
		}
		return res;
		
    }
	private Map<Character, Integer> prepareMap(String p){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < p.length(); i++) {
			char c = p.charAt(i);
			int val = map.getOrDefault(c, 0) + 1;
			map.put(c, val);
		}
		return map;
	}
	
	public static void main(String[] args) {
		P438_FindAllAnagramsInAString p = new P438_FindAllAnagramsInAString();
		System.out.println(p.findAnagrams("abab", "ab"));	//[0,1,2]
		System.out.println(p.findAnagrams("cbaebabacd", "abc"));	//[0,6]
		System.out.println(p.findAnagrams("abacbabc", "abc"));	//[1,2,3,5]
	}

}
