package LeetCode.round2;

import LeetCode.round1.common.ListNode;

import java.util.List;

/**
 * 判断链表是否有环
 */
public class P141_环形链表 {
    public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;
        while (true){
            if(slow.next == null)
                return false;
            else
                slow = slow.next;
            if(fast.next == null) {
                return false;
            }else {
                fast = fast.next;
                if(fast.next == null)
                    return false;
                else
                    fast = fast.next;
            }
            if(slow == fast)
                return true;
        }
    }

    public static void main(String[] args) {
        P141_环形链表 p = new P141_环形链表();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;
        System.out.println(p.hasCycle(n1)); //true

        n1 = new ListNode(1);
        System.out.println(p.hasCycle(n1)); //false

        System.out.println(p.hasCycle(null)); //false

    }
}
