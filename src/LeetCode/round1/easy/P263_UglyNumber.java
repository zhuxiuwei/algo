package LeetCode.round1.easy;
/**
 * 161214
Write a program to check whether a given number is an ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
Note that 1 is typically treated as an ugly number.
 */
public class P263_UglyNumber {
	
	/**
	 * 1 time AC: 2ms, 17.9%.
	 * @param num
	 * @return
	 */
	public boolean isUgly(int num){
		if(num <= 0)
			return false;
		
		while(num % 2 == 0)
			num /= 2;
		while(num % 3 == 0)
			num /= 3;
		while(num % 5 == 0)
			num /= 5;
		
		return num == 1;
	}
	
	public static void main(String[] args) {
		P263_UglyNumber p = new P263_UglyNumber();
		System.out.println(p.isUgly(6));
		System.out.println(p.isUgly(14));
	}

}
