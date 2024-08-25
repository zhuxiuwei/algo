package LeetCode.round3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 240824 medium
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class P560_SubArraySumEqualsK {

    /**
     * 非暴力解法。O(n)。但是AC比暴力解法反倒慢。
     * 自己想不到。参考思路：https://leetcode.com/problems/subarray-sum-equals-k/solutions/1759909/c-full-explained-every-step-w-dry-run-o-n-2-o-n-two-approaches/?envType=study-plan-v2&envId=top-100-liked
     * AC： Runtime 1714 ms Beats 5.00%， Memory 56.65 MB Beats 6.63%
     */
    public int subarraySum(int[] nums, int k) {
        int res = 0, tmpSum = 0;
        Map<Integer, List<Integer>> sum2IdxMap = new HashMap<>();   //注意k是个list
        Map<Integer, Integer> idx2SumMap = new HashMap<>();

        //填充两个map
        for (int i = 0; i < nums.length; i++) {
            tmpSum += nums[i];
            List<Integer> idxs = sum2IdxMap.getOrDefault(tmpSum, new ArrayList<>());
            idxs.add(i);
            sum2IdxMap.put(tmpSum, idxs);
            idx2SumMap.put(i, tmpSum);
        }

        //看sum2IdxMap里是否有delta
        for (int i = nums.length - 1; i >= 0; i--) {
            int delta = idx2SumMap.get(i) - k;
            List<Integer> idxs = sum2IdxMap.get(delta);
            if(idxs != null){
                for(int idx: idxs){
                    if(idx < i)
                        res ++;
                    else
                        break;
                }
            }
        }

        if(sum2IdxMap.containsKey(k))   //从头开始累加等于k的情况
            res += sum2IdxMap.get(k).size();

        return res;
    }

    /**
     * 暴力解法，时间复杂度o(n^2)，不过也AC了。
     * 思路：从nums[]每个idx开始，依次累加到最后一个num，看是否=k，若=k则找到了一个解，让res + 1。最后返回res。
     * AC: 1089ms Beats 13.07%, Memory 46.55MB Beats 34.65%
     */
    public int subarraySum_baoli(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmpSum = 0;
            for (int j = i; j < nums.length; j++) {
                tmpSum += nums[j];
                if(tmpSum == k)
                    res ++;
            }
        }
        return res;
    }

    /**
     * 貌似只考虑了都是0或正整数的情况。！！！ 忘了num可以是负数。
     * Case: p.subarraySum(new int[]{-1,-1,1}, 0) //期望1。错误输出0
     */
    public int subarraySum_wrong(int[] nums, int k) {
        int res = 0, start = 0, tmpSum = 0;
        for (int i = 0; i < nums.length; i++) {
            tmpSum += nums[i];
            if(tmpSum < k){
                //do nothing
            }else if(tmpSum == k){
                res ++;
            }else {
                while (tmpSum > k && start < i) {
                    tmpSum -= nums[start];
                    start++;
                }
                if(tmpSum == k){
                    res ++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P560_SubArraySumEqualsK p = new P560_SubArraySumEqualsK();
        System.out.println(p.subarraySum(new int[]{1,1,1}, 2)); //2
        System.out.println(p.subarraySum(new int[]{1,2,3}, 3)); //2
        System.out.println(p.subarraySum(new int[]{-1,-1,1}, 0)); //1
        System.out.println(p.subarraySum(new int[]{-1,-1,1}, -1)); //3 解释：第一个-1、第二个-1、三个加和
        System.out.println(p.subarraySum(new int[]{5,-2,-3,-5}, -5)); //3 解释：[5,-2,-3,-5]、[-2,-3]、[-5]
    }
}
