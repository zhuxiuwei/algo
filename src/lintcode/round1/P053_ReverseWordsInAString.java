package lintcode.round1;

/**
 * @author Zhu xiuwei
 * 170529 easy  http://www.lintcode.com/zh-cn/problem/reverse-words-in-a-string/
Given an input string, reverse the string word by word.

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
public class P053_ReverseWordsInAString {

	public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = array.length - 1; i >= 0; --i) {
            if (!array[i].equals("")) {
                sb.append(array[i]).append(" ");
            }
        }

        //remove the last " "
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
	
	public static void main(String[] args) {
		P053_ReverseWordsInAString p = new P053_ReverseWordsInAString();
		System.out.println(p.reverseWords("  I   see you.  "));
	}

}
