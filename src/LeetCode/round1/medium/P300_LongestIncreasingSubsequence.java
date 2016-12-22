package LeetCode.round1.medium;
/**
 * 161222
Given an unsorted array of integers, find the length of longest increasing subsequence.
For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */
public class P300_LongestIncreasingSubsequence {

	/**
	 * Refer: https://discuss.leetcode.com/topic/30721/my-easy-to-understand-o-n-2-solution-using-dp-with-video-explanation
	 * 27ms, 47.35%
	 */
	public int lengthOfLIS(int[] nums) {
		// Base case
		if (nums.length <= 1)
			return nums.length;

		// This will be our array to track longest sequence length
		int T[] = new int[nums.length];

		// Fill each position with value 1 in the array
		for (int i = 0; i < nums.length; i++)
			T[i] = 1;

		// Mark one pointer at i. For each i, start from j=0.
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				// It means next number contributes to increasing sequence.
				if (nums[j] < nums[i]) {
					// But increase the value only if it results in a larger value of the sequence than T[i]
					// It is possible that T[i] already has larger value from some previous j'th iteration
					if (T[j] + 1 > T[i]) 
						T[i] = T[j] + 1;
				}
			}
		}

		// Find the maximum length from the array that we just generated
		int longest = 0;
		for (int i = 0; i < T.length; i++)
			longest = Math.max(longest, T[i]);

		return longest;
	}

	public static void main(String[] args) {
		P300_LongestIncreasingSubsequence p = new P300_LongestIncreasingSubsequence();
		System.out.println(p.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));	//4
	}

}
