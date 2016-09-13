package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 160911-13
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number. 
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
 * Please note that your returned answers (both index1 and index2) are not zero-based. You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 
 * Output: index1=1, index2=2
 */
public class P167_TwoSumII_InputArrayIsSorted {

	/**
	 * Leverage Binary search solution. O(nlogn) runtime, O(1) space
	 */
	public int[] twoSum_binarySearch(int[] numbers, int target) {
		for (int i = 0; i < numbers.length; i++) {
			int t = binarySearch(numbers, target - numbers[i], i + 1);
			if(t != -1)
				return new int[]{i + 1, t + 1};
		}
		return null;
	}
	private int binarySearch(int[] numbers, int target, int start){
		int left = start, right = numbers.length - 1;
		while(left < right){
			int mid = (left + right)/2;
			if(numbers[mid] == target)
				return mid;
			else{
				if(numbers[mid] < target)
					left = mid + 1;
				else
					right = mid - 1 ;
			}
		}
		if(left == right && numbers[left] == target)
			return left;
		return -1;
	}
	
	/**
	 * O(n) runtime, O(1) space solution – Two pointers:
	 */
	public int[] twoSum_twoPointers(int[] numbers, int target) {
		int l = 0, r = numbers.length - 1;
		while(l < r){
			if(numbers[l] + numbers[r] == target)
				return new int[]{l + 1, r + 1};
			else if(numbers[l] + numbers[r] < target){
				l++;
			}else
				r--;
		}
		return null;
	}

	public static void main(String[] args) {
		P167_TwoSumII_InputArrayIsSorted p = new P167_TwoSumII_InputArrayIsSorted();
		System.out.println(Arrays.toString(p.twoSum_binarySearch(new int[] {3,24,50,79,88,150,345},201)));
		System.out.println(Arrays.toString(p.twoSum_twoPointers(new int[] {3,24,50,79,88,150,345},201)));
	}

}

/**
 * 1. 二分搜索写的还是不能bug free，导致reject了几次。
 * 2. O(n) Runtime的两个指针算法，没有想到。看的指导。其实应该挺直观好想的。
 */
