package LeetCode.round3;

/**
 * 240709 贪心问题。 第一次做。
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element
 * in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
public class P055_JumpGame {

    /**
     * AC：2ms, 80%. mem: 43%
     * 思路：每到一个位置，就比较当前剩下的步数，和此位置可以跳的步数，选更大者。即始终保持能跳尽可能远的位置。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums == null)
            return false;
        int curIdx = 0, jumpLeft = nums[0];
        while (jumpLeft >= 0){
            if(curIdx == nums.length - 1)   //已经到了数组最后的位置
                return true;
            int curJump = nums[curIdx]; //当前index元素可以jump的步数
            if(curJump > jumpLeft){     //当前index元素可以jump的步数更大，则更新剩余步骤。
                jumpLeft = curJump;
            }
            curIdx ++;
            jumpLeft --;
        }
        return false;
    }

    public static void main(String[] args) {
        P055_JumpGame p = new P055_JumpGame();
        System.out.println(p.canJump(new int[]{2,3,1,1,4}));    //true
        System.out.println(p.canJump(new int[]{3,2,1,0,4}));    //false
    }
}
