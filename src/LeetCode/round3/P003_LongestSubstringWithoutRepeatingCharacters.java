package LeetCode.round3;

import java.util.HashMap;
import java.util.Map;

/**
 240724
 Given a string, find the length of the longest substring without repeating characters.
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
     * AC: 6 ms Beats 68.48%, mem: 49%
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.equals(""))
            return 0;
        int res = 0, tmp = 0, lastStartIdx = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> charToIndexMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(charToIndexMap.containsKey(c)){
                tmp = charToIndexMap.size();
                if(res < tmp)
                    res = tmp;
                int endIdx = charToIndexMap.get(c);
                for (int j = lastStartIdx; j <= endIdx; j++) {
                    charToIndexMap.remove(chars[j]);
                }
                lastStartIdx = endIdx + 1;
            }
            charToIndexMap.put(c, i);
        }

        //！！！！！！！下面這行不能忘了。否则字符串只有一个字母时，会错误返回0
        if(res < charToIndexMap.size()){
            res = charToIndexMap.size();
        }
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
