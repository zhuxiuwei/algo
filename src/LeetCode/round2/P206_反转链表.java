package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 *
 反转一个单链表。 easy. 200215

 示例:
 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL

 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class P206_反转链表 {
    /**
     * 指针操作方式。
     * 1次AC
     */
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode pre = null, cur = head, next = cur.next;
        while (next != null){
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        return cur;
    }

    /**
     * 递归方式。
     * ！！！ 有一个奇怪的java reference bug。 reverseList_recursive方法，我本来声明是 private ListNode reverseList_recursive(ListNode head, ListNode newHead){
     * 在递归开始newHead=null，在递归最底层给newHead赋值。但是最底层递归返回后，newHead又变成了null。不太能理解。
     * reverseList_recursive方法改成返回数组，把newHead放到数组里返回就好了。
     */
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode[] reversed = reverseList_recursive(head);
        return reversed[0];
    }

    //返回的length=2的数组，第一个元素是翻转后的head，第二个是翻转后的tail。
    private ListNode[] reverseList_recursive(ListNode head){
        if(head.next.next == null){
            ListNode newHead = head.next;
            head.next.next = head;
            head.next = null;
            return new ListNode[]{newHead, head};
        }else {
            ListNode[] reversed = reverseList_recursive(head.next);
            reversed[1].next = head;
            head.next = null;
            return new ListNode[]{reversed[0], head};
        }
    }

    public static void main(String[] args) {
        P206_反转链表 p = new P206_反转链表();
        ListNode head = BuildListFromArray.build(new int[]{1,2,3,4,5});
        ListNode newHead = p.reverseList2(head);
        newHead.printList();
    }
}
