package LeetCode.round2;

import java.util.Arrays;

/**
 190229 HARD
 小马智行和华为都考了。
 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

 示例:
 输入:
 [
 ["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]
 ]
 输出: 6
 */
public class HARD_P085_最大矩形 {
    /**
     * 执行用时 : 6 ms , 在所有 Java 提交中击败了 83.72% 的用户。 内存消耗 : 43.3 MB , 在所有 Java 提交中击败了 25.69% 的用户。
     * 暴力法。 参考： https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
     * 自己实现了一遍。
     */
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        if(matrix == null || matrix.length == 0)
            return res;

        //记录连续1个数的数组
        int[][] count = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(j == 0){
                    if(matrix[i][j] == '1')
                        count[i][j] = 1;
                }else {
                    count[i][j] = matrix[i][j] == '1' ? count[i][j - 1] + 1: 0;
                }
            }
        }

        //从左上，往右下，一个个更新结果
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int minWidth = count[i][j];
                //往上行回看
                for (int k = i; k >= 0 && minWidth > 0; k--) {  //注意minWidth==0，则循环就没必要了。
                    int height = i - k + 1; //高度
                    minWidth = Math.min(minWidth, count[k][j]); //宽度
                    int square = height * minWidth; //面积
                    if(square > res)    //更新获得更大的面积。则更新res。
                        res = square;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        HARD_P085_最大矩形 p = new HARD_P085_最大矩形();
        System.out.println(p.maximalRectangle(new char[][]{new char[]{'1','0','1','0','0'},new char[]{'1','0','1','1','1'}, new char[]{'1','1','1','1','1'}, new char[]{'1','0','0','1','0'}}));
    }
}
