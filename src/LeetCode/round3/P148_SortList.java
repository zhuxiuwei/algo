package LeetCode.round3;

import LeetCode.CommonUtils.BuildListFromArray;
import LeetCode.round1.common.ListNode;

import java.util.Arrays;

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
    public ListNode sortList(ListNode head) {

        return null;
    }

    //============================= merge sort归并排序练习 ============================= //
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
    //============================= merge sort归并排序练习 ============================= //


    public static void main(String[] args) {
        //测试本题目
        P148_SortList t = new P148_SortList();
//        ListNode l1 = BuildListFromArray.build(new int[]{4,2,1,3});
//        l1.printList(); //1 2 3 4
//
//        l1 = BuildListFromArray.build(new int[]{-1,5,3,4,0});
//        l1.printList(); //-1,0,3,4,5
//
//        l1 = BuildListFromArray.build(new int[]{});
//        l1.printList(); //[]

        //测试 merge sort
        int e[] = {8,7,2,5,9,8,5};
        t.mergeSort(e, 0, e.length - 1);
        System.out.println(Arrays.toString(e));

        int a[] = {8,7,2,5,9,8};
        t.mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

        int b[] = {8,7};
        t.mergeSort(b, 0, b.length - 1);
        System.out.println(Arrays.toString(b));

        int c[] = {8};
        t.mergeSort(c, 0, c.length - 1);
        System.out.println(Arrays.toString(c));

        int d[] = {};
        t.mergeSort(d, 0, d.length - 1);
        System.out.println(Arrays.toString(d));

        int f[] = {8,7,2,10, 100, 99, 80, 98};
        t.mergeSort(f, 0, f.length - 1);
        System.out.println(Arrays.toString(f));
    }
}
