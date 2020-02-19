package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 *编写一个程序，找到两个单链表相交的起始节点。
 *注意：
 如果两个链表没有交点，返回 null.
 在返回结果后，两个链表仍须保持原有的结构。
 可假定整个链表结构中没有循环。
 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class P160_相交链表 {

    /**
     * 不难。
     * 在 #25 出现一次bug。注意需要重新获取shotLength、longLength，因为aL和bL的大小不一定。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        int aL = listLength(headA), bL = listLength(headB);
        ListNode shortList = aL <= bL ? headA: headB;
        ListNode longList = aL <= bL ? headB: headA;
        int shotLength = Math.min(aL, bL), longLength = Math.max(aL, bL);
        for (int i = 0; i < longLength - shotLength; i++) {
            longList = longList.next;
        }
        for (int i = 0; i < shotLength; i++) {
            if(shortList == longList)
                return shortList;
            shortList = shortList.next;
            longList = longList.next;
        }
        return null;
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
        ListNode head = BuildListFromArray.build(new int[]{1,2,3});
        P160_相交链表 p = new P160_相交链表();
        p.listLength(head);
        System.out.println(head);
    }
}
