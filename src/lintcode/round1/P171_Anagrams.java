package lintcode.round1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 170613 Medium
Given an array of strings, return all groups of strings that are anagrams.

Notice:All inputs will be in lower-case
Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].
Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Challenge 
What is Anagram?
- Two strings are anagram if they can be the same after change the order of characters.
 */
public class P171_Anagrams {

	/**
	 * ！！！！一个注意。。。！！！！！！！
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
    	List<String> res = new ArrayList<String>();
    	Set<String> found = new HashSet<String>();
    	if(strs == null || strs.length <= 1)
    		return res;
    	for (int i = 0; i < strs.length; i++) {
    		if(found.contains(strs[i]))
				continue;
			for (int j = i + 1; j < strs.length; j++) {
				String s1 = strs[i];
				String s2 = strs[j];
				if(isAnagram(s1, s2)){
					if(!found.contains(s1)){
						found.add(s1);
						res.add(s1);
					}
					if(!found.contains(s2)){
						found.add(s2);
					}
					/*
					 * 注意不能放在上面的if中，否则去重了。单词 可以重复出现，不能去重。
					 * 比如["ab", "ba", "cd", "dc", "dc","e"]， “dc”在结果中应该出现两次。
					 */
					res.add(s2);	
				}
			}
		}
    	return res;
    }
    public boolean isAnagram(String s, String t) {
    	if(s.length() != t.length())
    		return false;
    	
    	int[] map = new int[128];	//use constant extra space.
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c1 = s.charAt(i);
			map[c1] ++;
		}
    	for (int i = 0; i < t.length(); i++) {
    		char c1 = t.charAt(i);
    		if(map[c1] > 0){
    			map[c1]--;
    		}else
    			map[c1] ++;
		}
    	boolean res = true;
    	for (int i = 0; i < map.length; i++) {
    		if(map[i] > 0){
    			res = false;
    			break;
    		}
		}
    	return res;
    }
	
	public static void main(String[] args) {
		P171_Anagrams p = new P171_Anagrams();
		System.out.println(p.anagrams(new String[]{"lint", "intl", "inlt", "code"}));
		System.out.println(p.anagrams(new String[]{"ab", "ba", "cd", "dc", "dc","e"}));	//[ab, ba, cd, dc, dc] 注意dc要出现两次。
	}

}
