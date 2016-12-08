package LeetCode.round1.easy;

import LeetCode.round1.common.TreeNode;

/**
 * 161208
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class P112_PathSum {

	/**
	 * AC: 1ms
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null)
			return false;
		
		if(root.val == sum && root.left == null && root.right == null)	//！！！！ 注意Bug。要根节点的时候判断这个条件才对。
			return true;
		
		if(root.left != null){
			boolean leftMatchWithSelf = hasPathSum(root.left, sum - root.val);
			if(leftMatchWithSelf)
				return true;
		}
		
		if(root.right != null){
			boolean rightMatchWithSelf = hasPathSum(root.right, sum - root.val);
			if(rightMatchWithSelf)
				return true;
		}
		
		return false;
    }
	
	public static void main(String[] args) {
		P112_PathSum p = new P112_PathSum();
		
		TreeNode t1 = new TreeNode(2);
		TreeNode t2 = new TreeNode(1);
		t1.right = t2;
		System.out.println(p.hasPathSum(t1, 1));
	}
}
