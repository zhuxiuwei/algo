package LeetCode.round1.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 170608 Medium
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
 */
public class P200_NumberOfIslands {
	
	/**
	 * 1 time AC: 2.02%。
	 * 利用DFS。 ！！！注意一个可以投机取巧的策略(L80)。我没用到。！！！！
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0)
			return 0;
		Set<Coordinate> visited = new HashSet<Coordinate>();	//Trace visited Coordinates
		int res = 0;
		for (int i = 0; i < grid.length; i++) {
			char[] gj = grid[i];
			for (int j = 0; j < gj.length; j++) {
				char c = gj[j];
				if(c == '1'){	//注意必须用'1'而不是1。题目脑残地定义char，不是int。
					Coordinate coor = new Coordinate(i, j);
					if(!visited.contains(coor)){	//find a unvisited Coordinates
						res ++;
						dfs(coor, grid, visited);
					}
				}
			}
		}
		return res;
    }
	
	//DFS遍历。
	private void dfs(Coordinate coor, char[][] grid, Set<Coordinate> visited){
		visited.add(coor);
		if(coor.x != 0 && grid[coor.x - 1][coor.y] == '1' 
				&& !visited.contains(new Coordinate(coor.x - 1, coor.y))){
			dfs(new Coordinate(coor.x - 1, coor.y), grid, visited);
		}
		if(coor.x != grid.length - 1 && grid[coor.x + 1][coor.y] == '1'
				&& !visited.contains(new Coordinate(coor.x + 1, coor.y))){
			dfs(new Coordinate(coor.x + 1, coor.y), grid, visited);
		}
		if(coor.y != 0 && grid[coor.x][coor.y - 1] == '1'
				&& !visited.contains(new Coordinate(coor.x, coor.y - 1))){	
			dfs(new Coordinate(coor.x, coor.y - 1), grid, visited);
		}
		if(coor.y != grid[coor.x].length - 1 && grid[coor.x][coor.y + 1] == '1'
				&& !visited.contains(new Coordinate(coor.x, coor.y + 1))){		
			dfs(new Coordinate(coor.x, coor.y + 1), grid, visited);
		}
	}
	
	/**
	 * 坐标类。用来标记哪些坐标被访问过。
	 * ！！！！！！！！看别人的解法，一个投机取巧的优化方法是，访问过的1变成0.这样就不用记录哪些访问过了。！！！！！！！
	 */
	public static class Coordinate{
		int x;	//horizontal ordinate
		int y;	//vertical coordinates
		public Coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object o){
			if(null == o)
				return false;
			if(!(o instanceof Coordinate))
				return false;
			Coordinate other = (Coordinate)o;
			return this.x == other.x && this.y == other.y;
		}
		@Override
		public int hashCode(){
			int res = x;
			res = res * 31 + y;
			return res;
		}
	}
	
	public static void main(String[] args) {
		P200_NumberOfIslands p = new P200_NumberOfIslands();
		System.out.println(p.numIslands(new char[][]{{'1',0,0,0},{0,'1','1',0},{'1',0,'1',0},{'1','1','1',0}}));	//2
		System.out.println(p.numIslands(new char[][]{{'1','1','1','1',0},{'1','1',0,'1',0},{'1','1',0,0,0},{0,0,0,0,0}}));	//1
	}

}
