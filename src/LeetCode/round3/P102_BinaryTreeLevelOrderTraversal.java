package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.*;

/**
 * 240812 Medium
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class P102_BinaryTreeLevelOrderTraversal {

    /**
     * AC: 1ms Beats 91.23%, Memory 44.76MB Beats 72.43%
     * 总体顺利，有一个bug
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        if(root != null){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);    //表示一行结束的占位符
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node != null){
                    tmp.add(node.val);
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }else {
                    res.add(tmp);
                    tmp = new ArrayList<>();
                    if(!queue.isEmpty())   //！！！ 需要加上这个判断，否则队列里永远有一个null，死循环
                        queue.add(null);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P102_BinaryTreeLevelOrderTraversal p = new P102_BinaryTreeLevelOrderTraversal();
        System.out.println(p.levelOrder(BuildTreeFromArrayUtil.build(new Integer[]{1,null,2,3,4,null,null,5,null})));
    }
}
