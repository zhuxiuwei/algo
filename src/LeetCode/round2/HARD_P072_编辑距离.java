package LeetCode.round2;

import java.util.Arrays;

/**
 200220 Hard
 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

 你可以对一个单词进行如下三种操作：
 插入一个字符
 删除一个字符
 替换一个字符

 示例 1:
 输入: word1 = "horse", word2 = "ros"
 输出: 3
 解释:
 horse -> rorse (将 'h' 替换为 'r')
 rorse -> rose (删除 'r')
 rose -> ros (删除 'e')

 示例 2:
 输入: word1 = "intention", word2 = "execution"
 输出: 5
 解释:
 intention -> inention (删除 't')
 inention -> enention (将 'i' 替换为 'e')
 enention -> exention (将 'n' 替换为 'x')
 exention -> exection (将 'n' 替换为 'c')
 exection -> execution (插入 'u')
 */
public class HARD_P072_编辑距离 {
    /*
    DP问题 自顶向下版本。
    抄的https://leetcode-cn.com/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/

    思路：
    dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
    所以，
    当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
    当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
    其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
    */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i < len1 + 1; i++){
            for(int j = 0; j < len2 + 1; j++){
                if (i == 0 || j == 0) {//初始化数组的第一行与第一列
                    dp[i][j] = Math.max(i, j);
                }else{
                    int a = dp[i][j - 1] + 1;//插入字符所产生的距离
                    int b = dp[i - 1][j] + 1;//删除字符所产生的距离
                    int c = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1]: dp[i - 1][j - 1] + 1;//替换字符所产生的距离, 两字符相等就不用替换, 两字符不相等需要替换
                    dp[i][j] = Math.min(Math.min(a, b), c);
                }
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        HARD_P072_编辑距离 p = new HARD_P072_编辑距离();
        System.out.println(p.minDistance("horse", "ros"));  //3
        System.out.println(p.minDistance("ros", "horse"));  //3
        System.out.println(p.minDistance("intention", "execution"));  //5
    }
}
