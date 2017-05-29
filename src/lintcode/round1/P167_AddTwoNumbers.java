package lintcode.round1;
/**
 * @author zhu xiuwei 
 * 170529 easy http://www.lintcode.com/en/problem/add-two-numbers/
You have two numbers represented by a linked list, where each node contains a single digit. 
The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 7->1->6 + 5->9->2. That is, 617 + 295. Return 2->1->9. That is 912.
Given 3->1->5 and 5->9->2, return 8->0->8.
 */
public class P167_AddTwoNumbers {

	/**
	 * 很简单的题，结果弄错了好几次。状态不太好。
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
    	if(l1 == null && l2 == null)
    		return null;
    	if(l1 == null && l2 != null)
    		return l2;
    	if(l1 != null && l2 == null)
    		return l1;
    	ListNode res =  new ListNode((l1.val + l2.val) % 10), last = res;
    	boolean jinwei = l1.val + l2.val >= 10;
    	l1 = l1.next; l2 = l2.next;	//skip head
    	while(l1 != null || l2 != null){	//！！！！！！！！！！注意1. 和注意2要结合。不是判断.next是否为空
    		int l1val = l1 == null ? 0: l1.val;	
    		int l2val = l2 == null ? 0: l2.val;
    		int val = l1val + l2val;
    		if(jinwei) val ++;
    		if(val >= 10){	//!!!注意3： >=，不是>。低级错误。
    			val -= 10;
        		jinwei = true;
        	}else
        		jinwei = false;
    		ListNode node = new ListNode(val);
    		last.next = node;
    		last = node;
        	if(l1 != null)	l1 = l1.next;	//！！！！！！！！！！注意2： 注意判断空指针。
        	if(l2 != null)	l2 = l2.next;
    	}
    	if(jinwei){	//！！！！注意4： 不能忘记最后可能还需要进位
    		ListNode highest =  new ListNode(1);
    		last.next = highest;
    	}
    	return res;
    }
	
	public static void main(String[] args) {

	}

}
