package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 240803
 * You are given an m x n grid where each cell can have one of three values:
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class P994_RottingOranges {
    /**
     * AC: 2ms Beats 72.94%, Memory 41.87MB Beats 95.05%
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int res = 0;
        if(grid == null || grid.length == 0)
            return res;
        List<int[]> rottingList = new ArrayList<>();
        //找到所有初始的烂柿子
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 2 ){
                    rottingList.add(new int[]{i, j});
                }
            }
        }
        int top = bfs(grid, rottingList);
        //检查是否还有没腐烂的，有的话返回-1，否则返回top
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1 ){
                    return -1;
                }
            }
        }
        return top > 0? top - 1: 0;     //！！！！开始写的返回top -1，但是case: [[0]]，会返回-1，而标准答案认为是0。所以改成这样了。
    }

    private int bfs(int[][] grid, List<int[]> rottingList){
        int res = 0;
        while (!rottingList.isEmpty()){
            List<int[]> newList = new ArrayList<>();
            for(int[] rotting: rottingList){
                int i = rotting[0], j = rotting[1];
                if(i - 1 >= 0 && grid[i - 1][j] == 1){  //上
                    grid[i - 1][j] = 2;
                    newList.add(new int[]{i - 1, j});
                }
                if(i + 1 <= grid.length - 1 && grid[i + 1][j] == 1){  //下
                    grid[i + 1][j] = 2;
                    newList.add(new int[]{i + 1, j});
                }
                if(j - 1 >= 0 && grid[i][j - 1] == 1){  //左
                    grid[i][j - 1] = 2;
                    newList.add(new int[]{i, j - 1});
                }
                if(j + 1 <= grid[i].length - 1 && grid[i][j + 1] == 1){  //右
                    grid[i][j + 1] = 2;
                    newList.add(new int[]{i, j + 1});
                }
            }
            rottingList = newList;
            res ++;
        }
        return res;
    }


    public static void main(String[] args) {
        P994_RottingOranges p = new P994_RottingOranges();
        System.out.println(p.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));     //4
        System.out.println(p.orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}));     //-1
        System.out.println(p.orangesRotting(new int[][]{{0,2}}));      //0
        System.out.println(p.orangesRotting(new int[][]{{0,0,0,0,1,0},{2,1,1,1,2,1},{0,0,0,0,1,0}}));      //2
        System.out.println(p.orangesRotting(new int[][]{{0}}));     //0     --  这个特殊情况爱出错。
        System.out.println(p.orangesRotting(new int[][]{{1}}));     //-1
        System.out.println(p.orangesRotting(new int[][]{{2}}));     //0

    }
}
