package LeetCode.round1.easy;

/**
 * 161213
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class P070_ClimbingStairs {
	
	/**
	 * 1 time AC: 0ms. Use loop instead of recursive
	 * !!!!循环比递归快的多的例子！！！
	 */
	public int climbStairs(int n) {
		if(n <= 0)
			return 0;
		else if(n == 1)
			return 1;
		else{
			int a[] = new int[n];
			a[0] = 1;
			a[1] = 2;
			for (int i = 2; i < a.length; i++) 
				a[i] = a[i - 1] + a[i - 2];
			return a[a.length - 1];
		}
	}
	
	/**
	 * Recursive. Timeout.
	 * 递归式：f(n)=f(n-1)+f(n-2)
	 * @param n
	 * @return
	 */
	public int climbStairs_timeout(int n) {
		if(n <= 0)
			return 0;
		else if(n == 1)
			return 1;
		else if(n == 2)
			return 2;
		else
			return climbStairs_timeout(n - 1) + climbStairs_timeout(n - 2);
	}
	
	public static void main(String[] args) {
		P070_ClimbingStairs p = new P070_ClimbingStairs();
		System.out.println(p.climbStairs(44));	//1134903170
		System.out.println(p.climbStairs_timeout(44));	//1134903170
	}
}
