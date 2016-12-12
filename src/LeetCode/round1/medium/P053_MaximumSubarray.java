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
	
	/**
	 * divide and conquer solution. Wrong.
	 * 修修补补的，花了很多时间，最后还不对。
	 */
	public int maxSubArray_DivideAndConquer_WRONG(int[] nums) {
		ConquerResult res = helper(nums, 0, nums.length - 1);
		return res.maxValue;
	}
	private ConquerResult helper(int[] nums, int start, int end){
		int mid = (end + start)/2;
		ConquerResult leftRes = null, rightRes = null;
		if(start < mid)
			leftRes = helper(nums, start, mid);
		if(mid + 1 <= end && leftRes != null)
			rightRes = helper(nums, mid + 1, end);
		if(start == end){
			ConquerResult c = new ConquerResult();
			c.start = start; c.end = start; c.maxEnd = start; c.maxStart = start; c.maxValue = nums[start];
			return c;
		}
		if(start + 1 == end){
			ConquerResult c = new ConquerResult();
			c.start = start; c.end = end; 
			if(nums[start] >= 0 && nums[end] >= 0){
				c.maxStart = start;
				c.maxEnd = end;
				c.maxValue = nums[start] + nums[end];
			}else{
				int idx = nums[start] > nums[end] ? start: end;
				c.maxStart = idx;
				c.maxEnd = idx;
				c.maxValue = nums[idx];
			}
			return c;
		}else		
			return mergeConquerResult(nums, leftRes, rightRes);
	}
	private ConquerResult mergeConquerResult(int[] nums, ConquerResult leftRes, ConquerResult rightRes){
		if(leftRes.maxValue <= 0 && rightRes.maxValue >= 0){
			rightRes.start = leftRes.start;
			return rightRes;
		}
		else if(leftRes.maxValue >= 0 && rightRes.maxValue <= 0){
			leftRes.end = rightRes.end;
			return leftRes;
		}
		else if(leftRes.maxValue <= 0 && rightRes.maxValue <= 0){
			ConquerResult c = leftRes.maxValue >= rightRes.maxValue ? leftRes: rightRes;
			c.start = leftRes.start; c.end = rightRes.end;
			return c;
		}
		else{	//both > 0
			ConquerResult c = new ConquerResult();
			if(leftRes.start == 0 && rightRes.end == nums.length - 1){	//the last merge。这一段代码比较蠢。修修补补，最后{8,-19,5,-4,20}还是挂了。
				int newSum =  leftRes.maxValue, tempMax = leftRes.maxValue;
				for (int i = leftRes.maxEnd + 1; i < rightRes.maxStart; i++) {
					newSum += nums[i];
					if(tempMax < newSum){
						tempMax = newSum;
						c.maxValue = tempMax;
					}
				}
				newSum += rightRes.maxValue;
				if(c.maxValue < newSum)
					c.maxValue = newSum;
				if(c.maxValue < rightRes.maxValue)
					c = rightRes;
			}else{
				int newSum =  leftRes.maxValue + rightRes.maxValue;
				for (int i = leftRes.maxEnd + 1; i < rightRes.maxStart; i++) 
					newSum += nums[i];
				if(newSum >= leftRes.maxValue && newSum >= rightRes.maxValue){
					c.maxValue = newSum; c.start = leftRes.start; c.end = rightRes.end; c.maxStart = leftRes.maxStart; c.maxEnd = rightRes.maxEnd;
				}else{
					c = leftRes.maxValue >= rightRes.maxValue ?  leftRes: rightRes;
					c.start = leftRes.start; c.end = rightRes.end;
				}
			}
			return c;
		}
	}
	class ConquerResult{
		int start;
		int end;
		int maxStart;
		int maxEnd;
		int maxValue;
	}
	
	
	public static void main(String[] args) {
		P053_MaximumSubarray p = new P053_MaximumSubarray();
		System.out.println(p.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));	//6
		System.out.println(p.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,10}));	//11
		System.out.println(p.maxSubArray(new int[]{-2,1,-3,4,-1,-2,-1,-5,10}));	//10
		System.out.println(p.maxSubArray(new int[]{-2,-3,-1,-5}));	//-1
		System.out.println(p.maxSubArray(new int[]{1,-5,4}));	//4
		System.out.println(p.maxSubArray(new int[]{8,-19,5,-4,20}));	//21
	}

}
