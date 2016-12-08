package LeetCode.round1.medium;

import java.util.List;

import LeetCode.round1.common.TreeNode;

/**
 * 161208
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
	 * AC: 1ms
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        
    }
	
	public static void main(String[] args) {
		P113_PathSumII p = new P113_PathSumII();
		
		TreeNode t1 = new TreeNode(2);
		TreeNode t2 = new TreeNode(1);
		t1.right = t2;
		System.out.println(p.pathSum(t1, 3));
	}
}
