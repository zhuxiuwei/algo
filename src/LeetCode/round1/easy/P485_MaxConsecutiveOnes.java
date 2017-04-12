package LeetCode.round1.easy;
/**
 * 170412
Given a binary array, find the maximum number of consecutive 1s in this array.
Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
    
Note:
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
 */
public class P485_MaxConsecutiveOnes {

	/**
	 * AC: 10ms, 70.84%
	 * ！！！！！！！！注意1个bug。！！！！！！！！！！！
	 */
	public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cur = 0;
        for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			if(n == 1){
				cur ++;
			}else{
				if(cur > max)
					max = cur;
				cur = 0;
			}
		}
        if(cur > max)	//!!!!!!!!!!Note Bug: 必须加上这个判断。否则数组全是1的时候，会错误地返回0.！！！！！！！！！！！
        	max = cur;
        return max;
    }
	
	public static void main(String[] args) {
		P485_MaxConsecutiveOnes p = new P485_MaxConsecutiveOnes();
		System.out.println(p.findMaxConsecutiveOnes(new int[]{0,1,1,0,0,1,1,1,0,1,1,0,0}));
		System.out.println(p.findMaxConsecutiveOnes(new int[]{1,1}));	//!!!!fix bug test case
	}

}
