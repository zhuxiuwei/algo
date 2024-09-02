package LeetCode.round3;

import LeetCode.round1.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 240902 medium
 You are given a binary tree in which each node contains an integer value.
 Find the number of paths that sum to a given value.
 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:
 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
       10
      /  \
     5   -3
    / \    \
   3   2   11
  / \   \
 3  -2   1
 Return 3. The paths that sum to 8 are:
 1.  5 -> 3
 2.  5 -> 2 - > 1
 3. -3 -> 11
 */
public class P437_PathSumIII {

    /**
     * 感觉关键是递归思路。
     * 还是不会，还是参考的16年参考的方案。 不过会在倒数第二个case fail，先不管了。
     * root = [1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000], targetSum = 0, 期望0， 输出2
     *
     *
     * 思路：https://leetcode.com/problems/path-sum-iii/solutions/91884/simple-ac-java-solution-dfs/：
     * Each time find all the path start from current node
     * Then move start node to the child and repeat.
     * Time Complexity should be O(N^2) for the worst case and O(NlogN) for balanced binary Tree.
     * public class Solution {
     *     public int pathSum(TreeNode root, int sum) {
     *         if(root == null)
     *             return 0;
     *         return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
     *     }
     *
     *     public int findPath(TreeNode root, int sum){
     *         int res = 0;
     *         if(root == null)
     *             return res;
     *         if(sum == root.val)
     *             res++;
     *         res += findPath(root.left, sum - root.val);
     *         res += findPath(root.right, sum - root.val);
     *         return res;
     *     }
     *
     * }
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private int findPath(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    /**
     * 思路：从低向上，每层的sum结果都放到一个list里，向上的过程中不断更新累加list。累加到一个sum，结果就加一。
     * 思路错误之处：同一段公共path可能被累加多次。
     * [10,5,-3,3,2,null,11,3,-2,null,1] 期望3，错误返回4。因为5->3->3, 5->3->-2，5->3这个前缀被累加2次。
     * 尝试改成Map<level, List of level nodes> 也不行。
     */
    private int result = 0;
    public int pathSum_wrong(TreeNode root, int targetSum) {
        helper_wrong(root, targetSum);
        return result;
    }
    private List<List<Integer>> helper_wrong(TreeNode node, int targetSum){
        List<List<Integer>> res = new ArrayList<>();
        if(node.val == targetSum)
            result ++;
        if(node.left == null && node.right == null){    //根节点
            List<Integer> tmp = new ArrayList<>();
            tmp.add(node.val);
            res.add(tmp);
            return res;
        }
        List<List<Integer>> tmpLeft = null, tmpRight = null;
        if(node.left != null){
            tmpLeft = helper_wrong(node.left, targetSum);
            res.addAll(tmpLeft);
        }
        if(node.right != null){
            tmpRight = helper_wrong(node.right, targetSum);
            res.addAll(tmpRight);
        }
        for(List<Integer> tmpList: res){
            for (int i = 0; i < tmpList.size(); i++) {
                int num = tmpList.get(i) + node.val;
                if(num == targetSum) {
                    result++;
                }
                tmpList.set(i, num);
            }
            tmpList.add(node.val);
        }
        return res;
    }

    public static void main(String[] args) {
        P437_PathSumIII p = new P437_PathSumIII();
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(-3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(11);
        TreeNode t7 = new TreeNode(3);
        TreeNode t8 = new TreeNode(-2);
        TreeNode t9 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t5.right = t9;
        System.out.println(p.pathSum(t1, 8));	//3

        p = new P437_PathSumIII();
        TreeNode t11 = new TreeNode(0);
        TreeNode t12 = new TreeNode(1);
        TreeNode t13 = new TreeNode(1);
        t11.left = t12;
        t11.right = t13;
        System.out.println(p.pathSum(t11, 1));	//4

        p = new P437_PathSumIII();
        System.out.println(p.pathSum(t11, 0));	//1
    }

}
