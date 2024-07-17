package LeetCode.round3;

/**
 * 240717
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * Example 1:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class P240_SearchA2DMatrixII {
    /**
     * 比较暴力，基本从上到下几次二分查找。
     * AC: 6ms 64%, mem: 35%
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] <= target && matrix[i][matrix[i].length - 1] >= target){
                int idx = bSearch(matrix[i], 0, matrix[i].length - 1, target);
                if(idx >= 0)
                    return true;
            }
        }
        return false;
    }

    private int bSearch(int[] nums, int left, int right, int target){
         int mid = (left + right)/2;
         while (left <= right) {
             mid = (left + right)/2;
             if (nums[mid] == target) {
                 return mid;
             } else if (nums[mid] < target) {
                 left = mid + 1;
             } else {
                 right = mid - 1;
             }
         }
         return -1;
    }

    public static void main(String[] args) {
        P240_SearchA2DMatrixII p = new P240_SearchA2DMatrixII();
        System.out.println(p.searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}},5)); //true
        System.out.println(p.searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}},20)); //false

    }
}
