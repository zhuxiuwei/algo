/**
 * @author xwzhu
 * @Date 2015-3-17
 */
package study.datastructer.tree;

public class BinaryTreeNode {
	public int value;
	public BinaryTreeNode leftNode;
	public BinaryTreeNode rightNode;
	public BinaryTreeNode parentNode;
	
	public boolean hasLeftChild(){
		return leftNode != null;
	}
	
	public boolean hasRightChild(){
		return rightNode != null;
	}
	
	public boolean hasParent(){
		return parentNode != null;
	}
}
