package LeetCode.round3;

import LeetCode.round1.common.ListNode;

/**
 240714 medium 链表
 给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。
 示例：
 给定一个链表: 1->2->3->4->5, 和 n = 2.
 当删除了倒数第二个节点后，链表变为 1->2->3->5.

 说明：
 给定的 n保证是有效的。

 进阶：
 你能尝试使用一趟扫描实现吗？
 */
public class P019_RemoveNthNodeFromEndOfList {
    /**
     * AC: 0ms 100%， mem: 63%
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        ListNode res = head, cur = head, prev = null;
        int length = getLength(head);
        int idx = length - n + 1;   //删除这个位置的元素
        for (int i = 0; i < idx - 1; i++) {
            prev = cur;
            cur = cur.next;
        }
        if(prev != null && cur != null){
            prev.next = cur.next;
            cur.next = null;
        }else if(prev == null && cur != null){  //删除的是头结点
            res = cur.next;
            cur.next = null;
        }
        return res;
    }

    private int getLength(ListNode listNode){
        int res = 0;
        while (listNode != null){
            res ++;
            listNode = listNode.next;
        }
        return res;
    }

}
