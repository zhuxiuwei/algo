package LeetCode.round3;

import java.util.*;

/**
 * 240813 medium
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * Return a list of integers representing the size of these parts.
 * 给定一个字符串 s。我们希望将字符串划分为尽可能多的部分，使得每个字母最多只出现在一个部分中。
 * 请注意，分割后的所有部分按顺序连接后，得到的字符串应该是 s。
 * 返回一个整数列表，表示这些部分的大小。
 *
 * Example 1:
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 *
 * Example 2:
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class P763_PartitionLabels {

    /**
     * AC: 7ms Beats 56.13%, Memory 42.96MB Beats 6.58%
     * 总体还行，有些小问题需要debug
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        Map<Character, int[]> charStartEndIdxMap = new HashMap<>();    //保存每个字母首末位置。K: 字母， V: 记录字母首末位置的二维数组
        char[] chars = s.toCharArray();

        //初始化记录字母首末位置的map
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int[] startEndIdx = charStartEndIdxMap.get(c);
            if(startEndIdx == null){
                startEndIdx = new int[2];
                startEndIdx[0] = i;
                startEndIdx[1] = i; //！！！不能少，否则如果一个字母只出现一次，其最后位置就会没有被记录，是默认值0
                charStartEndIdxMap.put(c, startEndIdx);
            }else {
                startEndIdx[1] = i;
            }
        }

        //计算所有range
        int[] range = null;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(range == null){
                range = charStartEndIdxMap.get(c);
            }
            int[] curRange = charStartEndIdxMap.get(c);
            int[] mergedRange = mergeRange(range, curRange);
            if(mergedRange == null){    //找到了一个部分
                res.add(range[1] - range[0] + 1);
                range = curRange;
            }else {
                range = mergedRange;
            }
        }
        res.add(range[1] - range[0] + 1);   //！！！不能少，否则最后一组结果丢了

        return res;
    }

    /**
     * 合并两个range范围。
     * @param range1
     * @param range2
     * @return 合并range1、2后的range。如果没有交集，返回null。
     */
    private int[] mergeRange(int[] range1, int[] range2){
        int[] frontRange = range1[0] <= range2[0] ? range1: range2;
        int[] backRange = range1[0] <= range2[0] ? range2: range1;
        if(backRange[0] <= frontRange[1]){  //有交集
            return new int[]{frontRange[0], Math.max(backRange[1], frontRange[1])};     //！！！注意右半边的写法
        }
        return null;
    }

    public static void main(String[] args) {
        P763_PartitionLabels p = new P763_PartitionLabels();
        System.out.println(p.partitionLabels("ababcbacadefegdehijhklij"));  // [9,7,8]
        System.out.println(p.partitionLabels("eccbbbbdec"));  // [10]
        System.out.println(p.partitionLabels("eaaaabaaec"));  // [9,1]
    }
}
