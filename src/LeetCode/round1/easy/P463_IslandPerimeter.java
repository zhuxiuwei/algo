package LeetCode.round1.easy;
/**
 * 161122
岛的直径
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
Example:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public class P463_IslandPerimeter {

	/**
	 * One time AC: 140ms
	 * @param grid
	 * @return
	 */
	public int islandPerimeter(int[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] == 1){
					//left
					if(j == 0 || grid[i][j - 1] == 0)
						count ++;
					//up
					if(i == 0 || grid[i - 1][j] == 0)
						count ++;
					//right
					if(j == grid[i].length - 1 || grid[i][j + 1] == 0)
						count ++;
					//up
					if(i == grid.length - 1 || grid[i + 1][j] == 0)
						count ++;
				}
			}
		}
		return count;
	}
	 
	public static void main(String[] args) {
		P463_IslandPerimeter p = new P463_IslandPerimeter();
		System.out.println(p.islandPerimeter(new int[][]{{0,1}}));	//4
		System.out.println(p.islandPerimeter(new int[][]{{0,1,0,0}, {1,1,1,0}, {0,1,0,0}, {1,1,0,0}}));	//16
	}

}
