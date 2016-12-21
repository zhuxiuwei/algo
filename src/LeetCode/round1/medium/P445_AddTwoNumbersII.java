package LeetCode.round1.medium;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 * 161221
You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
public class P445_AddTwoNumbersII {
	
	/**
	 * 和问题2的区别是，问题2是左对齐，进位往右；本题是右对齐，进位往左。所以如果把两个链表倒转，就和问题2一样了。
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1 == null && l2 == null)
			return null;
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		int len1 = getLength(l1);
		int len2 = getLength(l2);
		int longLen = Math.max(len1, len2);
		int shortLen = Math.min(len1, len2);
		int delta = longLen - shortLen;
		//for facility, make l1 the longer one.
		if(len1 < len2){
			ListNode temp = l1;
			l1 = l2;
			l2 = temp;
		}
		
		int lastPossibleJinWeiForLong = -1;
		ListNode n1 = l1, n2 = l2, prev = n1;
		for (int i = 0; i < longLen; i++) {
			if(i < delta){
				if(n1.val != 9)
					lastPossibleJinWeiForLong = i;
				prev = n1;
				n1 = n1.next;
			}else{
				
			}
		}
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
		P445_AddTwoNumbersII p = new P445_AddTwoNumbersII();
		ListNode l1 = BuildListFromArray.build(new int[]{9,6,3,1});
		ListNode l2 = BuildListFromArray.build(new int[]{3,6,9});
		p.addTwoNumbers(l1, l2);	//[1,0,0,0,0]
	}

}
