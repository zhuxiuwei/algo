package LeetCode.round1.easy;
/**
 * 170417
Given a word, you need to judge whether the usage of capitals in it is right or not.
We define the usage of capitals in a word to be right when one of the following cases holds:
	1 All letters in this word are capitals, like "USA".
	2 All letters in this word are not capitals, like "leetcode".
	3 Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.

Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False *
 */
public class P520_DetectCapital {

	/**
	 * AC: 39ms, 39.6%.
	 * !!!!! One bug.
	 * @param word
	 * @return
	 */
	public boolean detectCapitalUse(String word) {
        if(word.length() == 1)
        	return true;
        else{
        	char first = word.charAt(0);
        	if(first >= 'a' && first <= 'z'){	//all lower is Ok
        		return judgeCases(word, 1, true);
        	}else{
        		char second = word.charAt(1);
        		if(second >= 'a' && second <= 'z'){	//First upper, other lower is OK
        			return judgeCases(word, 2, true);
        		}else{
        			return judgeCases(word, 2, false);	//All upper is OK.  !!!!!!!!!! Note bug: start is 2, not 3.
        		}
        	}
        }
    }
	/**
	 * 
	 * @param word Original word
	 * @param start start offset
	 * @param mode true to judge if all words to be lower case. False to judge if all words to be upper case.
	 * @return
	 */
	private boolean judgeCases(String word, int start, boolean lowerCase){
		for (int i = start; i < word.length(); i++) {
			char c = word.charAt(i);
			if(lowerCase && (c < 'a' || c > 'z')){
				return false;
			}
			if(!lowerCase && (c < 'A' || c > 'Z')){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		P520_DetectCapital p = new P520_DetectCapital();
		System.out.println(p.detectCapitalUse("USA"));
		System.out.println(p.detectCapitalUse("FlaG"));
		System.out.println(p.detectCapitalUse("Hello"));
		System.out.println(p.detectCapitalUse("hello"));
		System.out.println(p.detectCapitalUse("NIy"));
	}

}
