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
/**
 * Note:
 * 1. 原始使用Set 去重的方法，超时了；
 * 2. 应该用Bit操作。需要注意，当把一个char映射到一个bit时，用位移'>>'操作，比用幂指运算Math.pow(2, (int)(c - 'a')快很多。
 */
public class P318_MaximumProductOfWordLengths {

	/**
	 * One time AC. 28ms, 77%.
	 * @param words
	 * @return
	 */
	public int maxProduct_bit(String[] words) {
		if(words == null)
			return 0;
		
		int res = 0;
		//turn string to int. a -> 1, b -> 2(10), c -> 4(100)... etc.
		int[] bits = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			int bit = 0;
			for (int j = 0; j < words[i].length(); j++) {
				char c = words[i].charAt(j);
				//!!!!!!!Note: this is much faster than using Math.pow()!!! If use Math.pow(), AC: 134ms, 14%.
				bit = 1 <<(int)(c - 'a');	
				//bit = (int)Math.pow(2, (int)(c - 'a'));
				bits[i] = bits[i] | bit;
			}
		}
		
		for (int i = 0; i < bits.length - 1; i++) {
			for (int j =  i + 1; j < bits.length; j++) {
				if((bits[i] & bits[j]) == 0){	//no common character
					int temp = words[i].length() * words[j].length();
					if(res < temp)
						res = temp;
				}
			}
		}
		return res;
    }
	
	/**
	 * go through each word, and use set to de-dup.
	 * Correct, but failed to AC due to timeout for large input(对于一个大case，bit算法27ms，此算法1200ms+).
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
		System.out.println(p.maxProduct_FailTimeOut(new String[]{"abcwa", "baz", "foo", "bar", "xtfn", "abcdef"}));
	}

}
