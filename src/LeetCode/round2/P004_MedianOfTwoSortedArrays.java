package LeetCode.round2;

/**
 *There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 You may assume nums1 and nums2 cannot be both empty.

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]
 The median is 2.0

 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 The median is (2 + 3)/2 = 2.5
 */
public class P004_MedianOfTwoSortedArrays {
    // 思路：从两个数组里找到中间元素，然后以他俩为中点，从两边往中间不断消除多余的点；
    // 每轮查找都会使范围缩小，直到剩余最后1/2个元素
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1 = 0, end1 = nums1.length - 1;
        int start2 = 0, end2 = nums2.length - 1;

        //TODO: 补全

        return 0;
    }
}
