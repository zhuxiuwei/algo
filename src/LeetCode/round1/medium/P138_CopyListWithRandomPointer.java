package LeetCode.round1.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 170609 Medium
 A linked list is given such that each node contains an additional random pointer 
 which could point to any node in the list or null.
 Return a deep copy of the list.
 */
public class P138_CopyListWithRandomPointer {

	/**
	 * AC: 9ms, 10%.
	 * 用了DFS，但是仔细想想可能没必要。用DFS，相当于第一趟是node沿着next，依次创建newNode并给其next赋值，
	 * 递归回退的过程中，不断给newNode的random指针赋值。
	 * ！！！！！！！ 注意一个BUG！！！！！！！！
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null)
			return null;
		
		RandomListNode newHead = new RandomListNode(head.label);
		Set<RandomListNode> visited = new HashSet<RandomListNode>();
		Map<RandomListNode, RandomListNode> oldNewMap = new HashMap<RandomListNode, RandomListNode>(); //在给新节点补全random link时用。
		oldNewMap.put(head, newHead);	
		dfs(head, newHead, visited, oldNewMap);
		return newHead;
	}
	
	private void dfs(RandomListNode node, RandomListNode newNode, 
			Set<RandomListNode> visited, Map<RandomListNode, RandomListNode> oldNewMap){
		visited.add(node);
		if(node.next != null && !visited.contains(node.next)){
			RandomListNode newNext = new RandomListNode(node.next.label);
			oldNewMap.put(node.next, newNext);
			newNode.next = newNext;
			dfs(node.next, newNext, visited, oldNewMap);
		}
		if(node.random != null && !visited.contains(node.random)){
			RandomListNode newRan = new RandomListNode(node.random.label);
			oldNewMap.put(node.random, newRan);
			newNode.random = newRan;
			dfs(node.random, newRan, visited, oldNewMap);
		}
		/*
		 * ！！！！！！！！注意BUG: 必须在下面这个地方，给random指针赋值。如果不做这一步，newNode的random都为空。 ！！！！！！！！！！
		 * update random pointer of new Node.
		 */
		if(node.random != null && newNode.random == null){
			newNode.random = oldNewMap.get(node.random);
		}
	}
	
	public static void main(String[] args) {
		P138_CopyListWithRandomPointer p = new P138_CopyListWithRandomPointer();
		RandomListNode n1 = new RandomListNode(1);
		RandomListNode n2 = new RandomListNode(2);
		RandomListNode n3 = new RandomListNode(3);
		n1.next = n2;
		n2.next = n3;
		n3.random = n1;
		n1.random = n2;
		p.copyRandomList(n1);
	}
}

/**
 * Definition for singly-linked list with a random pointer.
 */
class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
    public String toString(){
    	return label + "";
    }
};