package LeetCode.round1.easy;

import LeetCode.round1.common.ListNode;

/**
 * 160927 
Reverse a singly linked list.
A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class P206_ReverseLinkedList {

	/**
	 * Iteratively solution. One time AC. 0ms.
	 */
	public ListNode reverseList_iteratively(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode c = head.next, p = head, n = c;
		while(c.next != null){
			n = c.next;
			c.next = p;
			p = c;
			c = n;
		}
		head.next = null;
		c.next = p;
		return c;
    }
	
	/**
	 * Recursively solution. One time AC. 0ms.
	 */
	public ListNode reverseList_recursively(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode res = reverseList_recursively_helper(head)[1];
		head.next = null;	//handle the original head, which is tail now.
		return res;
    }
	private ListNode[] reverseList_recursively_helper(ListNode head) {
		if(head.next != null){
			ListNode[] res = reverseList_recursively_helper(head.next);
			res[0].next = head;
			res[0] = head;
			return res;
		}else{
			ListNode[] res = new ListNode[2];
			res[0] = head;
			res[1] = head;	//Array index 1 just store new List head, which is also the result of the overall function.
			return res;
		}
    }
}
