package LeetCode.round1.common;

/**
 * 并查集实现。(Disjoint set implementation)
 * @author Zhu Xiuwei
 */
public class DisjointSetForest<T> {

	/**
	 * Define member class.
	 * @author Zhu Xiuwei
	 */
	public static class DisjointSetForestMember<T>{
		int rank;	//秩
		T val;	//值
		DisjointSetForestMember<T> p; //父节点
		public DisjointSetForestMember(T val){
			this.val = val;
		}
		public String toString(){
			return String.format("[val: %s rank: %d, p: %s]", val.toString(), rank, p.val);
		}
	}
	
	public DisjointSetForestMember<T> makeSet(T val){
		DisjointSetForestMember<T> m = new DisjointSetForestMember<T>(val);
		m.p = m;
		return m;
	}
	
	public DisjointSetForestMember<T> findSet(DisjointSetForestMember<T> member){
		if(member.p == member)
			return member;
		member.p = findSet(member.p);
		return member.p;
	}
	
	public DisjointSetForestMember<T> unionSet(DisjointSetForestMember<T> x, DisjointSetForestMember<T> y){
		DisjointSetForestMember<T> res = null;
		DisjointSetForestMember<T> xp = findSet(x);
		DisjointSetForestMember<T> yp = findSet(y);
		if(xp.rank < yp.rank){
			xp.p = yp;
			res = yp;
		}
		else if(xp.rank > yp.rank){
			yp.p = xp;
			res = xp;
		}
		else{
			xp.p = yp;
			yp.rank += 1;
			res = yp;
		}
		return res;
	}

}
