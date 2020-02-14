package LeetCode.round2;

import LeetCode.round1.common.ListNode;

/**
 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 示例：
 给定一个链表: 1->2->3->4->5, 和 n = 2.

 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 说明：
 给定的 n 保证是有效的。

 进阶：
 你能尝试使用一趟扫描实现吗？
 */
public class P019_删除链表的倒数第N个节点 {

    /**
     * 一次通过，比较顺利
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        //快指针先走
        for (int i = 0; i < n; i++) {
            if (fast.next != null)
                fast = fast.next;
            else {  //删除的是head
                return head.next;
            }
        }

        //删除的不是head
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
