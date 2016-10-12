package LeetCode.round1.hard;

import java.util.Arrays;

/**
 * 161010-12
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class P287_FindTheDuplicateNumber {

	/**
	 * 符合条件的解法。
	 * 思路：先计算从1 ~ n的二进制表示，每个bit的个数，记为数组target；
	 * 然后计算nums中，所有数字，每个bit的个数，记为数组actual;
	 * 数组actual比target的bit多的位（不管多几），就是多出的数的二级制表示位。根据二进制表示位可以推算出多出的数字的十进制表示。
	 * 
	 * Space: 两个额外的32 bit数组；
	 * Time: O(n)
	 * AC: 15ms, 7.75%.
	 */
	public int findDuplicate_bit(int[] nums) {
		int[] target = new int[32], actual = new int[32];
		
		//fill target and actual array.
		for (int i = 1; i <= nums.length; i++) {
			int bit = 1;
			for (int j = 0; j < 32; j++) {
				if((i != nums.length) && (i & bit) == bit)
					target[j] ++;
				if((nums[i - 1]& bit) == bit)
					actual[j] ++;
				bit = bit << 1;
			}
		}
		
		//calculate final result based on target & actual
		int res = 0, bit = 1;
		for (int i = 0; i < 32; i++) {
			if(target[i] < actual[i])
				res = res | bit;
			bit = bit << 1;
		}
		
        return res;
    }
	
	/**
	 * （不符合条件的解法）
	 * Array sorted...so array is modified. O(Nlgn).... 6ms, 20.11%.
	 */
	public int findDuplicate_sort(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if(nums[i] == nums[i+1])
				return nums[i];
		}
        return 0;
    }
	
	/**
	 * （不符合条件的解法）
	 * Straight forward O(N^2).... 96ms, 5.66%.
	 */
	public int findDuplicate_ON2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int t = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if(nums[j] == t)
					return t;
			}
		}
        return 0;
    }
	
	public static void main(String[] args) {
		P287_FindTheDuplicateNumber p = new P287_FindTheDuplicateNumber();
		System.out.println(p.findDuplicate_bit(new int[]{4,5,3,1,2,4}));
	}

}
