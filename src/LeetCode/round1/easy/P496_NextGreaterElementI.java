package LeetCode.round1.easy;

import java.util.Arrays;

/**
 * 170410
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
 */
public class P496_NextGreaterElementI {

	/**
	 * AC: 30ms, 7.2%
	 * @param findNums
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		if(findNums == null || nums == null)
			return null;
		
        int[] res = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
			int cur =  findNums[i], bigger = -1;
			boolean found = false;
			for (int j = 0; j < nums.length; j++) {
				if(nums[j] != cur && !found)
					continue;
				if(nums[j] == cur){
					found = true;
					continue;
				}
				if(nums[j] > cur){
					bigger = nums[j];
					break;
				}
			}
			res[i] = bigger;
		}
        return res;
    }
	
	public static void main(String[] args) {
		P496_NextGreaterElementI p = new P496_NextGreaterElementI();
		System.out.println(Arrays.toString(p.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2})));
		System.out.println(Arrays.toString(p.nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4})));
	}

}
