package LeetCode.round3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 240824 easy
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 * 1 <= n <= 45
 */
public class P070_ClimbingStairs {

    /**
     * 成功方法1： dp自底向上写法。
     * 关键在于递归公式。f(n) = f(n-1) + f(n-2)
     * gpt解释：
     * 如果你站在第 n 级台阶上，你可能是从 n-1 级台阶或 n-2 级台阶一步跨上来的。
     *  - 如果从 n-1 级跨上来，那么前面的走法有 f(n-1) 种；
     *  - 如果从 n-2 级跨上来，那么前面的走法有 f(n-2) 种。
     * 因此，可以得到递推公式：f(n) = f(n-1) + f(n-2)。
     *
     * AC: 0ms Beats 100.00%, Memory 40.48MB Beats 25.32%
     */
    public int climbStairs(int n) {
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        int lastLast = 1, last = 2, res = 0;
        for (int i = 3; i <= n; i++) {
            res = lastLast + last;
            lastLast = last;
            last = res;
        }
        return res;
    }

    /**
     *  成功方法2： dp自顶向下带备忘录写法。
     *  AC: 0ms Beats 100.00%, Memory 40.73MB Beats 5.63%
     */
    public int climbStairs_upBottom(int n) {
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        int[] cache = new int[n + 1];
        cache[1] = 1;
        cache[2] = 2;
        return helper_upBottom(n ,cache);
    }
    private int helper_upBottom(int n, int[] cache){
        if(cache[n] != 0)
            return cache[n];
        else {
            int n_2 = helper_upBottom(n - 2, cache);
            int n_1 = helper_upBottom(n - 1, cache);
            cache[n] = n_1 + n_2;
            return n_1 + n_2;
        }
    }

    /**
     * 失败方法： dp自顶向下带备忘录写法。因为解公式不是最优，计算过程过于复杂，虽然带着备忘录，依然会超时。
     * @param n
     * @return
     */
    public int climbStairs_timeout(int n) {
        List<Set<String>> cache = new ArrayList<>(n + 1);

        //初始化cache
        Set<String> cache0 = new HashSet<>();
        cache0.add("");
        cache.add(0, cache0);  //0

        Set<String> cache1 = new HashSet<>();
        cache1.add("1");
        cache.add(1, cache1);  //1

        for (int i = 1; i <= n - 1; i++) {
            cache.add(null);
        }

        helper_timeout(n, cache);
        return cache.get(n).size();
    }

    /**
     * 公式：
     * f(n)= 1 + f(n-1)
     *     = f(n-1) + 1
     *     = 2 + f(n-2)
     *     = f(n-2) + 2
     *  ！！！ 这个公式对，但过于复杂了。
     */
    private void helper_timeout(int n, List<Set<String>> cache){
        if(cache.get(n) != null) {
            return;
        } else {
            System.out.println("cal " + n);     //从这个log看，cache是生效的
            if(cache.get(n - 2) == null)
                helper_timeout(n - 2, cache);
            if(cache.get(n - 1) == null)
                helper_timeout(n - 1, cache);

            //从下面的log看，时间慢在生成cache部分了。效率很低。
            Set<String> newCache = new HashSet<>();
            System.out.println("begin new cache: " + n);
            //f(n-2)
            cache.get(n - 2).forEach(x -> {
                newCache.add(x + "2");
                newCache.add("2"+ x);

            });
            //f(n-1)
            cache.get(n - 1).forEach(x -> {
                newCache.add(x + "1");
                newCache.add("1"+ x);

            });
            System.out.println("end new cache: " + n);

            cache.add(n, newCache);
        }
    }

    public static void main(String[] args) {
        P070_ClimbingStairs p = new P070_ClimbingStairs();
        System.out.println(p.climbStairs_upBottom(1));       //1
        System.out.println(p.climbStairs_upBottom(2));       //2
        System.out.println(p.climbStairs_upBottom(3));       //3
        System.out.println(p.climbStairs_upBottom(35));       //14930352
        System.out.println(p.climbStairs(45));       //1836311903
        System.out.println(p.climbStairs_upBottom(45));       //1836311903
    }
}
