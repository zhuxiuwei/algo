package LeetCode.round1;

/**
 * Easy  160910
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */

public class P104_MaximumDepthofBinaryTree {

	public int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		
		if(root.left == null && root.right == null)
			return 1;
		else{
			int leftDepth = root.left == null ? 0: maxDepth(root.left);
			int rightDepth = root.right == null ? 0: maxDepth(root.right);
			return Math.max(leftDepth, rightDepth) + 1;
		}
    }
	
}
/**
  * 一次通过。
  */