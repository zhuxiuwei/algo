package LeetCode.round1.medium;

import java.util.Random;

import LeetCode.round1.common.ListNode;

/**
 * 160913  用到了Reservoir sampling（蓄水池抽样）
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
	private ListNode cursor;	//used by getRandom_fail2()
	
	/** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
	public P382_LinkedListRandomNode(ListNode head) {
		this.head = head;
		this.cursor = head;
	}
	
	/** 
	 *  Use Reservoir sampling（蓄水池抽样）. No need to know the List length.
	 * */
	public int getRandom() {
		
		//below will fail to AC
//		ListNode n = head;
//		Random ran = new Random();
//		for (int i = 2; ; i++) {
//			int v = ran.nextInt(i);
//			if(v == (i - 1))
//				return n.val; 
//			else{
//				n = n.next;
//				if(n == null){	//back to head
//					n = head;
//					i = 2;
//				}
//			}
//		}
		
		//below can AC, from http://www.cnblogs.com/grandyang/p/5759926.html 
		Random ran = new Random();
		int res = head.val, i = 2;
        ListNode cur = head.next;
        while (cur != null) {
            int j = ran.nextInt(i);
            if (j == 0) res = cur.val;
            ++i;
            cur = cur.next;
        }
        return res;
	        
	}
	
	/**
	 * Get list length first.
	 * Use O(1) space to store length, and use Java Random library.
	 */
	public int getRandom_getLentghFirst() {
		//get length first
		ListNode t = head;
		int length = 1;
		while(t.next != null){
			t = t.next;
			length ++;
		}
		
		t = head;
		Random ran = new Random();
		int r = ran.nextInt(length);
		for(int i = 0; i < r; i++)
			t =t.next;
		return t.val;
	}

	/** 
	 *  Failed to AC. Maybe due to lack of randomness?
	 *  Use O(1) pointer space.
	 *  return 1,2，...., length -1, 1,2 ... , evenly. 
	 * */
	public int getRandom_fail2() {
		//get return value
		int ret = cursor.val;
		
		//move cursor to prepare for next random call.
		cursor = cursor.next;
		if(cursor == null) cursor = head;	//go back to head
		
		return ret;
	}
	
	
	public static void main(String[] args) {
		
	}
}
/**
 * 1. 首先用了查询length的算法。注意：
 * 	1.1 Random ran = new Random();一定先生成好ran对象，不能放在for里： for(int i = 0; i < new Random().nextInt(length); i++)，这种不能AC；
 * 	1.2  ran.nextInt(length); 要保证length >0，否则报错： java.lang.IllegalArgumentException: bound must be positive
 * 2. 正规解法是用“蓄水池抽样”算法。不知道这个算法，搜索到的。http://www.cnblogs.com/grandyang/p/5759926.html 
 *  自己写的几次都不能AC。还是不太明白原理。不想浪费时间了。
 */
