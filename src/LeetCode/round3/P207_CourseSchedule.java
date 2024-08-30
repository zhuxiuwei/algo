package LeetCode.round3;

import java.util.*;

/**
 * 240830 medium
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
     * 思路：判断有向图是否有环。
     * 大体还行吧
     * AC: 8ms Beats 18.93%, Memory 45.21 MB Beats 38.84%
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, VertexNode> nodesMap = new HashMap<>();    //用来辅助构建图中顶点之间关系的

        //构建图
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edges = prerequisites[i];
            VertexNode node = nodesMap.getOrDefault(edges[0], new VertexNode(edges[0]));
            nodesMap.put(edges[0], node);
            VertexNode neighbour = nodesMap.getOrDefault(edges[1], new VertexNode(edges[1]));
            nodesMap.put(edges[1], neighbour);
            node.neighbours.add(neighbour);
        }

        for(VertexNode node: nodesMap.values()){
            boolean res = dfs(node);
            if(!res)
                return false;
        }

        return true;
    }

    private boolean dfs( VertexNode node){
        if(node.visitState == 1) {   //又访问到了一个访问中的节点，说明有环
            return false;
        }
        node.visitState = 1;
        for (VertexNode neighbour: node.neighbours){
            if(neighbour.visitState != 2) {
                boolean neighbourRes = dfs(neighbour);
                if(!neighbourRes) {
                    return false;
                }
            }
        }
        node.visitState = 2;
        return true;
    }


    //点
    class VertexNode{
        int val;
        int visitState;     //0-未访问(white)，1-访问中(grey)，2-访问过(black)
        public VertexNode(int val){
            this.val = val;
            this.neighbours = new ArrayList<>();
            this.visitState = 0;
        }
        List<VertexNode> neighbours;    //邻居边
        @Override
        public int hashCode(){
            return val;
        }
        @Override
        public boolean equals(Object o){
            if(o == null)
                return false;
            if(!(o instanceof VertexNode))
                return false;
            return this.val == ((VertexNode)o).val;
        }
        @Override
        public String toString(){
            return val + ", " + visitState;
        }
    }



    public static void main(String[] args) {
        P207_CourseSchedule p = new P207_CourseSchedule();
        System.out.println(p.canFinish(3, new int[][]{{0,1},{1,2},{2,0}}));	//false
        p = new P207_CourseSchedule();
        System.out.println(p.canFinish(2, new int[][]{{1,0}})); //true
        p = new P207_CourseSchedule();
        System.out.println(p.canFinish(2, new int[][]{{1,0},{0,1}})); //false
        p = new P207_CourseSchedule();
        System.out.println(p.canFinish(20, new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}})); //false。解释：{5,5}，自己成环。
        p = new P207_CourseSchedule();
        System.out.println(p.canFinish(1, new int[][]{{0,0}})); //false 自己成环
    }
}
