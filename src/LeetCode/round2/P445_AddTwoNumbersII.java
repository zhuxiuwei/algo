package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.util.Stack;

/**
 * 200212 Medium
 You are given two non-empty linked lists representing two non-negative integers.
 The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 Example:
 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7
 */
public class P445_AddTwoNumbersII {
    /**
     * Runtime: 3 ms, faster than 73.64% of Java online submissions for Add Two Numbers II.
     Memory Usage: 41.8 MB, less than 73.53% of Java online submissions for Add Two Numbers II.

     循环里的进位问题处理，还是有点乱。 debug了一下。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        //push stacks
        Stack<ListNode> s1 = new Stack<>(), s2 = new Stack<>();
        while(l1 != null){
            s1.push(l1);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.push(l2);
            l2 = l2.next;
        }

        boolean jinwei = false;
        ListNode res = new ListNode(-1);;
        while (!s1.isEmpty() && !s2.isEmpty()){
            int n1 = s1.pop().val;
            int n2 = s2.pop().val;
            int sum = n1 + n2;
            if(jinwei)
                sum++;
            if(sum >= 10){
                sum -= 10;
                jinwei = true;
            }else
                jinwei = false;
            res.val = sum;
            ListNode next = new ListNode(-1);
            next.next = res;
            res = next;
        }

        Stack<ListNode> s = s1.isEmpty() ? s2: s1;
        while (!s.isEmpty()){
            int n = s.pop().val;
            if(jinwei)
                n ++;
            if(n >= 10){
                n -= 10;
                jinwei = true;
            }else
                jinwei = false;
            res.val = n;
            ListNode next = new ListNode(-1);
            next.next = res;
            res = next;
        }

        if(jinwei) {
            res.val = 1;
            return res;
        }else
            return res.next;
    }

    public static void main(String[] args) {
        P445_AddTwoNumbersII p = new P445_AddTwoNumbersII();
        ListNode l1 = BuildListFromArray.build(new int[]{9,6,3,1});
        ListNode l2 = BuildListFromArray.build(new int[]{3,6,9});
        ListNode res = p.addTwoNumbers(l1, l2);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }

        l1 = BuildListFromArray.build(new int[]{7,2,4,3});
        l2 = BuildListFromArray.build(new int[]{5,6,4});
        res = p.addTwoNumbers(l1, l2);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}
