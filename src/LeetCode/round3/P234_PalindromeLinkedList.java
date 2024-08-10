package LeetCode.round3;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 * 240810 Easy
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * A palindrome is a sequence that reads the same forward and backward.
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 10000].
 * 0 <= Node.val <= 9
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class P234_PalindromeLinkedList {

    /**
     * AC: 6ms Beats 54.46%, Memory 69.54MB Beats 8.02%
     * 思路：
     * 1、找到中间节点，分两半儿，变成俩个链表。如果是奇数个节点，扔掉中间节点。
     * 2、前一半儿reverse
     * 3、然后俩指针遍历，一一对比俩个链表，看是否值相同
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return false;
        if(head.next == null)
            return true;
        int len = len(head);

        //1、找到中间节点，分两半儿，变成俩个链表。如果是奇数个节点，扔掉中间节点。
        ListNode left = head, right = head;
        for (int i = 0; i < len/2 - 1; i++) {
            right = right.next;
        }
        ListNode tmpRight = null;
        if(len % 2 == 0){   //偶数
            tmpRight = right.next;
        }else { //奇数，需要略过最中间节点
            tmpRight = right.next.next;
            right.next.next = null;
        }
        right.next = null;
        right = tmpRight;

        // 2、前一半儿reverse
        left = reverse(left);

        // 3、然后俩指针遍历，一一对比俩个链表，看是否值相同
        while (left != null){
            int v1 = left.val, v2 = right.val;
            if(v1 != v2)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    //求长度
    private int len(ListNode node){
        int res = 0;
        while (node != null){
            res ++;
            node = node.next;
        }
        return res;
    }

    //反转链表
    private ListNode reverse(ListNode node){
        ListNode pres = null, cur = node, next = cur.next;
        while (cur != null){
            cur.next = pres;
            pres = cur;
            cur = next;
            if(cur != null) {
                next = cur.next;
            }
        }
        return pres;
    }

    public static void main(String[] args) {
        P234_PalindromeLinkedList p = new P234_PalindromeLinkedList();
        ListNode l1 = BuildListFromArray.build(new int[]{1,2,2,2,1});
        System.out.println(p.isPalindrome(l1));
    }
}
