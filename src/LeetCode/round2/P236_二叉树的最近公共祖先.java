package LeetCode.round2;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 200301 Medium
 https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree

 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 例如，给定如下二叉树:root =[3,5,1,6,2,0,8,null,null,7,4]

 示例 1:
 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 输出: 3
 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。

 示例 2:
 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 输出: 5
 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 */
public class P236_二叉树的最近公共祖先 {

    //！！！！！！！！ 有时候用field记录结果，挺方便，尤其在递归时。
    private TreeNode result = null;

    /**
     * DFS + 回溯问题。
     * 思路直接参考： https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetcod/
     * 执行用时 : 8 ms , 在所有 Java 提交中击败了 99.68% 的用户。 内存消耗 : 42.2 MB , 在所有 Java 提交中击败了 5.01% 的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return result;
    }
    private int helper(TreeNode node, TreeNode p, TreeNode q){
        int count = 0, leftCount = 0, rightCount = 0;
        if(node == p || node == q)  //自己是
            count ++;
        if(node.left != null)   //遍历左树
            leftCount = helper(node.left, p, q);
        if(node.right != null)   //遍历右树
            rightCount = helper(node.right, p, q);
        count = count + leftCount + rightCount;
        if(count >= 2 && result == null)
            result = node;
        return count;
    }

    public static void main(String[] args) {
        P236_二叉树的最近公共祖先 ps = new P236_二叉树的最近公共祖先();
        TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{1,2,null});
        TreeNode p = root, q = root.left;
        System.out.println(ps.lowestCommonAncestor(root, p, q));
    }
}
