package LeetCode.round1.easy;

import LeetCode.round1.common.TreeNode;

/**
 * 160911
 Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */

public class P226_InvertBinaryTree {
	
	public TreeNode invertTree(TreeNode root) {
		
        if(root == null || 	//注意bug
        		(root.left == null && root.right == null))
        	return root;
        
        //交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        //对左右子树递归调用
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }

}

/**
 * 第一次提交失败，因为忘了加root == null 的判断条件，导致空指针异常。
 */
