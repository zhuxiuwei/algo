package study.interview.jiuzhang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import LeetCode.round1.common.DisjointSetForest;
import LeetCode.round1.common.DisjointSetForest.DisjointSetForestMember;

/**
 * 170609 hard
 * http://www.cnblogs.com/zcy-backend/p/6734304.html
 * 最小生成树
 */
public class Ama9Ti_MinimumSpanningTree {

	/**
	 * 用Kruskal算法
	 * ！！！！！！！！！ 
	 * 一个注意：记录已经访问过的vertex，必须用并查集，不能用普通的Set。 否则结果根本不对。 
	 * 比如：[a-b,1] [c-d,1] [a-c,2] [b-d,3]四条边的图，只用Set的话，会错误返回Connection集合：{{ab}, {cd}}。缺了一条边，根本构不成一个最小生成树。（正确答案： {{ab}, {cd}, {ac}}）
	 * ！！！！！！！！！
	 * @param connections given a list of connections include two cities and cost
	 * @return a list of connections from results
	 */
	public List<Connection> lowestCost_Kruskal(List<Connection> connections){
		
		List<Connection> res = new ArrayList<Connection>(); 
		if(connections == null || connections.size() == 0)
			return res;  
		
		//makeSet for each vertex
		DisjointSetForest<Character> djf = new DisjointSetForest<Character>();
		Map<Character, DisjointSetForestMember<Character>> disjointSet = new HashMap<Character, DisjointSetForestMember<Character>>();
		for(Connection connection: connections){
			if(!disjointSet.containsKey(connection.city1))
				disjointSet.put(connection.city1, djf.makeSet(connection.city1));
			if(!disjointSet.containsKey(connection.city2))
				disjointSet.put(connection.city2, djf.makeSet(connection.city2));
		}
		
		//sort by costs
		Collections.sort(connections);
		int sumCost = 0;	//sum of costs

		//union set by increasing cost
		for(Connection connection: connections){
			DisjointSetForestMember<Character> c1 = disjointSet.get(connection.city1);
			DisjointSetForestMember<Character> c2 = disjointSet.get(connection.city2);
			if(djf.findSet(c1) != djf.findSet(c2)){
				sumCost += connection.cost;
				res.add(connection);
				djf.unionSet(c1, c2); 
			}
		}
		
		System.out.println("Sum of cost: " + sumCost);
		return res;
	}
	
	/**
	 * 用Prim算法
	 * ！！！！！！！！！！！！几个注意！！！！！！！！！！
	 * 1. 算法非常繁琐。需要维护各种引用关系、集合。
	 * 2. 注意PriorityQueue的一个限制。一旦pq建好后，修改成员里面的属性，是不会re-sort的。所以代码里必须加上很SB的resortPriorityQueue()函数。
	 * 3. Vertex节点的π是必须有的，否则拼接List<Connection>结果集，寻找Connection的“from”会非常麻烦。π就是干这个的。
	 * @param connections given a list of connections include two cities and cost
	 * @return a list of connections from results
	 */
	public List<Connection> lowestCost_Prim(List<Connection> connections){
		List<Connection> res = new ArrayList<Connection>(); 
		if(connections == null || connections.size() == 0)
			return res;  
		
		int sum = 0;
		//准备好节点
		Map<Character, PrimVertex> vertexs = new HashMap<Character, PrimVertex>();
		Map<String, Integer> costs = new HashMap<String, Integer>();
		Set<Character> set = new HashSet<Character>();
		for(Connection conn: connections){
			if(!set.contains(conn.city1)){
				PrimVertex pc1 = new PrimVertex(conn.city1);
				vertexs.put(conn.city1, pc1);
				set.add(conn.city1);
			}
			if(!set.contains(conn.city2)){
				PrimVertex pc2 = new PrimVertex(conn.city2);
				vertexs.put(conn.city2, pc2);
				set.add(conn.city2);
			}
			costs.put(conn.city1 + "-" + conn.city2, conn.cost);
			costs.put(conn.city2 + "-" + conn.city1, conn.cost);
		}
		Set<Character> set2 = new HashSet<Character>(set);	//只是为了加速判断priorityQueue是否包含一个元素。
		
		//构建图
		Map<Character, Set<PrimVertex>> graph = new HashMap<Character, Set<PrimVertex>>();
		PriorityQueue<PrimVertex> pq = new PriorityQueue<PrimVertex>();
		for(Connection conn: connections){
			PrimVertex pc1 = vertexs.get(conn.city1);
			PrimVertex pc2 = vertexs.get(conn.city2);
			Set<PrimVertex> c1Neighbors = graph.getOrDefault(conn.city1, new HashSet<PrimVertex>());
			c1Neighbors.add(pc2);
			graph.put(conn.city1, c1Neighbors);
			Set<PrimVertex> c2Neighbors = graph.getOrDefault(conn.city2, new HashSet<PrimVertex>());
			c2Neighbors.add(pc1);
			graph.put(conn.city2, c2Neighbors);
			if(set.contains(conn.city1)){
				pq.offer(pc1);
				set.remove(conn.city1);
			}
			if(set.contains(conn.city2)){
				pq.offer(pc2);
				set.remove(conn.city2);
			}
		}
		
		//给根节点的key赋值0
		PrimVertex root = vertexs.entrySet().iterator().next().getValue();
		root.key = 0;
		
		while(!pq.isEmpty()){
			PrimVertex v = pq.poll();
			set2.remove(v.val);
			Set<PrimVertex> neighbors = graph.get(v.val);
			for (PrimVertex vv: neighbors) {
				if(set2.contains(vv.val)){
					int cost = costs.get(v.val + "-" + vv.val);
					PrimVertex pv = vertexs.get(vv.val);
					if(cost < pv.key){
						pv.π = v;
						pv.key = cost;
						pq = resortPriorityQueue(pq);	
					}
				}
			}
			if(v.π != null)
				res.add(new Connection(v.π.val, v.val, v.key));
			sum += v.key;
		}
		System.out.println("Sum of cost: " + sum);
		return res;
	}
	//Prim算法要用到的节点类。
	private static class PrimVertex implements Comparable<PrimVertex>{
		char val;
		PrimVertex π;
		int key = Integer.MAX_VALUE;	//权重值
		@Override
		public int compareTo(PrimVertex o) {
			if(this.key > o.key)
				return 1;
			else if(this.key < o.key)
				return -1;
			else
				return 0;
		}
		public PrimVertex(char val){
			this.val = val;
		}
		public String toString(){
			return val + "";
		}
	}
	/*
	 * !!!!!!!!!!!注意PriorityQueue的一个限制。一旦pq建好后，修改成员里面的属性，是不会re-sort的。这个函数就是解决这个问题的。!!!!!!!!!!!
	 * https://stackoverflow.com/questions/1871253/updating-java-priorityqueue-when-its-elements-change-priority
	 * https://stackoverflow.com/questions/6952660/java-priority-queue-reordering-when-editing-elements
	 */
	private <T> PriorityQueue<T> resortPriorityQueue(PriorityQueue<T> pq){
		PriorityQueue<T> newPq = new PriorityQueue<T>();
		while(!pq.isEmpty())
			newPq.offer(pq.poll());
		return newPq;
	}
	
	public static void main(String[] args) {
		Ama9Ti_MinimumSpanningTree p = new Ama9Ti_MinimumSpanningTree();
		
		//算法导论第三版 图23-4(p367)
		Connection c1 = new Connection('a','b',4);
		Connection c2 = new Connection('a','h',8);
		Connection c3 = new Connection('b','h',11);
		Connection c4 = new Connection('b','c',8);
		Connection c5 = new Connection('c','d',7);
		Connection c6 = new Connection('h','i',7);
		Connection c7 = new Connection('g','h',1);
		Connection c8 = new Connection('g','i',6);
		Connection c9 = new Connection('g','f',2);
		Connection c10 = new Connection('i','c',2);
		Connection c11 = new Connection('c','f',4);
		Connection c12 = new Connection('f','d',14);
		Connection c13 = new Connection('d','e',9);
		Connection c14 = new Connection('f','e',10);
		List<Connection> list = new ArrayList<Connection>();
		list.add(c1);list.add(c2);list.add(c3);list.add(c4);list.add(c5);list.add(c6);list.add(c7);
		list.add(c8);list.add(c9);list.add(c10);list.add(c11);list.add(c12);list.add(c13);list.add(c14);
		List<Connection> list2 = new ArrayList<Connection>(list);
		
		//Test Kruskal算法
		System.out.println("----------- Kruskal -----------");	//Sum of cost: 37
		System.out.println("Connections: " + p.lowestCost_Kruskal(list) + "\r\n"); //Connections: [[g-h, 1], [g-f, 2], [i-c, 2], [a-b, 4], [c-f, 4], [c-d, 7], [a-h, 8], [d-e, 9]]
		
		//Test Prim算法
		System.out.println("----------- Prim -----------");
		System.out.println("Connections: " + p.lowestCost_Prim(list2) + "\r\n");
	}

}

/**
 * Connection类
 */
class Connection implements Comparable<Connection>{
	char city1;
	char city2;
	int cost;
	public Connection(char city1, char city2, int cost){
		this.city1 = city1;
		this.city2 = city2;
		this.cost = cost;
	}
	@Override
	public String toString(){
		return String.format("[%s-%s, %d]", city1, city2, cost);
	}
	@Override
	public int compareTo(Connection o) {
		if(this.cost > o.cost)
			return 1;
		else if(this.cost < o.cost)
			return -1;
		else
			return 0;
	}
}