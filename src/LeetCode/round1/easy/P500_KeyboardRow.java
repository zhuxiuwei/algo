package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 170405
 * @author Zhu Xiuwei
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 *
 */
public class P500_KeyboardRow {

	private HashSet<Character> row1 = new HashSet<Character>();
	private HashSet<Character> row2 = new HashSet<Character>();
	private HashSet<Character> row3 = new HashSet<Character>();

	public P500_KeyboardRow(){
		row1.add('Q');
		row1.add('q');
		row1.add('W');
		row1.add('w');
		row1.add('E');
		row1.add('e');
		row1.add('R');
		row1.add('r');
		row1.add('T');
		row1.add('t');
		row1.add('Y');
		row1.add('y');
		row1.add('U');
		row1.add('I');
		row1.add('O');
		row1.add('P');
		row1.add('u');
		row1.add('i');
		row1.add('o');
		row1.add('p');
		row2.add('a');
		row2.add('s');
		row2.add('d');
		row2.add('f');
		row2.add('g');
		row2.add('h');
		row2.add('j');
		row2.add('k');
		row2.add('l');
		row2.add('A');
		row2.add('S');
		row2.add('D');
		row2.add('F');
		row2.add('G');
		row2.add('H');
		row2.add('J');
		row2.add('K');
		row2.add('L');
		row3.add('Z');
		row3.add('X');
		row3.add('C');
		row3.add('V');
		row3.add('B');
		row3.add('N');
		row3.add('M');
		row3.add('z');
		row3.add('x');
		row3.add('c');
		row3.add('v');
		row3.add('b');
		row3.add('n');
		row3.add('m');
	}
	
	/**
	 *	AC: 5ms, 42.5%
	 *	！！！ ！！！！一个注意点。
	 */
	public String[] findWords(String[] words) {
        if(words == null)
        	return null;
        
        List<String> res = new ArrayList<String> ();
        boolean ok = true;
        for(String word: words){
        	HashSet<Character> temp = null;
        	char first = word.charAt(0);
        	if(row1.contains(first)){
        		temp = row1;
        	}else if(row2.contains(first)){
        		temp = row2;
        	}else if(row3.contains(first)){
        		temp = row3;
        	}
        	for (int i = 1; i < word.length(); i++) {
        		char c = word.charAt(i);
        		if(!temp.contains(c)){
        			ok = false;
        			break;
        		}
			}
        	if(ok)
        		res.add(word);
        	ok = true;
        }
        
        //!!!!!注意点： 注意List转Array的语法。不能：return (String[])res.toArray();
        String[] strings = new String[res.size()];
        res.toArray(strings);
        return strings;
    }
	
	public static void main(String[] args) {
		P500_KeyboardRow p = new P500_KeyboardRow();
		System.out.println(Arrays.toString(p.findWords(new String[]{})));
	}

}
