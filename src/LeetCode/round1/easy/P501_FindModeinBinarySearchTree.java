package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.Arrays;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170513
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.
Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class P501_FindModeinBinarySearchTree {
	
	private int lastNum;
	private int maxCount;
	private int curCount;
	private ArrayList<Integer> res = new ArrayList<Integer>();
	
	/**
	 * AC: 6ms, 56.49%。
	 * ！！！！！！！！！！注意还是无法bug free，还是有一个bug，出了两次问题。
	 * @param root
	 * @return
	 */
	public int[] findMode(TreeNode root) {
		if(root == null)
			return new int[]{};
		
		lastNum = root.val + 1;	//确保lastNum和最小的字符不一样
		findMode_helper(root);
		int[] resArr = new int[res.size()];
		for (int i = 0; i < res.size(); i++) 
			resArr[i] = res.get(i);
		return resArr;
	}
	public void findMode_helper(TreeNode root) {
		if(root.left != null)
			findMode_helper(root.left);
		
		int val = root.val;
		if(val != lastNum){
			curCount = 1;
			lastNum = val;
		}else
			curCount ++;
		if(curCount == maxCount){
			res.add(val);
		}else if(curCount > maxCount){
			maxCount = curCount;
			if(res.size() >= 1)	//！！！！！！！！！注意bug。这个地方逻辑写错了两次。
				res.clear();
			res.add(val);
		}
		
		if(root.right != null)
			findMode_helper(root.right);
	}
	
	public static void main(String[] args) {
		P501_FindModeinBinarySearchTree p = new P501_FindModeinBinarySearchTree();
		TreeNode t = BuildTreeFromArrayUtil.build(new Integer[]{1,null,2,2});
		System.out.println(Arrays.toString(p.findMode(t)));

		TreeNode t2 = BuildTreeFromArrayUtil.build(new Integer[]{1,1,null,null,null});
		p = new P501_FindModeinBinarySearchTree();
		System.out.println(Arrays.toString(p.findMode(t2)));	//[1]，不要是[1,1]

	}

}
