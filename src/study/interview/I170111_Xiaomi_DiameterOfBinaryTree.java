package study.interview;
/**
 * 170111
 * 求二叉树的直径。所谓直径，就是把两个叶子节点“拎起来”，其间最多的节点个数。
 * 例子：下面的二叉树，直径是从节点12到11, 直径是8
 *         1
 *        /  \
 *       2    3
 *           / \
 *          4   5
 *         /\   /  
 *        6  7  8
 *       /    \  \
 *      9      10 11
 *     /
 *    12
 */
public class I170111_Xiaomi_DiameterOfBinaryTree {

	private int maxDepth = 0;
	
	public int getDiameter(TreeNode_Diameter node){
		getDiameter_helper(node);
		return maxDepth;
	}
	
	public void getDiameter_helper(TreeNode_Diameter node){
		if(node == null)
			return;
		
		if(node.left == null && node.right == null)
			return;
		
		if(node.left != null){
			getDiameter_helper(node.left);
			node.leftDepth = Math.max(node.left.leftDepth, node.left.rightDepth) + 1;
		}
		
		if(node.right != null){
			getDiameter_helper(node.right);
			node.rightDepth = Math.max(node.right.leftDepth, node.right.rightDepth) + 1;
		}
	
		if(node.leftDepth + node.rightDepth + 1 > maxDepth)
			maxDepth = node.leftDepth + node.rightDepth + 1;
	}
	
	public static void main(String[] args) {
		TreeNode_Diameter t1 = new TreeNode_Diameter();
		TreeNode_Diameter t2 = new TreeNode_Diameter();
		TreeNode_Diameter t3 = new TreeNode_Diameter();
		TreeNode_Diameter t4 = new TreeNode_Diameter();
		TreeNode_Diameter t5 = new TreeNode_Diameter();
		TreeNode_Diameter t6 = new TreeNode_Diameter();
		TreeNode_Diameter t7 = new TreeNode_Diameter();
		TreeNode_Diameter t8 = new TreeNode_Diameter();
		TreeNode_Diameter t9 = new TreeNode_Diameter();
		TreeNode_Diameter t10 = new TreeNode_Diameter();
		TreeNode_Diameter t11 = new TreeNode_Diameter();
		TreeNode_Diameter t12 = new TreeNode_Diameter();
		t1.left = t2; t1.right = t3;
		t3.left = t4; t3.right = t5;
		t4.left = t6; t4.right = t7;
		t5.left = t8;
		t6.left = t9;
		t7.right = t10;
		t8.right = t11;
		t9.left = t12;
		
		I170111_Xiaomi_DiameterOfBinaryTree ii = new I170111_Xiaomi_DiameterOfBinaryTree();
		System.out.println(ii.getDiameter(t1));	//8
	}

}

/**
 * 为了便于计算直径，特殊的tree。有leftDepth和rightDepth两个域。
 */
class TreeNode_Diameter{
	int leftDepth;	//左子树的深度
	int rightDepth;	//又子树的深度
	int value;	//其实可以没有。按照惯例，保留而已。
	TreeNode_Diameter left;
	TreeNode_Diameter right;
}