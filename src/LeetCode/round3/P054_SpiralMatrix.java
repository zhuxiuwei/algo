package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 250218 medium
 * https://leetcode.com/problems/spiral-matrix/?envType=study-plan-v2&envId=top-interview-150
 */
public class P054_SpiralMatrix {
    /**
     * AC: 0 ms Beats 100.00%, Memory 41.65 MB Beats 41.87%
     * debug着写的，比20年写的啰嗦多了。。。耗时50分钟。。。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        BorderInfo borderInfo = new BorderInfo(matrix[0].length - 1, matrix.length - 1, 0, 1);

        int[] startIdx = new int[]{0,0};
        int totalNums = matrix.length * matrix[0].length;   //元素总数

        while (true){
            startIdx = travelRight(matrix, startIdx, res, borderInfo);
            if(res.size() >= totalNums) //退出条件。也可以在具体的travel方法里做判断（开始位置已经超过border位置的极限则退出）
                break;
            startIdx = travelDown(matrix, startIdx, res, borderInfo);
            if(res.size() >= totalNums) //退出条件
                break;
            startIdx = travelLeft(matrix, startIdx, res, borderInfo);
            if(res.size() >= totalNums) //退出条件
                break;
            startIdx = travelUp(matrix, startIdx, res, borderInfo);
            if(res.size() >= totalNums) //退出条件
                break;
        }

        return res;
    }

    private int[] travelRight(int[][] matrix, int[] startIdx, List<Integer> res, BorderInfo borderInfo){
        int row = startIdx[0], column = startIdx[1];
        int[] data = matrix[row];
        for (int i = column; i <= borderInfo.rightBorder; i++) {
            res.add(data[i]);
        }
        borderInfo.rightBorder = borderInfo.rightBorder - 1;
        return new int[]{startIdx[0] + 1, borderInfo.rightBorder + 1};  //！！！这个地方debug着写的
    }

    private int[] travelDown(int[][] matrix, int[] startIdx, List<Integer> res, BorderInfo borderInfo){
        int row = startIdx[0], column = startIdx[1];
        for (int i = row; i <= borderInfo.downBorder; i++) {
            res.add(matrix[i][column]);
        }
        borderInfo.downBorder = borderInfo.downBorder - 1;
        return new int[]{borderInfo.downBorder + 1, startIdx[1] - 1}; //！！！这个地方debug着写的
    }

    private int[] travelLeft(int[][] matrix, int[] startIdx, List<Integer> res, BorderInfo borderInfo){
        int row = startIdx[0], column = startIdx[1];
        int[] data = matrix[row];
        for (int i = column; i >= borderInfo.leftBorder; i--) {
            res.add(data[i]);
        }
        borderInfo.leftBorder = borderInfo.leftBorder + 1;
        return new int[]{startIdx[0] - 1, borderInfo.leftBorder - 1};    //！！！这个地方debug着写的
    }

    private int[] travelUp(int[][] matrix, int[] startIdx, List<Integer> res, BorderInfo borderInfo){
        int row = startIdx[0], column = startIdx[1];
        for (int i = row; i >= borderInfo.upBorder; i--) {
            res.add(matrix[i][column]);
        }
        borderInfo.upBorder = borderInfo.upBorder + 1;
        return new int[]{borderInfo.upBorder - 1, startIdx[1] + 1};  //！！！这个地方debug着写的
    }

    /**
     * 遍历时的边界信息。可以达到的方向的极限idx
     */
    static class BorderInfo{
        int rightBorder;
        int downBorder;
        int leftBorder;
        int upBorder;

        public BorderInfo(int rightBorder, int downBorder, int leftBorder, int upBorder){
            this.downBorder = downBorder;
            this.rightBorder = rightBorder;
            this.upBorder = upBorder;
            this.leftBorder = leftBorder;
        }
    }

    public static void main(String[] args) {
        P054_SpiralMatrix p = new P054_SpiralMatrix();
        int[][] a1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(p.spiralOrder(a1));  //[1,2,3,6,9,8,7,4,5]
        a1 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(p.spiralOrder(a1));  //[1,2,3,4,8,12,11,10,9,5,6,7]
        a1 = new int[][]{{1,2}};
        System.out.println(p.spiralOrder(a1));  //[1,2]
        a1 = new int[][]{{1}};
        System.out.println(p.spiralOrder(a1));  //[1]
    }
}
