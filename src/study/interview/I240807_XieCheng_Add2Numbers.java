package study.interview;

import LeetCode.round1.common.ListNode;

/**
 * 携程，add two numbers，同leetcode 第二题 https://leetcode.com/problems/add-two-numbers/submissions/1347621456/
 */
public class I240807_XieCheng_Add2Numbers {

    /**
     *  leetcode AC:
     *  Runtime 2ms Beats 16.37%, Memory 43.98MB Beats 95.15%
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode head = null, pres = null, cur = null;
        boolean jinwei = false;

        while(l1 != null && l2 != null){
            int a = l1.val;
            int b = l2.val;
            int sum = a + b;
            if(jinwei){
                sum +=  1;
            }
            jinwei = sum > 9;
            sum = sum > 9 ? sum - 10: sum;
            cur = new ListNode(sum);
            if(head == null){
                head = cur;
                pres = cur;
            }else{
                pres.next = cur;
                pres = cur;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode notNullList = l1 == null ? l2: l1;

        while(notNullList != null){
            int sum =  notNullList.val;
            if(jinwei) {
                sum += 1;
            }
            jinwei = sum > 9;
            sum = sum > 9 ? sum - 10: sum;
            ListNode node = new ListNode(sum);
            cur.next = node;
            cur = node;
            notNullList = notNullList.next;
        }
        if(jinwei) {
            ListNode node = new ListNode(1);
            cur.next = node;
        }

        return head;
    }
}
