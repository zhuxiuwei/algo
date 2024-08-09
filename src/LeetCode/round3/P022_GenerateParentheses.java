package LeetCode.round3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 240706
 * # Backtracking - medium - 以前做过
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 *
 * Constraints:
 * 1 <= n <= 8
 */
public class P022_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n <= 0)
            return res;
        List<List<int[]>> pairsList = new ArrayList<>();
        int rightCount = 0, rightTotal = 0, leftTotal = 0;
        for (int leftCount = n; leftCount > 0 ; leftCount--) {
            List<int[]> pairs = new ArrayList<>();
//            while (Ll)
            for (rightCount = 1; rightCount <= leftCount; rightCount++) {

            }
        }
        return res;
    }

    /**
     * 以下错误思路：
     * 已经有了n的结果(记为res(n))，对n+1的结果，为以下三种组合：
     * 左边加括号：() res(n)
     * 右边加括号：res(n) ()
     * 两边加括号：(res(n))
     * 然后去重。
     * ----------
     * 以上思路错误之处在于：漏掉了括号可能加在中间的场景。 对于n=4，会漏掉一个结果："(())(())"
     */
    private void generateParenthesis_helper_wrong(int n, int idx, Set<String> resSet) {
        if (idx <= n) {
            Set<String> tempSet = new HashSet<>();
            if(resSet.size() >0){
                for(String s: resSet){
                    tempSet.add("()" + s);
                    tempSet.add(s + "()");
                    tempSet.add("(" + s + ")");
                }
            }else {
                tempSet.add("()");
            }
            resSet.clear();
            resSet.addAll(tempSet);     //！！！！ 不能忘了这一步。
            generateParenthesis_helper_wrong(n, ++idx, resSet);
        }
    }

    public static void main(String[] args) {
        P022_GenerateParentheses p = new P022_GenerateParentheses();
        System.out.println(p.generateParenthesis(1));   //["()"]
        System.out.println(p.generateParenthesis(2));   //[()(), (())]
        System.out.println(p.generateParenthesis(3));   //["((()))","(()())","(())()","()(())","()()()"]
        System.out.println(p.generateParenthesis(4));   //["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
    }
}
