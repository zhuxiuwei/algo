package lintcode.round1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * @author zhu xiuwei 
 * 170529 medium http://www.lintcode.com/en/problem/lowest-common-ancestor/
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

Notice: Assume two nodes are exist in tree.

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
public class P088_LowestCommonAncestor {
	
	private boolean found ;
	/**
	 * ！！！！注意点： 一旦有了filed(boolean found)，类就有状态了。注意状态在每次操作之间，一定要清零，否则下次操作可能就出错。
	 * 
     * @param root: The root of the binary search tree.
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
    	TreeNode res = null;
    	
    	//find ancestors of A.
    	found = false;	//!!!!!!!!!!!!!!!!! 注意： 一定要给状态清零！！！！！！！！！！！！！！
    	List<TreeNode> ancestorsA = new LinkedList<TreeNode>();
    	findAncestors(root, A, ancestorsA);
		
    	//find ancestors of B.
    	found = false;	//!!!!!!!!!!!!!!!!! 注意： 一定要给状态清零！！！！！！！！！！！！！！
    	List<TreeNode> ancestorsB = new LinkedList<TreeNode>();
    	findAncestors(root, B, ancestorsB);
    	
    	//find LCA
    	Iterator<TreeNode> ita = ancestorsA.iterator();
    	Iterator<TreeNode> itb = ancestorsB.iterator();
    	while(ita.hasNext() && itb.hasNext()){
    		TreeNode ta = ita.next();
    		TreeNode tb = itb.next();
    		if(ta == tb){
    			res = ta;
    		}else
    			break;
    	}
    	return res;
    }
    private void findAncestors(TreeNode root, TreeNode node, List<TreeNode> res){	////!!!! 注意： res是object，因此可以在递归中作为参数做引用传递。把boolean这样的基本类型，想在递归中这么用是不行的。所以特地声明成private boolean found
    	if(found)
    		return;
    	res.add(root);
    	if(root == node){
    		found = true;
    	}else{
    		if(root.left != null && !found){
    			findAncestors(root.left, node, res);
    			if(!found) res.remove(res.size() - 1);
    		}
    		if(root.right != null && !found){
    			findAncestors(root.right, node, res);
    			if(!found) res.remove(res.size() - 1);
    		}
    	}
    }

	public static void main(String[] args) {
		TreeNode root = BuildTreeFromArrayUtil.build(new Integer[]{4,3,7,null,null,5,6,null,null,null,null});
		P088_LowestCommonAncestor p = new P088_LowestCommonAncestor();
		TreeNode target6 = root.right.right;
		TreeNode target5 = root.right.left;
		TreeNode target3 = root.left;
		System.out.println(p.lowestCommonAncestor(root, target3, target5));		//4
		System.out.println(p.lowestCommonAncestor(root, target6, target5));		//7
		
		root = BuildTreeFromArrayUtil.build(new Integer[]{2, -1 ,null,null, null});
		TreeNode target1 = root.left;
		System.out.println(p.lowestCommonAncestor(root, root, target1));		//2
	}

}
