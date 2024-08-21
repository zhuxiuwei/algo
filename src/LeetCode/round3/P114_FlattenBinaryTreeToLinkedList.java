package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 240821 medium
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list
 * and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Example 1:
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class P114_FlattenBinaryTreeToLinkedList {

    /**
     * AC: 1ms Beats 22.94%, Memory 41.89 MB Beats 79.71%
     * 平铺直叙的写法，空间复杂度O(n)，没有达到最佳O(1)的要求。
     */
    public void flatten(TreeNode root) {
        if(root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            preOrderTravel(root, queue);
            TreeNode last = null, cur = null;
            while (!queue.isEmpty()){
                cur = queue.poll();
                cur.left = null;
                cur.right = null;
                if(last != null){
                    last.left = null;
                    last.right = cur;
                }
                last = cur;
            }
        }
    }

    private void preOrderTravel(TreeNode node, Queue<TreeNode> queue){
        queue.offer(node);
        if(node.left != null)
            preOrderTravel(node.left, queue);
        if(node.right != null)
            preOrderTravel(node.right, queue);
    }
}
