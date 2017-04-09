package LeetCode.round1.easy;

import java.util.Stack;

/**
 * 170409
 * @author Zhu Xiuwei
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
public class P557_ReverseWordsInAStringIII {

	/**
	 * 1 time AC: 75ms
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		if(s == null)
			return null;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c != ' ')
				stack.push(c);
			else{
				while(!stack.isEmpty())
					sb.append(stack.pop());
				sb.append(' ');
			}
		}
		
		//Note: do not miss the last word.
		while(!stack.isEmpty())
			sb.append(stack.pop());
		return sb.toString();
    }
	
	public static void main(String[] args) {
		P557_ReverseWordsInAStringIII p = new P557_ReverseWordsInAStringIII();
		System.out.println(p.reverseWords("Let's take LeetCode contest"));
	}

}
