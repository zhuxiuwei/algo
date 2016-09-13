package LeetCode.round1.medium;

import java.util.Random;

import LeetCode.round1.common.ListNode;

/**
 * 160913  典型的随机抽样问题。
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
Follow up: What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
 */
public class P382_LinkedListRandomNode {

	private ListNode head;
	
	/** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
	public P382_LinkedListRandomNode(ListNode head) {
		this.head = head;
	}

	/** Returns a random node's value. */
	public int getRandom() {
		//get length
		ListNode t = head;
		int length = 1;
		while(t.next != null){
			t = t.next;
			length ++;
		}
		
		t = head;
		for(int i = 0; i < new Random().nextInt(length); i++)
			t =t.next;
		return t.val;
	}
	
	/**
	 * Use O(1) space to store length, and use Java Random library, but failed to AC.
	 */
	public int getRandom_fail() {
		//get length
		ListNode t = head;
		int length = 1;
		while(t.next != null){
			t = t.next;
			length ++;
		}
		
		t = head;
		for(int i = 0; i < new Random().nextInt(length); i++)
			t =t.next;
		return t.val;
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		int end = 41;
		for (int i = 0; i < end; i++) {
			System.out.println(r.nextInt(end));
		}
			
	}
}
