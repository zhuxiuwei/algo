package LeetCode.round2;

import java.util.Calendar;
import java.util.Stack;

/**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true
 */
public class P020_ValidParentheses {

    /**
     Runtime: 1 ms, faster than 98.66% of Java online submissions for Valid Parentheses.
     Memory Usage: 37.6 MB, less than 5.06% of Java online submissions for Valid Parentheses.

     !!!! 问题：有一个bug
     */
    public boolean isValid(String s) {
        if(s == null || s.equals(""))
            return true;

        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if(c == ')'){
                if(stack.isEmpty() || stack.pop() != '(')
                    return false;
            }else if(c == ']'){
                if(stack.isEmpty() || stack.pop() != '[')
                    return false;
            }else if(c == '}'){
                if(stack.isEmpty() || stack.pop() != '{')
                    return false;
            }
        }

        // ！！！bug: 不加这个判断，在 [ 这个情况下会错误返回true
        if(!stack.isEmpty())
            return false;
        return true;
    }

    public static void main(String[] args) {
        P020_ValidParentheses p = new P020_ValidParentheses();
        System.out.println(p.isValid("()"));
        System.out.println(p.isValid("()[]{}"));
        System.out.println(p.isValid("(]"));
        System.out.println(p.isValid("([)]"));
        System.out.println(p.isValid("{[]}"));

    }
}
