package LeetCode.round3;

import LeetCode.round1.common.ListNode;

/**
 *240804
 * 编写一个程序，找到两个单链表相交的起始节点。
 *注意：
 如果两个链表没有交点，返回 null.
 在返回结果后，两个链表仍须保持原有的结构。
 可假定整个链表结构中没有循环。
 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class P160_IntersectionOfTwoLinkedLists {

    /**
     * AC: 1ms Beats 99.92%, Memory 49.00MB Beats 13.21%
     * 没啥难度顺利
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        int lengthA = listLength(headA);
        int lengthB = listLength(headB);
        //保证headA更长
        if(lengthB > lengthA){
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
        }

        int deltaLength = Math.abs(lengthA - lengthB);
        for (int i = 0; i < deltaLength; i++) {
            headA = headA.next;
        }

        while (headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int listLength(ListNode node){
        int res = 0;
        while (node != null){
            res ++;
            node = node.next;
        }
        return res;
    }

    public static void main(String[] args) {
        P160_IntersectionOfTwoLinkedLists p = new P160_IntersectionOfTwoLinkedLists();
        ListNode nodeA = new ListNode(4);
        ListNode nodeB = new ListNode(1);
        ListNode nodeC = new ListNode(5);
        ListNode nodeD = new ListNode(6);
        ListNode nodeE = new ListNode(1);
        ListNode nodeF = new ListNode(8);
        ListNode nodeG = new ListNode(4);
        ListNode nodeH = new ListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeF;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = nodeF;
        nodeF.next = nodeG;
        nodeG.next = nodeH;
        nodeH.next = null;

        System.out.println(p.getIntersectionNode(nodeA, nodeC));    //8
    }
}
