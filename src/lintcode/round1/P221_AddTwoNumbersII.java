package lintcode.round1;

import java.util.Stack;

/**
 * 170613 Medium
You have two numbers represented by a linked list, where each node contains a single digit.
The digits are stored in forward order, such that the 1's digit is at the head of the list. 
Write a function that adds the two numbers and returns the sum as a linked list.
Have you met this question in a real interview? Yes
Example
Given 6->1->7 + 2->9->5. That is, 617 + 295.
Return 9->1->2. That is, 912.
 */
public class P221_AddTwoNumbersII {

	/**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
    	if(l1 == null)
    		return l2;
    	if(l2 == null)
    		return l1;
    	
    	boolean jinwei = false;
    	Stack<Integer> s1 = new Stack<Integer>();
    	Stack<Integer> s2 = new Stack<Integer>();
    	while(l1 != null){
    		s1.push(l1.val);
    		l1 = l1.next;
    	}
    	while(l2 != null){
    		s2.push(l2.val);
    		l2 = l2.next;
    	}
    	
    	Stack<Integer> res = new Stack<Integer>();
    	while(!s1.isEmpty() || !s2.isEmpty()){
    		int n1 = s1.isEmpty() ? 0 : s1.pop();
    		int n2 = s2.isEmpty() ? 0 : s2.pop();
    		int sum = n1 + n2;
    		if(jinwei)
    			sum ++;
    		if(sum >= 10){
    			sum -= 10;
    			jinwei = true;
    		}else
    			jinwei = false;
    		res.push(sum);
    	}
    	
    	ListNode root = null;
    	if(jinwei)
    		root = new ListNode(1);
    	else{
    		root = new ListNode(res.pop());
    	}
    	ListNode cur = root;
    	while(!res.isEmpty()){
    		ListNode node = new ListNode(res.pop());
    		cur.next = node;
    		cur = node;
    	}
    	return root;
    }  
	
	public static void main(String[] args) {
		ListNode l6 = new ListNode(6);
		ListNode l1 = new ListNode(1);
		l6.next = l1;
		ListNode l7 = new ListNode(7);
		l1.next = l7;
		
		ListNode l2 = new ListNode(2);
		ListNode l9 = new ListNode(9);
		l2.next = l9;
		ListNode l5 = new ListNode(5);
		l9.next = l5;
		
		P221_AddTwoNumbersII p = new P221_AddTwoNumbersII();
		ListNode r = p.addLists2(l6, l2);
		System.out.println(r);
	}

}
