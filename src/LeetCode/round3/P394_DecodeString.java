package LeetCode.round3;

import java.util.Stack;

/**
 * 240919 medium
 * https://leetcode.com/problems/decode-string/description/?envType=study-plan-v2&envId=top-100-liked
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 10^5.
 *
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Example 3:
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class P394_DecodeString {

    /**
     * 写的时候稍微思考了下流程。写的过程总体顺利。
     * AC: Runtime 0 ms Beats 100.00%, Memory 41.95 MB Beats 13.57%
     */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        Stack<Integer> nums = new Stack<>();
        Stack<StringBuilder> sbs = new Stack<>();
        int k = 0;    //用于数字的叠加。k[encoded_string]中，k可能是个多位数字。
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){   //数字
                int curNum = c - '0';
                k = k * 10 + curNum;    //k可能是个多位数字
            }else { //非数字
                if(c == '['){   //是一个新的k[encoded_string]，k和代表encoded_string的sb，分别入栈。
                    nums.push(k);
                    k = 0;  //reset k
                    sbs.push(new StringBuilder());
                }else if(c == ']'){     //是一个k[encoded_string]的结束，需要'结算'一次。
                    //生成结束的k[encoded_string]代表的tmp string builder
                    int num = nums.pop();
                    StringBuilder tmpSb = new StringBuilder();
                    String tmpStr = sbs.pop().toString();
                    for (int j = 0; j < num; j++) {
                        tmpSb.append(tmpStr);
                    }
                    //生成的tmp string builder，依据是否包含在更上一层k[encoded_string]之中，有不同的append逻辑。
                    if(sbs.isEmpty()){  //没有包含在更上一层k[encoded_string]之中，tmp string builder直接append给res
                        res.append(tmpSb);
                    }else {     //包含在更上一层k[encoded_string]之中，tmp string builder append给上一层的string builder
                        sbs.peek().append(tmpSb);
                    }
                }else{  //字母
                    if(sbs.isEmpty()){  //包含在k[encoded_string]之中，c append给栈顶的string builder
                        res.append(c);
                    }else {
                        sbs.peek().append(c);    //不在k[encoded_string]之中，c直接append给res
                    }
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        P394_DecodeString p = new P394_DecodeString();
        System.out.println(p.decodeString("3[a]2[bc]"));    //aaabcbc
        System.out.println(p.decodeString("3[a2[c]]"));     //accaccacc
        System.out.println(p.decodeString("2[abc]3[cd]ef"));  //abcabccdcdcdef

    }
}
