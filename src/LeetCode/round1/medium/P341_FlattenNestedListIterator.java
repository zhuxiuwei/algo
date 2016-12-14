package LeetCode.round1.medium;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import LeetCode.round1.common.NestedInteger;
/**
 * 161214
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
public class P341_FlattenNestedListIterator implements Iterator<Integer>{

	/**
	 * AC: 8ms, 63.44%
	 * 花了比较多的时间。最复杂的是因为需要处理包含empty list，尤其是nested empty list的case。因为包含empty list的时候，hasNext应该也要返回false而不是true，因此我的方案是先过滤一遍List，递归删除掉所有的空list。
	 */
	private List<NestedInteger> list = null;
	Stack<Iterator<NestedInteger>> stack = new Stack<Iterator<NestedInteger>>();
	public P341_FlattenNestedListIterator(List<NestedInteger> nestedList){
		removeEmptyList(nestedList);
		this.list = nestedList;
		if(list != null && !list.isEmpty())
			stack.push(list.iterator());
	}
	
	/**
	 * Remove empty lists in NestedInteger list. For cases like[[]], or even more complicated [[[], []]]
	 * @return
	 */
	private void removeEmptyList(List<NestedInteger> list){
		Iterator<NestedInteger> ite = list.iterator();
		while(ite.hasNext()){
			NestedInteger ni = ite.next();
			if(!ni.isInteger()){
				if(ni.getList().isEmpty())
					ite.remove();
				else{
					removeEmptyList(ni.getList());
					if(ni.getList().isEmpty())	//！！！！！！ Note here:  Need double check parent list again. In case of nested empty list like [[[]]]
						ite.remove();
				}
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		if(list == null || list.isEmpty())
			return false;
		else{
			if(stack.isEmpty())
				return false;
			else{
				while(!stack.isEmpty()){
					if(stack.peek().hasNext())
						return true;
					else
						stack.pop();
				}
				return false;
			}
		}
	}
	
	@Override
	public Integer next() {
		Iterator<NestedInteger> ite = stack.peek();
		NestedInteger ni = ite.next();
		while(!ni.isInteger()){
			stack.push(ni.getList().iterator());
			ite = stack.peek();
			ni = ite.next();
		}
		return ni.getInteger();
	}
	
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
