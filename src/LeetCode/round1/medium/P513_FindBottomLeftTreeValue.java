package LeetCode.round1.medium;

import java.util.LinkedList;
import java.util.Queue;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170410
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:
    2
   / \
  1   3
Output:
1

Example 2: 
Input:
        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7
Output:
7

Note: You may assume the tree (i.e., the given root node) is not NULL.
 */
public class P513_FindBottomLeftTreeValue {

	static class NodeAndLevel{
		TreeNode node = null;
		int level = 0;
		public NodeAndLevel(TreeNode node, int level){
			this.node = node;
			this.level = level;
		}
	}
	
	/**
	 * AC: 16ms, 6.85%
	 * 费了些时间。 主要是在层次遍历时，如何分辨新的一层开始了。
	 */
	public int findBottomLeftValue(TreeNode root) {
		Queue<NodeAndLevel> q = new LinkedList<NodeAndLevel>();
		int lastRow = 0;
		NodeAndLevel temp = new NodeAndLevel(root, 1);
		q.offer(temp);
		
		while(!q.isEmpty()){
			NodeAndLevel head = q.poll();
			TreeNode node = head.node; 
			int headRow = head.level;
			if(headRow > lastRow){	//a new row
				temp = head;
				lastRow = headRow;
			}
			if(node.left != null)
				q.offer(new NodeAndLevel(node.left, headRow + 1));
			if(node.right != null)
				q.offer(new NodeAndLevel(node.right, headRow + 1));
		}
		return temp.node.val;
    }
	
	public static void main(String[] args) {
		P513_FindBottomLeftTreeValue p = new P513_FindBottomLeftTreeValue();
		TreeNode t1 = BuildTreeFromArrayUtil.build(new Integer[]{2,1,3});
		System.out.println(p.findBottomLeftValue(t1));	//1
		TreeNode t2 = BuildTreeFromArrayUtil.build(new Integer[]{1,2,3,4,null,5,6,null,null,7});
		System.out.println(p.findBottomLeftValue(t2));	//7
		TreeNode t3 = BuildTreeFromArrayUtil.build(new Integer[]{98,97,null,88,null,84,null,79,87,null,32,null,null,1});
		System.out.println(p.findBottomLeftValue(t3));	//1。第一次实现时这里有bug，错误返回32.
	}

}
