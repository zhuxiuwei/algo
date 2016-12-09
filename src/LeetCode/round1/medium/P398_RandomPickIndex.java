package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 161209
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
Subscribe to see which companies asked this question
 */
public class P398_RandomPickIndex {
	
	private int[] nums = null;
	public P398_RandomPickIndex(int[] nums) {
		this.nums = nums;
	}
	
	/**
	 * 使用很少的extra space。两次遍历。
	 * AC: 394ms, 28.8%.
	 * @param target
	 * @return
	 */
	Map<Integer, Integer> countCache = new HashMap<Integer, Integer>();	//cache，用来降低第一次遍历的时间成本。
	public int pick(int target) {
		//第一轮遍历，获取target出现的次数。如果cache里有直接从cache取。
		int count = countCache.getOrDefault(target, 0);
		if(count == 0){
			for (int i = 0; i < nums.length; i++) {
				if(nums[i] == target)
					count ++;
			}
			countCache.put(target, count);
		}
		
		//第二轮遍历，获取一个随机的第i个target，然后得到那第i个target在数组中对应的index位置。
		int ranIdx = new Random().nextInt(count) + 1;
		int temp = 0;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] == target){
				temp ++;
				if(temp == ranIdx){
					temp = i;
					break;
				}
			}
		}
		return temp;
	}
	
	/**
	 * 1 time AC: 428ms, 14%.
	 * 最直观的解法。以为不会过。居然过了。
	 * @param target
	 * @return
	 */
	public int pick_starightforward(int target) {
		List<Integer> match = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] == target)
				match.add(i);
		}
		return match.get(new Random().nextInt(match.size()));
	}
	
	public static void main(String[] args) {
		P398_RandomPickIndex p = new P398_RandomPickIndex(new int[]{1,2,3,3,3});
		for (int i = 0; i < 10; i++) {
			System.out.println(p.pick(3));
		}
			
	}

}
