package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

/**
 * 240826 easy
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced  binary search tree.
 * (A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.)
 *
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 */
public class P108_ConvertSortedArrayToBinarySearchTree {

    /**
     * 顺利。
     * AC: Runtime 0ms Beats 100.00%, Memory 42.80MB Beats 86.59%
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        int start = 0, end = nums.length - 1;
        return helper(nums, start, end);
    }

    private TreeNode helper(int[] nums, int start, int end){
        int mid = (start + end) / 2;
        TreeNode left = null, right = null;
        if(mid - start >= 2){
            left = helper(nums, start, mid - 1);    //递归左边
        }else {
            if(mid - start == 1){   //左边有一个或0个子节点
                left = new TreeNode(nums[start]);
            }
        }
        if(end - mid >= 2){
            right = helper(nums, mid + 1, end);     //递归右边
        }{
            if(end - mid == 1){    //右边有一个或0个子节点
                right = new TreeNode(nums[end]);
            }
        }
        TreeNode curNode = new TreeNode(nums[mid]);
        curNode.left = left;
        curNode.right = right;
        return curNode;
    }

    public static void main(String[] args) {
        P108_ConvertSortedArrayToBinarySearchTree p = new P108_ConvertSortedArrayToBinarySearchTree();
        System.out.println(p.sortedArrayToBST(new int[]{-10,-3,0,5,9}));    //[0,-3,9,-10,null,5] or [0,-10,5,null,-3,null,9]
        System.out.println(p.sortedArrayToBST(new int[]{1,3}));    //[1,null,3] and [3,1] are both height-balanced BSTs.
    }
}
