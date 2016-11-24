package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 161124
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.


For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
 */
public class P039_CombinationSum {

	/**
	 * ！！！！！！！！！！！极其不顺利，一个题目弄到凌晨一点多。主要是总超时，然后还不会剪枝，一剪枝结果根本就不对了，跟踪递归堆栈还跟不明白。。
	 * 最后只好用了一个很傻逼的memorized cache来尽量减少无效递归，虽然AC了，也只打败了1.9%更sb的。
	 * AC: 90ms, 1.9%
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Set<List<Integer>> resSet = new HashSet<List<Integer>>();	//for dedup
		if(candidates == null || candidates.length == 0)
			return res;
		
		Arrays.sort(candidates);
		List<Integer> can2 = new ArrayList<Integer>();
		for (int i = 0; i < candidates.length; i++) {
			for (int j = 0; j < target/candidates[i]; j++) 
				can2.add(candidates[i]);
		}
		
		List<Integer> temp = new ArrayList<Integer>();
		Set<TempTargetTrace> memorized = new HashSet<TempTargetTrace>();
		helper(can2, 0, temp, target, resSet, memorized);
			
		for (List<Integer> list: resSet)
			res.add(list);
		return res;
    }
	private void helper(List<Integer> candidates, int idx, List<Integer> temp, int target, Set<List<Integer>> result, Set<TempTargetTrace> memorized){
		
		/**避免过多重复递归*/
		TempTargetTrace t = new TempTargetTrace(temp, target, idx);
		if(!memorized.contains(t))
			memorized.add(t);
		else
			return;
		/**避免过多重复递归*/

		if(target == 0){	//hit target
			List<Integer> temp2 = new ArrayList<Integer>();
			temp2.addAll(temp);
			result.add(temp2);
		}else{
			if(idx < candidates.size() && candidates.get(idx) <= target){	
				temp.add(candidates.get(idx));
				int newTarget = target - candidates.get(idx);
				idx ++;
				helper(candidates, idx, temp, newTarget, result, memorized);	//go deeper
				if(temp.size() > 0){
					newTarget += temp.get(temp.size() - 1);
					temp.remove(temp.size() - 1);
					helper(candidates, idx, temp, newTarget, result, memorized);
				}
			}
		}
	}
	//memorized usage, avoid too much duplicated recursive call
	class TempTargetTrace{
		private List<Integer> temp;
		private int target;
		private int idx;
		TempTargetTrace(List<Integer> temp, int target, int idx){
			this.temp = temp;
			this.target = target;
			this.idx = idx;
		}
		@Override
		public boolean equals(Object o){
			if(o == null)
				return false;
			if(!(o instanceof TempTargetTrace))
				return false;
			TempTargetTrace other = (TempTargetTrace)o;
			if(other.target != this.target)
				return false;
			if(other.idx != this.idx)
				return false;
			if(other.temp == null && this.temp == null)
				return false;
			if((other.temp == null && this.temp != null) || (other.temp != null && this.temp == null))
				return false;
			if(other.temp.size() != this.temp.size())
				return false;
			for (int i = 0; i < temp.size(); i++) {
				if(temp.get(i) != other.temp.get(i))
					return false;
			}
			return true;
		}
		@Override
		public int hashCode(){
			if(temp == null )
				return target;
			else 
				return target + temp.hashCode() + idx;
		}
	}
	
	public static void main(String[] args) {
		P039_CombinationSum p = new P039_CombinationSum();
		System.out.println(p.combinationSum(new int[] {2,3,6,7,8}, 7));
		System.out.println(p.combinationSum(new int[] {7, 3, 2, 6}, 12));
		System.out.println(p.combinationSum(new int[] {6,4,3,10,12}, 28));
		System.out.println(p.combinationSum(new int[] {8,7,4,3}, 11));
	}
}
