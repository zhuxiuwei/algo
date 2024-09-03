package LeetCode.round3;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.util.PriorityQueue;

/**
 * 240903 hard
 * ou are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * Example 2:
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10^4.
 */
public class HARD_P023_MergeKSortedLists {

    /**
     * AC: 5ms Beats 40.48%, Memory 44.09 MB Beats 92.94%
     * 顺利。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(ListNode node: lists){
            while (node != null){
                pq.offer(node.val);
                node = node.next;
            }
        }
        ListNode res = null, last = null;
        while (!pq.isEmpty()){
            ListNode cur = new ListNode(pq.poll());
            if(res == null){
                res = cur;
            }else {
                last.next = cur;
            }
            last = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        HARD_P023_MergeKSortedLists p = new HARD_P023_MergeKSortedLists();
        ListNode l1 = BuildListFromArray.build(new int[]{1,4,5});
        ListNode l2 = BuildListFromArray.build(new int[]{1,3,4});
        ListNode l3 = BuildListFromArray.build(new int[]{2,6});
        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        ListNode node = p.mergeKLists(lists);
        node.printList();   //1->1->2->3->4->4->5->6
    }
}
