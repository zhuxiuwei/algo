package LeetCode.round3;

import LeetCode.round1.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 240815 Medium
 * 和环形链表I的区别是，I返回boolean(有无形成环)，这里返回环的起始点。
 */
public class P142_环形链表II {

    /**
     * AC: 1ms Beats 28.00%, Memory 44.58MB Beats 32.14%
     * 参考solution:
     * https://leetcode.com/problems/linked-list-cycle-ii/solutions/1701128/c-java-python-slow-and-fast-image-explanation-beginner-friendly/?envType=study-plan-v2&envId=top-100-liked
     * 大体思路：
     * 1、也是快慢指针，先让快慢指针碰撞（如果有环）
     * 2、head和slow同时走，相遇时，就是环的起点。
     * 具体方案里使用了一个图示。记住就行了。
     */
    public ListNode detectCycle(ListNode head) {
        if(head != null && head.next != null){  //大于一个node
            ListNode slow = head, fast = head;
            while (true){
                if(fast == null)
                    return null;
                slow = slow.next;
                fast = fast.next;
                if(fast != null){
                    fast = fast.next;
                }else {
                    return null;
                }
                if(slow == fast){
                    break;
                }
            }
            while (slow != head){
                slow = slow.next;
                head = head.next;
            }
            return head;
        }
        return null;
    }

    /**
     * AC: 3ms Beats 13.99%, Memory 45.14MB Beats 5.79%
     * 最简单的用一个hashset记录访问过的节点。。。空间复杂度O(n)
     */
    public ListNode detectCycle_useHashSet(ListNode head) {
        if(head != null && head.next != null){
            Set<ListNode> visitedSet = new HashSet<>();
            while (head != null){
                if(visitedSet.contains(head)) {
                    return head;
                } else {
                    visitedSet.add(head);
                }
                head = head.next;
            }
        }
        return null;
    }
}
