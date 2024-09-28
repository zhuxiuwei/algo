package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 240928 easy
 * https://leetcode.com/problems/valid-palindrome/?envType=study-plan-v2&envId=top-interview-150
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
 * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * Constraints:
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 */
public class P125_ValidPalindrome {
    /**
     * 总体还行吧。
     * ！！！！主要是：回文除了英文字符，也可以包含数字！！
     * AC: Runtime 3 ms Beats 72.89%, Memory 45.02 MB Beats 22.21%
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if((c >= 'a' && c <= 'z') ||
                    (c >= '0' && c <= '9') ) {      //！！！不能漏了，允许包含数字！！！！！！！！！
                chars.add(c);
            }
        }
        int left = 0, end = chars.size() - 1;
        while (left <= end){
            char charL = chars.get(left), charR = chars.get(end);
            if(charL == charR){
                left ++;
                end --;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P125_ValidPalindrome p = new P125_ValidPalindrome();
        System.out.println(p.isPalindrome("A man, a plan, a canal: Panama"));   //true
        System.out.println(p.isPalindrome("race a car"));       //false
        System.out.println(p.isPalindrome(" "));        //true
        System.out.println(p.isPalindrome("0P"));        //false

    }
}
