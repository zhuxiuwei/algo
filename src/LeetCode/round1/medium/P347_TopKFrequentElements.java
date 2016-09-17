package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 160917 
 Given a non-empty array of integers, return the k most frequent elements. For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
 Note: 
  	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
  	Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class P347_TopKFrequentElements {

	/**
	 * Use Java collections API -- PriorityQueue(Heap). AC: 37ms 
	 * Time: O(nlgn), space: O(n).
	 */
	public List<Integer> topKFrequent_Heap(int[] nums, int k) {
		// calculate value to times map.
		Map<Integer, Integer> valToTimes = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			valToTimes.put(nums[i], valToTimes.getOrDefault(nums[i], 0) + 1);

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<Map.Entry<Integer, Integer>>(
				new Comparator<Map.Entry<Integer, Integer>>() {
					public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
						return b.getValue() - a.getValue();
					}
				}); // Note: usage of Map.Entry. Not familiar before.
		
		for (Map.Entry<Integer, Integer> entry: valToTimes.entrySet()) 
			pq.add(entry);
		
		System.out.println(pq);
		
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) 
			res.add(pq.poll().getKey());
		
		return res;
	}

	/**
	 * Use Java collections API -- TreeMap. AC: 42ms. 
	 * Time: O(nlgn), space: O(n).
	 */
	public List<Integer> topKFrequent_TreeMap(int[] nums, int k) {

		// calculate value to times map.
		Map<Integer, Integer> valToTimes = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			valToTimes.put(nums[i], valToTimes.getOrDefault(nums[i], 0) + 1);

		// calculate times to value map. Use tree map. Note: in descendant order.
		Map<Integer, List<Integer>> timesToValues = new TreeMap<Integer, List<Integer>>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});
		for (int val : valToTimes.keySet()) {
			int times = valToTimes.get(val);
			if (!timesToValues.containsKey(times)) {
				List<Integer> l = new ArrayList<Integer>();
				l.add(val);
				timesToValues.put(times, l);
			} else {
				List<Integer> l = timesToValues.get(times);
				l.add(val);
			}
		}

		// get result from timesToValues map.
		List<Integer> res = new ArrayList<Integer>();

		int t = k;
		for (Iterator<Integer> it = timesToValues.keySet().iterator(); t > 0;) {
			List<Integer> val = timesToValues.get(it.next());
			if (val.size() <= t) {
				res.addAll(val);
				t -= val.size();
			} else {
				for (int i = 0; i < t; i++)
					res.add(val.get(i));
				break;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		P347_TopKFrequentElements p = new P347_TopKFrequentElements();
		System.out.println(p.topKFrequent_TreeMap(new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5 }, 3));
		System.out.println(p.topKFrequent_Heap(new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5 }, 3));
	}

}

/**
 * Note for the usage of Map.Entry<K, V> in line 31, which I was not familiar before.
 */
