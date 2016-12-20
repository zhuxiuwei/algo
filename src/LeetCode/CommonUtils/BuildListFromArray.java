package LeetCode.CommonUtils;

import LeetCode.round1.common.ListNode;
/**
 * 
 * @author Zhu Xiuwei
 * 161220
 */
public class BuildListFromArray {
	
	public static ListNode build(int[] nodeVals){
		if(nodeVals == null || nodeVals.length == 0)
			return null;
		ListNode head = new ListNode(nodeVals[0]);
		ListNode prev = head; 
		for (int i = 1; i < nodeVals.length; i++) {
			ListNode node = new ListNode(nodeVals[i]);
			prev.next = node;
			prev = node;
		}
		return head;
	}
}

