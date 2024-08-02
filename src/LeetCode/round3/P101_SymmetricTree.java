package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 240802 easy
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *       1
 *     /  \
 *    2    2
 *   / \ / \
 *  3  4 4  3
 *
 *  但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *    1
 *   / \
 *  2   2
 *  \   \
 *  3    3
 *
 *  如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 */
public class P101_SymmetricTree {

    /**
     * 迭代：层次遍历法。
     * 有一个小问题，其余顺利。
     * AC: 1ms Beats 13.66%, mem: 41.72MB Beats 33.54%
     */
    public boolean isSymmetric(TreeNode root) {
        List<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        while (true){
            //判断这一层是否对称
            int i = 0, j = levelNodes.size() - 1;
            while (i <= j){
                if((levelNodes.get(i) == null && levelNodes.get(j) != null)
                        || (levelNodes.get(i) != null && levelNodes.get(j) == null))    //！！！小bug，注意要先加这行判断，否则下一行可能NPE
                    return false;
                if((levelNodes.get(i) == null && levelNodes.get(j) == null)
                        || levelNodes.get(i).val == levelNodes.get(j).val){ //都为Null，或者值相同
                    i ++;
                    j --;
                }
                else {
                    return false;
                }
            }
            //把下层也放入队列，继续判断
            List<TreeNode> nextLevelNodes = new ArrayList<>();
            for (TreeNode node: levelNodes) {
                if(node != null){
                    nextLevelNodes.add(node.left);
                    nextLevelNodes.add(node.right);
                }
            }
            if(nextLevelNodes.isEmpty())    //整个树已遍历完
                break;
            levelNodes = nextLevelNodes;
        }
        return true;
    }

    /**
     * 递归法
     * 和20年类似，一开始也是比较蠢的思路（把二叉树左右镜像翻转，和原来的二叉树一样就是镜像的（比如2者的前跟后跟遍历一致））
     * 看了下20年的笔记说这个思路不行，仔细想了下想到正确的思路。
     *
     * AC: 0ms 100%, mem 41.54MB Beats 62.36%
     */
    public boolean isSymmetric_recursive(TreeNode root) {
        return isSymmetric_recursive_helper(root.left, root.right);
    }
    private boolean isSymmetric_recursive_helper(TreeNode node1, TreeNode node2){
        // ！！！ 总体还行，但是下面代码的if else顺序要仔细想明白，第一把写错了，导致错误结果，重新改过的
        if((node1 == null && node2 != null) || (node1 != null && node2 == null))    //一个空，一个不空，停止判断，返回false
            return false;
        else if((node1 == null && node2 == null) )  //都空，停止判断，返回true
            return true;
        else if(node1.val != node2.val)  //都不空，但值不相等，停止判断，返回false
            return false;
        else { //都不空，且值相等，需要继续递归判断
            boolean res = isSymmetric_recursive_helper(node1.left, node2.right);
            if (!res) {
                return false;
            } else {
                res = isSymmetric_recursive_helper(node1.right, node2.left);
                return res;
            }
        }
    }

    public static void main(String[] args) {
        P101_SymmetricTree p = new P101_SymmetricTree();
        System.out.println(p.isSymmetric(BuildTreeFromArrayUtil.build(new Integer[]{1})));  //true
        System.out.println(p.isSymmetric(BuildTreeFromArrayUtil.build(new Integer[]{1,2,null})));   //false
        System.out.println(p.isSymmetric(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2})));  //true
        System.out.println(p.isSymmetric(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2,4,3,3,4})));  //true
        System.out.println(p.isSymmetric(BuildTreeFromArrayUtil.build(new Integer[]{1,3,4,4,3,3,4})));  //false
        System.out.println(p.isSymmetric(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2,null,3,null,3})));  //false

        System.out.println("------------------");

        System.out.println(p.isSymmetric_recursive(BuildTreeFromArrayUtil.build(new Integer[]{1})));  //true
        System.out.println(p.isSymmetric_recursive(BuildTreeFromArrayUtil.build(new Integer[]{1,2,null})));   //false
        System.out.println(p.isSymmetric_recursive(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2})));  //true
        System.out.println(p.isSymmetric_recursive(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2,4,3,3,4})));  //true
        System.out.println(p.isSymmetric_recursive(BuildTreeFromArrayUtil.build(new Integer[]{1,3,4,4,3,3,4})));  //false
        System.out.println(p.isSymmetric_recursive(BuildTreeFromArrayUtil.build(new Integer[]{1,2,2,null,3,null,3})));  //false

    }
}
