package LeetCode.round3;

/**
 * 240709
 * DP问题。 Medium。 16做过。
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Example 1:
 * Input: m = 3, n = 7
 * Output: 28
 *
 * Example 2:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */
public class P062_UniquePaths {
    /**
     * AC: 100%, mem 48%
     * DP: 自顶向下带备忘录模式。
     * 思路：基于以下公式
     *   m=1 || n=1, res=1;
     *   m>1, n>1, res(m,n) = res(m-1,n) + res(m, n-1)
     * ！！！注意公式思路，需要好好想想，一开始没想清楚就写结果是错的。
     */
    public int uniquePaths(int m, int n) {
        if(m < 1 || n < 1)
            return 0;
        int[][] cache = new int[m+1][n+1];

        // init 备忘录
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                if(i == 1 || j == 1){
                    cache[i][j] = 1;
                }else {
                    cache[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return uniquePaths_upbottom(m, n, cache);
    }

    /**
     * @param m
     * @param n
     * @param cache 备忘录，缓存mn结果。
     * @return
     */
    private int uniquePaths_upbottom(int m, int n, int[][] cache){
        if(m < 1 || n < 1)
            return 0;
        if(cache[m][n] < Integer.MAX_VALUE)
            return cache[m][n];
        int res = uniquePaths_upbottom(m - 1, n, cache) + uniquePaths_upbottom(m, n - 1, cache);
        cache[m][n]=res;    //如果不加这行cache，提交会运行超时
        return res;
    }

    public static void main(String[] args) {
        P062_UniquePaths p = new P062_UniquePaths();
        System.out.println(p.uniquePaths(1,1));     //0
        System.out.println(p.uniquePaths(1,2));     //1
        System.out.println(p.uniquePaths(2,1));     //1
        System.out.println(p.uniquePaths(2,2));     //2
        System.out.println(p.uniquePaths(3,7));     //28
        System.out.println(p.uniquePaths(3,2));     //3
    }
}
