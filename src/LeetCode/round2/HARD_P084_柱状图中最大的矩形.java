package LeetCode.round2;

/**
 190229 Hard
 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 求在该柱状图中，能够勾勒出来的矩形的最大面积。

 示例:
 输入: [2,1,5,6,2,3]
 输出: 10 (5和6能组成的最大高度)
 */
public class HARD_P084_柱状图中最大的矩形 {

    /**
     * 执行用时 : 1537 ms , 在所有 Java 提交中击败了 5.03% 的用户。 内存消耗 : 40.8 MB , 在所有 Java 提交中击败了 77.61% 的用户
     * 思路： O(n^2)的方法。是暴力法？？？ 一次AC。
     * 对每个元素，从左往右看最大能构成多大的。最大的面积需要比较两个元素： 当前柱子的高度，和当前柱子之前最低柱子到当前柱子能构成的面积最大值。
     * 思路同：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode/ 方法 2：优化的暴力
     */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int lowest = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                if(heights[j] < lowest)
                    lowest = heights[j];
                int square = Math.max((j - i + 1) * lowest, heights[j]);    //比较1). 当前柱子的面积，和2).当前柱子到开始位置为宽、高为最低柱子的面积，二者取大的。
                if(res < square)
                    res = square;
            }
        }
        return res;
    }



    public static void main(String[] args) {
        HARD_P084_柱状图中最大的矩形 p = new HARD_P084_柱状图中最大的矩形();
        System.out.println(p.largestRectangleArea(new int[]{2,1,5,6,2,3})); //10
    }
}
