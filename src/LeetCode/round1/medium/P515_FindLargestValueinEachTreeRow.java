package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 170506
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
 */
public class P515_FindLargestValueinEachTreeRow {

	/**
	 * AC: 12ms, 45.6%
	 * !!!!!!!!!!!!Note 1 bug.
	 * @param root
	 * @return
	 */
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		
		if(root == null)
			return res;
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		q.offer(null);	//null means a level ends.
		
		int max = Integer.MIN_VALUE;
		while(!q.isEmpty()){
			TreeNode v = q.poll();
			if(v == null){
				res.add(max);
				if(!q.isEmpty()){	//!!!! Note bug: Must end this judge. Otherwise the last null will make dead loop.
					q.offer(null);
					max = Integer.MIN_VALUE;
				}
			}else{
				if(v.val > max)
					max = v.val;
				if(v.left != null)
					q.offer(v.left);
				if(v.right != null)
					q.offer(v.right);
			}
		}
		
		return res;
    }
	
	public static void main(String[] args) {
		TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{1,3,2,5,3,null,9});
		P515_FindLargestValueinEachTreeRow p = new P515_FindLargestValueinEachTreeRow();
		System.out.println(p.largestValues(root));
	}

}
