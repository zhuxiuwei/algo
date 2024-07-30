package LeetCode.round3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 240729
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 * Follow up:
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class P189_RotateArray {

    /**
     * AC: 15ms, 5%，mem: 96%
     * 依次查看法：对于数组中的每个元素，先找到它下一个元素，下一个元素再找下一个元素，直到全部找完为止。
     * ！！！ 这个算法的问题是step设置的不对，可能导致步骤间成环，最后死循环！！！
     * 满足 in-place with O(1) extra space
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length <= 1 || k <= 0)
            return;
        k = k % nums.length;
        List<Integer> steps = getSteps(nums.length, k);
        System.out.println(steps);
        for(int step: steps) {
            helper(nums, step);
        }
        System.out.println(Arrays.toString(nums));
    }

    //实际执行旋转的逻辑
    private void helper(int[] nums, int k) {
        int oldIdx = 0, newIdx = 0, oldVal = nums[0], tmp = 0;
        for (int i = 0; i < nums.length ; i++) {
            newIdx = (oldIdx + k) % nums.length;
            tmp = nums[newIdx];
            nums[newIdx] =  oldVal;
            oldVal = tmp;
            oldIdx = newIdx;
        }
    }

    /**
     * ！！！！难点在这里，需要知道能够形成环，以及梳理出什么条件下会成环。！！！！！
     * 当走的步数能和数组长度有公约数时，依次查看法会导致循环。 比如nums.length=4, k=2, 查看的会是 0 -> 2 -> 0 -> 2....无限循环
     * 这个方法将根据nums长度和k的值，将k拆开，避免重复循环走的情况。
     */
    private List<Integer> getSteps(int numLength, int k){
        List<Integer> steps = new ArrayList<>();
        while (k > 0){
            if(getGongYueShu(numLength , k)){ //有公约数
                k = k - 1;
                steps.add(1);
            }else {
                steps.add(k);
                k = k - k;
            }
        }
        return steps;
    }

    /**
     * i, j是否有公约数。
     * 最后AC的效率不高，应该是因为这个算法应该比较慢。
     */
    private boolean getGongYueShu(int i, int j){
        if(i == 1 || j == 1)
            return false;
        if(i % j == 0 || j % i == 0)
            return true;
        int min = Math.min(i, j);
        for (int k = 2; k <= min; k++) {
            if(i % k == 0 && j % k == 0){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P189_RotateArray p = new P189_RotateArray();
        p.rotate(new int[]{1,2,3,4,5,6,7}, 3);  //[5,6,7,1,2,3,4]
        System.out.println("---");
        p.rotate(new int[]{-1,-100,3,99}, 2);   //[3,99,-1,-100]
        System.out.println("---");
        p.rotate(new int[]{1,2,3,4,5,6}, 4);   //[3,4,5,6,1,2]
    }
}
