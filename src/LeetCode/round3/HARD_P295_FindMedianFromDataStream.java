package LeetCode.round3;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * 240906 hard
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * - For example, for arr = [2,3,4], the median is 3.
 * - For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Constraints:
 * -100000 <= num <= 100000
 * There will be at least one element in the data structure before calling findMedian.
 * At most 50000 calls will be made to addNum and findMedian.
 *
 * Follow up:
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class HARD_P295_FindMedianFromDataStream {

    private int delta = 100000;
    private int size = 0;

    //题目说了最多 -100000 <= num <= 100000
    //为了方便计算，num + 100000后存到数组里。
    private int[] level1Count = new int[200001]; //数组下表对应的值，表示下标在DataStream中出现的count。
    private int[] level2Count = new int[201];    //二级数组(索引)，用于帮一级数组查找加速。每一个格子，代表1000个元素。int[0]存0，剩下的每1000存一个格子。

    /**
     * AC:Runtime 100ms Beats 52.47%, Memory 71.41MB Beats 6.32%
     * 使用了二维数组降低查找时间。稍微类似跳表的思路。
     * 转换逻辑有点复杂，写得很花时间。
     */
    public HARD_P295_FindMedianFromDataStream() {
    }

    public void addNum(int num) {
        level1Count[num + delta] ++;
        level2Count[level2Idx(num)] ++;
        size ++;
    }
    public double findMedian() {
        //中位数所在的顺序序号
        int targetSeq1 = (int)Math.ceil((double)size / 2);

        //用二级数组加速。利用二级数组记录的结果，找到在一级数组中查找目标应该开始的索引，和剩余需要查找的元素个数。
        int[] startAndSkipCount = findTargetIdxFromLevel2(targetSeq1);
        int leftCount = targetSeq1 - startAndSkipCount[1];
        int m1 = findKElementsInArray(startAndSkipCount[0], leftCount) - delta;

        //如果有偶数个数字，还需要再多找一个元素。
        if(size % 2 == 0){
            int targetSeq2 = targetSeq1 + 1;
            startAndSkipCount = findTargetIdxFromLevel2(targetSeq2);
            leftCount = targetSeq2 - startAndSkipCount[1];
            int m2 = findKElementsInArray(startAndSkipCount[0], leftCount) - delta;
            return (double)(m1 + m2) / 2;
        }

        return m1;
    }


    //根据num,构建在二级level的idx
    private int level2Idx(int num){
        num += delta;
        if(num == 0)
            return 0;
        int res = (int)Math.ceil((double)num / 1000);
        return res;
    }

    /**
     * 在二级数组里，根据targetSeq，找在一维数组里应该跳过的数量。
     * 返回一个二级数组：
     *   res[0]: 在数组1中应该跳过的idx数
     *   res[1]: 在二级数组里查找median过程中，已经跳过的数字的数量
     */
    private int[] findTargetIdxFromLevel2(int targetSeq){
        int[] res = new int[2];
        int count = 0, level1Idx = 0;
        for (int i = 0; i < level2Count.length; i++) {
            if(count + level2Count[i] >= targetSeq){
                break;
            }
            //计算level1 idx
            if(i == 0){
                level1Idx += 1;  //leve2第一个格子只存0，一个idx代表1个
            }else {
                level1Idx += 1000;   //leve2其余格子存1000个，一个idx代表level1中1000个
            }

            //计算已经跳过的count
            count += level2Count[i];
        }
        res[0] = level1Idx;
        res[1] = count;
        return res;
    }

    /**
     * 从一级数组里，从startIdx开始查找，找targetCount数目的
     * @param startIdx 从一级数组的startIdx开始找
     * @param k 找k个元素
     * @return 找到k个元素时的一级数组的idx
     */
    private int findKElementsInArray(int startIdx, int k){
        int count = 0;
        for (int i = startIdx; ; i++) {
            count += level1Count[i];
            if (count >= k) {
                return i;
            }
        }
    }

    public static void main(String[] args) {
        HARD_P295_FindMedianFromDataStream p = new HARD_P295_FindMedianFromDataStream();
        p.addNum(-100000);
        p.addNum(-99999);
        System.out.println(p.findMedian()); //-99999.5
        p.addNum(888);
        System.out.println(p.findMedian()); //-99999
        p.addNum(1212);
        System.out.println(p.findMedian()); //-49555.5
        p.addNum(1212);
        p.addNum(2200);
        p.addNum(2200);
        System.out.println(p.findMedian()); //1212

        p = new HARD_P295_FindMedianFromDataStream();
        p.addNum(3);
        p.addNum(4);
        System.out.println(p.findMedian()); //3.5
        p.addNum(5);
        System.out.println(p.findMedian()); //4
    }
}

/**
 * 思路：暴力法。
 * 使用TreeMap（红黑树）记录每个k(number)出现的count(v)。
 * 查找中位数时，从小到大依次遍历每个k，计算目前累加的count数，当累加的count数到达一半儿的时候，相当于找到了结果。
 * 过程中需要注意处理奇数、偶数个元素的情况。
 *
 * 问题：能通过16/21个用例，第17个case运行超时！！！！
 */
class HARD_P295_FindMedianFromDataStream_treemap_timeout {

    private TreeMap<Integer, Integer> numCountMap;
    private int size = 0;

    public HARD_P295_FindMedianFromDataStream_treemap_timeout() {
        numCountMap = new TreeMap<>();
    }

    public void addNum(int num) {
        int count = numCountMap.getOrDefault(num, 0);
        numCountMap.put(num, count + 1);
        size++;
    }
    public double findMedian() {
        //！！！！注意【(double)size / 2】里的double不可少，否则里面是整数相除，前面的ceil方法根本不会生效，相当于永远向下取整了。
        int targetNum1 = (int)Math.ceil((double)size / 2),
                targetNum2 = size % 2 == 1 ? targetNum1: targetNum1 + 1;
        Iterator<Integer> it = numCountMap.keySet().iterator();
        int count = 0, targetRes1 = 0,targetRes2 = 0 ;
        boolean target1Found = false;
        while (it.hasNext()){
            int num = it.next();
            count += numCountMap.get(num);
            if(count >= targetNum1 && !target1Found) {
                targetRes1 = num;
                target1Found = true;
            }
            if(count >= targetNum2) {
                targetRes2 = num;
                break;
            }
        }
        return (double)(targetRes1 + targetRes2)/2; //！！！！不能少了前面的(double)
    }
}
