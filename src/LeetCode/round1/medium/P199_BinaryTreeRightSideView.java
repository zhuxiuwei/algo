package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.round1.common.TreeNode;

/**
 * 161214
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
 */
public class P199_BinaryTreeRightSideView {
	
	/**
	 * AC: 3ms, 10.86%. 
	 * Use BFS. !!!!! Note 1 bug.
	 */
	public List<Integer> rightSideView_BFS(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		q.offer(null);
		while(!q.isEmpty()){
			TreeNode t = q.poll();
			if(t != null){	//!!!!!! Note bug。 当所有元素遍历完后，最后queue里会剩下一个null。不加这个判断的话，后面会NullPointer异常。
				if(t.left != null) q.offer(t.left);
				if(t.right != null) q.offer(t.right);
				if(!q.isEmpty() && q.peek() == null){
					q.poll();
					q.offer(null);
					res.add(t.val);
				}
			}
		}
		return res;
	}
	
	/**
	 * Use DFS
	 * 思路错。想的太简单了。 以下case会错误返回[1,3]，期望[1,3,4]
	 * 		   1
	 * 		  / \
	 * 		 2	 3
	 * 		/
	 * 	   4
	 * DFS思路要用递归。Discuss里好多用递归的。如https://discuss.leetcode.com/topic/11768/my-simple-accepted-solution-java
	 */
	public List<Integer> rightSideView_DFS_Wrong(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		while(root != null){
			res.add(root.val);
			if(root.right != null)
				root = root.right;
			else if(root.left != null)
				root = root.left;
			else	//leaf reached
				root = null;
		}
		return res;
	}
	
	public static void main(String[] args) {
		P199_BinaryTreeRightSideView p = new P199_BinaryTreeRightSideView();
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		t1.left = t2;
		System.out.println(p.rightSideView_BFS(t1));	//[1,2]
	}

}
