package LeetCode.round3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 240730
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * Example 1:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 *
 * Constraints:
 * 1 <= numRows <= 30
 */
public class P118_PascalTriangle {

    /**
     * AC: 1ms 82.58%, mem: 96%
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows >= 1){
            int[][] cache = new int[numRows + 1][];
            cache[0] = new int[0];  //第一个位置为哨兵
            for (int row = 1; row <= numRows ; row ++) {
                cache[row] = new int[row];  //！！！！！！不要忘了初始化第二维的数组。
                for (int idx = 0; idx < row; idx++) {
                    int v = getIdxVal(cache, row, idx);
                    cache[row][idx] = v;
                }
            }
            //数组转list
            for (int i = 1; i < cache.length; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < cache[i].length; j++) {
                    list.add(cache[i][j]);
                }
                res.add(list);
            }
        }
        return res;
    }

    private int getIdxVal(int[][] cache, int row, int idx){
        if(row == 1 || row == 2){   //头两行，固定是1
            return 1;
        }
        if(idx == 0 || idx == row - 1) {
            return  1;
        }else {
            return cache[row - 1][idx - 1] + cache[row - 1][idx];
        }
    }

    public static void main(String[] args) {
        P118_PascalTriangle p = new P118_PascalTriangle();
        System.out.println(p.generate(5));  //[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
        System.out.println(p.generate(1));  //[[1]]
    }
}
