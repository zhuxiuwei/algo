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
	 * AC: 2ms, 24.1%. 
	 * Refer: https://discuss.leetcode.com/topic/42855/o-1-one-line-solution-without-loops
	 * The basic idea is from power of 2, We can use "n&(n-1) == 0" to determine if n is power of 2. 
	 * For power of 4, the additional restriction is that in binary form, the only "1" should always located at the odd position. 
	 * For example, 4^0 = 1, 4^1 = 100, 4^2 = 10000. So we can use "num & 0x55555555==num" to check if "1" is located at the odd position.
	 */
	public boolean isPowerOfFour_NonLoop_refer(int num){
		return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0x55555555) == num);	//!!!!!!! <--- 注意可16进制的使用！！！表示奇数位为1时。
	}
	
	/**
	 * AC: 2ms, 24.1%. 我的一句话方法。 
	 * @param num
	 * @return
	 */
	public boolean isPowerOfFour_NonLoop(int num){
		return (num == 1 || num == 1 << 2 || num == 1 << 4 || num == 1 << 6|| num == 1 << 8|| num == 1 << 10|| num == 1 << 12|| num == 1 << 14|| num == 1 << 16
				|| num == 1 << 18|| num == 1 << 20|| num == 1 << 22|| num == 1 << 24|| num == 1 << 26|| num == 1 << 28|| num == 1 << 30);
	}
	
	/**
	 * 求出Int范围内的4的最大power数。
	 * 这个思路不对。在Power Of 3可用，在PowerOfFour不行。因为4不是质数。 比图2也返回true。
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
		System.out.println(p.isPowerOfFour_NonLoop(4));
		System.out.println(p.isPowerOfFour_NonLoop(5));
	}

}
