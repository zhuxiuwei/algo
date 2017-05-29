package lintcode.round1;
/**
 * @author zhu xiuwei 
 * 170529 medium http://www.lintcode.com/en/problem/partition-array/
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

Notice
You should do really partition in array nums instead of just counting the numbers of integers smaller than k.
If all elements in nums are smaller than k, then return nums.length

Example
If nums = [3,2,2,1] and k=2, a valid answer is 1.
 */
public class P031_PartitionArray {

	/** 
	 * ！！！！！！！！注意有1个bug.！！！！！！！！！！！！
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
    	if(nums == null || nums.length == 0)
    		return 0;
    	int i = 0, j = nums.length - 1;
    	while(i < j){
	    	while(i < j && nums[j] >= k ){
	    		j --;
	    	}
	    	while(i < j && nums[i] < k ){
	    		i ++;
	    	}	
	    	if(i < j){
	    		//swap i j
		    	int temp = nums[i];
		    	nums[i] = nums[j];
		    	nums[j] = temp;
	    	}
    	}
    	int res = 0;
    	if(nums[i] > k)
    		res = i == 0 ? 0: i - 1;	//!!!!!!!注意bug！！！！！！！！！！
    	else if(nums[i] == k)
    		res = i;
    	else 
    		res = i + 1;
    	return res;
    }
    
	
	public static void main(String[] args) {

	}
}
