package LeetCode.round1.easy;
/**
 * 161214
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
public class P191_NumberOf1Bits {
	
	// you need to treat n as an unsigned value
	/**
	 * 1 time AC: 2ms, 12.19%.
	 * 负数和正数一样对待就行。
	 * @param n
	 * @return
	 */
	public int hammingWeight(int n) {
		int count = 0;
		while(n != 0){
			if((n & 1) == 1)
				count ++;
			n = n >>> 1;	//！！！！ 无符号右移
		}
		return count;
	}
	
	public static void main(String[] args) {
		P191_NumberOf1Bits p = new P191_NumberOf1Bits();
		System.out.println(p.hammingWeight(-2));
	}

}
