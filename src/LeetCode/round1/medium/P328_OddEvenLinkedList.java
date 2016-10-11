package LeetCode.round1.medium;

import LeetCode.round1.common.ListNode;

/**
 * 161011
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
 */
public class P328_OddEvenLinkedList {
	
	/**
	 * AC: 1ms, 3.5%...
	 * !!!!!!!!!!Note: 1 bug.
	 */
	public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		ListNode odd = head, preOdd = odd, even = head.next, evenHead = head.next;
		while(odd != null){
			//handle odd
			preOdd = odd;
			if(even != null){	//!!!!!!!!Note bug: must check if even is null here.
				odd.next = even.next;
				odd = even.next;
			}else
				odd = null;
			
			//handle even
			if(odd != null){
				even.next = odd.next;
				even = odd.next;
			}
		}
		preOdd.next = evenHead;
		return head;
    }
	
	public static void main(String[] args) {
		P328_OddEvenLinkedList p = new P328_OddEvenLinkedList();
		
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		l1.next = l2;
		
		ListNode l3 = new ListNode(3);
		l2.next = l3;

		l1.printList();
		
		p.oddEvenList(l1);
		l1.printList();
		
	}
}
