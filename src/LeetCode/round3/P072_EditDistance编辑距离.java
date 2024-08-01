package LeetCode.round3;

/**
 * 240731 Medium。 （20年还是hard）
 *  给定两个单词word1 和word2，计算出将word1转换成word2 所使用的最少操作数。
 *  你可以对一个单词进行如下三种操作：
 *  插入一个字符
 *  删除一个字符
 *  替换一个字符
 *
 *  示例1:
 *  输入: word1 = "horse", word2 = "ros"
 *  输出: 3
 *  解释:
 *  horse -> rorse (将 'h' 替换为 'r')
 *  rorse -> rose (删除 'r')
 *  rose -> ros (删除 'e')
 *
 *  示例2:
 *  输入: word1 = "intention", word2 = "execution"
 *  输出: 5
 *  解释:
 *  intention -> inention (删除 't')
 *  inention -> enention (将 'i' 替换为 'e')
 *  enention -> exention (将 'n' 替换为 'x')
 *  exention -> exection (将 'n' 替换为 'c')
 *  exection -> execution (插入 'u')
 *
 *  Constraints:
 *  0 <= word1.length, word2.length <= 500
 *  word1 and word2 consist of lowercase English letters.
 */
public class P072_EditDistance编辑距离 {

    //暂时放弃。
    public int minDistance(String word1, String word2) {

        return 0;
    }
    public static void main(String[] args) {
        P072_EditDistance编辑距离 p = new P072_EditDistance编辑距离();
        System.out.println(p.minDistance("horse", "ros"));  //3
        System.out.println(p.minDistance("ros", "horse"));  //3
        System.out.println(p.minDistance("intention", "execution"));  //5
    }
}
