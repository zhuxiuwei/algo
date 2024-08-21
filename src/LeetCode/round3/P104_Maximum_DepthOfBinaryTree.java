package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

/**
 * 240821 Easy
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */
public class P104_Maximum_DepthOfBinaryTree {
    /**
     * AC: Runtime 0ms Beats 100.00%, Memory 42.27MB Beats 87.35%
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }else if(root.left == null && root.right == null){
            return 1;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
