package LeetCode.round1.easy;


/**
 * 170405
 * @author Zhu Xiuwei
Given a positive integer, output its complement number(补码). The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.

Example 1:
Input: 5 Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

Example 2:
Input: 1 Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 */
public class P476_NumberComplement {

	/**
	 * ！！！1个注意点。当i=31时。Math.pow(2, 31)理应为2147483648，但是Java都会返回2147483647.所以当i=31时，算法要做特殊处理。
	 * 11ms, 64.1%。
	 */
	public int findComplement(int num) {
		int i = 0, oldNum = num;
		for (; num != 0; i++) 
			num = num >> 1;
		return i == 31 ? (int)Math.pow(2, i) - oldNum: (int)Math.pow(2, i) - 1 - oldNum;	//!!!!!!!!!注意bug
    }
	
	public static void main(String[] args) {
		P476_NumberComplement p = new P476_NumberComplement();
		System.out.println(p.findComplement(2147483647));	//0
	}

}
