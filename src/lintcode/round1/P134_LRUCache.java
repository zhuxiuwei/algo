package lintcode.round1;

import java.util.HashMap;
import java.util.Map;

/**
 * 170614 Hard
 * 
 */
public class P134_LRUCache {

	static class LRUCacheNode {
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

	public P134_LRUCache(int capacity) {
		this.capacity = capacity;
	}

	// @return an integer
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

	// @param key, an integer
	// @param value, an integer
	// @return nothing
	public void set(int key, int value) {
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
}
