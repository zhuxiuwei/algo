package LeetCode.round1.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 161010
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
 */
public class P318_MaximumProductOfWordLengths {

	/**
	 * go through each word, and use set to de-dup.
	 * Correct, but failed to AC due to timeout for large input.
	 */
	public int maxProduct_FailTimeOut(String[] words) {
		if(words == null)
			return 0;
		
		int res = 0;
		for (int i = 0; i < words.length - 1; i++) {
			Set<Character> s = new HashSet<Character>();
			for (int j = 0; j < words[i].length(); j++) 
				s.add(words[i].charAt(j));
			boolean ok = true;
			for (int k = i + 1; k < words.length; k++) {
				for (int k2 = 0; k2 < words[k].length(); k2++) {
					if(s.contains(words[k].charAt(k2))){
						ok = false;
						break;
					}else
						ok = true;
				}
				if(ok){
					int temp = words[i].length() * words[k].length();
					if(res < temp)
						res = temp;
				}
					
			}
		}
		return res;
    }
	
	public static void main(String[] args) {
		P318_MaximumProductOfWordLengths p = new P318_MaximumProductOfWordLengths();
		System.out.println(p.maxProduct_FailTimeOut(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
	}

}
