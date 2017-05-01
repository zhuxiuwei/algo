package LeetCode.round1.medium;

import java.util.Stack;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170501
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
Example:
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */
public class P538_ConvertBSTtoGreaterTree {

	/**
	 * AC: 22ms, 30%.
	 * 思路：利用BST的中序遍历是递增的结果。把in-oder结果存到stack，然后依次出栈，从大到小重新赋值。
	 */
	public TreeNode convertBST(TreeNode root) {
		if(root == null)
			return null;
		
		Stack<TreeNode> stack= new Stack<TreeNode>();
		addNodeToStackInOrder(root, stack);
		int sum = 0;
		while(!stack.isEmpty()){
			TreeNode t = stack.pop();
			t.val += sum;
			sum = t.val;
		}
        return root;
    }
	//add BST nodes to a stack, using in-order travel. BST in-order travel, will return nodes in incremental order.
	private void addNodeToStackInOrder(TreeNode node, Stack<TreeNode> stack){
		if(node.left != null)
			addNodeToStackInOrder(node.left, stack);
		stack.add(node);
		if(node.right != null)
			addNodeToStackInOrder(node.right, stack);
	}
	
	public static void main(String[] args) {
		P538_ConvertBSTtoGreaterTree p = new P538_ConvertBSTtoGreaterTree();
		TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{5,2,13});
		p.convertBST(root);
		System.out.println(root.val);
		System.out.println(root.left.val);
		System.out.println(root.right.val);
	}

}
