package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import LeetCode.round1.common.TreeNode;
/**
 * 161213
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class P173_BinarySearchTreeIterator {

	private List<Integer> nodes = new ArrayList<Integer>();
	private Iterator<Integer> iter = nodes.iterator();
	private void inOrder(TreeNode node){
		if(node == null)
			return;
		inOrder(node.left);
		nodes.add(node.val);
		inOrder(node.right);
	}
	
	/**
	 * AC: 5ms, 86.42%.
	 * 思路:中序遍历B search tree，就是递增的序列。把这个序列放到一个list里。然后iterator套用list的iterator就好。
	 * 不过此算法不满足uses O(h) memory的条件。用了O(n), n=node count的memory。 时间是O(1)。
	 * @param root
	 */
	public P173_BinarySearchTreeIterator(TreeNode root) {
		if(root != null){
			inOrder(root);
			iter = nodes.iterator();
		}
    }

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return iter.hasNext();
	}

	/** @return the next smallest number */
	public int next() {
		return iter.next();
	}

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		n2.left = n1;
		
		P173_BinarySearchTreeIterator p = new P173_BinarySearchTreeIterator(n2);	//1 2
		while (p.hasNext()) System.out.print(p.next() + " ");
	}

}
/**
* Your BSTIterator will be called like this:
* BSTIterator i = new BSTIterator(root);
* while (i.hasNext()) v[f()] = i.next();
*/