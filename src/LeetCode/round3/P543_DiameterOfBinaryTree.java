package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * 240803
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 */
public class P543_DiameterOfBinaryTree {
    /**
     * AC: 1m sBeats 35.70%.  mem: 44.41MB Beats 80.25%
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        ValHolder valHolder = new ValHolder();
        height(root, valHolder);
        return valHolder.value;
    }

    private int height(TreeNode node, ValHolder res){
        if(node.left == null && node.right == null){
            return 1;
        }else {
            int leftHeight = 0, rightHeight = 0;
            if(node.left != null){
                leftHeight = height(node.left, res);
            }
            if(node.right != null){
                rightHeight = height(node.right, res);
            }
            int height = Math.max(leftHeight, rightHeight) + 1;
            res.value = leftHeight + rightHeight > res.value ? leftHeight + rightHeight: res.value;
            return height;
        }
    }

    //用于在遍历树高时，保存结果。用Integer类不行。
    static class ValHolder{
        int value;
    }

    public static void main(String[] args) {
        P543_DiameterOfBinaryTree p = new P543_DiameterOfBinaryTree();
        System.out.println(p.diameterOfBinaryTree(BuildTreeFromArrayUtil.build(new Integer[]{1,2,3,4,5})));  //3
        System.out.println(p.diameterOfBinaryTree(BuildTreeFromArrayUtil.build(new Integer[]{1})));  //0
        System.out.println(p.diameterOfBinaryTree(BuildTreeFromArrayUtil.build(new Integer[]{2, 1, null})));      //1
        System.out.println(p.diameterOfBinaryTree(BuildTreeFromArrayUtil.build(new Integer[]{2, 3, null, 1})));  //2

    }
}
