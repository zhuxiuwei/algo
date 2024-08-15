package LeetCode.round3;

import LeetCode.round1.common.ListNode;

/**
 * 240815 Easy
 */
public class P141_环形链表 {
    public boolean hasCycle(ListNode head) {
        if(head != null && head.next != null){  //大于一个node
            ListNode slow = head;
            ListNode fast = head;
            while (true){
                if(fast == null)
                    return false;   //！！！这个不能丢，否则 1 -> 2无环场景，在「fast = fast.next;」一行会NPE
                slow = slow.next;
                fast = fast.next;
                if(fast != null){
                    fast = fast.next;
                }else {
                    return false;
                }
                if(slow == fast){
                    return true;
                }
            }
        }
        return false;
    }
}
