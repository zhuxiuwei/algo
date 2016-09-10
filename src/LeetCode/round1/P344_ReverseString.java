package LeetCode.round1;
/***
 * 160908
 * Difficulty: Easy
 * Write a function that takes a string as input and returns the string reversed.
Example:
Given s = "hello", return "olleh".
 *
 */
public class P344_ReverseString {

	public String reverseString(String s) {
        if(s == null)
        	return null;
        else{
        	StringBuilder sb = new StringBuilder();
        	char c[] = s.toCharArray();
        	for (int i = c.length - 1; i >= 0; i--) 
				sb.append(c[i]);
        	return sb.toString();
        }
    }
	
	public static void main(String[] args) {
		P344_ReverseString p = new P344_ReverseString();
		System.out.println(p.reverseString(""));
		System.out.println(p.reverseString(null));
		System.out.println(p.reverseString("h"));
		System.out.println(p.reverseString("hello world "));
	}

}

/**
 * 一次通过。
 */
