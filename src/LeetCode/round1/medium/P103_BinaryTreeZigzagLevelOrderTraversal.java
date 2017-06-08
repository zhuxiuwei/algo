package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;


/**
 * 170608 Medium
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */
public class P103_BinaryTreeZigzagLevelOrderTraversal {
	
	/**
	 * 1 time AC. 41.76%.
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null)
			return res;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		boolean reverse = false;
		q.offer(root);
		q.offer(null);
		List<Integer> temp = new LinkedList<Integer>();
		while(!q.isEmpty()){
			TreeNode node = q.poll();
			if(node != null){
				if(!reverse)
					temp.add(node.val);
				else
					temp.add(0, node.val);
				if(node.left != null)
					q.offer(node.left);
				if(node.right != null)
					q.offer(node.right);
			}else{
				res.add(new ArrayList<Integer>(temp));
				temp = new LinkedList<Integer>();
				reverse = !reverse;
				if(!q.isEmpty())
					q.offer(null);
			}
		}
		return res;
    }
	
	public static void main(String[] args) {
		TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{3,9,20,null,null,15,7,null,null,null,null});
		P103_BinaryTreeZigzagLevelOrderTraversal p = new P103_BinaryTreeZigzagLevelOrderTraversal();
		System.out.println(p.zigzagLevelOrder(root));	//[[3], [20, 9], [15, 7]]
	}

}
