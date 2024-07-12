package LeetCode.round3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 240712 Easy
 * AC: 2ms, 97.8%. mem: 50.3%
 */
public class P001_TwoSum {

    /**
     * 本身不难。思路和以前一样。
     * ！！！！但是还是有第一，二轮遇到的问题。bug一样。
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2)
            return null;
        Map<Integer, Integer> numIdxMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(numIdxMap.containsKey(target - nums[i])) {
                return new int[]{numIdxMap.get(target - nums[i]), i};
            }
            numIdxMap.put(nums[i], i);  //！！！如果放到if前面，会有bug。
        }
        return null;
    }

    public static void main(String[] args) {
        P001_TwoSum p = new P001_TwoSum();
        System.out.println(Arrays.toString(p.twoSum(new int[]{2,7,11,15}, 9))); //[0,1]
        System.out.println(Arrays.toString(p.twoSum(new int[]{3,2,4}, 6))); //[1,2]
        System.out.println(Arrays.toString(p.twoSum(new int[]{3,3}, 6)));   //[0,1]
    }
}
