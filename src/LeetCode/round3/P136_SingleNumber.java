package LeetCode.round3;

/**
 * 240806 Easy
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * Each element in the array appears twice except for one element which appears only once.
 */
public class P136_SingleNumber {
    /**
     * 看到了之前的实现思路，就没难度了。关键是思路。
     * AC: 1ms Beats 99.88%, Memory 46.07MB Beats 42.55%
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        P136_SingleNumber p = new P136_SingleNumber();
        System.out.println(p.singleNumber(new int[]{2,2,1}));   //1
        System.out.println(p.singleNumber(new int[]{4,1,2,1,2}));      //4
        System.out.println(p.singleNumber(new int[]{4}));      //4
    }
}