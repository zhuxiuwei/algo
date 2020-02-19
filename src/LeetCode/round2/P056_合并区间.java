package LeetCode.round2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 200219
 给出一个区间的集合，请合并所有重叠的区间。

 示例 1:
 输入: [[1,3],[2,6],[8,10],[15,18]]
 输出: [[1,6],[8,10],[15,18]]
 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

 示例 2:
 输入: [[1,4],[4,5]]
 输出: [[1,5]]
 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class P056_合并区间 {

    /**
     * 思路：
     * 1. 先排序。按照左边拍。 O(nlgn)
     * 2. 然后从左到右遍历合并，O(n)
     */
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length <= 1)
            return intervals;

        //按照第一个字母排序
        quickSort(intervals, 0, intervals.length - 1);
        List<int[]> tempRes = new ArrayList<>();

        int[] merged = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if(!(intervals[i][0] > merged[1])){  //可以合并
                int left = Math.min(intervals[i][0], merged[0]);
                int right = Math.max(intervals[i][1], merged[1]);
                merged = new int[]{left, right};
            }else {
                tempRes.add(merged);
                merged = intervals[i];
            }
        }
        tempRes.add(merged);   //！！！！ 这个不能丢。否则丢结果。
        int[][] res = new int[tempRes.size()][];
        for (int i = 0; i < tempRes.size(); i++)
            res[i] = tempRes.get(i);
        return res;
    }

    //对二维数组，把数组按照元素的第一个值进行快拍；
    private void quickSort(int[][] arr, int start, int end){
        int[] anchor = arr[start];
        int i = start, j = end;

        while (i < j){
            while (i < j && arr[j][0] >= anchor[0])
                j--;
            arr[i] = arr[j];

            while (i < j && arr[i][0] <= anchor[0])
                i++;
            arr[j] = arr[i];
        }
        arr[i] = anchor;

        if(start < i - 1)
            quickSort(arr, start, i - 1);
        if(end > i + 1)
            quickSort(arr, i + 1, end);
    }


    public static void main(String[] args) {
        P056_合并区间 p = new P056_合并区间();
        System.out.println(Arrays.deepToString(p.merge(new int[][]{{1,2},{2,4},{1,3}})));    //[[1,4]]
        System.out.println(Arrays.deepToString(p.merge( new int[][]{{1,3},{2,6},{8,10},{15,18}})));     //[[1, 6], [8, 10], [15, 18]]
    }
}
