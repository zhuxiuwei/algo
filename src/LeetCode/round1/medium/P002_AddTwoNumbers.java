package LeetCode.round1.medium;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 * 161220
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */
public class P002_AddTwoNumbers {

	/**
	 * AC: 60ms, 26.05%.
	 * 第一次理解错了，完全写的不对。
	 * 重新理解题目后，有一个bug。总体顺利。
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2){
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		int len1 = getLength(l1);
		int len2 = getLength(l2);
		int longLen = Math.max(len1, len2);
		int shortLen = Math.min(len1, len2);
		//for facility, make l1 the longer one.
		if(len1 < len2){
			ListNode temp = l1;
			l1 = l2;
			l2 = temp;
		}
		
		ListNode n1 = l1, n2 = l2;
		boolean jinwei = false;
		for (int i = 0; i < longLen; i++) {
			if(i < shortLen){
				int sum = jinwei? n1.val + n2.val + 1: n1.val + n2.val;
				if(sum > 9){
					n1.val = sum - 10;
					jinwei = true;
				}else{
					n1.val = sum;
					jinwei = false;
				}
				n2 = n2.next;
			}else{
				if(jinwei){
					int sum = n1.val + 1;
					if(sum > 9){
						n1.val = sum - 10;
						jinwei = true;
					}else{
						n1.val = sum;
						jinwei = false;
					}
				}
			}
			if(i < longLen - 1)	//！！！！！！注意bug：最后一次不做n1=n1.next，否则n1最后会是null，在68行会抛NullPointer。
				n1 = n1.next;
		}
		if(jinwei){
			ListNode last = new ListNode(1);
			n1.next = last;
		}
			
		return l1;
	}
	//calculate length of a list.
	private int getLength(ListNode head){
		int length = 0;
		while(head != null){
			length ++;
			head = head.next;
		}
		return length;
	}
	
	public static void main(String[] args) {
		P002_AddTwoNumbers p = new P002_AddTwoNumbers();
		ListNode l1 = BuildListFromArray.build(new int[]{5});
		ListNode l2 = BuildListFromArray.build(new int[]{9,9});
		p.addTwoNumbers(l1, l2);
	}

}
