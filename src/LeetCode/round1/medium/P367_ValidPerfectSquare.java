package LeetCode.round1.medium;
/**
 * 161219
Given a positive integer num, write a function which returns True if num is a perfect square else False.
Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Returns: True

Example 2:
Input: 14
Returns: False
 */
public class P367_ValidPerfectSquare {

	/**
	 * A square number is 1+3+5+7+.... Refer: https://discuss.leetcode.com/topic/49325/a-square-number-is-1-3-5-7-java-code
	 * AC: 1ms, 15.39%.
	 */
	public boolean isPerfectSquare(int num){
		int i = 1;
		while(num > 0){
			num -= i;
			i += 2;
		}
		return num == 0;
	}
	
	/**
	 * 平铺直叙法。！！！！！！！！！！！注意一个bug。
	 * AC: 2ms, 9.18%.
	 * @param num
	 * @return
	 */
	public boolean isPerfectSquare_straightForaward(int num){
		for (int i = 1; i <= num; i++) {
			int t = i * i;	
			if(t == num)
				return true;
			if(t > num 
				|| t < 0)	//<---------注意BUG!!! 一定要判断是否t < 0.因为当t > max integer时，会溢出，从负数开始。如果不加这个判断条件，就死循环了。
							//把t声明成long是不够的。 长度也不够。
				return false;
		}
		return false;
	}
	
	public static void main(String[] args) {
		P367_ValidPerfectSquare p = new P367_ValidPerfectSquare();
		System.out.println( p.isPerfectSquare(2147483647));		//false
		System.out.println( p.isPerfectSquare(9));		//false
	}

}
