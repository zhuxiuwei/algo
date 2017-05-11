package LeetCode.round1.easy;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170510,11
Given a binary tree, return the tilt of the whole tree.
The tilt of a tree node is defined as the absolute difference between (the sum of all left subtree node values) and (the sum of all right subtree node values). Null node has tilt 0.
The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
Note:
The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.
 */
public class P563_BinaryTreeTilt {

	private int sum = 0;
	/**
	 * AC: 9ms, 55.77%，
	 * ！！！！费了些时间才做对，提交错了两次。典型的二叉树递归操作问题，需要考虑清楚，比如tilt的定义，是“全部”左子树之和与“全部”右子树之和的差的绝对值，不只是左子树之和与右子树之和的差的绝对值。！！！！
	 * @param root
	 * @return
	 */
	public int findTilt(TreeNode root) {
		if(null == root) return 0;
		findTilt_helper(root);
        return sum;
    }
	private int findTilt_helper(TreeNode root) {
		int leftTilt = root.left == null ? 0: findTilt_helper(root.left);
		int rightTilt = root.right == null ? 0: findTilt_helper(root.right);
		sum += Math.abs(leftTilt - rightTilt);
		return leftTilt + rightTilt + root.val;
    }
	
	public static void main(String[] args) {
		P563_BinaryTreeTilt p = new P563_BinaryTreeTilt();
		TreeNode n = BuildTreeFromArrayUtil.build(new Integer[]{5,2,-3,7,-4,2,3});	//15
		System.out.println(p.findTilt(n));
	}

}
