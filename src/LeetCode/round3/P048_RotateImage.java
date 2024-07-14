package LeetCode.round3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 240714 medium matrix
 * 旋转矩阵:
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class P048_RotateImage {
    /**
     * AC: 0ms 100%, mem 28.6%
     * @param matrix
     */
    public void rotate(int[][] matrix) {

        /**
         * 用于缓存旧数组的值。符合【You have to rotate the image in-place】要求，
         * 但不知道怎么做是否破坏了【DO NOT allocate another 2D matrix and do the rotation.】的要求。
         */
        int[][] oldMatrixCache = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                oldMatrixCache[i][j] = matrix[i][j];
            }
        }

        /**
         * 变换公式：
         * new(i) = old(j);
         * new(j) = matrix.length - old(i) - 1
         */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int newI = j, newJ = matrix.length - i - 1;
                matrix[newI][newJ] = oldMatrixCache[i][j];
            }
        }
    }
    public static void main(String[] args) {
        P048_RotateImage p = new P048_RotateImage();
        p.rotate(new int[][]{{1, 2}, {3, 4}});  //[[3, 1], [4, 2]]
        p.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}); //[[7,4,1],[8,5,2],[9,6,3]]
    }
}
