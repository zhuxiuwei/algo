package lintcode.round1;

import java.util.Stack;

/**
 * 170613 easy
 * Given an input string, reverse the string word by word.
For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
 */
public class P053_AmaMoni_ReverseWordsInAString {

	/**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
    	if (s == null || s.length() == 0) {
            return "";
        }
    	
    	boolean inWord = true;
    	Stack<String> stack = new Stack<String>();
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c != ' '){
				inWord = true;
				sb.append(c);
			}else{
				if(inWord){
					if(sb.length() > 0){
						stack.push(sb.toString());
						sb.setLength(0);
					}
					stack.push(" ");
				}
				inWord = false;
			}
		}
    	
    	while(!stack.isEmpty()){
    		sb.append(stack.pop());
    	}
    	
    	return sb.toString().trim();
    }
    
    
	public static void main(String[] args) {
		P053_AmaMoni_ReverseWordsInAString p = new P053_AmaMoni_ReverseWordsInAString();
		System.out.println(p.reverseWords(" the   Skky. is   Blue  ！  "));
	}

}
