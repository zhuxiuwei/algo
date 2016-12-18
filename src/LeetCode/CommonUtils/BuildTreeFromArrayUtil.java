package LeetCode.CommonUtils;

import java.util.HashMap;
import java.util.Map;

import LeetCode.round1.common.TreeNode;
/**
 * 
 * @author Zhu Xiuwei
 * 161218
 */
public class BuildTreeFromArrayUtil {
	
	/**
	 * 
	 * 利用数组创建树。如[1,null,2]创建如下的树：
	 * 		  1
	 * 		 /
	 * 		2
	 * @param nodeVals
	 * @return
	 */
	public static TreeNode build(Integer[] nodeVals){
		if(nodeVals == null || nodeVals.length == 0)
			return null;
		if(nodeVals[0] == null)
			throw new BadTreeNodeFormatException("Root can not be null!");
		
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

}

//Define an unchecked Exception.
class BadTreeNodeFormatException extends RuntimeException {  
	private static final long serialVersionUID = 1761501230326431883L;
	public BadTreeNodeFormatException(String s) {  
        super(s);  
    }  
}  