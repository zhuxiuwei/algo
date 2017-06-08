package LeetCode.round1.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 170618
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:
2, [[1,0]]. There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
2, [[1,0],[0,1]]. There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
public class P207_CourseSchedule {

	private boolean res = true;
	
	/**
	 * 提交总体顺利。 就是通过DFS来检测途中是否有环。 DFS又熟悉了一次。
	 * ！！！！！！！ 两个注意。！！！！！！！！！！！
	 * AC: 16ms, 57.41%。
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if(numCourses <= 0 || prerequisites == null || prerequisites.length == 0)
			return true;
		
		//创建图
		Vertex[] vertexs = new Vertex[numCourses];	//！！！！注意必须先根据numCourses大小，建立好所有Vertex。好让graph map中key、neighbor list中引用指向同一个object。
		for (int i = 0; i < vertexs.length; i++) 
			vertexs[i] = new Vertex(i);
		Map<Vertex, Set<Vertex>> graph = new HashMap<Vertex, Set<Vertex>>();
		for (int i = 0; i < prerequisites.length; i++) {
			Vertex v = vertexs[prerequisites[i][0]];
			Set<Vertex> neighbors = graph.getOrDefault(v, new HashSet<Vertex>());
			neighbors.add(vertexs[prerequisites[i][1]]);
			graph.put(v, neighbors);
		}
		
		//通过DFS来检测途中是否有环。
		for (Vertex v: graph.keySet()) {	////！！！！注意foreach，也必须判断空。否则空指针异常。
			if(v.color == Color.WHITE){
				dfs(graph, v);
			}
		}
		return res;
	}
	
	private void dfs(Map<Vertex, Set<Vertex>> graph, Vertex v){
		if(res){
			v.color = Color.GREY;
			if(graph.containsKey(v)){
				for (Vertex neighbor: graph.get(v)) {
					if(neighbor.color == Color.WHITE)
						dfs(graph, neighbor);
					else if(neighbor.color == Color.GREY)	//A ring in graph detected!
						res = false;
				}
			}
			v.color = Color.BLACK;
		}
	}
	
	/**
	 *	Use color to tell if a vertex is visited. 
	 */
	public static enum Color {WHITE, GREY, BLACK;}
	/**
	 * Vertex class
	 */
	public static class Vertex{
		int val;
		Color color = Color.WHITE;
		public Vertex(int val){
			this.val = val;
		}
		@Override
		public boolean equals(Object o){
			if(o == null)
				return false;
			if(!(o instanceof Vertex))
				return false;
			Vertex other = (Vertex)o;
			return other.val == this.val;
		}
		@Override
		public int hashCode(){
			return val;
		}
		@Override
		public String toString(){
			return val + ", " + color;
		}
	}
	
	public static void main(String[] args) {
		P207_CourseSchedule p = new P207_CourseSchedule();
		System.out.println(p.canFinish(3, new int[][]{{0,1},{1,2},{2,0}}));	//false
		p = new P207_CourseSchedule();
		System.out.println(p.canFinish(2, new int[][]{{1,0}}));	//true
	}

}
