package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 160509
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class P508_MostFrequentSubtreeSum {

	private Map<Integer, Integer> valCount = new HashMap<Integer, Integer>();
	private int maxCount = Integer.MIN_VALUE;
	private List<Integer> res = new ArrayList<Integer>();
	
	/**
	 * AC: 23ms, 38.16%
	 * @param root
	 * @return
	 */
	public int[] findFrequentTreeSum(TreeNode root) {
		if(root == null) return new int[]{};
		calculateSum(root);
		int[] resArr = new int[res.size()];
		for (int i = 0; i < res.size(); i++) 
			resArr[i] = res.get(i);
		return resArr;
    }
	
	private int calculateSum(TreeNode root){
		int val = 0;
		if(root.left == null && root.right == null){
			val = root.val;
		}else{
			int leftV = root.left == null ? 0: calculateSum(root.left);
			int rightV = root.right == null ? 0: calculateSum(root.right);
			val = leftV + rightV + root.val;
		}
		int newCount = valCount.getOrDefault(val, 0) + 1;
		valCount.put(val, newCount);
		if(newCount > maxCount){
			maxCount = newCount;
			res.clear();
			res.add(val);
		}else if(newCount == maxCount){
			res.add(val);
		}
		return val;
	}
	
	public static void main(String[] args) {
		P508_MostFrequentSubtreeSum p = new P508_MostFrequentSubtreeSum();
		System.out.println(Arrays.toString(p.findFrequentTreeSum(BuildTreeFromArrayUtil.build(new Integer[]{}))));
	}

}
