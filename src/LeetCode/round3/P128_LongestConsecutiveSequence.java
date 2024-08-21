package LeetCode.round3;

import java.util.*;

/**
 * 240821 medium
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class P128_LongestConsecutiveSequence {
    /**
     * AC: Runtime 33ms Beats 47.60%, Memory 66.46 MB Beats 48.34%
     * 自己想不到。参考思路：https://leetcode.com/problems/longest-consecutive-sequence/solutions/5418430/video-check-n-1/
     * 思路：
     * 1、依次判断数组里的每个num，是否存在num-1；如果存在，忽略。如果不存在，到步骤2
     * 2、不存在num-1，说明num是一个start number。从num开始，依次判断num+1，num+2...在数组中是否存在。
     * 3、在循环2的过程中，记录发现的最长的Consecutive Seq，作为res返回。
     * 时间复杂度、空间复杂度都是O(N)
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            numsSet.add(nums[i]);
        }
        Iterator<Integer> it = numsSet.iterator();
        while (it.hasNext()){
            int num = it.next();
            //判断num的上一个数字是否存在。如果不存在， 它可以被视为一个start number，开始计算最大长度。
            if(!numsSet.contains(num - 1)){
                int count = 0, cur = num;
                while (true){
                    if(numsSet.contains(cur)){
                        count ++;
                        cur ++;
                    }else {
                        break;
                    }
                }
                if(count > res)
                    res = count;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P128_LongestConsecutiveSequence p = new P128_LongestConsecutiveSequence();
        System.out.println(p.longestConsecutive(new int[]{100,4,200,1,3,2}));  //4
        System.out.println(p.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));  //9
    }
}
