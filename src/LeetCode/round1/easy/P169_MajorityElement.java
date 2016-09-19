package LeetCode.round1.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 160919
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.
 */

/**
 *	1. O(1)的方案自己想不到，挺巧妙的。参考的LeetCode最热方案：https://discuss.leetcode.com/topic/8692/o-n-time-o-1-space-fastest-solution
 *  2. 利用quick sort partition思路的majorityElement_quickSortPartition_failed，理论上应该比majorityElement_sort更快，结果timeout，可能是我写的partition在一些特殊输入时（如1,1,1,1,2,2,2,2,2）不够高效吧。
 */
public class P169_MajorityElement {
	
	/**
	 * How to implement in O(1) space ----  time O(N) space O(1)。 AC: 2ms
	 */
	public int majorityElement(int[] nums) {
		int major = nums[0], count = 1;
		for (int i = 1; i < nums.length; i++) {
			if(nums[i] == major)
				count ++;
			else
				count --;
			
			if(count == 0){
				count = 1;
				major = nums[i];
			}
		}
        return major;
    }
	
	
	
	/**
	 * time O(N) space O(N)。 AC: 21ms
	 */
	public int majorityElement_hash(int[] nums) {
		Map<Integer, Integer>  m = new HashMap<Integer, Integer>();
		for (int num: nums) 
			m.put(num, m.getOrDefault(num, 0) + 1);
		
		int maxCount = -1, max = 0;
		for (Map.Entry<Integer, Integer> entry: m.entrySet()) 
			if(entry.getValue() > maxCount){
				max = entry.getKey();
				maxCount = entry.getValue();
			}
		
        return max;
    }
	
	
	
	/**
	 * time O(nlogn) space O(1)。 AC: 3ms
	 */
	public int majorityElement_sort(int[] nums) {
		Arrays.sort(nums);	//sort
        return nums[nums.length/2];	//pick the middle one.
    }
	
	
	
	/**
	 * Leverage quick sort partition idea. When find the mid position element, return it. 
	 * time O(nlogn) space O(1)。 Failed to AC: timeout.
	 */
	public int majorityElement_quickSortPartition_failed(int[] nums) {
		return partition(nums, 0, nums.length - 1);
    }
	private int partition(int num[], int start, int end){
		int t = num[end], l = start, r = end;
		
		while(l < r){
			while(l < r && num[l] <= t)
				l++;
			while(l < r && num[r] >= t)
				r--;
			//swap
			int temp = num[l];
			num[l] = num[r];
			num[r] = temp;
		}
		
		//when l==r
		num[end] = num[r];
		num[r] = t;
		if(r == num.length / 2)	//found the middle one.
			return num[r];
		else if((r > num.length / 2))
			return partition(num, start, r - 1);
		else
			return partition(num, r + 1, end);
	}
	
	
	public static void main(String[] args) {
		P169_MajorityElement p = new P169_MajorityElement();
		System.out.println(p.majorityElement_hash(new int[]{3,4,3}));
		System.out.println(p.majorityElement_sort(new int[]{3,4,3}));
		System.out.println(p.majorityElement_quickSortPartition_failed(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}));
		System.out.println(p.majorityElement(new int[]{3,4,3,4,4,4}));
	}

}
