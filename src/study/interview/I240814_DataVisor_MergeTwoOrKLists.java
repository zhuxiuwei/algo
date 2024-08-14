package study.interview;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 240814
 * 题目一：merge 2个有序递增的listNode
 * 题名二：上一个的扩展，merge k个有序递增的listNode
 * debug后，有一个Bug。
 */
public class I240814_DataVisor_MergeTwoOrKLists {

    /**
     * 合并2个有序递增的listNode
     */
    public ListNode mergeTwoList(ListNode node1, ListNode node2){
        if(node2 == null)
            return node1;
        if (node1 == null)
            return node2;
        ListNode res = node1.val <= node2.val ? node1: node2;
        ListNode n1Next = node1.next, n2Next = node2.next;
        while (node1 != null && node2 != null){
            if(node1.val <= node2.val){
                while (node1.val <= node2.val) {
                    if(n1Next != null && n1Next.val <= node2.val){
                        node1 = n1Next;
                        n1Next = n1Next.next;
                    }else {
                        node1.next = node2;
                        node1 = n1Next;
                        if (node1 != null) {
                            n1Next = node1.next;
                        }else {
                            break;  //！！！不能少，否则可能NPE
                        }
                    }
                }
            }else {
                while (node2.val <= node1.val) {
                    if(n2Next != null && n2Next.val <= node1.val){
                        node2 = n2Next;
                        n2Next = n2Next.next;
                    }else {
                        node2.next = node1;
                        node2 = n2Next;
                        if (node2 != null) {
                            n2Next = node2.next;
                        }else {
                            break;  //！！！不能少，否则可能NPE
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 合并K个有序递增的listNode
     * 思路：用一个优先级队列（堆），维护k个队列头里现在最小的元素。每次获取一个，然后从那个队列里拿出下一个元素，放入堆里。知道队列为空。
     */
    public ListNode mergeKLists(List<ListNode> heads) {
        PriorityQueue<ListNode> nodesQueue = new PriorityQueue<>();
        for (ListNode node: heads){
            if(node != null) {
                nodesQueue.offer(node);
            }
        }
        ListNode res = null, node = null, last = null;
        if(nodesQueue.isEmpty())
            return res;

        res = nodesQueue.peek();
        while (!nodesQueue.isEmpty()){
            node = nodesQueue.poll();
            if (last != null) {
                last.next = node;
            }
            last = node;
            if(node.next != null)
                nodesQueue.offer(node.next);
        }
        return res;
    }

    public static void main(String[] args) {
        I240814_DataVisor_MergeTwoOrKLists m = new I240814_DataVisor_MergeTwoOrKLists();

        //test merge two lists
        ListNode l1 = BuildListFromArray.build(new int[]{1,2,4});
        ListNode l2 = BuildListFromArray.build(new int[]{3,4});
        ListNode res = m.mergeTwoList(l1, l2);
        res.printList();    //1->2->3->4->4

        //test merge k lists
        l1 = BuildListFromArray.build(new int[]{1,2,4});
        l2 = BuildListFromArray.build(new int[]{3,4});
        ListNode l3 = BuildListFromArray.build(new int[]{0,4});
        ListNode l4 = null;
        ListNode l5 = BuildListFromArray.build(new int[]{});
        List<ListNode> heads = new ArrayList<>();
        heads.add(l1);
        heads.add(l2);
        heads.add(l3);
        heads.add(l4);
        heads.add(l5);
        ListNode listNode = m.mergeKLists(heads);
        listNode.printList();   //0->1->2->3->4->4->4
    }
}
