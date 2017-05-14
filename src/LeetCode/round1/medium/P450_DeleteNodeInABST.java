package LeetCode.round1.medium;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170514
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
 */
public class P450_DeleteNodeInABST {

	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode target = findNode(root, key);
		if(null != target){
			
		}
        return root;
    }
	
	/**
	 * 二叉树搜索。
	 */
	private TreeNode findNode(TreeNode root, int key){
		if(root == null)
			return null;
		while(true){	//!!!!注意是在循环里！
			if(root.val == key)
				return root;
			else{
				if(root.val > key && root.left != null)
					root = root.left;
				else if(root.val < key && root.right != null)
					root = root.right;
				else
					return null;
			}
		}
	}
	
	public static void main(String[] args) {
		P450_DeleteNodeInABST p = new P450_DeleteNodeInABST();
		TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{5,3,6,2,4,null,7});
		System.out.println(p.findNode(root, 2));
	}

}
