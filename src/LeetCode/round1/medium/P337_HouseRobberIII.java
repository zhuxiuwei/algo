package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.round1.common.TreeNode;

/**
 * 161206
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree".
It will automatically contact the police if two directly-linked houses were broken into on the same night.
Determine the maximum amount of money the thief can rob tonight without alerting the police.
Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

Example 3:
     2
    / \
   1   3
    \    
     4   
Maximum amount of money the thief can rob = 4 + 3 = 7.
 */
public class P337_HouseRobberIII {

	/**
	 * DFS + DP. Refer: https://discuss.leetcode.com/topic/41572/1ms-java-solution
	 * @param root
	 * @return
	 */
	public int rob(TreeNode root) {
		int[] res = robDP(root);
		return Math.max(res[0], res[1]);	//res[0] stores result without current node, res[1] contains current node.
    }
	private int[] robDP(TreeNode node){
		if(node == null)
			return new int[]{0, 0};
		
		int[] leftRes = robDP(node.left);
		int[] rightRes = robDP(node.right);
		
		int[] res = new int[2];
		res[0] = Math.max(leftRes[0], leftRes[1]) + Math.max(rightRes[0], rightRes[1]);
		res[1] = leftRes[0] + rightRes[0] + node.val;
		return res;
	}
	
	
	/**
	 * 思路：层次遍历（BFS），某一行全选，或者不选。问题就退化到和House robber 1一样了。
	 * 思路错！以下case错误选择2+4=6，正确是3+4=7.
	 *   2
	 *  / \
	 * 1   3
	 *  \
	 *   4
	 */
	public int rob_wrong(TreeNode root) {
        if(root == null)
        	return 0;
        List<Integer> levelSum = new ArrayList<Integer>();
        levelSum.add(root.val);
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if(root.left != null) q.offer(root.left);
        if(root.right != null) q.offer(root.right);
        q.offer(null);	//null means a level ends
        int curLevelSum = 0;
        while(!q.isEmpty()){
        	TreeNode node = q.poll();
        	if(node != null){
        		curLevelSum += node.val;
        		if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                if(q.peek() == null)	//one level will end
                	q.offer(null);
        	}else{	//one level ends.
        		int last1LevelSum = levelSum.get(levelSum.size() - 1);
        		int last2LevelSum = levelSum.size() > 1 ? levelSum.get(levelSum.size() - 2) + curLevelSum: 0 + curLevelSum;
        		levelSum.add(Math.max(last1LevelSum, last2LevelSum));
        		curLevelSum = 0;
        	}
        }
        return levelSum.get(levelSum.size() - 1);
    }
	
	public static void main(String[] args) {
		P337_HouseRobberIII p = new P337_HouseRobberIII();
		
		/**
		 *   3
		    / \
		   4   5
		  / \   \ 
		 1   3   1 
		 */
		TreeNode l1 = new TreeNode(3);
		TreeNode l21 = new TreeNode(4);
		TreeNode l22 = new TreeNode(5);
		TreeNode l31 = new TreeNode(1);
		TreeNode l32 = new TreeNode(3);
		TreeNode l33 = new TreeNode(1);
		l1.left = l21;
		l1.right = l22;
		l21.left = l31;
		l21.right = l32;
		l22.right = l33;
		System.out.println(p.rob(l1));	//9
		
		/**
		 *   3
		    / \
		   2   3
		    \   \ 
		     3   1 
		 */
		l1 = new TreeNode(3);
		l21 = new TreeNode(2);
		l22 = new TreeNode(3);
		l31 = new TreeNode(3);
		l32 = new TreeNode(1);
		l1.left = l21;
		l1.right = l22;
		l21.right = l31;
		l22.right = l32;
		System.out.println(p.rob(l1));	//7
		
		/**
		 *   2
		 *  / \
		 * 1   3
		 *  \
		 *   4
		 */
		l1 = new TreeNode(2);
		l21 = new TreeNode(1);
		l22 = new TreeNode(3);
		l31 = new TreeNode(4);
		l1.left = l21;
		l1.right = l22;
		l21.right = l31;
		System.out.println(p.rob(l1));	//7, 3 + 4
	}
}
