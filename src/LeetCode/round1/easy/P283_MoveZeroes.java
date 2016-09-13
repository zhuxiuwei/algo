package LeetCode.round1.easy;

import java.util.Arrays;

/**
 * 160913
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class P283_MoveZeroes {

	public void moveZeroes(int[] nums) {
		int i = -1;	//last 0 start index
		int j = 0;  //current non-zero start index
		int total = 0;	//total zero count
		
		//assign non-zeros
		for(; j < nums.length; j++){
			if(nums[j] == 0 ){
				if((j > 1 && nums[j - 1] != 0) || i == -1)
					i= j;
				total ++;
			}else{
				if(i != -1)
					nums[j - total] = nums[j];
			}
		}
		
		//assign zeros
		for(int k = nums.length - 1; k >= nums.length - total ; k--)
			if(nums[k] != 0 )
				nums[k] = 0;
		
        System.out.println(Arrays.toString(nums));
    }
	public static void main(String[] args) {
		P283_MoveZeroes p = new P283_MoveZeroes();
		p.moveZeroes(new int[]{0, 1, 0, 3, 12});
		p.moveZeroes(new int[]{2, 0, 1, 0, 3, 12, 0, 0});
	}

}

/**
 * 第一遍没仔细看题，没注意所有非0元素的要保持原来的顺序，错了。修改后一次AC。
 */
