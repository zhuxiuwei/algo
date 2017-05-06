package LeetCode.round1.medium;
/**
 * 170506
Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

Example:
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2

Note:
- The length of the input array is [1, 10].
- Elements in the given array will be in range [2, 1000].
- There is only one optimal division for each test case.
 */
public class P553_OptimalDivision {

	/**
	 * AC: 8ms, 67.81%
	 * 感觉比较无聊和“诡异”的一个题目。思路挺没劲的。而且想思路比较花时间。
	 */
	public String optimalDivision(int[] nums) {
        if(nums.length == 1)
        	return nums[0] + "";
        if(nums.length == 2)
        	return  nums[0] + "/" + nums[1];
        
        //当数组长度超过3时，就是第一个数字，除以后面的所有加一个括号。即总符合： num[0]/(num[1]/num[2]/.../num[length-1])
      	StringBuilder sb = new StringBuilder();
      	sb.append(nums[0]).append("/(");	
      	for (int i = 1; i < nums.length; i++) {
			sb.append(nums[i]);
			if(i != nums.length - 1)
				sb.append("/");
			else
				sb.append(")");
		}
        return sb.toString();
    }
	
	public static void main(String[] args) {
		P553_OptimalDivision p = new P553_OptimalDivision();
		System.out.println(p.optimalDivision(new int[]{1000}));
		System.out.println(p.optimalDivision(new int[]{1000,100}));
		System.out.println(p.optimalDivision(new int[]{1000,100,10,2}));
	}

}
