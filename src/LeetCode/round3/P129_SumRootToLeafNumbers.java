package LeetCode.round3;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 241104 medium
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * A leaf node is a node with no children.
 *
 * Example 1:
 * Input: root = [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 * Example 2:
 *       4
 *      / \
 *     9  0
*    / \
 *  5   1
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 */
public class P129_SumRootToLeafNumbers {

    /**
     * AC: Runtime 1 ms Beats 19.56%, Memory 40.93 MB Beats 68.09%
     * 用的递归写法，不是特别顺利，还是debug着写出来的，主要是因为一个错误点。
     */
    public int sumNumbers(TreeNode root) {
        AtomicInteger res = new AtomicInteger(0);   //用普通的int或Integer，无法在递归过程中传递值，所以用AtomicInteger
        if(root != null) {
            List<TreeNode> nums = new LinkedList<>();
            travel(root, nums, res);
        }
        return res.get();
    }

    private void travel(TreeNode node, List<TreeNode> nums, AtomicInteger res){
        nums.add(0, node);  //放list头部，为了方便calSumInList的计算。
        if(node.left != null){
            travel(node.left, nums, res);
        }
        if(node.right != null){
            travel(node.right, nums, res);
        }
        if(node.left == null && node.right == null){    //是叶子结点，进行一次计算
            calSumInList(nums, res);
        }
        //！！！！错误点：这个删除操作不能放在上面的if里，否则会有非叶子结点被重复计算，导致结果错误变大。
        nums.remove(0);   //一个元素递归完了（就是list里的头元素），从list中删除之。
    }

    private void calSumInList(List<TreeNode> nums, AtomicInteger res){
        Iterator<TreeNode> it = nums.iterator();
        int wei = 1;    //位数
        int tmpSum = 0;
        while (it.hasNext()){
            int n = it.next().val;
            tmpSum += n * wei;
            wei *= 10;
        }
        res.addAndGet(tmpSum);
    }

    public static void main(String[] args) {
        P129_SumRootToLeafNumbers p = new P129_SumRootToLeafNumbers();
        TreeNode node1 = BuildTreeFromArrayUtil.build(new Integer[]{1, 2, 3});
        System.out.println(p.sumNumbers(node1));    //25
        TreeNode node2 = BuildTreeFromArrayUtil.build(new Integer[]{4, 9, 0, 5, 1});
        System.out.println(p.sumNumbers(node2));    //1026
    }
}
