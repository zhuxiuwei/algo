package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 160230
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example,  Given [3,2,1,5,6,4] and k = 2, return 5.
Note:  You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 */
public class P215_KthLargestElementInAnArray {
	
	/**
	 * 最直观的先排序。。。。O(nlgn)
	 * AC: 5ms, 81.4%
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest_sort(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}
	
	/**
	 * 用快速排序思想。
	 * @param args
	 */
	public int findKthLargest_quickSort(int[] nums, int k) {
		return helper(nums, 0, 1, nums.length - 1, k);
	}
	private int helper(int[] nums, int pivokIdx, int left, int right, int k){
		int pivok = nums[pivokIdx];
		while(left < right){
			while(left < right && nums[left] <= pivok)
				left ++;
			while(left < right && nums[right] >= pivok)
				right --;
			if(left + 1 != right){
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
			}else{
				nums[pivokIdx] = nums[left];
				nums[left] = pivok;
			}
		}
		System.out.println(Arrays.toString(nums));
		return 0;
	}
	
	
	public static void main(String[] args) {
		P215_KthLargestElementInAnArray p = new P215_KthLargestElementInAnArray();
		p.findKthLargest_quickSort(new int[]{5,1,3,2,7,4,8}, 4);
	}

}
