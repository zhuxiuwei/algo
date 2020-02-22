package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 200222 Medium
 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 示例 1:
 给定链表 1->2->3->4, 重新排列为 1->4->2->3.

 示例 2:
 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class P143_重排链表 {

    /**
     * 执行用时 : 3 ms , 在所有 Java 提交中击败了 37.74% 的用户
     * 内存消耗 : 44 MB , 在所有 Java 提交中击败了 5.06% 的用户
     * 还算顺利。
     * 思路：把图画出来，观察到是一个螺旋形。然后观察到用一个双端队列即可以解决。
     */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;
        ArrayList<ListNode> nodes = new ArrayList<>();
        while (head != null){
            nodes.add(head);
            head = head.next;
        }
        int i = 0, j = nodes.size() - 1;
        while (i < j){
            ListNode start = nodes.get(i);
            ListNode end = nodes.get(j);
            end.next = start.next;
            start.next = end;
            i++;
            j--;
        }
        nodes.get(i).next = null;
    }

    public static void main(String[] args) {
        P143_重排链表 p = new P143_重排链表();
        ListNode n1 = BuildListFromArray.build(new int[]{1,2,3,4});
        p.reorderList(n1);
        n1.printList(); //1->4->2->3

        n1 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reorderList(n1);
        n1.printList(); //1->5->2->4->3

        n1 = BuildListFromArray.build(new int[]{1});
        p.reorderList(n1);
        n1.printList(); //1

        n1 = BuildListFromArray.build(new int[]{1, 2});
        p.reorderList(n1);
        n1.printList(); //1 -> 2

        n1 = BuildListFromArray.build(new int[]{});
        p.reorderList(n1);
    }
}
