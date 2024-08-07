package LeetCode.round3;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

/**
 * 240807 medium
 *
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 5*10^4].
 * -10^5 <= Node.val <= 10^5
 */
public class P148_SortList {

    /**
     * AC:21 ms Beats 6.54%, Memory 55.93 MB Beats 74.98%
     * 思路倒是不是很难，写起来费老鼻子劲了。
     * 完全一步步debug写的。写代码花了很长时间(大半天)，而且代码很长。
     *
     * 大致思路：每一轮都是2^n(n=轮次)个节点排好序。
     * 即，先每2、2、2、2、2...、2个节点排好序
     * 然后每4、4、4、...4个节点排好序
     * 直到整个链表排好序。
     * 每次排序，都是经典merge sort的思想。区别就是底层储存不是数组而是链表，指针操作比较繁琐，很易错。
     *
     * 为了方便处理，先将数组长度扩展到了2^n，新增临时节点用int.maxvalue补齐（类似哨兵思路）。排好序了再把临时节点从数组里删掉。
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        int range = 2, length = len(head);

        //扩展长度为2^n，方便处理
        expendLength(head);

        ListNode lastTail = null, left = head, right = head, nextHead = head, res = head;
        while (true){
            boolean reachEnd = false;   //是否已经处理到链表尾
            //right找到合适位置
            for (int i = 0; i < range / 2; i++) {
                if(right != null) {
                    right = right.next;
                }else {
                    reachEnd = true;
                }
            }
            //nextHead找到合适位置（也就是当前处理组到下一组的边界）
            for (int i = 0; i < range; i++) {
                if(nextHead != null) {
                    nextHead = nextHead.next;
                }else {
                    reachEnd = true;
                }
            }
            if(!reachEnd) {
                //进行merge sort
                ListNode[] sortedNodes = mergeSortList(left, right, nextHead);

                ListNode sortedNodesHead = sortedNodes[0];  //sort后，区间里的head
                ListNode sortedNodesTail = sortedNodes[1];  //sort后，区间里的tail
                if (lastTail != null) {
                    lastTail.next = sortedNodesHead;    //让上一个区间的尾巴，指向当前区间的头
                } else {  //对于第一组，要把res(也就是head)指向第一组的头
                    if (res.val > sortedNodesHead.val)
                        res = sortedNodesHead;
                }
                lastTail = sortedNodesTail;     //重新设置新的lastTail为当前组的tail，供下一组使用
                left = lastTail.next;
                right = lastTail.next;
                nextHead = lastTail.next;
            }else {   //本轮处理完成，开始下一轮
                range *= 2;
                lastTail = null;
                left = res;
                right = res;
                nextHead = res;
            }
            if(range >= length * 2) {   //全部轮次完成，整个链表已经有序
                break;
            }
        }
        //除掉多余的长度
        subList(res, length);
        return res;
    }

    /**
     * @param left  左边部分开始节点
     * @param right 右边部分开始节点
     * @param rightBorder 下一个区间的头
     * @return
     */
    private ListNode[] mergeSortList(ListNode left, ListNode right, ListNode rightBorder){
        ListNode leftBorder = right;
        ListNode smallestNode = left.val <= right.val ? left: right;
        ListNode largestNode = null;
        //！！！下面还是挺容易出错的。左右两部分比大小，同时调整指针。
        while (left != leftBorder && right != rightBorder){
            if(left.val <= right.val){
                if(left.next != leftBorder && left.next.val <= right.val) {
                    left = left.next;
                }else {
                    ListNode nextLeft = left.next;
                    left.next = right;
                    left = nextLeft;
                }
            }else {
                if(right.next != rightBorder && right.next.val < left.val) { //注意右边<不包含=。非常容易出错的细节。
                    right = right.next;
                }else {
                    ListNode nextRight = right.next;
                    right.next = left;
                    right = nextRight;
                }
            }
        }
        //处理完长的剩余的list。主要是为了找到largestNode。（largestNode一定是更长的list里的最后一个）
        while(left != leftBorder){
            largestNode = left;
            left = left.next;
        }
        while(right != rightBorder){
            largestNode = right;
            right = right.next;
        }
        largestNode.next = rightBorder;
        ListNode[] res =new ListNode[2];
        res[0] = smallestNode;
        res[1] = largestNode;
        return res;
    }

    private int len(ListNode head){
        int res = 0;
        while (head != null){
            res ++;
            head = head.next;
        }
        return res;
    }

    /**
     * 将数组扩展为2^n的长度。
     * 懒人方法，类似mergeSort加哨兵，方便排序后续处理。
     * @param head
     */
    private int expendLength(ListNode head){
        int len = len(head), step = 2;
        while (step < len){
            step *= 2;
        }
        int delta = step - len;
        while (head.next != null){
            head = head.next;
        }
        for (int i = 0; i < delta; i++) {
            ListNode tmpNode = new ListNode(Integer.MAX_VALUE);
            head.next = tmpNode;
            head = head.next;
        }
        return delta;   //补齐的长度
    }

    /**
     * 将链表处理为长度为subLength的子链表。
     * @param head 原始链表的head
     * @param subLength 子链表的长度
     */
    private void subList(ListNode head, int subLength){
        for (int i = 0; i < subLength - 1; i++) {
            head = head.next;
        }
        head.next = null;
    }


    //============================= 经典merge sort归并排序练习 ============================= //
    private void mergeSort(int[] nums, int start, int end){
        if(nums == null || nums.length == 0)
            return;
        int mid = (start + end)/2;
        if(start < mid){
            mergeSort(nums, start, mid);
        }
        if(mid < end){
            mergeSort(nums, mid + 1, end);
        }

        doMerge(nums, start, mid, end);
    }
    private void doMerge(int[] nums, int start, int mid, int end){
        if(start == end)
            return;

        //初始化左右数组
        int[] arrLeft = new int[mid - start + 2];
        int[] arrRight = new int[end - mid + 1];
        for (int i = 0; i < arrLeft.length - 1; i++) {
            arrLeft[i] = nums[start + i];
        }
        arrLeft[arrLeft.length - 1] = Integer.MAX_VALUE;
        for (int i = 0; i < arrRight.length - 1; i++) {
            arrRight[i] = nums[mid + i + 1];
        }
        arrRight[arrRight.length - 1] = Integer.MAX_VALUE;

        //左右数组归并
        int idxLeft = 0, idxRight = 0;
        for (int i = start; i <= end ; i++) {
            if(arrLeft[idxLeft] <= arrRight[idxRight]){
                nums[i] = arrLeft[idxLeft ++];
            }else {
                nums[i] = arrRight[idxRight ++];
            }
        }
    }
    //============================= 经典merge sort归并排序练习 ============================= //


    public static void main(String[] args) {
        //测试本题目
        P148_SortList t = new P148_SortList();
        ListNode l1 = BuildListFromArray.build(new int[]{4,2,3,1,6,-2});
        l1 = t.sortList(l1);
        l1.printList(); //-2->1->2->3->4->6

        l1 = BuildListFromArray.build(new int[]{4,2,3,1});
        l1 = t.sortList(l1);
        l1.printList(); //1 2 3 4

        l1 = BuildListFromArray.build(new int[]{-1,5,3,4,2,0,2,-2,9,7,6,3,4,2});
        l1 = t.sortList(l1);
        l1.printList(); //-2->-1->0->2->2->2->3->3->4->4->5->6->7->9

        //测试经典merge sort
//        int e[] = {8,7,2,5,9,8,5};
//        t.mergeSort(e, 0, e.length - 1);
//        System.out.println(Arrays.toString(e));
//
//        int a[] = {8,7,2,5,9,8};
//        t.mergeSort(a, 0, a.length - 1);
//        System.out.println(Arrays.toString(a));
//
//        int b[] = {8,7};
//        t.mergeSort(b, 0, b.length - 1);
//        System.out.println(Arrays.toString(b));
//
//        int c[] = {8};
//        t.mergeSort(c, 0, c.length - 1);
//        System.out.println(Arrays.toString(c));
//
//        int d[] = {};
//        t.mergeSort(d, 0, d.length - 1);
//        System.out.println(Arrays.toString(d));
//
//        int f[] = {8,7,2,10, 100, 99, 80, 98};
//        t.mergeSort(f, 0, f.length - 1);
//        System.out.println(Arrays.toString(f));
    }
}
