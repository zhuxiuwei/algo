package LeetCode.round1.medium;
/**
 * 161201, 1206
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times.
Find the length of a longest substring containing all repeating letters you can get after performing the above operations. Note: Both the string's length and k will not exceed 10^4.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA". The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class P424_LongestRepeatingCharacterReplacement {

	/**
	 * SLIDING WINDOW。不会，refer: https://discuss.leetcode.com/topic/64833/sliding-window-java-easy-explanation-15-lines
	 * @param s
	 * @param k
	 * @return
	 */
	/*
    The whole idea is that if we have a string of length N out of which M characters are same,
    we can replace (N - M) characters to get a continueous string of N characters. 
    If M <= K. N is the local maximum for this window.
    If this length is greater than K. Slide the window.
    */
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26];
        int left, right, maxCount, maxLen;
        left = right = maxCount = maxLen = 0;
        while(right < s.length()){
            charCount[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, charCount[s.charAt(right) - 'A']);
            int t = right - left + 1 - maxCount;
            if(t > k) 
            	charCount[s.charAt(left++) - 'A']--;
            int t2 = right++ - left + 1;
            maxLen = Math.max(t2, maxLen);
        }
        return maxLen;
    }
	
	public static void main(String[] args) {
		P424_LongestRepeatingCharacterReplacement p = new P424_LongestRepeatingCharacterReplacement();
		System.out.println(p.characterReplacement("ABAB", 2));	//4
		System.out.println(p.characterReplacement("AABABBA", 1));	//4
	}

}
