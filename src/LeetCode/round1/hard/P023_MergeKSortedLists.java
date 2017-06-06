package LeetCode.round1.hard;

import java.util.PriorityQueue;

/**
 * 170606 Hard
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * ！！！！！！！2个注意。
 */
public class P023_MergeKSortedLists {

	private ListNode merged = null;
	
	/**
	 * AC: 10.44%
	 * ！！！！！！！注意1： bug。！！！！！！！！！
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0)
			return null;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < lists.length; i++) {
			ListNode listNode = lists[i];
			while(listNode != null){
				pq.offer(listNode.val);
				listNode = listNode.next;
			}
		}
		if(pq.isEmpty())	//!!!!!!!!注意bug: 加上这个判断。否则下两行可能空指针。
			return null;
		ListNode res = new ListNode(pq.poll());
		ListNode node = res;
		while(!pq.isEmpty()){
			node.next = new ListNode(pq.poll());
			node = node.next;
		}
		merged = res;
		return res;
    }
	
	/**
	 * Amazon 面经 http://www.jiuzhang.com/qa/1009/
	 * ！！！！！！！注意2： bug： hasNext和next()方法，注意不要丢了头，或者尾巴元素。!!!!!!!!!!!!
	 */
	public P023_MergeKSortedLists(ListNode[] lists){
		mergeKLists(lists);
	}
	public boolean haseNext(){
		return merged != null;	//!!!!!!!!想好条件，和next配合好，否则可能丢了头/尾元素。
	}
	public ListNode next(){
		if(haseNext()){
			ListNode res = merged;
			merged = merged.next;
			return res;
		}
		else
			throw new java.util.NoSuchElementException();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ListNode l1a = new ListNode(3);
		ListNode l1b = new ListNode(5);
		l1a.next = l1b;
		ListNode l1c = new ListNode(12);
		l1b.next = l1c;
		
		ListNode l2a = new ListNode(3);
		ListNode l2b = new ListNode(7);
		l2a.next = l2b;
		ListNode l2c = new ListNode(9);
		l2b.next = l2c;
		ListNode l2d = new ListNode(9);
		l2c.next = l2d;
		
		ListNode l3a = new ListNode(1);
		ListNode l3b = new ListNode(37);
		l3a.next = l3b;
		
		ListNode[] lists = new ListNode[3];
		lists[0] = l1a;
		lists[1] = l2a;
		lists[2] = l3a;
		
		P023_MergeKSortedLists p = new P023_MergeKSortedLists(lists);
		while(p.haseNext()){
			System.out.print(p.next().val + " ");	//1 3 3 5 7 9 9 12 37
		}
		Thread.sleep(100);
//		p.next();	//java.util.NoSuchElementException
	}

}

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { val = x; }
}