package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 161128
Given a collection of distinct numbers, return all possible permutations.
For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
public class P046_Permutations {

	/**
	 * AC: 10ms, 12%。
	 * Backtracking,简化版的N皇后问题。  还是debug写的，算不上很困难也算不上很顺利。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(nums, res, new ArrayList<Integer>(), new HashSet<Integer>());
		return res;
    }
	private void helper(int[] nums, List<List<Integer>> res, List<Integer> tempList, Set<Integer> tempSet){
		for (int i = 0; i < nums.length; i++) {
			if(!tempSet.contains(i)){	//judge is location is legal
				tempList.add(nums[i]);
				tempSet.add(i);
				if(tempList.size() == nums.length){
					res.add(new ArrayList<Integer>(tempList));
					tempList.remove(tempList.size() - 1);
					tempSet.remove(i);
				}else{
					helper(nums, res, tempList, tempSet);
					tempList.remove(tempList.size() - 1);
					tempSet.remove(i);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		P046_Permutations p = new P046_Permutations();
		System.out.println(p.permute(new int[]{1,2,3}));
	}

}
