package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LeetCode.round1.common.IntArrayComparator;
import LeetCode.round1.common.Interval;

/**
 * 161123
Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
For any interval i, you need to store the minimum interval j's **index**, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
Note:
 - You may assume the interval's end point is always bigger than its start point.
 - You may assume none of these intervals have the same start point.

Example 1:
Input: [ [1,2] ]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: [ [3,4], [2,3], [1,2] ]
Output: [-1, 0, 1]
Explanation: There is no satisfied "right" interval for [3,4]. For [2,3], the interval [3,4] has minimum-"right" start point; For [1,2], the interval [2,3] has minimum-"right" start point.

Example 3:
Input: [ [1,4], [2,3], [3,4] ]
Output: [-1, 2, -1]
Explanation: There is no satisfied "right" interval for [1,4] and [3,4]. For [2,3], the interval [3,4] has minimum-"right" start point.
 */
public class P436_FindRightInterval {

	/**
	 * AC: 68ms, 35.3%.
	 * ！！！代码写起来比较繁琐。因为又要一顿折腾地sort，又要二分查找。 写的时候有点小bug。 错误AC了两次。
	 * Binary search. O(nlgn)
	 * @param intervals
	 * @return
	 */
	public int[] findRightInterval(Interval[] intervals) {
		int[] res = new int[intervals.length];
		for (int i = 0; i < res.length; i++) 
			res[i] = -2;	//initial value
		
		int[][] inters = new int[intervals.length][];	//Interval is not comparable, so has to change it to int[][] first.
		List<int[]> list = new ArrayList<int[]>(inters.length);
		Map<int[], Integer> indexMap = new HashMap<int[], Integer>();	//store original index
		for (int i = 0; i < intervals.length; i++) {
			inters[i] = new int[]{intervals[i].start, intervals[i].end};
			indexMap.put(inters[i], i);
			list.add(inters[i]);
		}
		
		//first, sort original intervals collection.
		Collections.sort(list, new IntArrayComparator());
		
		//then, fill result by using binary search. 
		for (int i = 0; i < list.size() - 1; i++) {
			int[] current = list.get(i);
			if(list.get(list.size() - 1)[0] < current[1])	//Its end is bigger than the biggest start, then result must be -1.
				res[indexMap.get(current)] = -1;
			else{	//binary search. Search self end.
				int left = 0, right = list.size() - 1, mid = (left + right) / 2;
				while(left <= right){	//must can hit target as the target is to search self.
					if(list.get(mid)[0] < current[1]){
						left = mid + 1;
						mid = (left + right) / 2;
					}else if(list.get(mid)[0] > current[1]){
						right = mid - 1;
						mid = (left + right) / 2;
					}else{	//hit
						res[indexMap.get(current)] = indexMap.get(list.get(mid));	//last element at end of list must have -1 value.
						break;
					}
				}
				if(res[indexMap.get(current)] == -2)	//no exact hit, find a bigger one
					res[indexMap.get(current)] = left;				
			}
		}
		res[indexMap.get(list.get(list.size() - 1))] = -1;	//last element at end of list must have -1 value.
		
        return res;
    }
	
	/**
	 * Straightforward O(n^2) solution. AC Timeout.
	 * @param intervals
	 * @return
	 */
	public int[] findRightInterval_timeout(Interval[] intervals) {
		int[] res = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			int smallestRight = Integer.MAX_VALUE;
			for (int j = 0; j < intervals.length; j++) {
				if(i != j){
					if(intervals[j].start >= intervals[i].end && intervals[j].start < smallestRight){
						res[i] = j;
						smallestRight = intervals[j].start;
					}
				}
			}
			if(smallestRight == Integer.MAX_VALUE)
				res[i] = -1;
			smallestRight = Integer.MAX_VALUE;
		}
        return res;
    }
	
	public static void main(String[] args) {
		P436_FindRightInterval p = new P436_FindRightInterval();
		System.out.println(Arrays.toString(p.findRightInterval(new Interval[]{new Interval(1,4), new Interval(2,3), new Interval(3, 4)}))); //[-1, 2, -1]
		System.out.println(Arrays.toString(p.findRightInterval(new Interval[]{new Interval(1,4)}))); //[-1]
		System.out.println(Arrays.toString(p.findRightInterval(new Interval[]{new Interval(1,5), new Interval(2,3), new Interval(4, 5)}))); //[-1, 2, -1]
		System.out.println(Arrays.toString(p.findRightInterval(new Interval[]{new Interval(3,4), new Interval(2,3), new Interval(1, 2)}))); //[-1,0,1]
	}

}
