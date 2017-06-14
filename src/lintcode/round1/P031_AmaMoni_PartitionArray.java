package lintcode.round1;

/**
 * 170613 easy
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

Notice
You should do really partition in array nums instead of just counting the numbers of integers smaller than k.
If all elements in nums are smaller than k, then return nums.length

Example: If nums = [3,2,2,1] and k=2, a valid answer is 1.
 */
public class P031_AmaMoni_PartitionArray {

	/** 
	 * ！！！！！！！！做的比上次费劲儿。注意2个bug！！！！！！！！：
	 *  1. 注意nums[j]、nums[i]与k比较大小的时候，包不包含等于。影响后面逻辑。
	 *  2. 注意return结果，需要nums[j]和k大小不同时分情况讨论。
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	
    	int i = 0, j = nums.length - 1;
    	while(i < j){
    		while(i < j && nums[j] >= k){
    			j --;
    		}
    		while(i < j && nums[i] < k){
    			i ++;
    		}
    		if(i < j){	//swap
    			int temp = nums[i];
    			nums[i] = nums[j];
    			nums[j] = temp;
    		}
    	}
    	if(nums[j] >= k)	//!!!!!!!注意bug！！！！！！！！
    		return j;
    	else 
    		return j + 1;
    }
    
	public static void main(String[] args) {
		P031_AmaMoni_PartitionArray p = new P031_AmaMoni_PartitionArray();
		System.out.println(p.partitionArray(new int[]{3,2,2,1}, 2));	//1
		System.out.println(p.partitionArray(new int[]{9,9,9,8,9,8,7,9,8,8,8,9,8,9,8,8,6,9},9));	//10
		System.out.println(p.partitionArray(new int[]{3,2,2,1}, 0));	//0
		System.out.println(p.partitionArray(new int[]{3,2,2,1}, 4));	//4
	}

}
