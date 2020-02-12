package LeetCode.round2;

import java.util.*;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class P003_LongestSubstringWithoutRepeatingCharacters {

    /**
     *Runtime: 5 ms, faster than 88.04% of Java online submissions for Longest Substring Without Repeating Characters.
     Memory Usage: 41.5 MB, less than 5.20% of Java online submissions for Longest Substring Without Repeating Characters.

     总体思路还算顺利，但是写的不快。
     有两个边界条件造成的bug。
     */
    public int lengthOfLongestSubstring(String s) {

        //！！ bug1: 边界条件。不能漏了s.equals("")，否则对于输入""，错误返回1.
        if(s == null || s.equals(""))
            return 0;

        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                end = i;
            }else {
                int temp = end - start + 1;
                if(temp > res)
                    res = temp;
                for (int j = start; j < i; j++) {
                    char c2 = s.charAt(j);
                    if(c2 != c) {
                        map.remove(s.charAt(j));
                    } else {
                        start = j + 1;
                        break;
                    }
                }
            }
            map.put(c, i);
        }

        //！！！ bug2: 下面三行代码不能少了。 否则有可能错误返回0，如"a" "az" 这种case。
        int temp = end - start + 1;
        if(temp > res)
            res = temp;

        return res;
    }

    public static void main(String[] args) {
        P003_LongestSubstringWithoutRepeatingCharacters p = new P003_LongestSubstringWithoutRepeatingCharacters();
        System.out.println(p.lengthOfLongestSubstring("abcabcbb")); //3
        System.out.println(p.lengthOfLongestSubstring("bbb")); //1
        System.out.println(p.lengthOfLongestSubstring("pwwkew")); //3
        System.out.println(p.lengthOfLongestSubstring(" ")); //1
        System.out.println(p.lengthOfLongestSubstring("a")); //1
        System.out.println(p.lengthOfLongestSubstring("az")); //2
        System.out.println(p.lengthOfLongestSubstring("aa")); //1
        System.out.println(p.lengthOfLongestSubstring("")); //0
    }

}
