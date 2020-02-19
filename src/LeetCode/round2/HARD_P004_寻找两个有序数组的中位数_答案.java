package LeetCode.round2;

/**
 200218 hard
 There are two sorted arrays nums1 and nums2 of size m and n respectively.
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
public class HARD_P004_寻找两个有序数组的中位数_答案 {

    /**
     * 抄的评论里最热门【Wait想念】的方法。
     * 那么回顾一下中位数的定义，如果某个有序数组长度是奇数，那么其中位数就是最中间那个，如果是偶数，那么就是最中间两个数字的平均值。
     * 这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。
     * 假如 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。

     这里我们需要定义一个函数来在两个有序数组中找到第K个元素，下面重点来看如何实现找到第K个元素。
     首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。
     然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。
     还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。
     难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，
     注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值。
     如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，所以必定至少会有一个数组是存在第K/2个数字的。
     最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，
     所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if((length1 + length2) % 2 == 0)
            return (findKth(nums1, 0, nums2, 0, (length1 + length2)/2)
                    + findKth(nums1, 0, nums2, 0, (length1 + length2)/2 + 1)) / 2.0;
        else
            return (findKth(nums1, 0, nums2, 0, (length1 + length2 + 1)/2));
    }
    /**
     * 2个有序数组中找第k大元素，k从1开始。
     * i: nums1的起始位置 j: nums2的起始位置。
     */
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        if( i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if(k == 1){     //k为第一个
            return Math.min(nums1[i], nums2[j]);
        }
        // 以下是普通情况
        // 思路其实和我之前的类似。从两个数组里找到中间元素，然后以他俩为中点，从两边往中间不断消除多余的点；每轮查找都会使范围缩小，直到剩余最后1/2个元素。
        // 就是写起来非常简洁。比如每次扔掉k/2个不好想。
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;    //max value的作用是哨兵。
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }

    public static void main(String[] args) {
        HARD_P004_寻找两个有序数组的中位数_答案 p = new HARD_P004_寻找两个有序数组的中位数_答案();
        System.out.println(p.findMedianSortedArrays(new int[]{3}, new int[]{-2, -1}));  //-1
        System.out.println(p.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));   //2.5
    }

    // 思路：从两个数组里找到中间元素，然后以他俩为中点，从两边往中间不断消除多余的点；
    // 每轮查找都会使范围缩小，直到剩余最后1/2个元素
//    public double findMedianSortedArrays_不会(int[] nums1, int[] nums2) {
//        int start1 = 0, end1 = nums1.length - 1;
//        int start2 = 0, end2 = nums2.length - 1;
//
//        //TODO: 补全
//
//        return 0;
//    }
}
