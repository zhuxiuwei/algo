package study.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 170620
给定多个可能重叠的区间，找出最大的重叠区间的个数。
举例如下：
输入：[1，5]，[10，15]，[5，10]，[20，30]
输出：2

说明：题意应该是找出重叠区间中区间的最大个数，当没有区间重叠时，重叠个数最大为1，比如
输入为：[1，5]，[10，15]，则输出为1；
输入为：[1，2]，[2，3]，[3，4]，[4，5]，则输出为2（重叠区间相互之间都要有交集）；
输入为：[1，7]，[2，5]，[3，4]，[8，15]，[9，17]，[20，25]，则输出为3。
 */
public class I170620_Amazon_OverLappingMaxCount {
	
	/**
	 * 排序：O(nlogn)，排序后的执行：O(n)
	 * @param A List of Interval
	 * @return Max count of overlapped Intervals in A.
	 */
	public static int GetOverLappingMaxCount(List<Interval> A) {
		Collections.sort(A);
		if (A == null || A.size() == 0)
			return 0;
		int max = 1, count = 0;

		PriorityQueue<Integer> ordered_ends = new PriorityQueue<Integer>();	//store ends in min-heap.
		ordered_ends.offer(A.get(0).end);
		count++;

		for (int i = 1; i < A.size(); i++) {
			Interval interval = A.get(i);
			if (!ordered_ends.isEmpty()) {
				int minEnd = ordered_ends.peek();
				if (interval.start > minEnd) {
					while (interval.start > minEnd && !ordered_ends.isEmpty()) {
						ordered_ends.poll();
						if(!ordered_ends.isEmpty())
							minEnd = ordered_ends.peek();	//!!!!!!注意bug。要判断isEmpty.！！！！！
						if(count > 2) count--;	//最小是1.
					}
				} else {
					count++;
					if (count > max)
						max = count;
				}
			}
			ordered_ends.offer(interval.end);
		}

		return max;
	}

	static class Interval implements Comparable<Interval>{
		int start;
		int end;

		Interval(int a, int b) {
			start = a;
			end = b;
		}

		@Override
		public int compareTo(Interval o) {
			if (this.start == o.start)
				return (this.end - o.end);
			else
				return this.start - o.start;
		}
		
		public String toString() {
			return "[" + start + "," + end + "]";
		}

	}
	
	public static void main(String[] args) // 测试样例
	{
		Interval i = new Interval(0, 3);
		Interval j = new Interval(1, 3);
		Interval k = new Interval(1, 8);
		Interval l = new Interval(2, 4);
		Interval m = new Interval(2, 5);
		Interval n = new Interval(4, 9);
		Interval o = new Interval(8, 9);
		List<Interval> in = new ArrayList<Interval>();
		in.add(i);
		in.add(j);
		in.add(k);
		in.add(l);
		in.add(m);
		in.add(n);
		in.add(o);
		System.out.println(GetOverLappingMaxCount(in)); // 5
		
		i = new Interval(1,5);
		j = new Interval(10,15);
		k = new Interval(5,10);
		l = new Interval(20,30);
		in = new ArrayList<Interval>();
		in.add(i);
		in.add(j);
		in.add(k);
		in.add(l);
		System.out.println(GetOverLappingMaxCount(in)); // 2
		
		i = new Interval(1,5);
		j = new Interval(10,15);
		in = new ArrayList<Interval>();
		in.add(i);
		in.add(j);
		System.out.println(GetOverLappingMaxCount(in)); // 1
		
		i = new Interval(1,2);
		j = new Interval(2,3);
		k = new Interval(3,4);
		l = new Interval(4,5);
		in = new ArrayList<Interval>();
		in.add(i);
		in.add(j);
		in.add(k);
		in.add(l);
		System.out.println(GetOverLappingMaxCount(in)); // 2
		
		i = new Interval(1,7);
		j = new Interval(2,5);
		k = new Interval(3,4);
		l = new Interval(8,15);
		m = new Interval(9,17);
		n = new Interval(20,25);
		in = new ArrayList<Interval>();
		in.add(i);
		in.add(j);
		in.add(k);
		in.add(l);
		in.add(m);
		in.add(n);
		System.out.println(GetOverLappingMaxCount(in)); // 3

		i = new Interval(1,1);
		j = new Interval(1,10);
		k = new Interval(8,10);
		l = new Interval(8,8);
		m = new Interval(7,17);
		n = new Interval(7,25);
		in = new ArrayList<Interval>();
		in.add(i);
		in.add(j);
		in.add(k);
		in.add(l);
		in.add(m);
		in.add(n);
		System.out.println(GetOverLappingMaxCount(in)); // 5
	}
}

