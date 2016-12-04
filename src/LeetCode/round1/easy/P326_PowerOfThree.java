package LeetCode.round1.easy;
/**
 * 161204
Given an integer, write a function to determine if it is a power of three.
Follow up: Could you do it without using any loop / recursion?
 */
public class P326_PowerOfThree {
	
	/**
	 * 不适用递归或者循环。 挺巧妙。 参考： https://discuss.leetcode.com/topic/36150/1-line-java-solution-without-loop-recursion/23
	 * AC: 21ms, 24.6%
	 */
	public boolean isPowerOfThree_NoLoopOrRecussive(int n) {
		if(n <= 0)
            return false;
		
		/**
		 * 求出Int范围内的3的最大power数。注意Math.log()的使用。比我下面用循环更简洁。
		 */
		int maxPowOf3 = (int)Math.pow(3, (int)(Math.log(Integer.MAX_VALUE)/Math.log(3)));	//1162261467
		
		//or:
//		int maxPowOf3 = 3;
//		while(maxPowOf3 <= Integer.MAX_VALUE / 3)
//			maxPowOf3 *= 3;
		
		return maxPowOf3 % n == 0;
    }
	
	/**
	 * AC:17ms, 87.8%
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree(int n) {
        if(n < 0)
        	return false;
        if(n == 1)
        	return true;
        if(n % 3 != 0)
        	return false;
        
        while(n > 3)
        {
        	n = n / 3;
        	if(n % 3 != 0)
            	return false;
        }
        return n == 3;
    }
	
	/**
	 * 错误。以下case， 82也会返回true. 必须加上模3是否为0的判断。
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree_wrong(int n) {
        if(n < 0)
        	return false;
        if(n == 1)
        	return true;
        
        while(n > 3)
        {
        	n = n / 3;
        }
        return n == 3;
    }
	
	public static void main(String[] args) {
		P326_PowerOfThree p = new P326_PowerOfThree();
		System.out.println(p.isPowerOfThree_NoLoopOrRecussive(0));
	}
}
