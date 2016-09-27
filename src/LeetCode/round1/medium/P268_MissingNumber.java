package LeetCode.round1.medium;
/**
 * 160927
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
For example, Given nums = [0, 1, 3] return 2.

Note: Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity? 
 */
public class P268_MissingNumber {

	/**
	 * AC: 1ms, 45%
	 * !!!1个bug: 要注意特殊处理这样的special case:  {0,1,2,3} should return 4。 因为这个case fail了3次。
	 */
	public int missingNumber(int[] nums) {
		int max = -1;
		int sum = 0;
		boolean hasZero = false;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] == 0)
				hasZero = true;
			sum += nums[i];
			if(nums[i] > max)
				max = nums[i];
		}
		int remain = max*(1 + max)/2 - sum;
		if(remain == 0 && hasZero)	
			return max + 1;		//!!!! Note bug: note for such special case: {0,1,2,3} should return 4.
		return remain;
	}
	
	public static void main(String[] args) {
		P268_MissingNumber p = new P268_MissingNumber();
		System.out.println(p.missingNumber(new int[]{0,2,3}));
		System.out.println(p.missingNumber(new int[]{1,2,3}));
		System.out.println(p.missingNumber(new int[]{1}));
		System.out.println(p.missingNumber(new int[]{0}));	//1
		System.out.println(p.missingNumber(new int[]{3,2,0,1}));	//4
	}

}
