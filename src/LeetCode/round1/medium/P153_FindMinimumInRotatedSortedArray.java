package LeetCode.round1.medium;
/**
 * 161214
Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element. You may assume no duplicate exists in the array.
 */
public class P153_FindMinimumInRotatedSortedArray {

	/**
	 * AC: 0m. 1 bug.
	 */
	public int findMin(int[] nums){
		if(nums.length == 1)	//!!!!!!!!!! note bug: for special case which array has only 1 element, like {1}. Otherwise will dead loop. 
			return nums[0];
		
		int start = 0, end = nums.length - 1; 
		if(nums[start] < nums[end])
			return nums[start];
		while(start <= end){
			int mid = (start + end)/2;
			if(start + 1 == end)
				return Math.min(nums[start], nums[end]);
			if(nums[mid] >= nums[start]){	//min is on right
				start = mid;
			}else{	//min is on left
				end = mid;
			}
		}
		return 0;	//unreachable
	}
	
	public static void main(String[] args) {
		P153_FindMinimumInRotatedSortedArray p = new P153_FindMinimumInRotatedSortedArray();
		System.out.println(p.findMin(new int[]{0,1,2,3,4}));
		System.out.println(p.findMin(new int[]{4,5,6,7,0,1,2}));
		System.out.println(p.findMin(new int[]{5,6,7,0,1,2,4}));
		System.out.println(p.findMin(new int[]{2,1}));
		System.out.println(p.findMin(new int[]{1}));
	}

}
