package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 160927
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8, return 13.

Note: You may assume k is always valid, 1 ≤ k ≤ n^2.
 */
public class P378_KthSmallestElementInASortedMatrix {
	
	/**
	 * Time: O(n). Space: O(1)
	 * AC: 53ms, 15.5%.
	 */
	public int kthSmallest(int[][] matrix, int k) {
		if(matrix.length == 1)
			return matrix[0][0];
		return 1;
    }

	/**
	 * Straight Forward way - expand matrix to an array, sort it, then get the kth element.
	 * Time: O(n^2). Space: O(n^2)
	 * AC: 18ms, 79.4%.
	 */
	public int kthSmallest_straightForward(int[][] matrix, int k) {
		int[] val = new int[matrix.length * matrix.length];
		
		//!!!!!! Note bug: 把一个二维数组展开到一个一位数组，最开始写得不对！！！
		//correct:
		int indexVal = 0;	
		for (int i = 0; i < matrix.length; i++) {
			int[] v = matrix[i];
			for (int j: v) 
				val[indexVal++] = j;
		}
		//wrong: 会导致只有二维数组matrix的第一个index，放到了val数组里。
//		for (int i = 0; i < matrix.length;) {   <---- wrong here!!!!
//			int[] v = matrix[i];
//			for (int j: v) 
//				val[i++] = j; <---- wrong here!!!!
//		}
		
		Arrays.sort(val);
		return val[k-1];
    }

	public static void main(String[] args) {
		P378_KthSmallestElementInASortedMatrix p = new P378_KthSmallestElementInASortedMatrix();
		int[][] matrix = new int[][]{{1,  5,  9},{10, 11, 13},{12, 13, 15}};
		System.out.println(p.kthSmallest_straightForward(matrix, 8));
		System.out.println(p.kthSmallest(matrix, 8));
		
		matrix = new int[][]{{12}};
		System.out.println(p.kthSmallest_straightForward(matrix, 1));
	}

}
