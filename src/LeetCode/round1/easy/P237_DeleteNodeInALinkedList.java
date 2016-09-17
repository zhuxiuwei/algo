package LeetCode.round1.easy;

import LeetCode.round1.common.ListNode;

/**
 * 160915
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
 */
public class P237_DeleteNodeInALinkedList {
	
    public void deleteNode(ListNode node) {
    	while(node.next != null){
    		node.val = node.next.val;
    		if(node.next.next == null){
    			node.next = null;
    			break;
    		}
    		node = node.next;
    	}
    }
	
	public static void main(String[] args) {

	}

}
/*
 * 一次AC，思路挺有意思，需要开阔思路。 
 */
