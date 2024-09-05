package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 240905 medium
 * https://leetcode.com/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-100-liked
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class P199_BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        levelTravel(queue, res);
        return res;
    }

    private void levelTravel(Queue<TreeNode> queue, List<Integer> res){
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node != null) {      //！！！还是要加上这一行。否则在最后一行时，queue里只剩一个null时，会NPE。
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                if (queue.peek() == null) {   //是一个level结束
                    res.add(node.val);
                    queue.poll();
                    queue.offer(null);
                }
            }
        }
    }

    public static void main(String[] args) {
        P199_BinaryTreeRightSideView p = new P199_BinaryTreeRightSideView();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        t1.left = t2;
        System.out.println(p.rightSideView(t1));	//[1,2]
    }

}
