package LeetCode.round3;
/**
 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

 示例 1:
 输入: [3,2,1,5,6,4] 和 k = 2
 输出: 5

 示例 2:
 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 输出: 4

 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

 */
public class P215_数组中的第K个最大元素 {

    /**
     * AC: 2754ms 5.15%, mem: 84.32%
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int targetIdx = nums.length - k;    //数组如果有序后，第k大元素所在数字的下标位置。
        int left = 0, right = nums.length - 1;
        while(true) {
            int tmpIdx = quickSortDivider(nums, left, right);
            if (tmpIdx == targetIdx)
                return nums[targetIdx];
            else if (tmpIdx < targetIdx) { //右边找
                left = tmpIdx + 1;
            } else {    //左边找
                right = tmpIdx -1;
            }
        }
    }

    /**
     * ！！！ 写的还是比较费劲。主要是大于 小于 等号的边界条件爱出错！！！
     * 二分查找分拆步骤。
     * @param nums  原始数组
     * @param left  左下标
     * @param right 右下标
     * @return 最右边元素找到的合适的位置
     */
    private int quickSortDivider(int[] nums, int left, int right){
        int l = left, r = right;
        while (l != r) {
            while (l < r && nums[l] <= nums[right]) {
                l++;
            }
            while (l < r && nums[r] >= nums[right]) {
                r--;
            }
            if(l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        int temp = nums[l];
        nums[l] = nums[right];
        nums[right] = temp;
        return l;
    }

    public static void main(String[] args) {
        P215_数组中的第K个最大元素 p = new P215_数组中的第K个最大元素();
        System.out.println(p.findKthLargest(new int[]{3,2,1,5,6,4}, 2));    //5
        System.out.println(p.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));  //4
        System.out.println(p.findKthLargest(new int[]{1}, 1));  //1
        System.out.println(p.findKthLargest(new int[]{5,2,4,1,3,6,0}, 4));  //3
        System.out.println(p.findKthLargest(new int[]{5,1,5}, 3));  //1
    }
}
