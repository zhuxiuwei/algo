package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 240904 medium
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * Constraints:
 * 1 <= n <= 10000
 */
public class P279_PerfectSquares {

    /**
     * 自底向上的dp。 AC: 188ms Beats 13.31%, Memory 44.02MB Beats 27.92%
     * 思路：
     * n的结果记为f(n)。初始条件： f(0)=0, f(1) = 1。
     * for i = 2 to n:
     *   for each perfect square that <= i:
     *     计算tmp = f(i - perfectSquare) + 1
     *   f(i) = 上面过程中最小的一个tmp。
     * 循环结束后，返回f(n)即可。
     */
    public int numSquares(int n) {
        int[] res = new int[n + 1];
        res[1] = 1;

        //记录perfectSquares
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; ; i++) {
            int square = i * i;
            if(square <= n) {
                perfectSquares.add(square);
            }else{
                break;
            }
        }

        return helper(res, n, perfectSquares);
    }

    private int helper(int[] res, int n, List<Integer> perfectSquares){
        for (int i = 2; i < res.length; i++) {
            int tmp = Integer.MAX_VALUE;
            for (int square: perfectSquares){
                if(square <= i) {
                    tmp = Math.min(res[i - square] + 1, tmp);
                }
            }
            res[i] = tmp;
        }
        return res[n];
    }

    public static void main(String[] args) {
        P279_PerfectSquares p = new P279_PerfectSquares();
        System.out.println(p.numSquares(12));   //3, 12 = 4 + 4 + 4
        System.out.println(p.numSquares(13));   //2, 13 = 4 + 9
    }
}
