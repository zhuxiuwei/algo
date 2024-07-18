package LeetCode.round3;

import LeetCode.round1.common.ListNode;

/**
 * 240718
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 * Constraints:
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class P021_MergeTwoSortedLists {

    /**
     * AC: 0ms 100%, mem: 65%
     * ！！！！第一次思路完全是错的。第二轮一次改对了。虽然是easy，还是费了功夫。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        ListNode head = list1.val <= list2.val ? list1: list2;
        ListNode small = list1.val <= list2.val ? list1: list2;
        ListNode big = list1.val <= list2.val ? list2: list1;
        while (true){
            while (small.next != null && small.next.val <= big.val){
                small = small.next;
            }
            if(small.next == null)
                break;
            ListNode tmpSmallNext = small.next;
            small.next = big;
            small = big;
            big = tmpSmallNext;
        }
        small.next = big;
        return head;
    }

    /**
     * 以下思路是错的。
     * 用例：list1 = [5]， list2 = [1,2,4]，输出：[1,5]。期望：[1,2,4,5]
     */
    public ListNode mergeTwoLists_wrong(ListNode list1, ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        ListNode l1 = list1, l2 = list2, l1Next = list1, l2Next = list2;
        ListNode head = list1.val <= list2.val ? list1: list2;

        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                l1Next = l1.next;
                l1.next = l2;
                l1 = l1Next;
            }else {
                l2Next = l2.next;
                l2.next = l1;
                l2 = l2Next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        P021_MergeTwoSortedLists p = new P021_MergeTwoSortedLists();
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(1);
        p.mergeTwoLists(l1, l2).printList();
    }

}
