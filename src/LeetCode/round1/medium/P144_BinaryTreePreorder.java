package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import LeetCode.round1.common.TreeNode;

/**
 * 160928
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].
Note: Recursive solution is trivial, could you do it iteratively?
 */
public class P144_BinaryTreePreorder {

	/**
	 * AC: 1ms, 29%
	 * 很快写完一次AC，比写中序顺利得多。本来也比中序简单。
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode n = root;
        while(n != null){
        	res.add(n.val);
        	if(n.right != null)
        		stack.push(n.right);
        	if(n.left != null)
        		n = n.left;
        	else
        	{
        		if(!stack.isEmpty())
        			n = stack.pop();
        		else
        			break;
        	}
        }
        return res;
    }
}
