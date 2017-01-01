/**
 * @author xwzhu
 * @Date 2015-3-17
 */
package study.datastructer.tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Notes:
 * Value of the tree nodes needs to be unique.
 */
public class BinaryTree {
	
	public BinaryTreeNode rootNode;
	
	public Map<Integer, BinaryTreeNode> nodes = new HashMap<Integer, BinaryTreeNode>();
	
	//Construct tree with a given array.
	public BinaryTree(int[] values){
		if(values.length == 0)
			return;
		else{
			//Set root node;
			BinaryTreeNode root = new BinaryTreeNode();
			root.value = values[0];
			nodes.put(root.value, root);
			this.rootNode = root;
			
			for(int i = 0, currentAddedIndex = 0; ; i ++){
				BinaryTreeNode node = nodes.get(values[i]);	//get current node to set left child and right child.
				
				if(++currentAddedIndex < values.length){	//set left child
					BinaryTreeNode leftNode = new BinaryTreeNode();
					leftNode.value = values[currentAddedIndex];
					node.leftNode = leftNode;
					leftNode.parentNode = node;
					nodes.put(leftNode.value, leftNode);
				}else
					break;	//all set.
				
				if(++currentAddedIndex < values.length){	//set right child
					BinaryTreeNode rightNode = new BinaryTreeNode();
					rightNode.value = values[currentAddedIndex];
					node.rightNode = rightNode;
					rightNode.parentNode = node;
					nodes.put(rightNode.value, rightNode);
				}else
					break;	//all set.
					
			}
		}
	}
	
	//Construct a tree by giving its root node.
	public BinaryTree(BinaryTreeNode rootNode){
		this.rootNode = rootNode;
	}
	
	//Default Constructor
	public BinaryTree(){
	}
	
	//Remove all nodes in the tree.
	public void clearTree(){
		nodes.clear();
	}
	
	//Traversal the tree in pre-order 
	public void preOrderTraversal(BinaryTreeNode node){
		if(node == null){
			return;
		}
		else{
			System.out.print(node.value + " ");
			if(node.hasLeftChild())
				preOrderTraversal(node.leftNode);
			if(node.hasRightChild())
				preOrderTraversal(node.rightNode);
		}
		
	}
	
	//remove node from tree. This is to facility debug code.
	public boolean removeNode(int value){
		BinaryTreeNode node = nodes.get(value);
		
		if(node != null){
			BinaryTreeNode parent = node.parentNode;
			if(parent == null){
				clearTree();	//remove root
				return true;
			}
			
			//disconnect parent
			if(parent.hasLeftChild() && parent.leftNode.value == node.value)
				parent.leftNode = null;
			else if(parent.hasRightChild() && parent.rightNode.value == node.value)
				parent.rightNode = null;
			
			//disconnect self from parent
			node.parentNode = null;
			
			return true;
		}else
			return false;
	}
	
	//Traversal the tree in in-order 
	public void inOrderTraversal(BinaryTreeNode node){
		if(node == null){
			return;
		}
		else{
			if(node.hasLeftChild())
				inOrderTraversal(node.leftNode);
			System.out.print(node.value + " ");
			if(node.hasRightChild())
				inOrderTraversal(node.rightNode);
		}
		
	}
	
	//Traversal the tree in post-order 
	public void postOrderTraversal(BinaryTreeNode node){
		if(node == null){
			return;
		}
		else{
			if(node.hasLeftChild())
				postOrderTraversal(node.leftNode);
			if(node.hasRightChild())
				postOrderTraversal(node.rightNode);
			System.out.print(node.value + " ");
		}
	}
	
	//Traversal the tree in level order
	public void levelOrderTraversal(BinaryTreeNode rootNode){
		if(rootNode == null){
			return;
		}
		Queue<BinaryTreeNode> q = new ArrayDeque<BinaryTreeNode>();
		q.offer(rootNode);
		while(!q.isEmpty()){
			//put left child and right child of current node into queue.
			BinaryTreeNode currentNode = q.poll();
			System.out.print(currentNode.value + " ");
			if(currentNode.leftNode != null)
				q.offer(currentNode.leftNode);
			if(currentNode.rightNode != null)
				q.offer(currentNode.rightNode);
		}
	}
	
	/**Get the mirror of a tree. e.g.
	Input:
		   3
		 4    5
	    6 7  8 9
	return:
	    	3
	     5     4
	    9 8   7 6
    */
	public void mirror(BinaryTreeNode node){
		if(node == null)
			return;
		
		BinaryTreeNode temp = node.leftNode;
		node.leftNode = node.rightNode;
		node.rightNode = temp;
		
		mirror(node.leftNode);
		mirror(node.rightNode);
	}
	
	public static void main(String[] args) {
		int[] values = {8,6,10,5,7,9,11,1,2,3,4,12,13,14,15,16};
		BinaryTree tree = new BinaryTree(values);
		System.out.print("\r\nPre order: ");
		tree.preOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nIn order: ");
		tree.inOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nPost order: ");
		tree.postOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nLevel order: ");
		tree.levelOrderTraversal(tree.rootNode);
		
		tree.mirror(tree.rootNode);
		System.out.print("\r\nLevel order After mirro: ");
		tree.levelOrderTraversal(tree.rootNode);
		
		tree.mirror(tree.rootNode);
		System.out.print("\r\nLevel order After mirro back: ");
		tree.levelOrderTraversal(tree.rootNode);
		
		System.out.println("\r\n///////////////////");
		
		tree.removeNode(8);
		
		System.out.println("Mirror tree now");
		
		tree.mirror(tree.rootNode);
		System.out.print("\r\nLevel order: ");
		tree.levelOrderTraversal(tree.rootNode);

		System.out.print("\r\nPre order: ");
		tree.preOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nIn order: ");
		tree.inOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nPost order: ");
		tree.postOrderTraversal(tree.rootNode);
		
		System.out.println("\r\n-------------------------------");
		//Test another way to construct a tree.
		tree = new BinaryTree(values);
		BinaryTreeNode root = new BinaryTreeNode();
		root.value = 1;
		tree.rootNode = root;
		BinaryTreeNode node2 = new BinaryTreeNode();
		node2.value = 2;
		BinaryTreeNode node3 = new BinaryTreeNode();
		node3.value = 3;
		root.leftNode = node2;
		root.rightNode = node3;
		BinaryTreeNode node4 = new BinaryTreeNode();
		node4.value = 4;
		BinaryTreeNode node5 = new BinaryTreeNode();
		node5.value = 5;
		node3.leftNode = node4;
		node3.rightNode = node5;
		
		System.out.print("\r\nPre order: ");
		tree.preOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nLevel order: ");
		tree.levelOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nLevel order of node3: ");
		tree.levelOrderTraversal(node3);
		
		System.out.println("\r\nMirror tree now");
		tree.mirror(tree.rootNode);
		
		System.out.print("\r\nPre order: ");
		tree.preOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nLevel order: ");
		tree.levelOrderTraversal(tree.rootNode);
		
		System.out.print("\r\nLevel order of node3: ");
		tree.levelOrderTraversal(node3);
	}
}
