package LeetCode.round3;

import LeetCode.round1.common.TreeNode;
import study.javase.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 240828 medium
 * 根据先根顺序、中根顺序构建二叉树。
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is
 * the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * Constraints:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class P105_ConstructBinaryTreeFomPreorderAndInorderTraversal {
    /**
     * 思路：
     * 1、依次遍历先根数组中的每个节点n
     * 2、对先根遍历中的每个节点n，到中跟数组中寻找它的位置
     *  寻找方式：
     *  从先跟遍历数组第一个看起，寻找当前节点n，与先跟遍历数组数组中当前节点m的位置关系。n在m左边，就往m的左子树继续看，否则往右，直到找到一个空为止位置。.
     * AC: 56ms Beats 5.68%, Memory 45.74MB Beats 11.21%
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderIdxMap = new HashMap<>();  //保存每个节点value在中序数组的索引位置。 k: val, v：在中序数组的idx
        Map<Integer, TreeNode> valueToNodeMap = new HashMap<>();    //保留每个value对应的TreeNode。 k: val, v: TreeNode
        //构建俩map
        for (int i = 0; i < inorder.length; i++) {
            inOrderIdxMap.put(inorder[i], i);
            TreeNode node = new TreeNode(inorder[i]);
            valueToNodeMap.put(inorder[i], node);
        }
        TreeNode root = valueToNodeMap.get(preorder[0]);
        //遍历前序数组，并在中序数组中判断它的位置，为每个节点找到合适位置
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = valueToNodeMap.get(preorder[i]);
            int inIdx = inOrderIdxMap.get(preorder[i]);
            TreeNode tmpNode = root;
            while (tmpNode != null) {
                int tmpInIdx = inOrderIdxMap.get(tmpNode.val);
                if(tmpInIdx < inIdx){   //当前node节点，在tmpNode的右边
                    TreeNode tmpRightNode = tmpNode.right;
                    if(tmpRightNode == null){   //找到了位置
                        tmpNode.right = node;
                        break;
                    }else {
                        tmpNode = tmpRightNode;
                    }
                }else { //当前node节点，在tmpNode的左边
                    TreeNode tmpLeftNode = tmpNode.left;
                    if(tmpLeftNode == null){   //找到了位置
                        tmpNode.left = node;
                        break;
                    }else {
                        tmpNode = tmpLeftNode;
                    }
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        P105_ConstructBinaryTreeFomPreorderAndInorderTraversal p = new P105_ConstructBinaryTreeFomPreorderAndInorderTraversal();
        p.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});    //[3,9,20,null,null,15,7]
    }
}
