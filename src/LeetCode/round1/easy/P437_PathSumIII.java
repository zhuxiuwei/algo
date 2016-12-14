package LeetCode.round1.easy;

import java.util.HashSet;
import java.util.Set;

import LeetCode.round1.common.TreeNode;

/**
 * 161213,14
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
5  -2   1
Return 3. The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */
public class P437_PathSumIII {
	
	/**
	 * Refer: https://discuss.leetcode.com/topic/64388/simple-ac-java-solution-dfs 
	 * AC: 31ms, 49.4%
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
	 * Fail on case [0,1,1]. expect 4, output 2.
	 */
	public int pathSum_wrong(TreeNode root, int sum){
		if(root == null)
			return 0;
		originSsum = sum;
		helper(root, sum);
		return res;
	}
	private int res = 0;
	private int originSsum = 0;
	Set<TreeNode> matched = new HashSet<TreeNode>();	//for dedup.
	private void helper(TreeNode root, int sum){
		if(root.val == sum){
			if(!matched.contains(root)){
				res ++;
				matched.add(root);
			}
		}
		if(root.left != null){
			helper(root.left, originSsum);
			helper(root.left, sum - root.val);
		}
		if(root.right != null){
			helper(root.right, originSsum);
			helper(root.right, sum - root.val);
		}
	}
	

	public static void main(String[] args) {
		P437_PathSumIII p = new P437_PathSumIII();
		TreeNode t1 = new TreeNode(10);
		TreeNode t2 = new TreeNode(5);
		TreeNode t3 = new TreeNode(-3);
		TreeNode t4 = new TreeNode(3);
		TreeNode t5 = new TreeNode(2);
		TreeNode t6 = new TreeNode(11);
		TreeNode t7 = new TreeNode(5);
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
		System.out.println(p.pathSum(t1, 8));	//4
		
		p = new P437_PathSumIII();
		TreeNode t11 = new TreeNode(0);
		TreeNode t12 = new TreeNode(1);
		TreeNode t13 = new TreeNode(1);
		t11.left = t12;
		t11.right = t13;
		System.out.println(p.pathSum(t11, 1));	//4
	}

}
