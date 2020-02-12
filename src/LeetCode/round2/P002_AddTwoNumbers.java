package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 *You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Example:
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */
public class P002_AddTwoNumbers {

    /**
     * Runtime: 2 ms, faster than 77.19% of Java online submissions for Add Two Numbers.
     Memory Usage: 41.7 MB, less than 90.91% of Java online submissions for Add Two Numbers.
     总体还算顺利，在ListNode shaobing、ListNode res的关系(29-30行)、保证返回第一个节点这儿花了点时间。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        boolean jinwei = false;
        ListNode res = new ListNode(-1);
        ListNode shaobing = res;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val;
            if(jinwei)
                sum ++;
            if(sum >= 10){
                sum -= 10;
                jinwei = true;
            }else
                jinwei = false;
            ListNode next = new ListNode(sum);
            res.next = next;
            res = next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode node = l1 == null? l2: l1;
        while (node != null){
            int v = node.val;
            if(jinwei)
                v ++;
            if(v >= 10){
                v -= 10;
                jinwei = true;
            }else
                jinwei = false;
            ListNode next = new ListNode(v);
            res.next = next;
            res = next;
            node = node.next;
        }

        if(jinwei) {
            ListNode next = new ListNode(1);
            res.next = next;
        }

        return shaobing.next;
    }

    public static void main(String[] args) {
        P002_AddTwoNumbers p = new P002_AddTwoNumbers();
        ListNode l1 = BuildListFromArray.build(new int[]{2,4,3});
        ListNode l2 = BuildListFromArray.build(new int[]{5,6,4});
        ListNode res = p.addTwoNumbers(l1, l2);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}
