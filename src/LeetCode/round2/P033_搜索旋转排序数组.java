package LeetCode.round2;

/**
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 你可以假设数组中不存在重复的元素。
 你的算法时间复杂度必须是 O(log n) 级别。

 示例 1:
 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4

 示例 2:
 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1
 */
public class P033_搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        //找到旋转点
        int start = 0, end = nums.length - 1;
        int midPoint = 0;
        while (true){
            int mid = (start + end) / 2;
            if(nums[start] > nums[mid]){    //在左边找
                end = mid;
            }else { //在右边找
                start = mid;
            }
            if (start == end || start + 1 == end) {
                midPoint = start;
                break;
            }
        }
        if(target == nums[midPoint])
            return midPoint;
        else if(target >= nums[0] && target <= nums[midPoint])
            return bSearch(nums, 0, midPoint, target);
        else
            return bSearch(nums, midPoint + 1, nums.length - 1, target);
    }

    private int bSearch(int[] nums, int start, int end, int target){
        int res = -1;
        //！！！！！一个bug： start < end时，{0,1,2}搜0会搜不到。
        while (start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        P033_搜索旋转排序数组 p = new P033_搜索旋转排序数组();
        System.out.println(p.search(new int[]{3,4,5,6,7,8,1,2}, 1));
        System.out.println(p.search(new int[]{5,6,1,2,3,4}, 5));
        System.out.println(p.search(new int[]{5,6,1,2,3,4}, 8));
        System.out.println(p.search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(p.search(new int[]{1}, 1));
    }
}
