package LeetCode.round2;

import java.util.Arrays;
import java.util.HashMap;

/**
 200229 Medium
 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。

 示例1:
 输入: coins = [1, 2, 5], amount = 11
 输出: 3
 解释: 11 = 5 + 5 + 1

 示例2:
 输入: coins = [2], amount = 3
 输出: -1

 说明:
 你可以认为每种硬币的数量是无限的。
 */
public class P322_零钱兑换 {
    /**
     * 思路： 动态规划问题
     * 以下代码，好像结果不对！！！
     * case: new int[]{186,419,83,408}, 6249，应返回20，这里会返回-1。
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[][] notes = new int[coins.length][amount + 1];
        return helper(coins, coins.length - 1, amount, notes);
    }
    private int helper(int[] conts, int idx, int amount, int[][] notes){
        if(notes[idx][amount] != 0)
            return notes[idx][amount];
//        System.out.println("cal " + idx + " " + amount);
        int res = -1;
        if(idx == 0) {
            res = amount % conts[idx] == 0 ? amount / conts[idx] : -1;
        } else {
            int m1 = helper(conts, idx - 1, amount % conts[idx], notes);
            if (m1 != -1) m1 += amount / conts[idx];
            int m2 = helper(conts, idx - 1, amount, notes);
            if (m1 == -1)
                res = m2;
            else if (m2 == -1)
                res = m1;
            else
                res = Math.min(m1, m2);
        }
        notes[idx][amount] = res;
        return res;
    }

    public static void main(String[] args) {
        P322_零钱兑换 p = new P322_零钱兑换();
//        System.out.println(p.coinChange(new int[]{1,2,5}, 11)); //3
//        System.out.println(p.coinChange(new int[]{5, 3}, 9)); //3
//        System.out.println(p.coinChange(new int[]{2, 5, 3}, 8)); //2
//        System.out.println(p.coinChange(new int[]{2, 5, 3}, 1)); //-1
//        System.out.println(p.coinChange(new int[]{5, 3,2,7,1,6,8}, 100000));
        System.out.println(p.coinChange(new int[]{186,419,83,408}, 6249));    //20  错误输出-1
        System.out.println(p.coinChange(new int[]{3,11, 7}, 15));    //5

    }
}
