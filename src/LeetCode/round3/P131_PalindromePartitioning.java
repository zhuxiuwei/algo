package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 240904 medium
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * A palindrome is a string that reads the same forward and backward.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class P131_PalindromePartitioning {
    /**
     * AC: 18ms Beats 6.40%. Memory 56.50MB Beats 80.62%
     * back tracking问题，对这类问题有阴影所以想的时间比较久，动手比较晚。但真写起来还挺顺利。
     */
    public List<List<String>> partition(String s) {
        return helper(s.toCharArray(), 0);
    }

    /**
     * 大体思路：
     * 对每一个字符，从当前开始位置往后，判断是不是形成回文。
     * 如果能形成回文，就对它后面的部分进行递归调用。
     * 将当前形成的回文，放到地柜调用返回结果的头部。
     */
    private List<List<String>> helper(char[] chars, int start){
        List<List<String>> res = new ArrayList<>();
        if(start <= chars.length - 1) {
            for (int i = start; i < chars.length; i++) {
                if (isPalindrome(chars, start, i)) {
                    List<List<String>> tmpRes = helper(chars, i + 1);
                    String cur = getSubStrFromCharArray(chars, start, i);
                    if(tmpRes.isEmpty()){
                        List<String> newList = new ArrayList<>();
                        newList.add(cur);
                        res.add(newList);
                    }else {
                        for (List<String> partResList : tmpRes) {
                            partResList.add(0, cur);
                        }
                        res.addAll(tmpRes);  //！！！！少了这行会返回空结果！！！！
                    }
                }
            }
        }
        return res;
    }

    //判断是否是回文
    private boolean isPalindrome(char[] chars, int start, int end){
        while (start <= end){
            char charS = chars[start];
            char chatE = chars[end];
            if(chatE != charS)
                return false;
            start ++;
            end --;
        }
        return true;
    }

    //获取子字符串
    private String getSubStrFromCharArray(char[] chars, int start, int end){
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        P131_PalindromePartitioning p = new P131_PalindromePartitioning();
        System.out.println(p.partition("aab")); //[["a","a","b"],["aa","b"]]
        System.out.println(p.partition("ababa")); //[["a","b","a","b","a"],["a","b","aba"],["a","bab","a"],["aba","b","a"],["ababa"]]
        System.out.println(p.partition("ababab")); //[["a","b","a","b","a","b"],["a","b","a","bab"],["a","b","aba","b"],["a","bab","a","b"],["a","babab"],["aba","b","a","b"],["aba","bab"],["ababa","b"]]
    }
}
