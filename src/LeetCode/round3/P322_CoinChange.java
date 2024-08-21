package LeetCode.round3;

import java.util.*;

/**
 * 240816 & 240821 medium
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class P322_CoinChange {

    /**
     * 回溯法。结果不对。
     * case: new int[]{186,419,83,408}, 6249
     * 应该返回20
     * 这里会返回-1（tmpNums里都是83，最后找零失败）
     *
     * 动态规划思路放弃。
     */
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        Arrays.sort(coins);
        PriorityQueue<Integer> okCoins = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Integer> idxToAmountMap = new HashMap<>();
        for (int i = 0; i < coins.length ; i++) {
            idxToAmountMap.put(coins[i], i);
        }
        boolean canChange = helper_wrong(coins, amount, coins.length - 1, okCoins, idxToAmountMap);
        return canChange ? okCoins.size(): -1;
    }

    /**
     * @param coins 已排序的硬币数组
     * @param leftAmount 剩余找零额
     * @param idx 当前找零在coins数组的索引位置
     * @param tmpNums 当前找零临时方案
     * @param idxToAmountMap coins下标到数组的映射map
     * @return
     */
    private boolean helper_wrong(int[] coins, int leftAmount, int idx, PriorityQueue<Integer> tmpNums, Map<Integer, Integer> idxToAmountMap ){
        if(coins[idx] <= leftAmount){
            tmpNums.add(coins[idx] );
            leftAmount -= coins[idx];
            if(leftAmount == 0) { //找到解
                return true;
            }else {
                return helper_wrong(coins, leftAmount, idx, tmpNums, idxToAmountMap);   //继续用当前硬币尝试
            }
        }else {
            if(idx > 0){    //继续用一个更小的硬币尝试
                idx --;
                return helper_wrong(coins, leftAmount, idx, tmpNums, idxToAmountMap);
            }else {
                if(tmpNums.size() == 0){    //即没有更小的硬币，也没有历史硬币可以退出，匹配失败，返回
                    return false;
                }
                int amount = tmpNums.remove();    //已经没有更小的硬币了，从tmp里移除最大的，继续尝试
                idx = idxToAmountMap.get(amount);
                if(idx > 0){
                    return helper_wrong(coins, leftAmount + amount, idx - 1, tmpNums, idxToAmountMap);
                }else{  //已经没有更小的了，匹配失败，退出
                    return false;
                }
            }
        }
    }


    public static void main(String[] args) {
        P322_CoinChange p = new P322_CoinChange();
        System.out.println(p.coinChange(new int[]{1,2,5}, 11)); //3
        System.out.println(p.coinChange(new int[]{2}, 3)); //-1
        System.out.println(p.coinChange(new int[]{1}, 0)); //0

        System.out.println(p.coinChange(new int[]{2, 4, 7}, 15)); //3
        System.out.println(p.coinChange(new int[]{2}, 1)); //-1

        System.out.println(p.coinChange(new int[]{186,419,83,408}, 6249));    //20
        System.out.println(p.coinChange(new int[]{3,11, 7}, 15));    //5

        System.out.println(p.coinChange(new int[]{2}, 1)); //-1

    }
}
