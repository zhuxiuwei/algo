package LeetCode.round1.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 161123 
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input: [[0,0],[1,0],[2,0]]
Output: 2
Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class P447_NumberOfBoomerangs {

	/**
	 * O(n^2)
	 * AC: 166ms, 88.8%.
	 * !!!!!!!Note: 开始思路是错的，错了两次。最后才捋明白关系。
	 */
	public int numberOfBoomerangs(int[][] points) {
		if(points == null || points.length <= 2)
			return 0;
		int count = 0;
		//get all distances of each node
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if(i != j){
					int dis = (points[j][0] - points[i][0])*(points[j][0] - points[i][0]) + (points[j][1] - points[i][1])*(points[j][1] - points[i][1]);
					int val = map.getOrDefault(dis, 0);
					map.put(dis, val + 1);
				}
			}
			//calculate Number Of Boomerangs for this node.
			for (int dist: map.values()) {
				if(dist > 1)
					count += dist * (dist - 1); //C(n,2)*2! = n!*2!/2!(n-2)! = n * (n-1) 
			}
			map.clear();
		}
		
		return count;
    }
	
	public static void main(String[] args) {
		P447_NumberOfBoomerangs p = new P447_NumberOfBoomerangs();
		System.out.println(p.numberOfBoomerangs(new int[][]{{0,0},{1,0},{-1,0},{0,1},{0,-1}}));	//20
		System.out.println(p.numberOfBoomerangs(new int[][]{{0,0},{1,0},{2,0}}));	//2
	}

}
