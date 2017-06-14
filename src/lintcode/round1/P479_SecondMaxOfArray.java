package lintcode.round1;
/**
 * 170614 Naive
Find the second max number in a given array.

Notice: You can assume the array contains at least two numbers.

Example
Given [1, 3, 2, 4], return 3.
Given [1, 2], return 1.
 */
public class P479_SecondMaxOfArray {

	/**
	 * 一次AC。简单。
     * @param nums: An integer array.
     * @return: The second max number in the array.
     */
	public int secondMax(int[] nums) {
		if(nums == null || nums.length < 2)
			return Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE, maxTime = 1;
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			if(n >= max){
				if(n == max)
					maxTime ++;
				else
					maxTime = 1;
				max = n;
			}
		}
		if(maxTime > 1)
			return max;
		int second = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			if(second < n && n != max)
				second = n;
		}
		return second;
    }
	
	public static void main(String[] args) {
		P479_SecondMaxOfArray p = new P479_SecondMaxOfArray();
		System.out.println(p.secondMax(new int[]{6,7,4,3,6,1, 7, 7}));	//7
		System.out.println(p.secondMax(new int[]{6,7,4,3,6,1}));	//6
	}

}
