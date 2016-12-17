package LeetCode.round1.easy;

import LeetCode.round1.common.TreeNode;

/**
 * 161217
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST. According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class P235_LowestCommonAncestorOfaBinarySearchTree {

	/**
	 * 1 time AC: 8ms, 54.8%
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || p == null || q == null)
			return null;
		if(p == q)
			return p;
		
		TreeNode res = null;
		while(true){
			if(root == null) break;
			if((root.val < p.val && root.val > q.val) || (root.val < q.val && root.val > p.val))
				return root;
			else if( root == p )
				return p;
			else if(root == q)
				return q;
			else{
				if(p.val <= root.val && q.val <= root.val)
					root = root.left;
				else
					root = root.right;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {

	}

}
