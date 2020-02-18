package LeetCode.round2;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 * 200215 Easy
 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 示例：
 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4
 */
public class P021_合并两个有序链表 {

    /**
     * 不是特别顺利。 有一个bug，debug着出来的。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode small = l1.val <= l2.val ? l1: l2;
        ListNode big = l1.val <= l2.val ? l2: l1;
        ListNode res = small;
        while (true){
            ListNode smallTmp = small.val <= big.val ? small: big;  //！！！！ bug: 注意small和big指针交换时，要引入2个临时变量。
            ListNode bigTmp = small.val <= big.val ? big: small;
            small = smallTmp;
            big = bigTmp;
            while (small.val <= big.val & small.next != null){
                if(small.next.val <= big.val)
                    small = small.next;
                else
                    break;
            }
            ListNode tmp = small.next;
            small.next = big;
            small = tmp;
            if(small == null)
                break;
        }
        return res;
    }

    /**
     * 错误。
     * 思路就错了。在 {1,3,4} {1,2,4} 下，错误返回1->1->3->4。 元素2 丢了。
     */
    public ListNode mergeTwoLists_wrong(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode small = l1.val <= l2.val ? l1: l2;
        ListNode big = l1.val <= l2.val ? l2: l1;
        ListNode res = small;
        while (small != null && big != null){
            ListNode smallTmp = small.val <= big.val ? small: big, bigTmp = small.val <= big.val ? big: small;
            small = smallTmp;
            big = bigTmp;
            ListNode temp = small.next;
            small.next = big;
            small = temp;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode l1 = BuildListFromArray.build(new int[]{1,3,4});
        ListNode l2 = BuildListFromArray.build(new int[]{1,2,4});
        ListNode res = new P021_合并两个有序链表().mergeTwoLists(l1, l2);
        res.printList();
    }

}
