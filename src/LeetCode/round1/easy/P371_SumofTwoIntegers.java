package LeetCode.round1.easy;

/**
 * 位操作的题目，对我比较新，有些意思。一次通过。
 * 
 * 160908 
 * Difficulty: Easy
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
Example:
Given a = 1 and b = 2, return 3.
 */
public class P371_SumofTwoIntegers {

	/**
	 * 需要熟悉位运算的概念。 http://www.cnblogs.com/zhuangshq/p/5659897.html
	 */
	public int getSum(int a, int b) {
		
		//递归退出条件
		if(a == 0)
			return b;
		else if(b == 0)
			return a;
		
		//step 1. 按位异或，取得按位加的效果。注意此时不考虑进位。
		int temp1 = a ^ b;
		
		//step 2. 按位与。看哪些位有进位。
		int temp2 = a & b;
		
		//step 3. 处理进位。让temp2左移1位，和temp1相加即可。
		return getSum(temp1, temp2 << 1);
    }
	

	public static void main(String[] args) {
		P371_SumofTwoIntegers p = new P371_SumofTwoIntegers();
		System.out.println(p.getSum(1, 0));
		System.out.println(p.getSum(3, 1));
		System.out.println(p.getSum(31, 11));
		
		//位运算
//		int a = 3, b = 1;
//		System.out.println(a & b);
//		System.out.println(a ^ b);
//		System.out.println(a | b);
//		System.out.println(a << b);
	}

}
