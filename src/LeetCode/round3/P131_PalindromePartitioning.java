package LeetCode.round3;

import java.util.List;

/**
 * 240902 medium
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * A palindrome is a string that reads the same forward and backward.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class P131_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        return null;
    }

    public static void main(String[] args) {
        P131_PalindromePartitioning p = new P131_PalindromePartitioning();
        System.out.println(p.partition("aab")); //[["a","a","b"],["aa","b"]]
        System.out.println(p.partition("ababa")); //[["a","b","a","b","a"],["a","b","aba"],["a","bab","a"],["aba","b","a"],["ababa"]]
        System.out.println(p.partition("ababab")); //[["a","b","a","b","a","b"],["a","b","a","bab"],["a","b","aba","b"],["a","bab","a","b"],["a","babab"],["aba","b","a","b"],["aba","bab"],["ababa","b"]]
    }
}
