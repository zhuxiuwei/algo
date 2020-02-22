package LeetCode.round2;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;
import study.javase.annotations.Test;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 200222 easy
 给定一个二叉树，检查它是否是镜像对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
      1
    /  \
   2    2
  / \ / \
 3  4 4  3

 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
   1
  / \
 2   2
 \   \
 3    3

 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

 */
public class P101_对称二叉树 {

    /**
     * 我开始的思路比较蠢：把二叉树左右镜像翻转，和原来的二叉树一样就是镜像的（比如2者的前跟后跟遍历一致）、
     * 看了最佳答案的思路比较好。关键思路：每个树的右子树都与另一个树的左子树镜像对称，这两个树互为镜像。
     * https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode/
     */

    //方案1： 递归
    public boolean isSymmetric1(TreeNode root) {
        if(root == null)
            return true;
        else
            return isSymmetricRecursive(root.left, root.right);
    }
    private boolean isSymmetricRecursive(TreeNode n1, TreeNode n2) {
        if(n1 == null && n2 == null)    //都为null，ok
            return true;
        else if((n1 == null && n2 != null) || (n2 == null && n1 != null))   //一个null一个不是null，不行
            return false;
        else if(n1.left == null && n1.right == null && n2.right == null & n2.left == null)  //都是叶子节点，判断值
            return n1.val == n2.val;
        else {  //否则还有子树，继续递归。
            boolean ok1 = isSymmetricRecursive(n1.left, n2.right);
            if(ok1) {
                boolean ok2 = isSymmetricRecursive(n1.right, n2.left);
                return ok2 && n1.val == n2.val;
            }else
                return false;
        }
    }

    //方案2： 迭代。我开始想用DFS(栈)，写起来很麻烦，还需要自定义一个带颜色的树节点（类似DFS里的颜色）标记节点是否访问过。
    //参考了上面最佳答案的方法，用BFS(队列)更简单。 注意进入队列的顺序，一左一右入队列。
    public boolean isSymmetric2(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return true;
        if((root.left == null && root.right != null) ||(root.right == null && root.left != null))   //左右结构不一样返回false
            return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if(node1.val != node2.val)  //值不一样返回false
                return false;
            if((node1.left != null && node2.right == null) || (node1.left == null && node2.right != null)   //左右结构不一样返回false
                    || (node1.right != null && node2.left == null) || (node1.right == null && node2.left != null))
                return false;
            if(node1.left != null && node2.right != null){
                queue.add(node1.left);
                queue.add(node2.right);
            }
            if(node1.right != null && node2.left != null){
                queue.add(node1.right);
                queue.add(node2.left);
            }
        }
        return true;
    }

    //没用上。本打算给迭代方式DFS用的。
    static class TreeNode101 {
        public boolean isVisited = false;  //是否被访问过，类似CLRS的DFS,BFS一张里Vertex的节点（白色，灰色，黑色）
        public int val;
        public TreeNode101 left;
        public TreeNode101 right;
    }


    public static void main(String[] args) {
        P101_对称二叉树 p = new P101_对称二叉树();
        System.out.println(p.isSymmetric2(BuildTreeFromArrayUtil.build(new Integer[]{1})));  //true
        System.out.println(p.isSymmetric2(BuildTreeFromArrayUtil.build(new Integer[]{1,2,null})));   //false
        System.out.println(p.isSymmetric2(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2})));  //true
        System.out.println(p.isSymmetric2(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2,4,3,3,4})));  //true
        System.out.println(p.isSymmetric2(BuildTreeFromArrayUtil.build(new Integer[]{1,3,4,4,3,3,4})));  //false

    }
}
