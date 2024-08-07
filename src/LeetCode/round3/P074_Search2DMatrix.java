package LeetCode.round3;

/**
 * 240806 medium
 * You are given an m x n integer matrix with the following two properties:
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class P074_Search2DMatrix {
    /**
     * AC: 0ms Beats 100.00%, Memory 41.86MB Beats 75.23%
     * 思路：2轮二分查找。第一轮查找每行的头元素，定位到行。第二轮在行内二分查找。
     * 总体顺利，！！！！！！有一个bug。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int[] rowFirst = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rowFirst[i] = matrix[i][0];
        }
        int rowIdx = bSearch(rowFirst, 0, rowFirst.length - 1, target);
        if(rowFirst[rowIdx] == target) {
            return true;
        }else {
            int idx = bSearch(matrix[rowIdx], 0, matrix[rowIdx].length - 1, target);
            return (matrix[rowIdx][idx] == target);
        }
    }

    /**
     * 找数组中target出现的位置。如果出现，返回位置。如果没出现，返回第一个比它小的位置。
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int bSearch(int[] nums, int start, int end, int target){
        while (start <= end){
            int mid = (start + end)/2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] < target){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        //!!!!!! 直接返回start - 1的话，p.searchMatrix(new int[][]{{1}}, 1)用例会失败，因为返回的数组下标是-1。
        return start - 1 < 0 ? 0: start - 1;
    }

    public static void main(String[] args) {
        P074_Search2DMatrix p = new P074_Search2DMatrix();
        System.out.println(p.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));  //true
        System.out.println(p.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13)); //false
        System.out.println(p.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 7));  //true
        System.out.println(p.searchMatrix(new int[][]{{1,1,1},{1,1,1},{1,1,2}}, 3));  //false
        System.out.println(p.searchMatrix(new int[][]{{1,1,1},{1,1,1},{1,1,2}}, 1));  //true
        System.out.println(p.searchMatrix(new int[][]{{1,1,1},{1,1,1},{1,1,2}}, 2));  //true
        System.out.println(p.searchMatrix(new int[][]{{1}}, 0));  //false
        System.out.println(p.searchMatrix(new int[][]{{1}}, 1));  //false

    }

}
