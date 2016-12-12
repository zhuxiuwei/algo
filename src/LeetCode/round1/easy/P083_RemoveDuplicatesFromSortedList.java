package LeetCode.round1.easy;

import java.util.HashSet;
import java.util.Set;

import LeetCode.round1.common.ListNode;

/**
 * 161212
Given a sorted linked list, delete all duplicates such that each element appear only once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 */
public class P083_RemoveDuplicatesFromSortedList {
	
	/**
	 * 1 time AC: 5ms, 2.31%.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null)
			return null;
		ListNode res = head;
		Set<Integer> dedupSet = new HashSet<Integer>();
		ListNode pre = head;
		dedupSet.add(head.val);
		head = head.next;
		while(head != null){
			if(!dedupSet.contains(head.val)){
				dedupSet.add(head.val);
				pre = head;
			}else{
				pre.next = head.next;
			}
			head = head.next;
		}
        return res;
    }
}
