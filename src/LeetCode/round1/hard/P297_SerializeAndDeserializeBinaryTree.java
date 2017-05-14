package LeetCode.round1.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import LeetCode.round1.common.TreeNode;

/**
 * 170514
	Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
	or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
	Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. 
	You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
	For example, you may serialize the following tree
	    1
	   / \
	  2   3
	     / \
	    4   5
	as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
	Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class P297_SerializeAndDeserializeBinaryTree {

	/**
	 * AC: 91ms, 9.92%
	 */
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if(root == null)
    		return "";
    	StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
        	TreeNode node = queue.poll();
        	if(node != null)
        		sb.append(node.val).append(",");
        	else
        		sb.append("null,");
        	if(node != null){
	        	queue.offer(node.left);
	        	queue.offer(node.right);
        	}
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if(data == null || data.equals(""))
    		return null;
    	List<Integer> nodeVals = new ArrayList<Integer>();
    	String[] vals = data.split(",");
    	for (int i = 0; i < vals.length; i++) {
			String val = vals[i];
			if(!val.equals("null"))
				nodeVals.add(Integer.parseInt(val));
			else
				nodeVals.add(null);
		}
    	if(nodeVals == null || nodeVals.size() == 0)
			return null;
		
		TreeNode root = new TreeNode(nodeVals.get(0));
		Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
		map.put(0, root);
		
		//fill map
		for (Integer i = 1; i < nodeVals.size(); i++) {
			if(nodeVals.get(i) != null)
				map.put(i, new TreeNode(nodeVals.get(i)));
		}
		int nullCount = 0;
		//create tree
		for (Integer i = 0; i < nodeVals.size() - 2; i++) {
			if(nodeVals.get(i) != null){
				TreeNode node = map.get(i);
				node.left = map.getOrDefault(2 * (i - nullCount) + 1, null);
				node.right = map.getOrDefault(2 * (i - nullCount) + 2, null);
			}else
				nullCount ++;
		}
		return root;
    }
	
	public static void main(String[] args) {
		P297_SerializeAndDeserializeBinaryTree p = new P297_SerializeAndDeserializeBinaryTree();
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		root.left = node2;
		root.right = node3;
		node3.left = node4;
		node3.right = node5;
		
		String ser = p.serialize(root);
		System.out.println(ser);
		TreeNode des = p.deserialize(ser);
		System.out.println(des);
	}

}
