package LeetCode.round3;

import java.util.*;

/**
 * 240816 medium
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
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        Arrays.sort(coins);
        List<Integer> okCoins = new ArrayList<>();
        Map<Integer, Integer> idxToAmoountMap = new HashMap<>();
        for (int i = 0; i < coins.length ; i++) {
            idxToAmoountMap.put(coins[i], i);
        }
        helper(coins, amount, coins.length - 1, okCoins, idxToAmoountMap);
        return okCoins.size() > 0 ? okCoins.size(): -1;
    }

    private void helper(int[] coins, int leftAmount, int idx, List<Integer> tmpNums, Map<Integer, Integer> idxToAmoountMap ){
        if(coins[idx] <= leftAmount){
            tmpNums.add(coins[idx] );
            leftAmount -= coins[idx];
            if(leftAmount == 0) { //找到解
                return;
            }else {
                helper(coins, leftAmount, idx, tmpNums, idxToAmoountMap);
            }
        }else {
            if(idx > 0){    //继续用一个更小的硬币尝试
                idx --;
                helper(coins, leftAmount, idx, tmpNums, idxToAmoountMap);
            }else {
                if(tmpNums.size() == 0){
                    return;
                }
                int amount = tmpNums.remove(0);    //已经没有更小的硬币了，从tmp里移除最大的，继续尝试
                idx = idxToAmoountMap.get(amount);
                if(idx > 0){
                    helper(coins, leftAmount + amount, idx - 1, tmpNums, idxToAmoountMap);
                }else{
                    return;
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
