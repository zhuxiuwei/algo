package LeetCode.round3;

import java.util.Stack;

/**
 * 240906 easy
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Example 4:
 * Input: s = "([])"
 * Output: true
 *
 * Constraints:
 * 1 <= s.length <= 10000
 * s consists of parentheses only '()[]{}'.
 */
public class P020_ValidParentheses {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '{':
                case '(':
                case '[':
                    stack.push(c);
                    break;
                case '}':
                    if(!validate(stack, '{'))
                        return false;
                    break;
                case ')':
                    if(!validate(stack, '('))
                        return false;
                    break;
                case ']':
                    if(!validate(stack, '['))
                        return false;
                    break;
            }
        }

        // ！！！bug: 不加这个判断，在 [ 这个情况下会错误返回true
        if(!stack.isEmpty())
            return false;
        return true;
    }

    private boolean validate(Stack<Character> stack, char expectedChar){
        if(!stack.isEmpty()) {
            char last = stack.pop();
            if(last != expectedChar)
                return false;
        }else {
            return false;
        }
        return true;
    }
}
