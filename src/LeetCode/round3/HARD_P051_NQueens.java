package LeetCode.round3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 240809 N皇后问题 hard
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Example 2:
 * Input: n = 1
 * Output: [["Q"]]
 *
 * Constraints:
 * 1 <= n <= 9
 */
public class HARD_P051_NQueens {

    /**
     * AC: 3ms Beats 59.47%. Memory 44.66MB Beats 72.98%
     * 以前写过，参考了以前的代码。自己从头写估计还是费劲。
     */
    public List<List<String>> solveNQueens(int n) {
        /*
            用于记录每行皇后的位置。如[1,3,0,2]代表:
            [".Q.."]
            ["...Q"]
            ["Q..."]
            ["..Q."]
         */
        int[] res = new int[n];
        List<List<String>> resList = new ArrayList<>();
        helper(res, 0, resList);
        return resList;
    }

    private void helper(int[] res, int row, List<List<String>> resList) {
        for (int column = 0; column < res.length; column++) {   //看当前行的每一列位置可行性
            if (isLegalLocation(res, row, column)) {
                res[row] = column;
                if (row == res.length - 1) {  //已经找到了一个解决方案
                    //数组结果存储res List
                    List<String> tmpList = new ArrayList<>();
                    for (int i = 0; i < res.length; i++) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < res.length; j++) {
                            if (res[i] == j)
                                sb.append("Q");
                            else
                                sb.append(".");
                        }
                        tmpList.add(sb.toString());
                    }
                    resList.add(tmpList);
                } else {
                    helper(res, row + 1, resList);
                }
            }
        }
    }

    /**
     * 位置是否是合法位置
     * @param res 保存结果的矩阵
     * @param row 行
     * @param column 当前的column
     * @return
     */
    private boolean isLegalLocation(int[] res, int row, int column){
        if(row == 0){   //第一行总是合法的
            return true;
        }
        for (int i = 0; i <= row - 1; i++) {    //看是否在同一列
            if(res[i] == column)
                return false;
        }
        for (int i = 0; i <= row - 1; i++) {    //看是否形成对角
            if(Math.abs(column - res[i]) == Math.abs(row - i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HARD_P051_NQueens p = new HARD_P051_NQueens();
        System.out.println(p.solveNQueens(4));  //[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    }
}
