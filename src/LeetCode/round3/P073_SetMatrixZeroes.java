package LeetCode.round3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 240823 Medium
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 *
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *
 * Follow up:
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class P073_SetMatrixZeroes {

    /**
     * 空间o(m+n)的解法。不是最优的O(1)解法。
     * Runtime 1ms Beats 79.59%, Memory 45.42MB Beats 67.67%
     */
    public void setZeroes(int[][] matrix) {
        //先统计哪些行列有0
        List<Integer> rowWithZeroIdx = new ArrayList<>();
        List<Integer> columnWithZeroIdx = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0){
                    rowWithZeroIdx.add(i);
                    columnWithZeroIdx.add(j);
                }
            }
        }

        //根据统计结果，修改matrix数组
        //行
        for (Iterator<Integer> iterator = rowWithZeroIdx.iterator(); iterator.hasNext(); ) {
            int r = iterator.next();
            for (int i = 0; i < matrix[r].length; i++) {
                matrix[r][i] = 0;
            }
        }
        //列
        for (Iterator<Integer> iterator = columnWithZeroIdx.iterator(); iterator.hasNext(); ) {
            int column = iterator.next();
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][column] = 0;
            }
        }

        //debug
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void main(String[] args) {
        P073_SetMatrixZeroes p = new P073_SetMatrixZeroes();
        p.setZeroes(new int[][]{{1,1,1},{1,0,1},{1,1,1}});  //[[1, 0, 1], [0, 0, 0], [1, 0, 1]]
        p.setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});    //[[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
    }
}