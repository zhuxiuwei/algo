package LeetCode.round3;

/**
 * 240801 Medium
 * ou are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words,
 * if you are at nums[i], you can jump to any nums[i + j] where:
 *  - 0 <= j <= nums[i] and
 *  - i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 */
public class P045_JumpGameII {
    /**
     * AC: 11ms Beats 26.02%, mem 57.25%
     * 思路：
     * 1、从后往前遍历，找到从后往前最远处还能到达出发点的位置。
     * 2、从后往前遍历时，从1开始计数，计数结果记为c。如果c > 数组位置元素，则表示从此idx能走到起始计数位置。
     * 3、遍历一遍，记录最早能到的位置。
     * 4、然后从此位置，继续遍历，回到1。直到数组起点能够到达计数位置为止。
     */
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1)
            return 0;
        int res = 0, idx = nums.length - 1, lastMax = idx, steps = 0;
        while (true){
            while (idx > 0){
                idx --;
                steps ++;
                if(nums[idx] >= steps){ //从这个位置能走到计数开始处
                    lastMax = idx;
                }
            }
            res ++;     //！！！ 记录结果放在这里。这块出了bug，debug写的。
            if(lastMax == 0)    //已经遍历完，退出
                break;
            else {
                idx = lastMax;
                steps = 0;
            }
        }
        return res;
    }

    /**
     * 按照jump game1思路写的代码，思路不对，不能按照jumpGame1的思路。
     * 如，{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3}，按照这个思路返回4，而最少的步数是2.（7 + 7 步）
     */
    public int jump_wrong(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1)
            return 0;
        int res = 1, idx = 0, jumpLeft = nums[idx];
        while (jumpLeft >=0){
            if(idx + jumpLeft >= nums.length - 1)
                return res;
            idx ++;
            if(idx >= nums.length - 1)
                break;
            int curJump = nums[idx];
            jumpLeft --;
            if(curJump > jumpLeft){
                res ++;
                jumpLeft = curJump;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P045_JumpGameII p = new P045_JumpGameII();
        System.out.println(p.jump(new int[]{2,3,1,1,4}));   //2
        System.out.println(p.jump(new int[]{2,3,0,1,4}));   //2
        System.out.println(p.jump(new int[]{0}));   //0
        System.out.println(p.jump(new int[]{2,3,1}));   //1
        System.out.println(p.jump(new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3}));   //2
    }
}
