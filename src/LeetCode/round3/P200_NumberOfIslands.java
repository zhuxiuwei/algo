package LeetCode.round3;

import java.util.*;

/**
 * 240710 # graph # medium
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class P200_NumberOfIslands {
    /**
     * 结果：fail，49个case，到第48个挂了。
     * O(n^2)的方案。
     * 思路：从左上到右下一个个看数字，如果是1，就重新看上边和左边，如果有1，那么就合并到上/左所在的岛里。如果都是0，就暂定发现了一个新岛屿。
     * 当某个1发现上和左两个岛的编号不同时，就需要将两个编号不同的岛记录下来，后面进行合并。代码里的map就是用于最后做合并的。
     * 代码写的很ugly。 感觉逻辑上ok，不知道错在哪了。
     */
    public int numIslands_wrong(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int[][] cache = new int[grid.length][grid[0].length];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int order = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1'){
                    char left = j > 0 ? grid[i][j - 1]: '0';
                    char up = i > 0 ? grid[i - 1][j]: '0';
                    if(left == '0' & up == '0'){
                        cache[i][j] = order;
                        Set<Integer> set = new HashSet<>();
                        set.add(order);
                        map.put(order, set);
                        order ++;
                    }
                    if(left == '1'){
                        cache[i][j] = cache[i][j - 1];
                    }
                    if(up == '1'){
                        if(left == '1'){
                            if(cache[i][j - 1] != cache[i - 1][j]){     //兩個island實際是同一個，需要合并
                                Set<Integer> leftSet = map.get(cache[i][j - 1]);
                                Set<Integer> upSet = map.get(cache[i - 1][j]);
                                //set合并
                                upSet.addAll(leftSet);
                                map.put(cache[i][j - 1], upSet);
                            }
                        }
                        cache[i][j] = cache[i - 1][j];
                    }
                }
            }
        }

        //合并本来应该都是同一个的岛屿
        Set<Set<Integer>> forCount = new HashSet<>();
        for(Integer k: map.keySet()){
            boolean found = false;
            for(Set<Integer> set: forCount){
                if(set.contains(k)){
                    set.addAll(map.get(k));
                    found = true;
                }
            }
            if(!found)
                forCount.add(map.get(k));
        }
        return forCount.size();
    }


    /**
     * AC: 4ms 48.7%, mem: 99.6%
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int[][] cache = new int[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(cache[i][j] == 0 && grid[i][j] == '1') {
                    res ++;
                    numIslands_travel(grid, cache, res, i, j);
                }
            }
        }
        return res;
    }

    private void numIslands_travel(char[][] grid, int[][] cache, int res, int i, int j){
        if(grid[i][j] == '0')
            return;
        if(grid[i][j] == '1' && cache[i][j] == 0) {
            cache[i][j] = 1;
            if (i > 0) {    //上
                numIslands_travel(grid, cache, res, i - 1, j);
            }
            if(i < grid.length - 1){    //下
                numIslands_travel(grid, cache, res, i + 1, j);
            }
            if(j > 0){    //左
                numIslands_travel(grid, cache, res, i, j - 1);
            }
            if(j < grid[i].length - 1){    //右
                numIslands_travel(grid, cache, res, i, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        P200_NumberOfIslands p = new P200_NumberOfIslands();
        System.out.println(p.numIslands(new char[][]{{'1','0','0','0'},{'0','1','1','0'},{'1','0','1','0'},{'1','1','1','0'}}));	//2
        System.out.println(p.numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}}));	//1
        System.out.println(p.numIslands(new char[][]{
                {'1','1','1','1','1','0','1','1','1','1'},
                {'1','0','1','0','1','1','1','1','1','1'},
                {'0','1','1','1','0','1','1','1','1','1'},
                {'1','1','0','1','1','0','0','0','0','1'},
                {'1','0','1','0','1','0','0','1','0','1'},
                {'1','0','0','1','1','1','0','1','0','0'},
                {'0','0','1','0','0','1','1','1','1','0'},
                {'1','0','1','1','1','0','0','1','1','1'},
                {'1','1','1','1','1','1','1','1','0','1'},
                {'1','0','1','1','1','1','1','1','1','0'}}));	//2

    }
}