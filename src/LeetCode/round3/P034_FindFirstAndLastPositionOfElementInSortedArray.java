package LeetCode.round3;

import java.util.Arrays;

/**
 * 240707
 * # Binary Search - Medium - 第一次做
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class P034_FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * AC: 0ms 100%, mem: 11.25%
     */
    public int[] searchRange(int[] nums, int target) {
        int left = -1, right = -1;
        int[] res = new int[]{left, right};
        int idx = bSearch(nums, target, 0, nums.length - 1);
        left = idx;
        right = idx;
        //找左边
        while (true){
            if(left > 0 && nums[left - 1] == target){
                int newLeft = bSearch(nums, target, 0, left - 1);
                if(newLeft == -1){
                    break;
                }else {
                    left = newLeft;
                }
            }else {
                break;
            }
        }
        //找右边
        while (true){
            if(right < nums.length - 1 && nums[right + 1] == target){
                int newRight = bSearch(nums, target, right + 1, nums.length - 1);
                if(newRight == -1){
                    break;
                }else {
                    right = newRight;
                }
            }else {
                break;
            }
        }
        return new int[]{left, right};
    }

    public int bSearch(int nums[], int target, int start, int end){
        int res = -1;
        if(nums == null || nums.length == 0)
            return res;
        while (start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == target)
                return mid;
            else if(target < nums[mid]){    //往左
                end = mid - 1;
            }else {     //往右
                start = mid + 1;
            }
        }
        return  res;
    }

    public static void main(String[] args) {
        P034_FindFirstAndLastPositionOfElementInSortedArray p = new P034_FindFirstAndLastPositionOfElementInSortedArray();
        System.out.println(Arrays.toString(p.searchRange(new int[]{5,7,7,8,8,10}, 8)));  //[3,4]
        System.out.println(Arrays.toString(p.searchRange(new int[]{5,7,7,8,8,10}, 6)));  //[-1,-1]
        System.out.println(Arrays.toString(p.searchRange(new int[]{}, 0)));  //[-1,-1]
        System.out.println(Arrays.toString(p.searchRange(new int[]{8,8,8,8,8,10}, 8)));  //[0,4]
    }
}
