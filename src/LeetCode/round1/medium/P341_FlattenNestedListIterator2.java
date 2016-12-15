package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import LeetCode.round1.common.NestedInteger;
/**
 * 161215
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
public class P341_FlattenNestedListIterator2 implements Iterator<Integer>{

	/**
	 * AC: 9ms, 47.81%
	 * 稍微花了些时间，有略微小bug。比P341_FlattenNestedListIterator.java写的快。
	 * 
	 * 思路：
	 * 不同于P341_FlattenNestedListIterator.java, 不用Stack。 递归遍历List<NestedInteger>，把所有的integer放到一个intList里，然后next和hasNext都可以直接调用intList的iterator相应方法。
	 */
	private List<Integer> intList = new ArrayList<Integer>();
	private Iterator<Integer> iterator = null;
	public P341_FlattenNestedListIterator2(List<NestedInteger> nestedList){
		pumpIntList(nestedList);
		iterator = intList.iterator();
	}
	
	/**
	 * 根据List<NestedInteger> nestedList， 递归生成一个List<Integer> intList. 
	 */
	private void pumpIntList(List<NestedInteger> list){
		Iterator<NestedInteger> ite = list.iterator();
		while(ite.hasNext()){
			NestedInteger ni = ite.next();
			if(!ni.isInteger()){
				if(!ni.getList().isEmpty()){
					Iterator<NestedInteger> ite2 = ni.getList().iterator();
					while(ite2.hasNext()){
						NestedInteger ni2 = ite2.next();
						if(ni2.isInteger())
							intList.add(ni2.getInteger());
						else
							pumpIntList(ni2.getList());
					}
				}
			}else
				intList.add(ni.getInteger());
		}
	}

	@Override
	public Integer next() {
		return iterator.next();	//直接利用intList的iterator
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext(); //直接利用intList的iterator
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
