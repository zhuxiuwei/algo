package LeetCode.round3;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 * 240715
 * 交换链表中俩俩节点的位置。
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying
 * the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */
public class P024_SwapNodesInPairs {
    /**
     * AC: 0ms 100%, mem 29%
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)   //空链表，或者只有一个元素
            return head;
        ListNode res = head.next;

        ListNode curNode = head, curGroup = curNode, nextGroup = null, preGroup = null;
        while (true){
            if(curNode == null || curNode.next == null)
                break;

            if(preGroup != null){
                preGroup.next = curGroup.next;  //！！！这里容易出错
            }

            nextGroup = curNode.next.next;

            //交换当前俩节点
            curNode.next.next = curNode;
            curNode.next = nextGroup;
            curNode = nextGroup;

            //处理preGroup和curGroup
            preGroup = curGroup;
            curGroup = nextGroup;

        }
        return res;
    }

    public static void main(String[] args) {
        P024_SwapNodesInPairs p = new P024_SwapNodesInPairs();
        ListNode l1 = BuildListFromArray.build(new int[]{1});
        l1 = p.swapPairs(l1);
        l1.printList(); //1

        l1 = BuildListFromArray.build(new int[]{1,2});
        l1 = p.swapPairs(l1);
        l1.printList(); //2,1

        l1 = BuildListFromArray.build(new int[]{1,2,3});
        l1 = p.swapPairs(l1);
        l1.printList(); //2,1,3

        l1 = BuildListFromArray.build(new int[]{1,2,3,4});
        l1 = p.swapPairs(l1);
        l1.printList(); //2,1,4,3

        l1 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        l1 = p.swapPairs(l1);
        l1.printList(); //2,1,4,3,5
    }
}
