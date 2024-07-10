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
     * 49个case，第48个挂了。
     * 代码写的很ugly。感觉逻辑上ok，不知道错在哪了。
     * @param grid
     * @return
     */
    public int numIslands_wrong(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int[][] cache = new int[grid.length][grid[0].length];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int res = 0, order = 1;
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


    public int numIslands(char[][] grid) {
        return 0;
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
