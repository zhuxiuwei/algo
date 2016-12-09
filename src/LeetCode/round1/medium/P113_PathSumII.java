package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;

import LeetCode.round1.common.TreeNode;

/**
 * 161209
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
For example: Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
 */
public class P113_PathSumII {

	/**
	 * 1 time AC: 3ms, 43.3%
	 * DFS(backtracking)
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(root, sum, new ArrayList<Integer>(), res);
		return res;
    }
	private void helper(TreeNode node, int sum, List<Integer> temp, List<List<Integer>> res){
		if(node == null)
			return;
		
		temp.add(node.val);
		
		if(node.left == null && node.right == null && node.val == sum){
			res.add(new ArrayList<Integer>(temp));
			return;
		}
		
		if(node.left != null){
			helper(node.left, sum - node.val, temp, res);
			if(!temp.isEmpty()) temp.remove(temp.size() - 1);
		}
		
		if(node.right != null){
			helper(node.right, sum - node.val, temp, res);
			if(!temp.isEmpty()) temp.remove(temp.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		P113_PathSumII p = new P113_PathSumII();
		
		TreeNode t1 = new TreeNode(2);
		TreeNode t2 = new TreeNode(1);
		t1.right = t2;
		System.out.println(p.pathSum(t1, 3));	//[[2, 1]]
	}
}
