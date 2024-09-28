package LeetCode.round3;
/**
 * 240929 medium
 * https://leetcode.com/problems/find-peak-element/?envType=study-plan-v2&envId=top-interview-150
 *
 * A peak element is an element that is strictly greater than its neighbors.
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums[i] != nums[i + 1] for all valid i.
 */
public class P162_FindPeakElement {
    /**
     *  AC: Runtime 0 ms Beats 100.00%, Memory 42.93 MB Beats 7.83%
     *  o(logn)的思路自己想不到，看的别人：
        https://leetcode.com/problems/find-peak-element/solutions/1290642/intuition-behind-conditions-complete-explanation-diagram-binary-search/?envType=study-plan-v2&envId=top-interview-150
     * 二分查找的应用。这种o(lgn)的可以往二分查找去想。
     * 我一开始往堆排序调整思路去向，构建完全二叉树，但是发现找不到规律。
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return 0;

        //避免极端情况: mid后递增
        if(nums[0] > nums[1])
            return 0;
        //避免极端情况: mid前递减
        if(nums[n-1] > nums[n - 2])
            return n - 1;

        //二分查找的使用，只要不是极端的mid左右的递增/递减，一定能找到。极端的mid左右的递增/递减情况，被上面几行代码就catch住了。
        int left = 1, right = n - 2, mid = 0;
        while (left <= right){
            mid = (left + right) / 2;
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {    //mid就是最高峰
                return mid;
            }else if(nums[mid] > nums[mid-1]) {  //往右找
                left = mid + 1;
            }else if(nums[mid] < nums[mid-1]) {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        P162_FindPeakElement p = new P162_FindPeakElement();
        System.out.println(p.findPeakElement(new int[]{1,2,3,1}));  //2
        System.out.println(p.findPeakElement(new int[]{1,2,1,3,5,6,4}));  //1 or 5

    }
}
