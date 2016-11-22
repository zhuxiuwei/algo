package LeetCode.round1.easy;

import java.util.Arrays;

public class P462_MinimumMovesToEqualArrayElementsII {

	/**
	 * One time AC: 9ms
	 * Sort the array, Minimum Moves are moves of all numbers to the middle number in array.
	 * @param nums
	 * @return
	 */
	public int minMoves2(int[] nums) {
		int sum = 0;
		if(nums == null || nums.length <= 1)
			return 0;
        Arrays.sort(nums);
        int mid = nums[nums.length/2];
        for (int i = 0; i < nums.length; i++) 
			sum += Math.abs(nums[i] - mid);
        return sum;
    }
	
	public static void main(String[] args) {
		P462_MinimumMovesToEqualArrayElementsII p = new P462_MinimumMovesToEqualArrayElementsII();
		System.out.println(p.minMoves2(new int[]{1,1,1,1,1,2,3}));
	}

}
