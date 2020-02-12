package LeetCode.round2;

import java.lang.reflect.Array;
import java.util.*;

/**
 * EASY 200212
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 Example:
 Given nums = [2, 7, 11, 15], target = 9,
 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class P001_TwoSum {
    /**
     * 错2次。
     *  Runtime: 1 ms, faster than 99.91% of Java online submissions for Two Sum.
        Memory Usage: 41.4 MB, less than 5.65% of Java online submissions for Two Sum.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> others = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int another = target - n;
            if(others.containsKey(another)){
                return new int[]{i, others.get(another)};
            }
            /**
             * !!!!注意
             * 1. 这行不能忘了加
             * 2. 不能放在if(others.containsKey(another)判断之前，否则{3,2,4} 6 会错误返回 {0,0}
             */
            others.put(n, i);
        }
        return null;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(new P001_TwoSum().twoSum(nums, target)));

        nums = new int[]{3,2,4};
        target = 6;
        System.out.println(Arrays.toString(new P001_TwoSum().twoSum(nums, target)));

    }
}
