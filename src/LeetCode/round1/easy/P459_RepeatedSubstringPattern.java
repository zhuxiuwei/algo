package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 161124
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
Example 1: Input: "abab"   Output: True
Explanation: It's the substring "ab" twice.
Example 2: Input: "aba" Output: False
Example 3: Input: "abcabcabcabc"  Output: True Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class P459_RepeatedSubstringPattern {
	/**
	 * AC: 54ms, 43.7%
	 * !!!!!!! Note： 最后判断返回true false的条件需要小心，写错了一次。 构造pattern什么的倒是很顺利。
	 */
	public boolean repeatedSubstringPattern(String str) {
		if(str == null || str.length() <= 1)
			return false;
		int prefixCurIdx = 0, prefixEndIdx = 0;
		List<Character> prefix = new ArrayList<Character>();
		prefix.add(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c == prefix.get(prefixCurIdx)){	//still match pattern
				prefixCurIdx ++;
				if(prefixCurIdx == prefix.size())
					prefixCurIdx = 0;
			}else{
				for (int j = prefixEndIdx + 1; j <= i ; j++) 	//update pattern
					prefix.add(str.charAt(j));
				prefixEndIdx = i;
				prefixCurIdx = 0;
			}
		}
        return str.charAt(str.length() - 1) == prefix.get(prefixEndIdx) && prefixEndIdx != str.length() - 1;	//Note!!! 注意最后条件不是很好写对。
    }

	public static void main(String[] args) {
		P459_RepeatedSubstringPattern p = new P459_RepeatedSubstringPattern();
		System.out.println(p.repeatedSubstringPattern("aba"));	//false
		System.out.println(p.repeatedSubstringPattern("abab"));	//true
		System.out.println(p.repeatedSubstringPattern("aabaab"));	//true
		System.out.println(p.repeatedSubstringPattern("aabaabc"));	//false
		System.out.println(p.repeatedSubstringPattern("aabcaab"));	//false
		System.out.println(p.repeatedSubstringPattern("abbabb"));	//true

	}
}
