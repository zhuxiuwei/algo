package lintcode.round1;
/**
 * 170613 Easy
Write a method anagram(s,t) to decide if two strings are anagrams or not.

Clarification
What is Anagram?
- Two strings are anagram if they can be the same after change the order of characters.

Example
Given s = "abcd", t = "dcab", return true.
Given s = "ab", t = "ab", return true.
Given s = "ab", t = "ac", return false.

Challenge : O(n) time, O(1) extra space
 */
public class P158_AmaMoni_TwoStringsAreAnagrams {

	/**
	 * O(n) time, O(1) extra space
	 * ！！！！一个注意：map，必须老老实实的加加减减。不要投机取巧置0或1，当有重复字符会有bug。 ！！！！！
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
    	if((s == null && t != null) || (s != null && t == null))
    		return false;
    	if(s == null && t == null)
    		return false;
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
	
	/**
	 * 用了异或和加和。结果不对。"az", "by"会错误返回true。
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram_wrong(String s, String t) {
    	if((s == null && t != null) || (s != null && t == null))
    		return false;
    	if(s == null && t == null)
    		return false;
    	if(s.length() != t.length())
    		return false;
    	int xor = 0, sum1 = 0, sum2 = 0;
    	for (int i = 0; i < s.length(); i++) {
    		char c1 = s.charAt(i);
    		char c2 = t.charAt(i);
    		sum1 += c1;
    		sum2 += c2;
    		xor = xor ^ c1 ^ c2;
		}
    	return sum1 == sum2 && xor == 0;
    }
	
	public static void main(String[] args) {
		P158_AmaMoni_TwoStringsAreAnagrams p = new P158_AmaMoni_TwoStringsAreAnagrams();
		System.out.println(p.anagram("az", "by"));	//false
		System.out.println(p.anagram("az", "za"));	//true
	}

}
