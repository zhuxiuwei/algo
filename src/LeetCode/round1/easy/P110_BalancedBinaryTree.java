package LeetCode.round1.easy;

import LeetCode.round1.common.TreeNode;

/**
 * 170516
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class P110_BalancedBinaryTree {

	public boolean isBalanced(TreeNode root) {
		if(root == null)
			return true;
		int i = isBalanced_helper(root, 0);
		return i <= 1;
    }
	
	private int isBalanced_helper(TreeNode node, int depth){
		if(node.left == null && node.right == null)
			return depth;
		if(node.left != null){
			int leftV = isBalanced_helper(node.left, depth + 1);
		}
		if(node.right != null){
			int rightV = isBalanced_helper(node.right, depth + 1);
		}
		return 1;
	}
	
	public static void main(String[] args) {

	}

}
