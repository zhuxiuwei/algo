package lintcode.round1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import LeetCode.round1.common.TreeNode;

/**
 * 170613 Medium
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Example
For the following binary tree:
  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4
LCA(5, 6) = 7
LCA(6, 7) = 7
 */
public class P088_AmaMoni_LowestCommonAncestor {
	
	private boolean found = false;
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B){
		if(root == null || A == null || B == null)
			return null;
		
		//find ancestors
		List<TreeNode> ancestorsA = new ArrayList<TreeNode>();
		findAncestors(ancestorsA, root, A);
		found = false;
		List<TreeNode> ancestorsB = new ArrayList<TreeNode>();
		findAncestors(ancestorsB, root, B);
		found = false;
		
		//compare ancestors to find LCA
		TreeNode res = null;
		Iterator<TreeNode> ita = ancestorsA.iterator(), itb = ancestorsB.iterator();
		while(ita.hasNext() && itb.hasNext()){
			TreeNode na = ita.next();
			TreeNode nb = itb.next();
			if(na == nb)
				res = na;
			else
				break;
		}
		return res;
	}
	private void findAncestors(List<TreeNode> ancestors, TreeNode root, TreeNode A){
		if(!found){
			if(root == A){
				ancestors.add(root);
				found = true;
			}else{
				ancestors.add(root);
				if(root.left != null){
					findAncestors(ancestors, root.left, A);
					if(!found) ancestors.remove(ancestors.size() - 1);
				}
				if(root.right != null){
					findAncestors(ancestors, root.right, A);
					if(!found) ancestors.remove(ancestors.size() - 1);
				}
			}
		}
	}
    
	public static void main(String[] args) {
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		t4.left = t3;
		t4.right = t7; 
		t7.left = t5;
		t7.right = t6;
		P088_AmaMoni_LowestCommonAncestor p = new P088_AmaMoni_LowestCommonAncestor();
		List<TreeNode> r = new ArrayList<TreeNode>();
		p.findAncestors(r, t4, t5);
		System.out.println(r);

	}

}
