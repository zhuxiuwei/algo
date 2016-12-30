package LeetCode.round1.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import LeetCode.CommonUtils.BuildTreeFromArrayUtil;
import LeetCode.round1.common.TreeNode;

/**
 * 161230 
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class P449_SerializeAndDeserializeBST {

	/**
	 * AC: 40ms, 9.35%. 没用到二叉搜索树的特性。用的层次遍历。利用了我的工具类BuildTreeFromArrayUtil.java的思想。
	 * @param root
	 * @return
	 */
	// Encodes a tree to a single string.
    public String serialize(TreeNode root){
    	if(root == null)
    		return null;
    	StringBuilder sb = new StringBuilder();
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()){
			TreeNode t = q.poll();
			if(t != null)
				sb.append(t.val).append(",");
			else
				sb.append(t).append(",");
			if(t != null){
				q.offer(t.left);
				q.offer(t.right);
			}
		}
		String temp = sb.toString();
		return temp.substring(0, temp.length() - 1);
    }
	
    //Decodes your encoded data to tree.
    public TreeNode deserialize(String data){
    	if(data == null || data.isEmpty())
			return null;
    	
    	String[] source = data.split(",");
    	Integer[] nodeVals = new Integer[source.length];
    	for (int i = 0; i < nodeVals.length; i++) {
    		if(source[i].equals("null"))
    			nodeVals[i] = null;
    		else
    			nodeVals[i] = Integer.parseInt(source[i]);
    	}
    	
		TreeNode root = new TreeNode(nodeVals[0]);
		Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
		map.put(0, root);
		
		//fill map
		for (Integer i = 1; i < nodeVals.length; i++) {
			if(nodeVals[i] != null)
				map.put(i, new TreeNode(nodeVals[i]));
		}
		int nullCount = 0;
		//create tree
		for (Integer i = 0; i < nodeVals.length - 2; i++) {
			if(nodeVals[i] != null){
				TreeNode node = map.get(i);
				node.left = map.getOrDefault(2 * (i - nullCount) + 1, null);
				node.right = map.getOrDefault(2 * (i - nullCount) + 2, null);
			}else
				nullCount ++;
		}
		return root;
    }
    
	public static void main(String[] args) {
		P449_SerializeAndDeserializeBST p = new P449_SerializeAndDeserializeBST();
		String se = p.serialize(BuildTreeFromArrayUtil.build(new Integer[]{2,1,3}));
		System.out.println(se);
		TreeNode des = p.deserialize(se);
		System.out.println(des);
	}

}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));