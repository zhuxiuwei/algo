package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.util.List;

/**
 200223 Hard
 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 k 是一个正整数，它的值小于或等于链表的长度。
 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 示例 :
 给定这个链表：1->2->3->4->5
 当 k = 2 时，应当返回: 2->1->4->3->5
 当 k = 3 时，应当返回: 3->2->1->4->5

 说明 :
 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class HARD_P025_K个一组翻转链表 {

    /**
     执行用时 : 1 ms , 在所有 Java 提交中击败了 75.12% 的用户， 内存消耗 : 40.9 MB , 在所有 Java 提交中击败了 5.01% 的用户
     总体还算顺利，！！！！！有2个边界值的bug。
     思路： 先计算链表长度length，然后根据length和k，计算要做几次链表翻转，并进行那么多次。每次翻转后，把相关指针返回。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        ListNode res = null;

        //先计算节点长度
        ListNode start = head;
        int length = 0;
        while (start != null){
            length ++;
            start = start.next;
        }

        //！！！！！！ 当k太大的时候，直接返回head。不能忘！！！！！！！！！！
        if(k > length)
            return head;

        ListNode lastTail = null;   //记录上次翻转后，数组的尾巴
        for (int i = 0; i < length/k; i++) {
            ListNode[] nodes = reverseHelper(head, k);
            if(i == 0)
                res = nodes[0];     //确定好返回值
            if(lastTail != null)
                lastTail.next = nodes[0];   //把上一组的尾巴，指向本组的head
            lastTail = nodes[1];
            head = nodes[2];

            if(i == length/k - 1) //最后一次循环，把尾巴后面的接上。
                nodes[1].next = nodes[2];
        }
        return res;
    }

    //翻转，最多走k步，返回一个数组，数组包含三个元素，依次是： 翻转后的新head，翻转后的新tail，数组翻转前最后一个node指向的next
    private ListNode[] reverseHelper(ListNode head, int step){
        ListNode[] res = new ListNode[3];
        res[1] = head;  //目前的head 一定是翻转后的tail
        if(step == 1){
            res[0] = head;
            res[2] = head.next;
        }else {
            ListNode pre = null;
            ListNode cur = head;
            ListNode next = cur.next;
            for (int i = 0; i < step; i++) {
                cur.next = pre;
                pre = cur;
                cur = next;
                if(next != null)    //！！！！！！！！！！ 这个条件不能少了，否则对于step正好等于数据长度时，下一步会null pointer。
                    next = next.next;
            }
            res[0] = pre;
            res[2] = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        HARD_P025_K个一组翻转链表 p = new HARD_P025_K个一组翻转链表();
        ListNode l1 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reverseKGroup(l1, 2).printList();   //2->1->4->3->5
        ListNode l2 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reverseKGroup(l2, 1).printList();     //1->2->3->4->5
        ListNode l3 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reverseKGroup(l3, 3).printList();     //3->2->1->4->5
        ListNode l4 = BuildListFromArray.build(new int[]{1,2});
        p.reverseKGroup(l4, 2).printList();     //2->1
        l4 = BuildListFromArray.build(new int[]{1,2,3});
        p.reverseKGroup(l4, 3).printList();     //3->2->1
    }
}
