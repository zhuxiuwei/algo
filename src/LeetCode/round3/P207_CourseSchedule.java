package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 140828 medium
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class P207_CourseSchedule {

    /**
     * 思路：判断图是否有环。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }

    //图
    class Graph{
        List<VertexNode> nodes;
        List<Edge> edges;
        public Graph(List<VertexNode> nodes, List<Edge> edges){
            this.nodes = nodes;
            this.edges = edges;
        }
    }

    //点
    class VertexNode{
        int val;
        public VertexNode(int val){
            this.val = val;
        }
    }

    //边
    class Edge{
        VertexNode fromNode;
        VertexNode toNode;
        boolean visited;
        public Edge(VertexNode fromNode, VertexNode toNode){
            this.fromNode = fromNode;
            this.toNode = toNode;
        }
    }

    public static void main(String[] args) {
        P207_CourseSchedule p = new P207_CourseSchedule();
        System.out.println(p.canFinish(3, new int[][]{{0,1},{1,2},{2,0}}));	//false
        p = new P207_CourseSchedule();
        System.out.println(p.canFinish(2, new int[][]{{1,0}})); //true
        p = new P207_CourseSchedule();
        System.out.println(p.canFinish(2, new int[][]{{1,0},{0,1}})); //false
    }
}
