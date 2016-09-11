package LeetCode.round1.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 160910 Easy 
Given an array of integers, every element appears twice except for one. Find that single one.
Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class P136_SingleNumber {

	/**
	 * Implementation without using extra memory(hash table), the preferred answer.
	 * Leverage XOR: a ^ a = 0;
	 */
	public int singleNumber(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) 
			res = res ^ nums[i];
		return res;
    }
	
	/**
	 * Simple when use hash table. But need O(N) space.
	 */
	public int singleNumber_HashTable(int[] nums) {
		int res = 0;
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(!set.contains(nums[i]))
				set.add(nums[i]);
			else
				set.remove(nums[i]);
		}
		for (int i: set)	//should be only 1 element left in set. 
			res = i;
		return res;
    }
	
	
	/**
	 * 失败1： 拿特例当普遍的典型。思路是错的。
	 * 可以通过的case：恰好出现2次的在在左右各出现一次。这个假设条件明显不对。
	 *  {2,2,1}
		{1,1,2}
		{10,2,3,3,2}
		{2,3,10,2,3}
		{1,2,3,3,2}
		{2,3,1,2,3}
		下面这个case就过不了：{10,1,1,3,3}，错误输出-4.
	 */
	public int singleNumber_fail1(int[] nums) {
		int leftSum =0,  rightSum = 0;
		//calculate left
		for (int i = 0; i < nums.length/2; i++) 
			leftSum += nums[i];
		
		//calculate right
		for (int i = nums.length/2 + 1; i < nums.length; i++) 
			rightSum += nums[i];
		
		if(leftSum == rightSum)
			return nums[nums.length/2];
		else{
			int leftRightDiff = Math.abs(rightSum - leftSum);
			
			//the single one has two possible values
			int possible1 = nums[nums.length/2] + leftRightDiff;
			int possible2 = nums[nums.length/2] - leftRightDiff;
			
			//check possible1 or possible2 
			int possible1Count = 0;
			for (int i = 0; i < nums.length; i++) {
				if(nums[i] == possible1)
					possible1Count ++;
			}
			return possible1Count % 2 == 0 ? possible2: possible1;
		}
    }
	
	public static void main(String[] args) {
		P136_SingleNumber p = new P136_SingleNumber();
		System.out.println(p.singleNumber(new int[]{2,2,1}));
		System.out.println(p.singleNumber(new int[]{1,1,2}));
		System.out.println(p.singleNumber(new int[]{10,2,3,3,2}));
		System.out.println(p.singleNumber(new int[]{2,3,10,2,3}));
		System.out.println(p.singleNumber(new int[]{1,2,3,3,2}));
		System.out.println(p.singleNumber(new int[]{2,3,1,2,3}));
		System.out.println(p.singleNumber(new int[]{10,1,1,3,3}));
	}

}
/**
 * - 第一次的思路完全是错的，见singleNumber_fail1
 * - 利用异或的性质：性质1：a ^ a = 0，性质2：0 ^ a = a。 性质3：交换律。  --->  a ^ a ^ b = b.  不熟悉这个操作。
 */
