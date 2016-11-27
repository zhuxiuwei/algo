package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import LeetCode.round1.common.Interval;

/**
161127
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
Note:
- You may assume the interval's end point is always bigger than its start point.
- Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
Input: [ [1,2], [1,2], [1,2] ]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
Input: [ [1,2], [2,3] ]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class P435_NonOverlappingIntervals {

	/**
	 * ！！！！！！思路不对， 思路是：从overlap最多的interval，依次删除。思路对这个case错误：[[0,2],[1,3],[1,3],[2,4],[3,5],[3,5],[4,6]]。按照这个思路先删除overlap最多的[2 4]，最后一共5次。而正确答案是删除全部[1 3][3 5]共4次。
	 * 花了很多时间。（包括一个奇怪的debug结果和run结果不一致的问题，为了fix这个问题，在生成itvToOverLapItvSet时用LinkedHashMap）
	 */
	public int eraseOverlapIntervals_ideaWrong(Interval[] intervals) {
		if(intervals == null || intervals.length <= 1)
			return 0;
		Map<Integer, List<Interval>> overLapCountToItvMap = new TreeMap<Integer, List<Interval>>(Collections.reverseOrder());
		Map<Interval, List<Interval>> itvToOverLapItvSet = new LinkedHashMap<Interval, List<Interval>>();
		
		//fill itvToOverLapCountMap
		for (int i = 0; i < intervals.length - 1; i++) {
			for (int j = i + 1; j < intervals.length; j++) {
				if(isOverlapped(intervals[i], intervals[j])){
					List<Interval> set = itvToOverLapItvSet.getOrDefault(intervals[i], new ArrayList<Interval>());
					set.add(intervals[j]);
					itvToOverLapItvSet.put(intervals[i], set);
					set = itvToOverLapItvSet.getOrDefault(intervals[j], new ArrayList<Interval>());
					set.add(intervals[i]);
					itvToOverLapItvSet.put(intervals[j], set);
				}
			}
		}
		
		//fill overLapCountToItvMap based on itvToOverLapCountMap
		System.out.println(itvToOverLapItvSet.entrySet());
		for (Map.Entry<Interval,List<Interval>> entry: itvToOverLapItvSet.entrySet()) {
			List<Interval> set = overLapCountToItvMap.getOrDefault(entry.getValue().size(), new ArrayList<Interval>());
			set.add(entry.getKey());
			overLapCountToItvMap.put(entry.getValue().size(), set);
		}
		System.out.println(overLapCountToItvMap);

		int count = 0;
		while(true){
			if(overLapCountToItvMap.size() > 0){
				int maxCount = overLapCountToItvMap.keySet().iterator().next();
				if(maxCount > 0){
					System.out.println("---------------------------");
					System.out.println(overLapCountToItvMap);
					System.out.println(itvToOverLapItvSet);
					count ++;
					//remove the interval with max overlap count, then update two map.
					List<Interval> maxIntervals =  overLapCountToItvMap.get(maxCount);
					Interval maxInterval = maxIntervals.iterator().next();
					System.out.println("remove: " + maxInterval);
					maxIntervals.remove(maxInterval);
					if(maxIntervals.size() == 0)
						overLapCountToItvMap.remove(maxCount);
					
					List<Interval> maxIntervalOverlapItvs = itvToOverLapItvSet.get(maxInterval);
					for (Interval itv: maxIntervalOverlapItvs) {
						int oldCount = itvToOverLapItvSet.get(itv).size();
						itvToOverLapItvSet.get(itv).remove(maxInterval);	//update itvToOverLapItvSet
						int newCount = itvToOverLapItvSet.get(itv).size();
						
						List<Interval> set = overLapCountToItvMap.getOrDefault(newCount, new ArrayList<Interval>());	//update overLapCountToItvMap
						set.add(itv);
						overLapCountToItvMap.put(newCount, set);
						set = overLapCountToItvMap.getOrDefault(oldCount, new ArrayList<Interval>());	//update overLapCountToItvMap
						set.remove(itv);
						overLapCountToItvMap.put(oldCount, set);
						if(set.size() == 0)
							overLapCountToItvMap.remove(oldCount);
					}
				}
				else
					break;
			}else
				break;
		}
        return count;
    }
	private boolean isOverlapped(Interval i1, Interval i2){
		if(i1.end <= i2.start || i2.end <= i1.start)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		P435_NonOverlappingIntervals p = new P435_NonOverlappingIntervals();
//		System.out.println(p.eraseOverlapIntervals(new Interval[]{new Interval(1,2),new Interval(2,3),new Interval(3,4),new Interval(1,3), new Interval(1,100)}));
//			System.out.println(p.eraseOverlapIntervals(new Interval[]{new Interval(1,2),new Interval(1,2),new Interval(1,2)}));
//		System.out.println(p.eraseOverlapIntervals(new Interval[]{new Interval(0,2),new Interval(1,3),new Interval(2,4),new Interval(3,5), new Interval(4,6)}));
//		System.out.println(p.eraseOverlapIntervals(new Interval[]{new Interval(0,2),new Interval(1,3),new Interval(2,4),new Interval(3,5), new Interval(4,6), new Interval(5,7)}));
		System.out.println(p.eraseOverlapIntervals_ideaWrong(new Interval[]{new Interval(0,2),new Interval(1,3),new Interval(1,3),new Interval(2,4),new Interval(3,5),new Interval(3,5), new Interval(4,6)}));
	}

}
