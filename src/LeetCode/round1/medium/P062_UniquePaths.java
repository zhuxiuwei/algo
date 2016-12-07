package LeetCode.round1.medium;
/**
 * 161207
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
Note: m and n will be at most 100.
 */
public class P062_UniquePaths {

	/**
	 * AC: 1ms
	 * 注意一个数组越界的小bug。
	 */
	public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        res[0][0] = 1;	//just to make OJ AC. OJ takes input 1 1 as expected output to be 1, not 0.
        dp(m - 1, n - 1, res);	//Note bug: 是m-1和n-1，不是m和n,否则数组越界。
        return res[m - 1][n - 1];
    }
	private void dp(int m, int n, int[][] res){
		if(res[m][n] != 0)	//use cached result
			return;
		if(m == 0 || n == 0){
			res[m][n] = 1;
			return;
		}
		dp(m - 1, n, res);
		dp(m, n - 1, res);
		res[m][n] = res[m - 1][n] + res[m][n - 1];
	}
	
	public static void main(String[] args) {
		P062_UniquePaths p = new P062_UniquePaths();
		System.out.println(p.uniquePaths(4, 3));	//10
	}

}
