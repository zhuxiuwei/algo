package LeetCode.round3;

import java.util.HashMap;
import java.util.Map;

/**
 * 241014 easy
 * https://leetcode.com/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 */
public class P383_RansomNote {

    /**
     * AC: Runtime 10 ms Beats 58.79%, Memory 44.99 MB Beats 50.74%
     * 顺利
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> charCountInMaga = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            int count = charCountInMaga.getOrDefault(c, 0);
            charCountInMaga.put(c, count + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            int count = charCountInMaga.getOrDefault(c, 0);
            if(count == 0) {
                return false;
            }
            charCountInMaga.put(c, count - 1);
        }
        return true;
    }
}
