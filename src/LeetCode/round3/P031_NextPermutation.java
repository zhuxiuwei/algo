package LeetCode.round3;

import java.util.Arrays;

/**
 * 240725
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * The replacement must be in place and use only constant extra memory.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 * Example 2:
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 *
 * Example 3:
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class P031_NextPermutation {

    /**
     * AC： 0ms 100%, mem 91%
     * 正确思路：
     * 1、整体来说，应该尽量找最右侧的，可以交换的元素。所以找交换位置的顺序应该是从倒数第二个数字往前看，每个数字又从最后一个为位置往前看直到找到比当前数字更大的，有就交换
     * 2、找到后，交换两个位置的元素
     * 3、前一个位置元素后面数组中的所有元素，要进行排序
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        for (int i = nums.length  - 2; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if(nums[i] < nums[j]){  //找到可以交换的位置，退出
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    //剩余的部分要重新排序
                    quickSort(nums, i + 1, nums.length  - 1);
                    System.out.println(Arrays.toString(nums));
                    return;
                }
            }
        }
        //前面没找到交换元素，需要重新排列
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    //没写，直接抄以前的。
    private void quickSort(int[] a, int start, int end){
        if(a == null || a.length == 0 || start > end)
            return;

        int anchor = a[start];

        int i = start;
        int j = end;
        while(i < j){

            //left to right
            while(i < j && a[j] >= anchor)
                j--;
            a[i] = a[j];

            while( i < j && a[i] <= anchor)
                i++;
            a[j] = a[i];
        }
        a[i] = anchor;

        if(start < i - 1)	//小于等于也行
            quickSort(a, start, i-1);

        if(end > i + 1)		//大于等于也行
            quickSort(a, i + 1, end);
    }

    /**
     * 思路还是不对。。。思路：
     * 1、从最后一个元素开始，依次往前找到它第一个可以交换的点。交换的点，是找到第一个比它小的位置。  --- 思路问题出在这里
     * 2、找到后，交换两个位置的元素
     * 3、前一个位置元素后面数组中的所有元素，要进行排序
     *
     * p.nextPermutation(new int[]{4,2,0,2,3,2,0}); // [4,2,0,3,0,2,2]  -- 这里会输出[4,2,2,0,0,2,3]
     * 因为正确思路应该是交换idx=4的'3'和idx=3的'2'。图示：{4,2,0,|2|,|3|,2,0}
     * 而错误思路下交换的是idx=5的'2'和idx=2的'0'。图示：{4,2,|0|,2,3,|2|,0}
     */
    public void nextPermutation_wrong2(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        for (int i = nums.length  - 1; i >= 0 ; i--) {
            for (int j = i - 1; j >= 0 ; j--) {
                if(nums[i] > nums[j]){  //找到可以交换的位置，退出
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    //剩余的部分要重新排序
                    quickSort(nums, j + 1, nums.length  - 1);
                    System.out.println(Arrays.toString(nums));
                    return;
                }
            }
        }
        //前面没找到交换元素，需要重新排列
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 思路不对。
     * p.nextPermutation(new int[]{1,3,2}); //2 1 3  -- 在这里会错误输出2 3 1
     */
    public void nextPermutation_wrong(int[] nums) {
        for (int i = nums.length - 1; i >= 0 ; i--) {
            for (int j = i - 1; j >= 0 ; j--) {
                if(nums[i] > nums[j]){  //找到可以交换的位置，退出
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    System.out.println(Arrays.toString(nums));
                    return;
                }
            }
        }
        //前面没找到交换元素，需要重新排列
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        P031_NextPermutation p = new P031_NextPermutation();
        p.nextPermutation(new int[]{1,2,3}); //1 3 2
        p.nextPermutation(new int[]{1,3,2}); //2 1 3  -- nextPermutation_wrong在这里会错误输出2 3 1
        p.nextPermutation(new int[]{3,2,1}); // 1 2 3
        p.nextPermutation(new int[]{1,1,5}); // 1 5 1
        p.nextPermutation(new int[]{1,8,5,3,2}); // 2, 1, 3, 5, 8
        p.nextPermutation(new int[]{1,8,5,3,2,7}); // 1,8,5,3,7,2
        p.nextPermutation(new int[]{5,4,7,5,3,2}); // [5,5,2,3,4,7]
        p.nextPermutation(new int[]{4,2,0,2,3,2,0}); // [4,2,0,3,0,2,2]
    }
}
