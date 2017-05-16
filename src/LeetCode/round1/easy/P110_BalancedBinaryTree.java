package LeetCode.round1.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170516
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class P110_BalancedBinaryTree {

	private boolean res = true;
	
	/**
	 * AC:	递归： 2ms, 26.95%. 
	 * 		非递归：24ms, 2.4%。
	 * ！！！！！！！！！！递归法稍微费劲儿点。非递归反倒挺顺利。！！！！！！！！！！
	 * 1. 首先有个bug。不能在函数总出口处，判断if(Math.abs(leftH - rightH) > 1 )，否则对如下树会误报true。应该在执行中就判断，把结果存在filed中。
	 *          o
	 *         / \
	 *        o   o
	 *       /     \
	 *      o       o  
	 * 2. 注意递归过程中既然无法退出，但是我们可以设法降低递归深度，见L47。
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if(root == null)
			return true;
		isBalanced_helper(root);
		return res;
    }
	
	//递归法
	private int[] isBalanced_helper(TreeNode node){ 
		if((node.left == null && node.right == null))
			return new int[]{0,0};
		int leftH = 0, rightH = 0;
		if(node.left != null && res){	//！！！！如果不加这个条件，递归会多执行几次。AC变成3ms。
			int[] leftV = isBalanced_helper(node.left);
			leftH = Math.max(leftV[0], leftV[1]) + 1;
		}
		if(node.right != null && res){
			int[] rightV = isBalanced_helper(node.right);
			rightH = Math.max(rightV[0], rightV[1]) + 1;
		}
		if(Math.abs(leftH - rightH) > 1 )	//！！！！在这直接判断结果，不要等递归都结束了才判断，会有bug。
			res = false;
		return new int[]{leftH, rightH};
	}
	
	//非递归
	private void isBalanced_helper_noRecursive(TreeNode node){ 
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Set<TreeNode> visited = new HashSet<TreeNode>();
		Map<TreeNode, Integer[]> scores = new HashMap<TreeNode, Integer[]>();
		stack.push(node);
		visited.add(node);
		while(!stack.isEmpty()){
			TreeNode top = stack.peek();
			if(top.left != null && !visited.contains(top.left)){
				stack.push(top.left);
				visited.add(top.left);
				continue;
			}
			if(top.right != null && !visited.contains(top.right)){
				stack.push(top.right);
				visited.add(top.right);
				continue;
			}
			stack.pop();
			if(top.left == null && top.right == null){	//叶子节点
				scores.put(top, new Integer[]{0,0});
			}else{
				Integer[] leftChildScore = scores.get(top.left);
				int leftH = leftChildScore == null ? 0: Math.max(leftChildScore[0], leftChildScore[1]) + 1;
				
				Integer[] rightChildScore = scores.get(top.right);
				int rightH = rightChildScore == null? 0: Math.max(rightChildScore[0], rightChildScore[1]) + 1;
				
				if(Math.abs(leftH - rightH) > 1 ){
					res = false;
					break;
				}else{
					scores.put(top, new Integer[]{leftH,rightH});
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TreeNode n = BuildTreeFromArrayUtil.build(new Integer[]{1,2,2,3,null,null,3,4,null,null,4,null,null});
		P110_BalancedBinaryTree p = new P110_BalancedBinaryTree();
		System.out.println(p.isBalanced(n));
		
		n = BuildTreeFromArrayUtil.build(new Integer[]{1,null, 2});
		System.out.println(p.isBalanced(n));
	}

}
