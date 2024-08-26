package LeetCode.round3;

/**
 * 240826 easy
 * https://leetcode.com/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-100-liked
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums contains distinct values sorted in ascending order.
 * -104 <= target <= 104
 */
public class P035_SearchInsertPosition {

    /**
     * AC: 0ms Beats 100.00%, Memory 42.81MB Beats 50.22%
     */
    public int searchInsert(int[] nums, int target) {
        return bSearch(nums, 0, nums.length - 1, target);
    }

    private int bSearch(int[] nums, int start, int end, int target){
        while (start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){   //找左边
                end = mid - 1;
            }else { //找右边
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        P035_SearchInsertPosition p = new P035_SearchInsertPosition();
        System.out.println(p.searchInsert(new int[]{1,3,5,6}, 5));  //2
        System.out.println(p.searchInsert(new int[]{1,3,5,6}, 2));  //1
        System.out.println(p.searchInsert(new int[]{1,3,5,6}, 7));  //4

    }
}
