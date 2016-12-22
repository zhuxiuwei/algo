package LeetCode.round1.easy;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 161222
Write a function that takes a string as input and reverse only the vowels(a,e,i,o,u) of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
 */
public class P345_ReverseVowelsOfAString {

	/**
	 * O(n) using two pointers.
	 * AC: 4ms, 93.03%.
	 */
	public String reverseVowels(String s) {
		char[] c = s.toCharArray();
		int left = 0, right = s.length() - 1;
		while(left < right){
			while(left < c.length && !isVowels(c[left]))
				left ++;
			while(right > 0  && !isVowels(c[right]))
				right --;
			
			if(left < right){	//swap
				char temp = c[left];
				c[left] = c[right];
				c[right] = temp;
			}
			
			//!!!!!!!!注意bug，必须加上下面两句话，否则“Aa”就死循环了。
			left ++;
			right --;
		}
		return new String(c);
	}
	
	/**
	 * O(n) using a stack.
	 * AC: 18ms, 18.17%
	 */
	public String reverseVowels_stack(String s) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(isVowels(c))
				stack.push(c);
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(isVowels(c))
				sb.append(stack.pop());
			else
				sb.append(c);
		}
		return sb.toString();
	}
	
	private boolean isVowels(char c){
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ;
	}
	
	private boolean isVowels2(char c){	//这个比isVowels(char c)要慢50%左右。
		if(vowelSet.isEmpty()){
			vowelSet.add('a');vowelSet.add('e');vowelSet.add('i');vowelSet.add('o');vowelSet.add('u');
			vowelSet.add('A');vowelSet.add('E');vowelSet.add('I');vowelSet.add('O');vowelSet.add('U');
		}
		return vowelSet.contains(c);
	}
	private Set<Character> vowelSet = new HashSet<Character>();
	
	
	public static void main(String[] args) {
		P345_ReverseVowelsOfAString p = new P345_ReverseVowelsOfAString();
		System.out.println(p.reverseVowels("Aa"));	//aA
		System.out.println(p.reverseVowels("leetcode"));	//leotcede

	}

}
