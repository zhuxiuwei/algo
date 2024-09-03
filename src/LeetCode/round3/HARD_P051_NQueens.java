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
            tempLocations: 用于记录每行皇后的位置。如[1,3,0,2]代表:
            [".Q.."]
            ["...Q"]
            ["Q..."]
            ["..Q."]
         */
        int[] tempLocations = new int[n];
        List<List<String>> resList = new ArrayList<>();
        helper(tempLocations, 0, resList);
        return resList;
    }

    private void helper(int[] tempLocations, int row, List<List<String>> resList) {
        for (int column = 0; column < tempLocations.length; column++) {   //看当前行的每一列位置可行性
            if (isLegalLocation(tempLocations, row, column)) {
                //【！！！感觉tempLocations记录中间结果比较精妙的一点是，当需要回溯时，不需要从tempLocations里移除数据。算法本身逻辑保证了不会出错(见isLegalLocation方法)。！！！】
                tempLocations[row] = column;
                if (row == tempLocations.length - 1) {  //已经找到了一个解决方案

                    //将一维数组转化为结果需要的格式，然后存入result List
                    List<String> partialResList = new ArrayList<>();
                    for (int i = 0; i < tempLocations.length; i++) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < tempLocations.length; j++) {
                            if (tempLocations[i] == j)
                                sb.append("Q");
                            else
                                sb.append(".");
                        }
                        partialResList.add(sb.toString());
                    }
                    resList.add(partialResList);

                } else {    //继续看下一行
                    helper(tempLocations, row + 1, resList);
                }
            }
        }
    }

    /**
     * 位置是否是合法位置
     * @param tempLocations 保存当前临时方案的数组
     * @param row 行
     * @param column 当前的column
     * @return
     */
    private boolean isLegalLocation(int[] tempLocations, int row, int column){
        if(row == 0){   //第一行总是合法的
            return true;
        }
        //看是否有在同一列的。注意是从上往下行查看，只看到本行上一行即可。不看本行下面的。【！！所以tempLocations数组中，本行之后的、历史回溯形成的脏数据不需要清理！！】
        for (int i = 0; i <= row - 1; i++) {
            if(tempLocations[i] == column)
                return false;
        }
        //看是否有形成对角的。同上，也只看到本行上一行即可。
        for (int i = 0; i <= row - 1; i++) {
            if(Math.abs(column - tempLocations[i]) == Math.abs(row - i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HARD_P051_NQueens p = new HARD_P051_NQueens();
        System.out.println(p.solveNQueens(4));  //[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    }
}
