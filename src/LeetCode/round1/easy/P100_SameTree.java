package LeetCode.round1.easy;

import LeetCode.round1.common.TreeNode;

/**
 * 160917
Given two binary trees, write a function to check if they are equal or not.
Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class P100_SameTree {

	private StringBuilder sb = new StringBuilder();
	
	/**
	 * Leverage this thought:
	 * If two trees are same, their pre-order sequence and mid-order sequence are the same.
	 * AC: 1ms
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		
		if(p == null && q == null)
			return true;
		if((p == null && q != null)
				|| (p != null && q == null))
			return false;
		
		//Pre-oder result
		preOrder(p);
		String preP = sb.toString();
		sb.setLength(0);
		preOrder(q);
		String preq = sb.toString();
		sb.setLength(0);
		
		if(preP.equals(preq)){
			//Mid-oder result
			midOrder(p);
			String midP = sb.toString();
			sb.setLength(0);
			midOrder(q);
			String midq = sb.toString();
			
			return midP.equals(midq);
		}else
			return false;
    }
	
	//Pre-order travel tree.
	private void preOrder(TreeNode n){
		if(n == null){
			/*
			 *  !!!!! Note bug here: must add line 64 'sb.append("null,")', otherwise will fail for:
			 *  
			 * t1:
			 *   1
			 *  /
			 * 1
			 *  
			 * t2:
			 *   1
			 *    \
			 *     1
			 *  As pre=oder, mid-order will all print(1,1).
			 *  Same bug for midOrder.
			 */
			sb.append("null,");		
			return;
		}
		sb.append(n.val).append(",");
		preOrder(n.left);
		preOrder(n.right);
	}
	
	//Mid-order travel tree.
	private void midOrder(TreeNode n){
		if(n == null){
			sb.append("null,");
			return;
		}
		preOrder(n.left);
		sb.append(n.val).append(",");
		preOrder(n.right);
	}
	
	public static void main(String[] args) {
		P100_SameTree p = new P100_SameTree();

		TreeNode t = new TreeNode(1);
		t.left = new TreeNode(1);
		
		TreeNode t2 = new TreeNode(1);
		t2.left = new TreeNode(1);
		
		System.out.println(p.isSameTree(t, t2));
	}
}

/**
 * Note bug: One bug. See comments in line 51.
 */
