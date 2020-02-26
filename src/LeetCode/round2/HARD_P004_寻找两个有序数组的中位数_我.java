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
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
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
