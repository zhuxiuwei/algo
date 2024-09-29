package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 240929 easy
 * https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150
 * You are given a sorted unique integer array nums.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * Constraints:
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */
public class P228_SummaryRanges {
    /**
     * 顺利。
     * AC: Runtime 0 ms Beats 100.00%, Memory 41.40 MB Beats 89.55%
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums != null && nums.length > 0) {
            int start = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int n = nums[i], last = nums[i - 1];
                if (n != last + 1) {
                    res.add(genStr(start, last));
                    start = n;
                }
            }
            res.add(genStr(start, nums[nums.length - 1]));
        }
        return res;
    }

    private String genStr(int start, int end){
        if(start == end){
            return start + "";
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append(start);
            sb.append("->");
            sb.append(end);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        P228_SummaryRanges p228_summaryRanges = new P228_SummaryRanges();
        System.out.println(p228_summaryRanges.summaryRanges(new int[]{0})); //["0"]
        System.out.println(p228_summaryRanges.summaryRanges(new int[]{0,3})); //["0","3"]
        System.out.println(p228_summaryRanges.summaryRanges(new int[]{0,1,2,4,5,7})); //["0->2","4->5","7"]
        System.out.println(p228_summaryRanges.summaryRanges(new int[]{0,2,3,4,6,8,9})); //["0","2->4","6","8->9"]
    }
}
