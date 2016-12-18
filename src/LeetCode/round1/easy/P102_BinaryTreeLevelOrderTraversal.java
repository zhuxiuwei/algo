package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 161218
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
public class P102_BinaryTreeLevelOrderTraversal {

	public List<List<Integer>> levelOrder(TreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		q.offer(null);
		List<Integer> res2 = new ArrayList<Integer>();
		while(!q.isEmpty()){
			TreeNode t = q.poll();
			if(t != null){	
				res2.add(t.val);
				if(t.left != null) q.offer(t.left);
				if(t.right != null) q.offer(t.right);
				if(!q.isEmpty() && q.peek() == null){
					q.poll();
					q.offer(null);
					res.add(new ArrayList<Integer>(res2));
					res2.clear();
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		P102_BinaryTreeLevelOrderTraversal p = new P102_BinaryTreeLevelOrderTraversal();
		System.out.println(p.levelOrder(BuildTreeFromArrayUtil.build(new Integer[]{1,null,2,3,4,null,null,5,null})));
	}
	
}
