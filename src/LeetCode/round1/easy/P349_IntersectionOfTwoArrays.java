package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 160917
Given two arrays, write a function to compute their intersection.
Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
Note:
Each element in the result must be unique.
The result can be in any order.
 */
public class P349_IntersectionOfTwoArrays {

	/**
	 * Use quick sort and two pointers. AC: 17 ms
	 * Time: O(nlgn), space: 0(1)
	 * @param num
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		//sort first. O(nlgn)
		sort(nums1);
		sort(nums2);
		
		//merge. O(n).
		int i = 0, j = 0;
		List<Integer> res = new ArrayList<Integer>();
		while(i <= nums1.length - 1 && j <= nums2.length - 1){
			if(nums1[i] < nums2[j])
				i ++; 
			else if(nums1[i] > nums2[j])
				j ++;
			else{
				if(res.size() == 0 || res.get(res.size() - 1) != nums1[i])
					res.add(nums1[i]);
				i++;
				j++;
			}
		}
		
		int[] r = new int[res.size()];
        Iterator<Integer> it = res.iterator();
        for (int k = 0; it.hasNext(); k++) 
        	r[k] = it.next();
        System.out.println(res);
        return r;
	}
	
	//sort
	private void sort(int[] num){
		quickSort(num, 0, num.length - 1);
		System.out.println(Arrays.toString(num));
	}
	private void quickSort(int num[], int start, int end){
		if(num == null || num.length == 0 || start >= end)
			return;

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
			
		quickSort(num, start, r - 1);
		quickSort(num, r + 1, end);
	}
	
	
	
	/**
	 * Use hash table version. Simple. AC: 7ms
	 * Time: O(n), space: O(n)
	 */
	public int[] intersection_hashtable(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> res = new HashSet<Integer>();
        for (int i : nums1)
        	set.add(i);
        for (int i : nums2)
        	if(set.contains(i))
        		res.add(i);
        int[] r = new int[res.size()];
        Iterator<Integer> it = res.iterator();
        for (int j = 0; it.hasNext(); j++) 
        	r[j] = it.next();
        return r;
    }
	
	public static void main(String[] args) {
		P349_IntersectionOfTwoArrays p = new P349_IntersectionOfTwoArrays();
		p.sort(new int[]{0,1, 0,0});
		
		p.intersection(new int[]{1,4,3,2,2,1}, new int[]{4,2,2});
	}

}
