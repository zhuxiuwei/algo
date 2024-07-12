package LeetCode.round3;

/**
 * 240710, Graph, Medium
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 *
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class P200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        return 0;
    }

    public static void main(String[] args) {
        P200_NumberOfIslands p = new P200_NumberOfIslands();
        System.out.println(p.numIslands(new char[][]{{'1', 0, 0, 0}, {0, '1', '1', 0}, {'1', 0, '1', 0}, {'1', '1', '1', 0}}));    //2
        System.out.println(p.numIslands(new char[][]{{'1', '1', '1', '1', 0}, {'1', '1', 0, '1', 0}, {'1', '1', 0, 0, 0}, {0, 0, 0, 0, 0}}));    //1
    }
}
