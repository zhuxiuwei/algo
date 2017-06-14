package lintcode.round1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 170614 Medium
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
You need to support the following method:
1. connect(a, b), add an edge to connect node a and node b. 
2.query(a, b)`, check if two nodes are connected

5 // n = 5
query(1, 2) return false
connect(1, 2)
query(1, 3) return false
connect(2, 4)
query(1, 4) return true
 */
public class P589_ConnectingGraph {

	private Map<Integer, CGNode> disjoint = new HashMap<Integer, CGNode>();
	
	/**
	 * 考察并查集(disjoint set)。顺利，一次AC。
	 */
	public P589_ConnectingGraph(int n) {
		//make-set.
		for (int i = 1; i <= n; i++) {
			disjoint.put(i, new CGNode(i));
		}
	}

	public void connect(int a, int b) {
		unionSet(disjoint.get(a), disjoint.get(b));
	}

	public boolean query(int a, int b) {
		return findSet(disjoint.get(a)) == findSet(disjoint.get(b));
	}
	
	//find-set of disjoint set.
	private CGNode findSet(CGNode node){
		CGNode res = null;
		Stack<CGNode> stack = new Stack<CGNode>();
		while(node != node.p){
			stack.push(node);
			node = node.p;
		}
		res = node;
		while(!stack.isEmpty())
			stack.pop().p = res;
		return res;
	}

	//union-set of disjoint set.
	private CGNode unionSet(CGNode x, CGNode y){
		CGNode xp = findSet(x);
		CGNode yp = findSet(y);
		if(xp.rank < yp.rank){
			xp.p = yp;
			return yp;
		}else if(xp.rank > yp.rank){
			yp.p = xp;
			return xp;
		}else{
			xp.p = yp;
			yp.rank ++;
			return yp;
		}
	}
	
	//Node of disjoint set.
	static class CGNode{
		int val;
		int rank;
		CGNode p;
		public CGNode(int i){
			this.val = i;
			p = this;
		}
	}
	
	public static void main(String[] args) {
		P589_ConnectingGraph p = new P589_ConnectingGraph(5);
		System.out.println(p.query(1, 2)); //return false
		p.connect(1, 2);
		System.out.println(p.query(1, 3)); //return false
		p.connect(2, 4);
		System.out.println(p.query(1, 4)); //return true
	}
	
}
