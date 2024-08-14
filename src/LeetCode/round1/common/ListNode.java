package LeetCode.round1.common;

public class ListNode implements Comparable<ListNode>{
	public int val;
	public ListNode next;
	public ListNode(int x) {
		val = x;
	}
	
	public void printList(){
		ListNode cur = this;
		while(cur != null){
			System.out.print(cur);
			if(cur.next != null)
				System.out.print("->");
			cur = cur.next;
		}
		System.out.println();
	}
	
	@Override
	public String toString(){
		return this.val + "";
	}

	@Override
	public int compareTo(ListNode o) {
		if(o == null)
			return 1;
		return this.val - o.val;
	}
}