package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 200219 hard
 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

 示例:
 输入:
 [
   1->4->5,
   1->3->4,
   2->6
 ]
 输出: 1->1->2->3->4->4->5->6

 */
public class HARD_P023_合并K个排序链表 {

    /**
     利用PriorityQueue。
     7ms, beat 53.60%
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(ListNode node: lists){
            while (node != null){
                pq.add(node.val);
                node = node.next;
            }
        }
        ListNode head = null, node = null, pre = null;
        while (!pq.isEmpty()){
            int poped = pq.poll();
            if(head == null) {
                head = new ListNode(poped);
                pre = head;
                node = head;
            }else {
                node = new ListNode(poped);
                pre.next = node;
                pre = node;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        HARD_P023_合并K个排序链表 p = new HARD_P023_合并K个排序链表();
        ListNode[] lists  = new ListNode[2];
        lists[0] = BuildListFromArray.build(new int[]{1,2,5});
        lists[1] = BuildListFromArray.build(new int[]{1,2,7});
        ListNode m = p.mergeKLists(lists);
        m.printList();
    }
}
