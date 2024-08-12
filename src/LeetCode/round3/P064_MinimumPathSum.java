package LeetCode.round3;

/**
 * 240812 Medium
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 *
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * Example 2:
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12   (path: 1 → 2 → 3 → 6)
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
public class P064_MinimumPathSum {

    /**
     * AC: 1ms Beats 92.28%, Memory 45.35MB Beats 60.79%
     * 顺利，一次过。
     * 自顶向下带备忘录。
     */
    public int minPathSum(int[][] grid) {
        int cache[][] = new int[grid.length][grid[0].length];
        return findPath(grid, 0, 0, cache);
    }

    private int findPath(int[][] grid, int i, int j, int[][] cache){
        if(cache[i][j] != 0) {
            return cache[i][j];
        }
        else {
            if(i == cache.length - 1 && j == cache[i].length - 1){
                cache[i][j] = grid[i][j];
                return cache[i][j];
            }
            //右边
            int rightVal = j + 1 == cache[i].length ? Integer.MAX_VALUE: findPath(grid, i, j + 1, cache);
            //下边
            int downVal = i + 1 == cache.length ? Integer.MAX_VALUE: findPath(grid, i + 1, j, cache);
            int min = grid[i][j] + Math.min(rightVal, downVal);
            cache[i][j] = min;
            return min;
        }
    }

    public static void main(String[] args) {
        P064_MinimumPathSum p = new P064_MinimumPathSum();
        System.out.println(p.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}})); //7
        System.out.println(p.minPathSum(new int[][]{{1,2,3},{4,5,6}})); //12
    }
}
