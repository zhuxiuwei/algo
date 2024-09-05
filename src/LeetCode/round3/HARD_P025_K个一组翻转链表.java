package LeetCode.round3;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 240905 Hard
 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 k是一个正整数，它的值小于或等于链表的长度。
 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。

 示例 :
 给定这个链表：1->2->3->4->5
 当k= 2 时，应当返回: 2->1->4->3->5
 当k= 3 时，应当返回: 3->2->1->4->5

 说明 :
 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class HARD_P025_K个一组翻转链表 {

    /**
     * AC: Runtime 0ms Beats 100.00%, Memory 43.96 MB Beats 90.01%
     * 轻视了这个题。思路比较简单清晰，但是写的时候小问题还是挺多的，花了不少时间。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode res = null;
        int len = len(head);
        if(len > 0){
            int reversTimes = len / k;  //要翻转的组数
            ListNode nextHead = null, lastTail = null;
            for (int i = 0; i < reversTimes; i++) {
                //获取下一组的head
                nextHead = getNextGroupHeader(head, k);

                //翻转当前组，并获取当前组的head和tail
                ListNode[] revertedNodes = reversList(head, k);
                ListNode curHead = revertedNodes[0], curTail = revertedNodes[1];
                if(res == null){
                    res = curHead;  //更新res
                }

                /**
                 * 上一组的tail指向当前组的head。
                 * ！！！开始我以为有了【当前组的tail指向next head】，这一步就可以省了，实际省了会出错。
                 * 因为next head在下一组发生了反转后，next head实际就不是head了。逻辑上就错了。
                 */
                if(lastTail != null){
                    lastTail.next = curHead;
                }
                lastTail = curTail;

                //当前组的tail指向next head
                curTail.next = nextHead;

                head = nextHead;    //更新head
            }
        }
        return res;
    }

    //获取链表长度
    private int len(ListNode node){
        int res = 0;
        while (node != null){
            res ++;
            node = node.next;
        }
        return res;
    }

    /**
     * 获取下一组的header。
     * @param curNode  当前节点
     * @param k  k个后是下一组
     * @return  k个后下一组的header
     */
    private ListNode getNextGroupHeader(ListNode curNode, int k){
        ListNode res = curNode;
        for (int i = 0; i < k; i++) {
            res = res.next;
        }
        return res;
    }

    //翻转链表，从node开始，只翻转k个节点。
    //返回反转后的数组，元素0是head，元素1是tail。
    private ListNode[] reversList(ListNode node, int k){
        ListNode[] res = new ListNode[2];
        res[1] = node;
        ListNode pres = null, next = node.next;
        for (int i = 0; i < k; i++) {
            node.next = pres;
            pres = node;
            if(i == k - 1){
                res[0] = node;
            }
            node = next;
            if(next != null)    //！！！不能少，否则某些case可能NPE!!!
                next = next.next;
        }
        return res;
    }

    public static void main(String[] args) {
        HARD_P025_K个一组翻转链表 p = new HARD_P025_K个一组翻转链表();
        ListNode l1 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reverseKGroup(l1, 2).printList();   //2->1->4->3->5
        ListNode l2 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reverseKGroup(l2, 1).printList();     //1->2->3->4->5
        ListNode l3 = BuildListFromArray.build(new int[]{1,2,3,4,5});
        p.reverseKGroup(l3, 3).printList();     //3->2->1->4->5
        ListNode l4 = BuildListFromArray.build(new int[]{1,2});
        p.reverseKGroup(l4, 2).printList();     //2->1
        l4 = BuildListFromArray.build(new int[]{1,2,3});
        p.reverseKGroup(l4, 3).printList();     //3->2->1
    }
}
