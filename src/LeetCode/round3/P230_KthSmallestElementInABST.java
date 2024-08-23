package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 240823 medium
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 * Constraints:
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to
 * find the kth smallest frequently, how would you optimize?
 */
public class P230_KthSmallestElementInABST {
    /**
     * AC: 0ms Beats 100.00%, Memory 44.67MB Beats 15.51%
     */
    public int kthSmallest(TreeNode root, int k) {
        return inOrderTravel(root, k, new AtomicInteger(0));
    }

    private int inOrderTravel(TreeNode node, int k, AtomicInteger counter){
        if(node.left != null){
            int res = inOrderTravel(node.left, k, counter);
            if(res != -1) {
                return res;
            }
        }
        int curCount = counter.addAndGet(1);
        if(curCount == k){
            return node.val;
        }
        if(node.right != null){
            int res = inOrderTravel(node.right, k, counter);
            if(res != -1) {
                return res;
            }
        }
        return -1;
    }
}
