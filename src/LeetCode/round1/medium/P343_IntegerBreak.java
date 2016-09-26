package LeetCode.round1.medium;

/**
 * 160926
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class P343_IntegerBreak {
	
	/**
	 * O(n) mathematical solution.
	 * AC: 0ms, 50%
	 */
	public int integerBreak_math(int n) {
		if(n == 2)
			return 1;
		else if(n == 3)
			return 2;
		else{
			if(n % 3 == 0)
				return (int)Math.pow(3, n/3);
			else if(n % 3 == 1)
				return (int)Math.pow(3, n/3 - 1) * 2 * 2;
			else
				return (int)Math.pow(3, n/3) * 2;
		}
    }
	
	/**
	 * Dynamic Programming bottom up. O(n^2)
	 * AC: 1ms, 33%
	 */
	public int integerBreak_DP_bottomUp(int n) {
		//m[i]: integerBreak of i.
		int[] m = new int[n + 1];	
		
		for (int i = 2; i < m.length; i++) {
			int max = 0;
			for (int j = 1; j <= i/2; j++) {
				int left = j > m[j] ? j: m[j];
				int right = i - j > m[i - j] ? i - j : m[i - j];
				if(left * right > max)
					max = left * right;
			}
			m[i] = max;
		}
		//System.out.println(Arrays.toString(m));
		return m[n];
    }
	
	/**
	 * Dynamic Programming up bottom memorized. O(n^2)
	 * AC: 1ms, 33%
	 */
	public int integerBreak_DP_upBottom(int n) {
		//m[i]: integerBreak of i. To memorize result of sub problems.
		int[] m = new int[n + 1];
		m[1] = 1;
		int res = integerBreak_DP_upBottom_helper(m, n);
		//System.out.println(Arrays.toString(m));
		return res;
    }
	public int integerBreak_DP_upBottom_helper(int[] m, int n){
		if(m[n] != 0)	//Return memorized result if have.
			return m[n];
		
		for (int i = 2; i < m.length; i++) {
			int max = 0;
			for (int j = 1; j <= i/2; j++) {
				int left = integerBreak_DP_upBottom_helper(m, j);
				if(left < j) 
					left = j;
				
				int right = integerBreak_DP_upBottom_helper(m, i - j);
				if(right < i - j) 
					right = i - j;
				
				if(left * right > max)
					max = left * right;
			}
			m[i] = max;
		}
		return m[n];
	}
	
	public static void main(String[] args) {
		P343_IntegerBreak p = new P343_IntegerBreak();
		System.out.println(p.integerBreak_DP_bottomUp(11));
		System.out.println(p.integerBreak_DP_upBottom(12));
		System.out.println(p.integerBreak_math(3));
	}
}
