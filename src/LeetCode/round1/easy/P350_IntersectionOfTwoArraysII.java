package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 160927
Given two arrays, write a function to compute their intersection.
Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays. The result can be in any order.
 */
public class P350_IntersectionOfTwoArraysII {
	
	/**
	 * Use quick sort and two pointers. AC: 4ms, 79%.
	 * Time: O(nlgn), space: 0(1)
	 */
	public int[] intersect_sort(int[] nums1, int[] nums2) {
		List<Integer> res = new ArrayList<Integer>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0;
		while(i < nums1.length && j < nums2.length){
			if(nums1[i] < nums2[j])
				i++;
			else if(nums1[i] > nums2[j])
				j++;
			else{
				res.add(nums1[i]);
				i++;
				j++;
			}
		}
		
		int[] r = new int[res.size()];
		for (int k = 0; k < r.length; k++) 
			r[k] = res.get(k);
	    return r;    
    }

	/**
	 * Use HashMap. AC: 8ms, 26%.
	 * Time: O(n), space: 0(n)
	 */
	public int[] intersect_hash(int[] nums1, int[] nums2) {
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    List<Integer> res = new ArrayList<Integer>();
	    for (int i: nums1) 
			map.put(i, map.getOrDefault(i, 0) + 1);
	    
	    for (int i: nums2){
	    	if(map.containsKey(i)){
	    		int val = map.get(i);
    			res.add(i);
	    		if(val == 1)
	    			map.remove(i);
	    		else
		    		map.put(i, val - 1);
	    	}
	    }
	    
	    int[] r = new int[res.size()];
		for (int k = 0; k < r.length; k++) 
			r[k] = res.get(k);
	    return r;
	 }
	
}
