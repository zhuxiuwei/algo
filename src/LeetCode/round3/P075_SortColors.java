package LeetCode.round3;

import java.util.Arrays;

/**
 * 240725
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 *
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 */
public class P075_SortColors {

    /**
     * AC: 0ms 100%, mem: 8%
     * @param nums
     */
    public void sortColors(int[] nums) {

        if(nums == null || nums.length <= 1)
            return;

        //先统计0，1，2出现的次数
        int countCache[] = new int[3];
        for (int i = 0; i < nums.length; i++) {
            countCache[nums[i]] ++;
        }

        //赋值。写这部分的时候不能bug free。还是需要调试
        int idx = 0;
        for (int c = 0; c <= 2; c++) {
            for (int i = 0; i < countCache[c]; i++) {
                nums[idx] = c;
                idx ++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        P075_SortColors p = new P075_SortColors();
        p.sortColors(new int[]{2,0,2,1,1,0});   // [0,0,1,1,2,2]
        p.sortColors(new int[]{2,0,1});     //0 1 2

    }
}
