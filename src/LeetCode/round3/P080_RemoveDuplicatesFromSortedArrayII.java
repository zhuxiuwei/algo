package LeetCode.round3;

import java.util.Arrays;

/**
 * 241008 medium
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * Return k after placing the final result in the first k slots of nums.
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Custom Judge:
 * The judge will test your solution with the following code:
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * int k = removeDuplicates(nums); // Calls your implementation
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 */
public class P080_RemoveDuplicatesFromSortedArrayII {
    /**
     * AC: Runtime 1 ms Beats 19.69%, Memory 44.37 MB Beats 67.98%
     * 顺利
     */
    public int removeDuplicates(int[] nums) {
        int res = 1, lastIdx = 1, last = nums[0], lastCount = 1;
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            if(n != last){
                res ++;
                nums[lastIdx ++] = n;
                last = n;
                lastCount = 1;
            }else {
                lastCount ++;
                if(lastCount == 2){
                    nums[lastIdx ++] = n;
                    res++;
                }
            }
        }
//        System.out.println(Arrays.toString(nums));
        return res;
    }

    public static void main(String[] args) {
        P080_RemoveDuplicatesFromSortedArrayII p = new P080_RemoveDuplicatesFromSortedArrayII();
        System.out.println(p.removeDuplicates(new int[]{1,1,1,2,2,3}));   //5, nums = [1,1,2,2,3,_]
        System.out.println(p.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));   //7, nums = [0,0,1,1,2,3,3,_,_]
    }
}

