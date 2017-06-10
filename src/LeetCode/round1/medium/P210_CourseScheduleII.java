package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 170609 Medium
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:
2, [[1,0]]: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
public class P210_CourseScheduleII {
	
	private boolean canFinish = true;
	
	/**
	 * AC: 23ms, 39.11%
	 * ！！！！！！！！几个注意：！！！！！！！！！
	 * 1. 注意，我不用Vertex类、Color枚举类这些比较heavy的方式了，直接用Integer表示Vertex, Set<Integer>表示邻居集合。然后，基于本题业务需要，必须用grey、black两个颜色区分，这里用了个Set（visiting，visited）代表两个颜色。
	 * 2. 注意2个bug。其中第一个比较严重，会导致丢失结果，其实昨天【207 Course Schedule】我也犯过类似错误。
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if(numCourses <= 0 || prerequisites == null )
			return new int[]{};
		
		List<Integer> res = new ArrayList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();	//already visited. (vertex COLOR=black)
		Set<Integer> visiting = new HashSet<Integer>();	//visiting. (vertex COLOR=grey)

		//prepare graph
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		/*
		 * ！！！！！！！！！！！ 注意bug 1: 算严重bug。必须先用numCourses初始化graph（下面第1个for）。
		 * 如果只用prerequisites初始化graph（下面第2个for），那么prerequisites[]里面没有定义的依赖，在结果集里都丢了。 ！！！！！！！！！！！！
		 */
		for (int i = 0; i < numCourses; i++) {	
			Set<Integer> neighbors = new HashSet<Integer>();
			graph.put(i, neighbors);
		}
		for (int i = 0; i < prerequisites.length; i++) {
			int from = prerequisites[i][0], to = prerequisites[i][1];
			Set<Integer> neighbors = graph.get(from);
			neighbors.add(to);
		}
		
		//Use dfs to do topology sort.
		for (int vertex: graph.keySet()) {
			if(!visited.contains(vertex)){
				dfs(graph, vertex, res, visited, visiting);
			}
		}
		int[] r = new int[res.size()];
		for (int i = 0; i < r.length; i++) 
			r[i] = res.get(i);
		return r;
	}
	private void dfs(Map<Integer, Set<Integer>> graph, int vertex, List<Integer> res, Set<Integer> visited, Set<Integer> visiting){
		if(canFinish){
			visiting.add(vertex);
			Set<Integer> neighbors = graph.get(vertex);
			if(neighbors != null){
				for(int neighbor: neighbors){
					if(visiting.contains(neighbor)){	//can not finish!
						canFinish = false;
						res.clear();	//clear the result list.
					}else{
						if(!visited.contains(neighbor)){
							dfs(graph, neighbor, res, visited, visiting);
						}
					}
				}
			}
			//Same as turn "COLOR" from GREY to BLACK.
			visiting.remove(vertex);
			visited.add(vertex);
			if(canFinish) res.add(vertex);	//！！！！！！！注意bug2： 注意res添加一个vertex的时机，以及点添加到res的index位置，以及添加到res时必须是canFinish情况下。！！！！！！
		}
	}
	
	public static void main(String[] args) {
		P210_CourseScheduleII p = new P210_CourseScheduleII();
		System.out.println(Arrays.toString(p.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));	//[0, 1, 2, 3]
		p = new P210_CourseScheduleII();
		System.out.println(Arrays.toString(p.findOrder(2, new int[][]{{1,0},{0,1}})));	//[]
		p = new P210_CourseScheduleII();
		System.out.println(Arrays.toString(p.findOrder(1, new int[][]{})));	//[0]。注意结果不能为空(bug1)。
		p = new P210_CourseScheduleII();
		System.out.println(Arrays.toString(p.findOrder(4, new int[][]{{1,0}})));	//[0, 1, 2, 3]。注意结果不能只包含0,1(bug1)。

	}

}
