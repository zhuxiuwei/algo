package LeetCode.round1.easy;
/**
 * 161219
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
Example:
Given num = 16, return true. Given num = 5, return false.
Follow up: Could you solve it without loops/recursion?
 */
public class P342_PowerOfFour {

	/**
	 * 求出Int范围内的4的最大power数。
	 * 这个思路不对。在PowerOfThree可用，在PowerOfFour不行。否则2也返回true。
	 * @param num
	 * @return
	 */
	public boolean isPowerOfFour_wrong(int num){
		if(num <= 0)
            return false;
		int maxPowOf4 = (int)Math.pow(4, (int)(Math.log(Integer.MAX_VALUE)/Math.log(4)));
		return maxPowOf4 % num == 0;
	}
	
	
	public static void main(String[] args) {
		P342_PowerOfFour p = new P342_PowerOfFour();
	}

}
