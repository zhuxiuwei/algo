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
public class HARD_P004_寻找两个有序数组的中位数_我 {

    /**
     * 抄的评论里最热门【Wait想念】的方法。然后自己实现的。
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/comments/
     * 其实本质和我思路一样：
     *  思路：从两个数组里找到中间元素，然后以他俩为中点，从两边往中间不断消除多余的点；
        每轮查找都会使范围缩小，直到剩余最后1/2个元素
     ！！！！！！！！！！！！！ 有一个bug。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if(totalLength % 2 == 0){
            return (findKth(nums1, 0, nums2, 0, totalLength/2) + findKth(nums1, 0, nums2, 0, totalLength/2 + 1))/2.0;
        }else
            return findKth(nums1, 0, nums2, 0, totalLength/2 + 1);
    }

    /*
     这里我们需要定义一个函数来在两个有序数组中找到第K个元素，下面重点来看如何实现找到第K个元素。首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。
     然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。
     还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。

     难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，
     注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值。
     如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，
     因为我们的K不是任意给的，而是给的m+n的中间值，所以必定至少会有一个数组是存在第K/2个数字的。最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，
     如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
     反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     */
    private int findKth(int[] num1, int start1, int[] num2, int start2, int k){
        if(start1 >= num1.length) return num2[start2 + k - 1];      //!!!! 注意bug： if条件是>=，不只是>，否则可能会在开k=1的时候发生ArrayOutOfIndex异常！
        if(start2 >= num2.length) return num1[start1 + k - 1];
        if(k == 1) return Math.min(num1[start1], num2[start2]);
        int mid1 = (start1 + k/2 - 1) < num1.length ? num1[start1 + k/2 - 1]: Integer.MAX_VALUE;    //MAX_VALUE是哨兵作用
        int mid2 = (start2 + k/2 - 1) < num2.length ? num2[start2 + k/2 - 1]: Integer.MAX_VALUE;
        if(mid1 < mid2){    //数组1往右收敛
            return findKth(num1, start1 + k/2, num2, start2, k - k/2);
        }else { //数组2往右收敛
            return findKth(num1, start1, num2, start2 + k/2, k - k/2);
        }
    }


    public static void main(String[] args) {
        HARD_P004_寻找两个有序数组的中位数_我 p = new HARD_P004_寻找两个有序数组的中位数_我();
        System.out.println(p.findMedianSortedArrays(new int[]{3}, new int[]{-2, -1}));  //-1
        System.out.println(p.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));   //2.5
    }

    //
//    public double findMedianSortedArrays_不会(int[] nums1, int[] nums2) {
//        int start1 = 0, end1 = nums1.length - 1;
//        int start2 = 0, end2 = nums2.length - 1;
//
//        //TODO: 补全
//
//        return 0;
//    }
}
