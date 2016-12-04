package LeetCode.round1.medium;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 161204
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31. Find the maximum result of ai XOR aj, where 0 ≤ i, j < n. Could you do this in O(n) runtime?
Example:
Input: [3, 10, 5, 25, 2, 8]
Output: 28
Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class P421_MaximumXORofTwoNumbersInAnArray {
	
	/**
	 * Refer: https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/2
	 */
	public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);	// reserve Left bits and ignore Right bits
            }
            
            /* Use 0 to keep the bit, 1 to find XOR
             * 0 ^ 0 = 0 
             * 0 ^ 1 = 1
             * 1 ^ 0 = 1
             * 1 ^ 1 = 0
             */
            int tmp = max | (1 << i);	// in each iteration, there are pair(s) whose Left bits can XOR to max
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
	/*
	This algorithm's idea is:
	to iteratively determine what would be each bit of the final result from left to right. And it narrows down the candidate group iteration by iteration. e.g. 
	assume input are a,b,c,d,...z, 26 integers in total. In first iteration, if you found that a, d, e, h, u differs on the MSB(most significant bit), 
	so you are sure your final result's MSB is set. Now in second iteration, you try to see if among a, d, e, h, u there are at least two numbers make the 2nd MSB differs, 
	if yes, then definitely, the 2nd MSB will be set in the final result. And maybe at this point the candidate group shinks from a,d,e,h,u to a, e, h. 
	Implicitly, every iteration, you are narrowing down the candidate group, but you don't need to track how the group is shrinking, you only cares about the final result.
	
	example: Given [14, 11, 7, 2], which in binary are [1110, 1011, 0111, 0010].
	Since the MSB is 3, I'll start from i = 3 to make it simplify.
	i = 3, set = {1000, 0000}, max = 1000
	i = 2, set = {1100, 1000, 0100, 0000}, max = 1100
	i = 1, set = {1110, 1010, 0110, 0010}, max = 1100
	i = 0, set = {1110, 1011, 0111, 0010}, max = 1100
	*/
	
	
	/**
	 * 花了不少时间写了复杂code，最后还超时。。
	 * @param nums
	 * @return
	 */
	public int findMaximumXOR_overtime(int[] nums) {
		if(nums == null || nums.length == 1)
			return 0;
		
		Set<Integer> uniqueSet = new HashSet<Integer>();
		Map<Integer, Set<Integer>> map = new TreeMap<Integer, Set<Integer>>(Collections.reverseOrder());
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			uniqueSet.add(n);
			for (int j = 0; n > 0; j++) {
				if((n & 1) == 1){
					Set<Integer> bitcount = map.getOrDefault(j, new HashSet<Integer>());
					bitcount.add(nums[i]);
					map.put(j, bitcount);
				}
				n = n >>> 1;
			}
		}
//		System.out.println(map);
		Iterator<Set<Integer>> it = map.values().iterator();
		Set<Integer> as = null;
		while(it.hasNext()){
			as = it.next();
			if(as.size() != uniqueSet.size())
				break;
		}
		Map<Integer, Set<Integer>> abmap = new TreeMap<Integer, Set<Integer>>(Collections.reverseOrder());
		
		//init abmap. Initial value is all nums except the ones in a(MSB) set.
		for (int a: as) {
			Set<Integer> bs = new HashSet<Integer>();
			abmap.put(a, bs);
			for (int i = 0; i < nums.length; i++) {
				if(!as.contains(nums[i]))
					bs.add(nums[i]);
			}
		}
//		System.out.println(abmap);
		int res = 0;
		while(it.hasNext()) {
			Set<Integer> vals = it.next();
			for(int a: abmap.keySet()){
				Set<Integer> bs = abmap.get(a);
				if(bs.size() == 1){	//a possible result
					int xor = a ^ bs.iterator().next();
					if(xor > res)
						res = xor;
				}
				if(!bs.isEmpty()){
					if(vals.contains(a)){	//then all values in val can not be in b. 
						if(vals.containsAll(bs)){
							//if all elements in b set will be removed, calculate res first. Otherwise return 0.
							for (int temp: vals) {
								int xor = a ^ temp;
								if(xor > res)
									res = xor;
							}
						}
						bs.removeAll(vals);
					}
					else{
						bs.retainAll(vals);
					}
				}
			}
		}
		for(int a: abmap.keySet()){
			Set<Integer> bs = abmap.get(a);
			if(bs.size() == 1){	//a possible result
				int xor = a ^ bs.iterator().next();
				if(xor > res)
					res = xor;
			}
		}
		return res;    
	}
	
	public static void main(String[] args) {
		P421_MaximumXORofTwoNumbersInAnArray p = new P421_MaximumXORofTwoNumbersInAnArray();
//		System.out.println(p.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8, 4, 26}));	//26 ^ 5 = 31
		System.out.println(p.findMaximumXOR(new int[]{3, 10}));		//3 ^ 10 = 9
//		System.out.println(p.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));	//5 ^ 25 = 28.
//		System.out.println(p.findMaximumXOR(new int[]{4, 6, 7}));	//4 ^ 7 = 3
//		System.out.println(p.findMaximumXOR(new int[]{4, 4, 4}));	//0
//		System.out.println(p.findMaximumXOR(new int[]{8,10,2}));	//8 ^ 2 = 10
//		System.out.println(p.findMaximumXOR(new int[]{14,15,9,3,2}));	//15 ^ 2 = 13
	}

}
