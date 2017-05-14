package LeetCode.round1.medium;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170514
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
 */
public class P450_DeleteNodeInABST {

	/**
	 * AC: 8ms. 42。55%
	 * !!!!!!!!!!!还是挺费劲儿的。错了三次。写起来太麻烦、
	 * !!!!!!!!第三种情况还是最复杂。参考的算法导论。
	 */
	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode node = findNode(root, key);
		if(null != node){
			TreeNode p = findParent(root, node);
			if(node.left == null && node.right == null){	//case 1: 叶子节点
				if(p == null)	//删除的是根节点，而且树中只有根节点一个节点
					root = null;
				else{
					if(p.left == node) p.left = null;
					else p.right = null;
				}
			}else if(node.left != null && node.right == null){	//case 2.1： 只有左子树
				if(p == null){	//删除的是根节点
					root = node.left;
				}else{
					if(p.left == node) p.left = node.left;
					else p.right = node.left;
				}
			}else if(node.right != null && node.left == null){	//case 2.1： 只有右子树
				if(p == null){	//删除的是根节点
					root = node.right;
				}else{
					if(p.left == node) p.left = node.right;
					else p.right = node.right;
				}
			}else{	//有左右子树
				TreeNode successor = successorForNodeHasRight(node);
				
				//!!!!!!!!第三种情况还是最复杂。参考的算法导论。
				if(node.right == successor){	//case 3.1, successor is right child
					if(p != null){
						if(p.left == node) p.left = successor;
						else p.right = successor;
					}
					else{
						root = successor;
					}
					successor.left = node.left;
				}else{	//case 3.2, successor is not right child, but in right side tree.
					node.val = successor.val;	//replace target with successor.
					//then delete successor
					deleteNode(node.right, node.val);
				}
			}
		}
        return root;
    }
	
	/**
	 * 二叉树搜索。
	 */
	private TreeNode findNode(TreeNode root, int key){
		if(root == null)
			return null;
		while(true){	//!!!!注意是在循环里！
			if(root.val == key)
				return root;
			else{
				if(root.val > key && root.left != null)
					root = root.left;
				else if(root.val < key && root.right != null)
					root = root.right;
				else
					return null;
			}
		}
	}
	
	/**
	 * 寻找node在root为根的树中的父节点
	 * @param node
	 * @return
	 */
	private TreeNode findParent(TreeNode root, TreeNode node){
		if(root == null || node == null ||
				root == node)	//node就是根节点，则没有父节点
			return null;
		TreeNode p = root, cur = root.val > node.val ? root.left: root.right;
		while(cur != null){
			if(cur == node){
				return p;
			}
			else{
				p = cur;
				if(cur.val > node.val){
					cur = cur.left;
				}else{
					cur = cur.right;
				}
			}
				
		}
		return null;
	}
	
	/**
	 * 查找存在有子树的节点的后继（一定在有字数中）
	 * 注意如果节点没有右子树，则不能用次方法，应该从下往上找。
	 * @param node node的条件是有右孩子。
	 * @return node的左子树
	 */
	public TreeNode successorForNodeHasRight(TreeNode node){
		if(node == null)
			return null;
		if(node.right == null)
			return null;
		else{
			TreeNode cur = node.right;
			while(cur.left != null){
				cur = cur.left;
			}
			return cur;
		}
	}
	
	public static void main(String[] args) {
		P450_DeleteNodeInABST p = new P450_DeleteNodeInABST();
		TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{5,3,6,2,4,null,7});
		TreeNode node = p.findNode(root, 3);
		System.out.println(node);
		TreeNode nodeParent = p.findParent(root, node);
		System.out.println(nodeParent);
		TreeNode successor = p.successorForNodeHasRight(node);
		System.out.println(successor);
		
		node = p.deleteNode(root, 3);
		System.out.println(node);
		
		root = BuildTreeFromArrayUtil.build(new Integer[]{2,1,3});
		node = p.deleteNode(root, 1);
		System.out.println(node);
		
		root = BuildTreeFromArrayUtil.build(new Integer[]{3,1,4,null,2});
		node = p.deleteNode(root, 1);
		System.out.println(node);
		
		root = BuildTreeFromArrayUtil.build(new Integer[]{7,6,9,null,null,8,null});	//case 3.2
		node = p.deleteNode(root, 7);
		System.out.println(node);
	}

}
