package study.interview;

import LeetCode.round1.common.ListNode;

/**
 * 链表环检测 I 和 II
 */
public class I240816_DataVisor_ListNodeDetectCycle {
    public boolean containsCycle(ListNode head){
        if(head != null && head.next != null){
            ListNode slow = head, fast = head;
            while (true){
                if(fast == null){
                    return false;
                }
                slow = slow.next;
                if(fast != null) {
                    fast = fast.next;
                    if(fast != null) {
                        fast = fast.next;
                    }
                }
                if(slow == fast)
                    return true;
            }
        }
        return false;
    }

    public ListNode containsCycleFirstNode(ListNode head){
        if(head != null && head.next != null){
            ListNode slow = head, fast = head;
            while (true){
                if(fast == null){
                    return null;
                }
                slow = slow.next;
                if(fast != null) {
                    fast = fast.next;
                    if(fast != null) {
                        fast = fast.next;
                    }
                }
                if(slow == fast)
                    break;
            }
            while (head != slow){
                head = head.next;
                slow = slow.next;
            }
            return head;
        }
        return null;
    }
}
