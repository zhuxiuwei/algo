package LeetCode.round1.easy;

import LeetCode.round1.common.ListNode;

/**
 * 161127
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class P021_MergeTwoSortedLists {

	/**
	 * One bug.
	 * AC: 17ms, 26.5%。
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		ListNode head = l1.val <= l2.val ? l1: l2;	//!!!!!!!!!Note bug: head要指向l1 l2头元素小的一个。我开始写的head = l1. 在{l1=2, l2=1}的case下，会错误地只返回[1]，而不是期望的[1 2]，
		ListNode c1 = l1, c2 = l2, temp = c1;
		while(c1 != null && c2 != null){
			if(c1.val <= c2.val){
				while(c1 != null && c1.val <= c2.val){
					temp = c1;
					c1 = c1.next;
				}
				temp.next = c2;
			}else{
				if(c1 != null){
					while(c2 != null && c2.val <= c1.val){
						temp = c2;
						c2 = c2.next;
					}
					temp.next = c1;
				}
			}
		}
        return head;
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		ListNode l2 = new ListNode(1);
		new P021_MergeTwoSortedLists().mergeTwoLists(l1, l2).printList();
	}

}
