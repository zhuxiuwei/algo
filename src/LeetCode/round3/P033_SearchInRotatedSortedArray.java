package LeetCode.round3;

/**
 * @240706
 * # Binary Search - medium - 20做过
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that
 * the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 * Constraints:
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -10^4 <= target <= 10^4
 */
public class P033_SearchInRotatedSortedArray {

    /**
     * 比20年第一轮做费劲。
     * 0ms: 100%, mem: 54%
     */
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        //找到旋转点
        int pivot = findPivotIndex(nums);

        //如果旋转点位置符合要求，直接返回
        if(nums[pivot] == target)
            return pivot;


        //否则，寻找旋转点的左边，或者右边  ----
        // ！！！！！这部分，直接抄了我20年的版本。
        // 我一开始，比20年的版本搞的复杂。想的是搞一个【虚拟的right】，可以超过实际数组边界，，就搞得很复杂。20年这样转换个思路，写起来就简单很多！！！
        if(target <= nums[nums.length-1]  && target > nums[pivot])
            return bSearch(nums, pivot + 1, nums.length - 1, target) ;    //找右边
        else
            return bSearch(nums, 0, pivot - 1, target) ;    //找左边
    }

    private int bSearch(int[] nums, int left, int right, int target){
        int res = -1;
        while (left <= right){
            int mid = (left + right)/2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return res;
    }

    /**
     * 找到发生Rotate的index位置
     * 偏移的位置： 返回最小元素开始的索引。
     */
    private int findPivotIndex(int nums[]){
        int idx = 0;
        if(nums == null || nums.length == 0 || nums.length == 1)
            return idx;
        if(nums[nums.length - 1] > nums[0])     //没有发生偏移，是递增序列
            return idx;
        int left = 0, right = nums.length - 1;
        while (true){
            int mid = (left + right)/2;
            if(mid == left)
                return mid + 1;
            if(nums[mid] < nums[left]) {//pivot在左边
                right = mid;
            }else if(nums[mid] > nums[right]) { //pivot在右边
                left =  mid;
            }
        }
    }

    public static void main(String[] args) {
        P033_SearchInRotatedSortedArray p = new P033_SearchInRotatedSortedArray();
        int num1[] = new int[]{6,7,0,1,2,4,5};
        System.out.println(p.search(num1 ,0));  //2
        System.out.println(p.search(num1 ,6));  //0
        System.out.println(p.search(num1 ,4));  //5
        System.out.println(p.search(num1 ,5));  //6
        System.out.println(p.search(num1 ,7));  //1

        int num2[] = new int[]{4,5,6,7,0,1,2};
        System.out.println(p.search(num2 ,0));  //4

        int num3[] = new int[]{1,2};
        System.out.println(p.search(num3 ,1));  //0

        int num4[] = new int[]{2,1};
        System.out.println(p.search(num4 ,1));   //1
    }
}
