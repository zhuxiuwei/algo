package LeetCode.round1.easy;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.round1.common.TreeNode;

/**
 * 170516
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class P111_MinimumDepthOfBinaryTree {

	/**
	 * 1 time AC: 1ms
	 * BFS问题。
	 */
	public int minDepth(TreeNode root) {
		if(root == null)
			return 0;
		int depth = 0;
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		q.offer(null);
		while(!q.isEmpty()){
			TreeNode n = q.poll();
			if(n == null){	//一层结束
				depth ++;
				q.offer(null);
			}else{
				if(n.left == null && n.right == null){	//find a leaf. Can exit loop now.
					depth ++;
					break;
				}else{
					if(n.left != null)
						q.offer(n.left);
					if(n.right != null)
						q.offer(n.right);
				}
				
			}
		}
		
        return depth;
    }
	
	public static void main(String[] args) {

	}

}
