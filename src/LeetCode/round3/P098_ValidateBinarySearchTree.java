package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 240708
 * # Binary Tree，Medium，第一次做
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 */
public class P098_ValidateBinarySearchTree {

    //思路：中根遍历，是递增的。
    //AC: 6ms 16.3%, mem 6.86%
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return false;
        List<Integer> values = new ArrayList<>();
        inOrderTravel(root, values);
        System.out.println(values);
        int preVal = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            int curVal = values.get(i);
            if(curVal <= preVal)
                return false;
            preVal = curVal;        //！！！这行别忘了！！！
        }
        return true;
    }

    //中序遍历递归
    private void inOrderTravel(TreeNode node, List<Integer> values){
        if(node.left != null)
            inOrderTravel(node.left, values);
        values.add(node.val);
        if(node.right != null)
            inOrderTravel(node.right, values);
    }

    public static void main(String[] args) {
        P098_ValidateBinarySearchTree p = new P098_ValidateBinarySearchTree();
        System.out.println(p.isValidBST(BuildTreeFromArrayUtil.build(new Integer[]{2,1,3})));    //true
        System.out.println(p.isValidBST(BuildTreeFromArrayUtil.build(new Integer[]{5,1,4,null,null,3,6})));    //false
        System.out.println(p.isValidBST(BuildTreeFromArrayUtil.build(new Integer[]{2,2,2})));    //false    ！！注意在题目中，这个也不符合BST定义。
    }
}
