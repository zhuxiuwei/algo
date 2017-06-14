package LeetCode.round1.hard;

import java.util.HashMap;
import java.util.Map;
/**
 * 170604
 * @author Zhu Xiuwei
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:
LRUCache cache = new LRUCache( 2 );  2 is the capacity
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */
public class P146_LRUCache {

	/**
	 * AC: 45.33% 属于本身逻辑不算复杂，但是实现起来步骤多、繁琐，容易出错的问题。
	 * ！！！！！！！！！几个问题：！！！！！！！！！！！！！
	 * 1. 注意如果cache里有key，再添加时属于**UPDATE**的情况。这块引入了bug。
	 * 2. 对于LRU Cache的size，只需要用map的size即可，不需要引入单独一个变量。加入一个变量，就加入bug的风险。
	 */
	public static class LRUCacheNode {
		private int key;
		private int val;
		LRUCacheNode prve;
		LRUCacheNode next;

		public LRUCacheNode(int key, int val) {
			this.key = key;
			this.val = val;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (!(o instanceof LRUCacheNode))
				return false;
			LRUCacheNode other = (LRUCacheNode) o;
			return this.key == other.key;
		}

		@Override
		public int hashCode() {
			return key;
		}

		@Override
		public String toString() {
			return key + "";
		}
	}

	private int capacity = 0;
	private LRUCacheNode head = null, tail = null;
	private Map<Integer, LRUCacheNode> cache = new HashMap<Integer, LRUCacheNode>();

	public P146_LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		int res = -1;
		if (cache.containsKey(key)) {
			LRUCacheNode node = cache.get(key);
			res = node.val;

			// move this node to tail
			if (tail != node) {
				if (node.prve != null) { // not head
					node.prve.next = node.next;
					node.next.prve = node.prve;
				} else { // head
					head = node.next;
					node.next.prve = null;
				}
				// update old tail, and move node as a new tail.
				tail.next = node;
				node.next = null;
				node.prve = tail;
				tail = node;
			}
		}
		return res;
	}

	public void put(int key, int value) {
		LRUCacheNode node = new LRUCacheNode(key, value);
		if (cache.containsKey(key)) { // !!!!!!!!!!!!注意不要漏了，只是update的情况。而且注意不要放错了地方！！！！！！！！
			node = cache.get(key);
			node.val = value;
			if (node != tail) {
				if (node.prve != null) { // not head
					node.prve.next = node.next;
					node.next.prve = node.prve;
				} else { // head
					head = node.next;
					node.next.prve = null;
				}
				// update old tail, and move node as a new tail.
				tail.next = node;
				node.next = null;
				node.prve = tail;
				tail = node;
			}
		} else {
			if (cache.size() < capacity) { // not reach capacity, just add to
											// tail.
				if (cache.size() == 0) {
					tail = node;
					head = node;
				} else {
					tail.next = node;
					node.prve = tail;
					tail = node;
				}
			} else {
				if (!cache.containsKey(key)) { // need remove head.
					cache.remove(head.key);

					// update tail
					tail.next = node;
					node.prve = tail;
					tail = node;

					// remove head
					head.next.prve = null;
					head = head.next;
				}
			}
		}
		cache.put(key, node);
	}

	public static void main(String[] args) {
		// P146_LRUCache cache = new P146_LRUCache(2);
		// cache.put(1, 1);
		// cache.put(2, 2);
		// cache.get(1); // returns 1
		// cache.put(3, 3); // evicts key 2
		// cache.get(2); // returns -1 (not found)
		// cache.put(4, 4); // evicts key 1
		// cache.get(1); // returns -1 (not found)
		// cache.get(3); // returns 3
		// cache.get(4); // returns 4

		// P146_LRUCache cache = new P146_LRUCache(1);
		// cache.put(2, 1);
		// cache.get(2); // returns 1
		// cache.put(3, 2); // evicts key 2
		// cache.get(2); // returns -1 (not found)
		// cache.get(3); // returns 2

		// P146_LRUCache cache = new P146_LRUCache(2);
		// cache.get(2); // returns -1
		// cache.put(2, 6);
		// cache.get(1); // returns -1
		// cache.put(1, 5); //
		// cache.put(1, 2); // update value from 5 to 2
		// cache.get(1); // returns 2
		// cache.get(2); // returns 6

		P146_LRUCache cache = new P146_LRUCache(10);
		cache.put(10, 13);
		cache.put(3, 17);
		cache.put(6, 11);
		cache.put(10, 5);
		cache.put(9, 10);
		cache.get(13); // -1
		cache.put(2, 19);
		cache.get(2); // 19
		cache.get(3); // 17
		cache.put(5, 25);
		cache.get(8); // -1
		cache.put(9, 22);
		cache.put(5, 5);
		cache.put(1, 30);
		cache.get(11); // -1
		cache.put(9, 12);
		cache.get(7); // -1
		cache.get(5); // 5
		cache.get(8); // -1
		cache.get(9); // 12
		cache.put(4, 30);
		cache.put(9, 3);
		cache.get(9);
		cache.get(10); // 5
		cache.get(10); // 5
	}

}
