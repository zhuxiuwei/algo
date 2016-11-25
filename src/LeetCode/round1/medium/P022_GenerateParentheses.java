package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;

/** 161025
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class P022_GenerateParentheses {

	/**
	 * 典型回溯法问题。
	 * AC: 3ms, 53%、
	 * Note: 不顺利。在第37行哪里卡了半天。
	 */
	public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(res, new StringBuilder(), n, 0);
        return res;
    }
	private void helper(List<String> res, StringBuilder temp, int leftAllow, int rightAllow){
		if(leftAllow > 0){
			temp.append("(");
			helper(res, temp, leftAllow - 1, rightAllow + 1);
			temp.deleteCharAt(temp.length() - 1);
			if(rightAllow > 0){
				temp.append(")");
				helper(res, temp, leftAllow, rightAllow - 1);
			}
			/**!!!!!!!!!!!!这里卡了很久。 如果写成   else if(temp.length() > 0)  ，后两个结果就会多一个左括号，因为temp=(()时有一个应该符合if的条件没进去，deleteChar少执行一次。我死活以为这个else if能执行到，
				if条件进不去我还误以为是eclipse debug的bug。其实仔细想想就是进不去，当时rightAllow>0，if(rightAllow > 0)后面的elsf if.. 自然进不去
				其实还是没非常明白为何要这样写条件。
				!!!!!!!!!*/
			if(temp.length() > 0 && temp.charAt(temp.length() - 1) == ')')	
				temp.deleteCharAt(temp.length() - 1);
		}else if(rightAllow > 0){
			temp.append(")");
			helper(res, temp, leftAllow, rightAllow - 1);
			temp.deleteCharAt(temp.length() - 1);
		}else{
			res.add(temp.toString());
		}
	}
	
	public static void main(String[] args) {
		P022_GenerateParentheses p = new P022_GenerateParentheses();
		System.out.println(p.generateParenthesis(3));	
	}

}
