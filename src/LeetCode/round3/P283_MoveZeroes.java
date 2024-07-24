package LeetCode.round3;

import java.util.Arrays;

/**
 * 240724
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class P283_MoveZeroes {

	/**
	 * AC: 1ms 100%, mem: 28.8%
	 */
	public void moveZeroes(int[] nums) {
		if(nums == null || nums.length == 0)
			return;

		//移动不为0的
		int okIdx = 0;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] != 0) {
				nums[okIdx] = nums[i];
				okIdx++;
			}
		}

		//补齐0
		for (int i = okIdx; i < nums.length; i++) {
			nums[i] = 0;
		}

		System.out.println(Arrays.toString(nums));

    }
	public static void main(String[] args) {
		P283_MoveZeroes p = new P283_MoveZeroes();
		p.moveZeroes(new int[]{0, 1, 0, 3, 12});	//[1, 3, 12, 0, 0].
		p.moveZeroes(new int[]{2, 0, 1, 0, 3, 12, 0, 0});	//{2, 1, 3, 12, 0, 0, 0, 0}
	}

}
