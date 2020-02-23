package LeetCode.round2;

import LeetCode.round1.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
200223
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */
public class P199_二叉树的右视图 {
    /**
     * 之前的思路是二叉树层次遍历。 更好的方法是DFS。 总体顺利。
     * 执行用时 : 1 ms , 在所有 Java 提交中击败了 99.60% 的用户 内存消耗 : 38.1 MB , 在所有 Java 提交中击败了 5.09% 的用户
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root != null){
            Set<Integer> visitedLevel = new HashSet<>();
            dfs_helper(root, visitedLevel, 1, res);
        }
        return res;
    }
    private void dfs_helper(TreeNode node, Set<Integer> visitedLevel, int level, List<Integer> res){
        if(!visitedLevel.contains(level)) {
            res.add(node.val);
            visitedLevel.add(level);
        }
        if(node.right != null)
            dfs_helper(node.right, visitedLevel, level + 1, res);
        if(node.left != null)
            dfs_helper(node.left, visitedLevel, level + 1, res);
    }
}
