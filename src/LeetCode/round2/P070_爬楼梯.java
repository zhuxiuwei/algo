package LeetCode.round2;

/**
 200220 Easy
 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 注意：给定 n 是一个正整数。

 示例 1：
 输入： 2
 输出： 2
 解释： 有两种方法可以爬到楼顶。
 1.  1 阶 + 1 阶
 2.  2 阶

 示例 2：
 输入： 3
 输出： 3
 解释： 有三种方法可以爬到楼顶。
 1.  1 阶 + 1 阶 + 1 阶
 2.  1 阶 + 2 阶
 3.  2 阶 + 1 阶
 */
public class P070_爬楼梯 {

    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        else if(n == 2)
            return 2;
        else {
            int a[] = new int[n];
            a[0] = 1;
            a[1] = 2;
            for (int i = 2; i < a.length; i++) {
                a[i] = a[i - 1] + a[i - 2];
            }
            return a[a.length - 1];
        }
    }

    /**
     * 递归超时。
     *
     * 240824: 解决递归timeout的办法，就是自顶向下带备忘录的DP思路。加上备忘录cache就好了。
     */
    public int climbStairs_recursive(int n) {
        if(n == 1)
            return 1;
        else if(n == 2)
            return 2;
        else return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        P070_爬楼梯 p = new P070_爬楼梯();
        System.out.println(p.climbStairs(45));
    }
}
