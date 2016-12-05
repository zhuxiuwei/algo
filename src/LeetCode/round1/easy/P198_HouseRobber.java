package LeetCode.round1.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 161202
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping 
you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses 
were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class P198_HouseRobber {
	
	/**
	 * DP 
	 * 
	 */
	public int rob(int[] nums) {
		return 1;
	}
	
	/**
	 * 思路：每次看三个，依次往前看。
	 * 完全不对。因为可能导致“链”断掉。 {2,4,8,9,9,3}就过不了。最后选了8,9.把2丢掉了。
	 */
	public int rob_wrong(int[] nums) {
		Set<Integer> robbedIndex = new HashSet<Integer>();
		if(nums == null || nums.length == 0)
			return 0;
		else if(nums.length == 1)
			return nums[0];
		else if(nums.length == 2)
			return Math.max(nums[0], nums[1]);
		else{
			for (int i = 0; i < nums.length - 2; i++) {
				if(nums[i + 1] >= nums[i] + nums[i+2])
					robbedIndex.add(i + 1);
				else{
					robbedIndex.add(i);
					robbedIndex.add(i + 2);
					if(i > 0 && robbedIndex.contains(i - 1)){	//这个if，能通过{4, 9, 7, 1}的case。开始这里想简单了，4 9 7 1这种过不了。
						if(nums[i - 1] + nums[i + 1] <= nums[i] + nums[i + 2]){
							if(nums[i] >= nums[i - 1])
								robbedIndex.remove(i - 1);
							else
								robbedIndex.remove(i);
							robbedIndex.remove(i + 1);
						}
						else{
							robbedIndex.remove(i);
							robbedIndex.remove(i + 2);
							robbedIndex.add( i + 1);
						}
					}
				}
			}
			int res = 0;
			for (int i: robbedIndex) 
				res += nums[i];
			return res;
		}
    }
	
	public static void main(String[] args) {
		P198_HouseRobber p = new P198_HouseRobber();
		System.out.println(p.rob(new int[]{1,3}));	//3
		System.out.println(p.rob(new int[]{0,1,3,0,5,1,7,100}));	//108
		System.out.println(p.rob(new int[]{1,100,2,3,1000,5,100,200,300,400}));	//1700
		System.out.println(p.rob(new int[]{4, 9, 7, 1}));	//11 *
		System.out.println(p.rob(new int[]{2,4,8,9,9,3}));	//19 **
		System.out.println(p.rob(new int[]{2,4,8,9,9,11,13,15,15,15,9}));	//56 **
	}

}
