package LeetCode.round1;

/**
 * Easy 160910
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
For example:
Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 */
public class P258_AddDigits {
	
	public int addDigits(int num) {
		if(num == 0) return 0;	//注意 bug
        return num % 9 == 0 ? 9: num % 9;
    }
	
	//fail1. 输入0， 错误地返回9.
	public int addDigits_fail1(int num) {
        return num % 9 == 0 ? 9: num % 9;
    }

	public static void main(String[] args) {
		P258_AddDigits p = new P258_AddDigits();
		System.out.println(p.addDigits(7));
		System.out.println(p.addDigits(0));
		System.out.println(p.addDigits(9));
		System.out.println(p.addDigits(18));
	}

}
/**
 * 第一次submit失败，因为少了 if(num == 0) return 0;	导致输入0，错误返回9  
 */
