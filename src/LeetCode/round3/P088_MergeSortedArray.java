package LeetCode.round3;

import java.util.Arrays;

/**
 * 240923 Easy
 * https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class P088_MergeSortedArray {
    /**
     * AC: Runtime 0 ms Beats 100.00%, Memory 41.75 MB Beats 95.76%
     * 开始我想搞简化写法，导致出了错。还是老老实实的写法吧。
     * 时间：o(m+n)。空间:o(m+n)。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m + n];
        int i = 0, j = 0, idx = 0;
        while (i < m && j < n){     //！！！开始我不想加23-31那几行，这里就用的||，导致各种数组越界
            if(nums1[i] <= nums2[j]){
                tmp[idx ++] = nums1[i];
                i ++;
            }else {
                tmp[idx ++] = nums2[j];
                j ++;
            }
        }

        //！！！还是老老实实的加上下面8行吧。
        while (i < m){
            tmp[idx ++] = nums1[i];
            i ++;
        }
        while (j < n){
            tmp[idx ++] = nums2[j];
            j ++;
        }

        for (int k = 0; k < nums1.length; k++) {
            nums1[k] = tmp[k];
        }
    }

    public static void main(String[] args) {
        P088_MergeSortedArray p = new P088_MergeSortedArray();
        int[] nums1 = new int[]{1,2,3,0,0,0};
        p.merge(nums1, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(nums1)); //[1, 2, 2, 3, 5, 6]

        nums1 = new int[]{1};
        p.merge(nums1, 1, new int[]{}, 0);
        System.out.println(Arrays.toString(nums1)); //[1]

        nums1 = new int[]{0};
        p.merge(nums1, 0, new int[]{2}, 1);
        System.out.println(Arrays.toString(nums1)); //[2]

        nums1 = new int[]{2,0};
        p.merge(nums1, 1, new int[]{1}, 1);
        System.out.println(Arrays.toString(nums1)); //[1, 2]
    }
}
