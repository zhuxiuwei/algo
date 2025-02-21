package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 250221 easy
 * https://leetcode.com/problems/count-complete-tree-nodes/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class P222_CountCompleteTreeNodes {
    /**
     * AC: Runtime 0 ms Beats 100.00%, Memory 48.14 MB Beats 10.28%
     *
     * 思路：计算高度h，则前h-1层是满二叉树，最少有2^(h-1)-1个节点
     * 最下面一层节点个数计算思路：
     * 利用后序遍历，每遍历到一个h层的叶子节点，数量加一。遇到第一个层数不是h的叶子结点，退出遍历。最后加一的结果记为sum。
     * 最后结果= 2^(h-1)-1 + sum
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;

        //计算前h-1层节点数
        int level = 0;
        TreeNode node = root;
        while (node != null){
            level ++;
            node = node.left;
        }
        int firstLevelNodeCount = (int)Math.pow(2, level - 1) - 1;

        //计算h层叶子结点的个数
        AtomicInteger bottomLevelNodeCount = new AtomicInteger(0);
        helper(root, level, 1, bottomLevelNodeCount, new AtomicBoolean(true));

        return firstLevelNodeCount + bottomLevelNodeCount.get();
    }


    /**
     * 通过后根遍历，计算h层叶子结点的个数
     * @param node  当前遍历节点
     * @param totalLevel 二叉树总层数
     * @param curNodeLevel  当前遍历的层数
     * @param bottomLevelNodeCount 记录已经遍历到的第h层叶子结点的个数
     * @param goOn 是否继续遍历
     *
     * ！！！注意最后两个基本类型变量，为了在递归过程中传递引用，用的是Atomic类型。最开始用的是P222_CountCompleteTreeNodes类的filed，但是很ugly。两次运行之间类变量的值还会传递影响。【见main函数】
     */
    private void helper(TreeNode node, int totalLevel, int curNodeLevel, AtomicInteger bottomLevelNodeCount, AtomicBoolean goOn){
        if(node.left != null && goOn.get()){
            helper(node.left, totalLevel, curNodeLevel + 1, bottomLevelNodeCount, goOn);
        }
        if(node.right != null && goOn.get()){
            helper(node.right, totalLevel, curNodeLevel + 1, bottomLevelNodeCount, goOn);
        }

        if(node.left == null && node.right == null){
            if(totalLevel == curNodeLevel) {
                bottomLevelNodeCount.addAndGet(1);
            }else {
                goOn.set(false);
            }
        }
    }

    public static void main(String[] args) {
        P222_CountCompleteTreeNodes p = new P222_CountCompleteTreeNodes();
        TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{1,2,3,4,5,6,null});
        System.out.println(p.countNodes(root)); //6

//        p = new P222_CountCompleteTreeNodes();    //！！！ 如果用类变量，每次都要new。否则历史运行的类属性值，会影响这次运行。
        root = null;
        System.out.println(p.countNodes(root)); //0

//        p = new P222_CountCompleteTreeNodes();    //！！！ 如果用类变量，每次都要new。否则历史运行的类属性值，会影响这次运行。
        root = BuildTreeFromArrayUtil.build(new Integer[]{1});
        System.out.println(p.countNodes(root)); //1
    }
}
