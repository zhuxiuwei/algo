package LeetCode.round1.medium;
/**
 * 161212
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class P053_MaximumSubarray {
	
	/**
	 * TODO: divide and conquer solution
	 */
	public int maxSubArray_DivideAndConquer(int[] nums) {
		return 0;
	}
	
	/**
	 * 1 time AC: 17ms, 29.6%.
	 * O(n) solution.
	 */
	public int maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE, tempSum = 0;
		if(nums == null || nums.length == 0)
			return max;
		
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] > max)
				max = nums[i];
			if(tempSum + nums[i]> 0){
				tempSum += nums[i];
				if(tempSum > max)
					max = tempSum;
			}else
				tempSum = 0;
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		P053_MaximumSubarray p = new P053_MaximumSubarray();
		System.out.println(p.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));	//6
		System.out.println(p.maxSubArray(new int[]{-2,-3,-1,-5}));	//-1
	}

}
