package LeetCode.round1.medium;

import LeetCode.round1.common.TreeNode;

/**
 * 161127
 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class P108_ConvertSortedArrayToBinarySearchTree {

	/**
	 * AC: 1ms。  一个低级bug。别的顺利。
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums == null || nums.length == 0)
			return null;
		if(nums == null || nums.length == 0)
			return null;
		return helper(nums, 0, nums.length - 1);
    }
	public TreeNode helper(int[] nums, int start, int end) {
		if(end < start)
			return null;
		else{
			int mid = (start + end) / 2;	//!!!!!!! Note bug: 算mid index，是mid=(start+end)/2， 而不是(end-start)/2..... 太低级的错误了。
			TreeNode root = new TreeNode(nums[mid]);
			TreeNode left = helper(nums, start, mid - 1);
			TreeNode right = helper(nums, mid + 1, end);
			root.left = left;
			root.right = right;
			return root;
		}
		
    }
	
	public static void main(String[] args) {
		P108_ConvertSortedArrayToBinarySearchTree p = new P108_ConvertSortedArrayToBinarySearchTree();
		TreeNode s = p.sortedArrayToBST(new int[]{1,2});
		System.out.println(s);
	}

}
