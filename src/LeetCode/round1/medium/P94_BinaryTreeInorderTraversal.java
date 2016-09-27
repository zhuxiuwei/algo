package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import LeetCode.round1.common.TreeNode;

/**
 * 160927  提交了8次才AC。根本无法bug free!
Given a binary tree, return the inorder traversal of its nodes' values.
Note: Recursive solution is trivial, could you do it iteratively?
 */
public class P94_BinaryTreeInorderTraversal {
	
	/**
	 * AC: 1ms, 41%.
	 * ！！！！！！！提交了8次才AC。根本无法bug free!
	 * 	bug1: 注意从stack弹出元素，不能再访问其左子树，防止走回头路，死循环。
	 *  bug2: 什么时候该res.add(val)，什么时候该canGoLeft = true/false，弄错了好几次。
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode cur = root;
		boolean canGoLeft = true;	//！！！bug1: 注意从stack弹出元素，不能再访问其左子树，防止走回头路，死循环。
		while(cur != null || !s.isEmpty()){
			if(cur.left != null && canGoLeft){
				s.push(cur);
				cur = cur.left;
			}else{
				if(canGoLeft)
					res.add(cur.val);
				if(cur.right != null){
					cur = cur.right;
					canGoLeft = true;
				}
				else{
					canGoLeft = false;
					//!!!!! bug2: 什么时候该res.add(val)，什么时候该canGoLeft = true/false，弄错了好几次。
					if(!s.isEmpty()){
						cur = s.pop();
						res.add(cur.val);
						if(cur.right != null){
							cur = cur.right;
							canGoLeft = true;
						}
						else
							if(!s.isEmpty()) {
								cur = s.pop();
								res.add(cur.val);
							}
					}
					else
						break;
				}
			}
		}
		return res;
    }
	
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
//		node2.left = node3;
//		node3.left = node1;
		
//		node1.right = node2;
//		node2.left = node3;
		
		node3.left = node1;
		node3.right = node2;
		
		
		P94_BinaryTreeInorderTraversal p = new P94_BinaryTreeInorderTraversal();
		System.out.println(p.inorderTraversal(node3));
	}
}
