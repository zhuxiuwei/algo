package LeetCode.round1.easy;

import LeetCode.round1.common.TreeNode;

/**
 * 160926
Find the sum of all left leaves in a given binary tree.

Example:
    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/
public class P404_SumOfLeftLeaves {
	private int sum = 0;
	
	/**
	 * AC: 9ms
	 */
	public int sumOfLeftLeaves(TreeNode root) {
		if(root != null){
			preOrder(root.left, true);
			preOrder(root.right, false);
		}
        return this.sum;
    }
	
	/**
	 * @param root
	 * @param mode	true: parent call left recursive; false: parent call right recursive
	 */
	private void preOrder(TreeNode root, boolean mode){
		if(root == null)
			return;
		
		if(mode && root.left == null && root.right == null)
			sum += root.val;
		
		preOrder(root.left, true);
		preOrder(root.right, false);
	}
	
}

