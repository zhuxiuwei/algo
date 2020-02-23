package LeetCode.round2;

import java.util.ArrayList;
import java.util.List;

/**
 200222 medium
 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 示例 1:
 输入:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 输出: [1,2,3,6,9,8,7,4,5]

 示例 2:
 输入:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class P054_螺旋矩阵 {

    /**
     * 不是特别难，关键要细心。
     * 执行用时 : 0 ms , 在所有 Java 提交中击败了 100.00% 的用户。 内存消耗 : 37.9 MB , 在所有 Java 提交中击败了 5.09% 的用户
     * ！！！！！ 在case=3时写错了几个地方。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0)
            return res;
        int m = matrix.length, n = matrix[0].length;    //行数和列数
        int up = 0, left = 0, right = n - 1, down = m - 1;  //边界
        int mode = 0;   //0: →， 1：↓， 2：←，3：↑
        while (true) {
            boolean canWalk = false;
            switch (mode) {
                case 0:
                    for (int i = left; i <=right ; i++) {
                        res.add(matrix[up][i]);
                        canWalk = true;
                    }
                    up++;
                    mode++;
                    break;
                case 1:
                    for (int i = up; i <=down ; i++) {
                        res.add(matrix[i][right]);
                        canWalk = true;
                    }
                    mode++;
                    right --;
                    break;
                case 2:
                    for (int i = right; i >= left ; i--) {
                        res.add(matrix[down][i]);
                        canWalk = true;
                    }
                    mode++;
                    down--;
                    break;
                case 3:
                    for (int i = down; i >= up ; i--) {
                        res.add(matrix[i][left]);
                        canWalk = true;
                    }
                    mode = 0;
                    left++;
                    break;
            }
            if(!canWalk)
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        P054_螺旋矩阵 p = new P054_螺旋矩阵();
        List<Integer> res = p.spiralOrder(new int[][]{new int[]{1, 2, 3, 4},new int[]{5, 6, 7, 8},new int[]{9, 10, 11, 12},new int[]{13, 14, 15, 16}});
        System.out.println(res);    //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]
        res = p.spiralOrder(new int[][]{new int[]{1, 2, 3, 4},new int[]{5, 6, 7, 8},new int[]{9, 10, 11, 12}});
        System.out.println(res);    //[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}
