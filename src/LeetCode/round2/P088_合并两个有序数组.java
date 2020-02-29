package LeetCode.round2;

import java.util.Arrays;

/**
 200229 Easy
 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

 说明:
 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

 示例:
 输入:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3
 输出: [1,2,2,3,5,6]
 */
public class P088_合并两个有序数组 {
    /**
     * 执行用时 : 0 ms , 在所有 Java 提交中击败了 100.00% 的用户。内存消耗 : 38.6 MB , 在所有 Java 提交中击败了 5.05% 的用户
     * 用了额外的O(m+n)空间。
     * ！！！ 有一个bug。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //!!!!!!!!!!!!!!!! 下面判断条件不对。如果num1为空，num2不是空，应该把num2赋值给num1.
//        if(nums1 == null || m == 0 || nums2 == null || n == 0)
//            return;
        int[] tmp = new int[m+n];
        int i = 0, j = 0, idx = 0;
        while (true){
            if(i < m && j < n) {
                if (nums1[i] <= nums2[j])
                    tmp[idx] = nums1[i ++];
                else
                    tmp[idx] = nums2[j++];
            } else if(i >= m && j < n){
                tmp[idx] = nums2[j++];
            } else if(i < m && j >= n){
                tmp[idx] = nums1[i++];
            }else
                break;
            idx ++;
        }
        //!!!!!!!!!!!!!!!!! 注意，必须nums1重新赋值一遍，不能简单写nums1 = tmp。这样最后nums1还是原始的值。
        for (int k = 0; k < tmp.length; k++)
            nums1[k] = tmp[k];
    }

    public static void main(String[] args) {
        P088_合并两个有序数组 p = new P088_合并两个有序数组();
        int[] nums1 = new int[]{1,2,3,0,0,0};
        p.merge(nums1, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
