package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

/**
 * 240801 Easy
 * Given the root of a binary tree, invert the tree, and return its root.
 * Example 1:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Example 2:
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * Example 3:
 * Input: root = []
 * Output: []
 *
 *     4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class P226_InvertBinaryTree {
    /**
     * AC: 0ms 100%, mem: 55.8%
     * 没啥难度
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            if (root.left != null) {
                invertTree(root.left);
            }
            if (root.right != null) {
                invertTree(root.right);
            }
        }
        return root;
    }

}
