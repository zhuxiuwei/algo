package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 170501
 * @author Zhu Xiuwei
In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.
You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
Output: 
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
Output: 
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
Note:
The height and width of the given matrix is in range [1, 100].
The given r and c are all positive.
 */
public class P566_ReshapeTheMatrix {

	/**
	 * 1 time AC: 14ms, 9%.
	 * @param nums
	 * @param r
	 * @param c
	 * @return
	 */
	public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums == null || nums.length == 0)
        	return null;
        
        if(nums.length * nums[0].length != r * c)
        	return nums;
        
        List<Integer> temp = new ArrayList<Integer>(r*c);
        for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				temp.add(nums[i][j]);
			}
		}
        
        Iterator<Integer> it = temp.iterator();
        int res[][] = new int[r][c];
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
    			res[i][j] = it.next();
    		}
		}
        return res;
    }
	
	public static void main(String[] args) {
		P566_ReshapeTheMatrix p = new P566_ReshapeTheMatrix();
		int[][] s = new int[][]{{1,2},{3,4}};
		System.out.println(Arrays.deepToString(p.matrixReshape(s, 1, 4)));
		System.out.println(Arrays.deepToString(p.matrixReshape(s, 4, 1)));
		System.out.println(Arrays.deepToString(p.matrixReshape(s, 3, 1)));
	}

}
