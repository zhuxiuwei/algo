package LeetCode.round1.medium;
/**
 * 161207
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.
Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
 */
public class P035_SearchInsertPosition {
	
	/**
	 * O(lgn)
	 * AC: 6ms, 22.47%
	 */
	public int searchInsert(int[] nums, int target) {
		int left = 0, right = nums.length - 1, mid = 0;
		while(left <= right){	//!!!Note bug. left < right will be wrong.
			mid = (right + left)/2;
			if(nums[mid] == target)
				return mid;
			else if(nums[mid] < target)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return left;
    }
	
	/**
	 * O(n)
	 * AC: 7ms, 9.36%.
	 */
	public int searchInsert_O_N(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] >= target)
				return i;
		}
		return nums.length;
    }
	
	public static void main(String[] args) {
		P035_SearchInsertPosition p = new P035_SearchInsertPosition();
		int nums[] = new int[]{1,3,5,6};
		System.out.println(p.searchInsert(nums, 5));	//2
		System.out.println(p.searchInsert(nums, 2));	//1
		System.out.println(p.searchInsert(nums, 7));	//4
		System.out.println(p.searchInsert(nums, 0));	//0
	}
}
