package LeetCode.round1.medium;

import LeetCode.round1.common.TreeNode;

/**
 * 161013
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
/**
 * Note: 1.  递归写的并不顺利。开始不想定义全局变量current res，但是各种出错。只好最后弄这种简单的写法了。这样可以让递归方法void，而不是返回int，或者Node。
 * Follow up的答案： 可以给TreeNode增加一个filed，记录其左孩子的数目，也就是小于它的节点数。每次插入删除节点时，都可以更新受牵连的节点的这个field值。
 */
public class P230_KthSmallestElementInABST {
	
	/**
	 * 依据BST中序遍历是递增的原理。 
	 * AC： 1ms, 52.7%.
	 * !!!!! NOTE Bug： 递归写的并不顺利。开始不想定义全局变量current res，但是各种出错。只好最后弄这种简单的写法了。这样可以让递归方法void，而不是返回int，或者Node。
	 */
	public int kthSmallest(TreeNode root, int k) {
        kthSmallest_helper(root, k);
        return res;
    }
	private int current = 0; 
	private int res = 0;
	private void kthSmallest_helper(TreeNode node, int k) {
		if(node.left != null)
			kthSmallest_helper(node.left, k);
		current ++;
		if(current == k){
			res = node.val;
			return;
		}
		if(node.right != null)
			kthSmallest_helper(node.right, k);
	}
	
	public static void main(String[] args) {
		P230_KthSmallestElementInABST p = new P230_KthSmallestElementInABST();
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		
		//t2.left = t1;
		//System.out.println(p.kthSmallest(t2, 2));
		
		t1.right = t2;
		System.out.println(p.kthSmallest(t1, 2));
	}
}
