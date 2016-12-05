package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 161205
Note: This is an extension of House Robber.
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
Meanwhile, the security system for these houses remain the same as for those in the previous street.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class P213_HouseRobberII {

	/**
	 * 思路：两次利调用 P198 HouseRobber里的算法，分别求0...n-1 和1...n的值，选择2者之间大的。
	 * AC: 1ms, 5.2%.
	 */
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		if(nums.length == 1)
			return nums[0];
		int[] temp1 = new int[nums.length - 1], temp2 = new int[nums.length - 1];
		for (int i = 0; i < nums.length - 1; i++) 
			temp1[i] = nums[i];
		for (int i = 1; i < nums.length; i++) 
			temp2[i - 1] = nums[i];
		int r1 = rob_orogin(temp1);
		int r2 = rob_orogin(temp2);
		return Math.max(r1, r2);
	}
	public int rob_orogin(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		if(nums.length == 1)
			return nums[0];
		int[] res = new int[nums.length];
		res[0] = nums[0];
		res[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < res.length; i++) 
			res[i] = Math.max(res[i -1], res[i - 2] + nums[i]);
		return res[res.length - 1];
	}
	
	/**
	 * 思路错了。{2,2,4,3,2,5}会返回9.
	 */
	public int rob_wrong(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		if(nums.length == 1)
			return nums[0];
		int[] res = new int[nums.length];
		boolean[] containNum0 = new boolean[nums.length];
		containNum0[0] = true;
		res[0] = nums[0];
		res[1] = Math.max(nums[0], nums[1]);
		if(nums[1] < nums[0])
			containNum0[1] = true;
		for (int i = 2; i < res.length; i++) {
			if(i < res.length - 1){
				int r1 = res[i -1];
				int r2 = res[i - 2] + nums[i];
				if(r2 >= r1){
					if(containNum0[i - 2])
						containNum0[i] = true;
					res[i] = r2;
				}else{
					if(containNum0[i - 1])
						containNum0[i] = true;
					res[i] = r1;
				}
			}
			else{
				int r1 = res[i -1];
				int r2 = 0;
				if(!containNum0[i - 2]){
					r2 = res[i - 2] + nums[i];
				}else{
					r2 = nums[i] >= nums[0] ? res[i - 2]- nums[0] + nums[i]: res[i - 2];
				}
				res[i] = Math.max(r1, r2);
			}
		}
		System.out.println(Arrays.toString(containNum0));
		return res[res.length - 1];
    }
	
	public static void main(String[] args) {
		P213_HouseRobberII p = new P213_HouseRobberII();
		System.out.println(p.rob(new int[]{1,3}));	//3
		System.out.println(p.rob(new int[]{1,2,3}));	//3
		System.out.println(p.rob(new int[]{2,1,1,2}));	//3
		System.out.println(p.rob(new int[]{6,3,10,8,2,10,3,5,10,5,3}));	//36	
		System.out.println(p.rob(new int[]{2,2,4,3,2,5}));	//10
	}

}
