package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.*;

/**
 * 240707 - Easy - 16年做过
 * 二叉树中序遍历。 需要考虑递归、非递归，2种方式。
 */
public class P094_BinaryTreeInorderTraversal {

    /**
     * 递归方式，没什么问题
     * AC: 0ms 100%, mem 50.7%
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        inorderTraversalRecursive_helper(root, res);
        return res;
    }
    private void inorderTraversalRecursive_helper(TreeNode node, List<Integer> res){
        if(node.left != null)
            inorderTraversalRecursive_helper(node.left, res);
        res.add(node.val);
        if(node.right != null)
            inorderTraversalRecursive_helper(node.right, res);
    }

    /**
     * 非递归方式，用堆栈
     * AC: 1ms 100%， mem: 10.3%
     * ！！！ 注意需要记录访问过的左节点，避免不断走回头路，死循环。 这么写好像比16年的简单点。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;

        Set<TreeNode> visited = new HashSet<>();    //用于记录已经访问过的左节点。避免不断走回头路死循环。

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node.left != null && !visited.contains(node.left)){
                stack.push(node.left);
            }else {
                node = stack.pop();
                res.add(node.val);
                visited.add(node);
                if(node.right != null){
                    stack.push(node.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P094_BinaryTreeInorderTraversal p = new P094_BinaryTreeInorderTraversal();
        System.out.println("递归");
        System.out.println(p.inorderTraversalRecursive(BuildTreeFromArrayUtil.build(new Integer[]{1, null, 2, 3, null})));    //[1,3,2]
        System.out.println(p.inorderTraversalRecursive(BuildTreeFromArrayUtil.build(new Integer[]{})));    //[]
        System.out.println(p.inorderTraversalRecursive(BuildTreeFromArrayUtil.build(new Integer[]{1})));    //[1]

        System.out.println("非递归");
        System.out.println(p.inorderTraversal(BuildTreeFromArrayUtil.build(new Integer[]{1, null, 2, 3, null})));    //[1,3,2]
        System.out.println(p.inorderTraversal(BuildTreeFromArrayUtil.build(new Integer[]{})));    //[]
        System.out.println(p.inorderTraversal(BuildTreeFromArrayUtil.build(new Integer[]{1})));    //[1]
    }
}