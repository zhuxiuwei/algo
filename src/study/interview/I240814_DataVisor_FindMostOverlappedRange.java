package study.interview;

import java.util.*;

/**
 * 查找对多重叠的区间。
 * One day, we want to have an all-company meeting.
 * A group of people submit their available time shown as an interval:
 * input: [[1,4], [2,5], [4,6], [2,3], [16,24], [1000,2000]] - time range is any positive int.
 *
 * Goal: Find any 1-hour time period that most people are available.
 * Return the maximum number of people (and preferably the best time).
 * answer: 3 people, the hour 2-3
 */
public class I240814_DataVisor_FindMostOverlappedRange {

    /**
     * 思路想了比较久，经过了面试官的一些提示。写起来倒是还行。
     * 思路：
     * 1、遍历给定的timeRange，找到在每个时间Hour idx下，有多少个range开始，有多少个range截止
     * 2、遍历过程中，也将所有的特殊时间点记录下来，保存到一个TreeSet中（保证时间序列的有序性）
     * 3、遍历第二部的timeSet，每拿到一个特殊时间点，计算有几个是range开始，有几个是range结束。通过这个信息，能算出来在一个绝对时间节点处，有空的人的总数
     * 4、返回计算过程中发现的最大的人总数和对应的时间idx
     */
    public String getRes(int[][] times){
        int tmpCount = 0, maxCount = 0, maxStart = 0;

        Map<Integer, Integer> inCountMap = new HashMap<>();
        Map<Integer, Integer> outCountMap = new HashMap<>();
        TreeSet<Integer> timeSet = new TreeSet<>();
        for (int i = 0; i < times.length; i++) {
            int inIdx = times[i][0];
            int v = inCountMap.getOrDefault(inIdx, 0);
            inCountMap.put(inIdx, ++v);
            timeSet.add(inIdx);

            int outIdx = times[i][1];
            v = outCountMap.getOrDefault(inIdx, 0);
            outCountMap.put(outIdx, ++v);
            timeSet.add(outIdx);
        }
        Iterator<Integer> it = timeSet.iterator();
        while (it.hasNext()){
            int idx = it.next();
            int inCount = inCountMap.getOrDefault(idx, 0), outCount = outCountMap.getOrDefault(idx, 0);
            int delta = inCount - outCount;
            tmpCount += delta;
            if(maxCount < tmpCount){
                maxCount = tmpCount;
                maxStart = idx;
            }
        }

        return maxCount + " people, the hour " + maxStart + "-" + (maxStart + 1);
    }

    public static void main(String[] args) {
        I240814_DataVisor_FindMostOverlappedRange p = new I240814_DataVisor_FindMostOverlappedRange();
        System.out.println(p.getRes(new int[][]{{1,4}, {2,5}, {4,6}, {2,3}, {16,24}, {1000,2000}} ));
    }
}
