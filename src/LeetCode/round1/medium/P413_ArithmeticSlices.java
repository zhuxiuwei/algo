package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 161117
A sequence of number is called arithmetic(等差数列) if it consists of at least three elements and if the difference between any two consecutive elements is the same.
For example, these are arithmetic sequence:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
The function should return the number of arithmetic slices in the array A.

Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class P413_ArithmeticSlices {

	/**
	 * AC: 2ms, 33.4%.
	 * 
	 * Note: 不考虑“跳跃”的，必须都是紧挨着的数字。我刚开始想复杂了。
	 * 比如，1,2,3,4,5,10,6,7,8，分成两组1 2 3 4 5 和 6 7 8. 因为5和6不挨着，因此不是拿1-8来算。
	 * 而且，1 2 3 4 5 返回是6， 因为不考虑1 3 5.故只有： [1 2 3]， [2 3 4]， [3 4 5]， [1 2 3 4]， [2 3 4 5]， [1 2 3 4 5] 6个结果。（如果[1 3 5]也算的话要复杂很多） 
	 * 
	 * Note 2 bugs:
	 * 1. 要考虑A.length<=2的edge case。
	 * 2. needCalculateFn.add(count + 2);	不是count+1，没仔细考虑就写(count + 1)了。
	 * 
	 * @param A
	 * @return
	 */
	public int numberOfArithmeticSlices(int[] A) {
		if(A.length <= 2)	//Note bug1: consider edge case
			return 0;
		
		int res = 0;
		int dif[] = new int[A.length - 1];
		
		int count = 0;
		for (int i = 0; i < dif.length; i++) {
			dif[i] = A[i + 1] - A[i];
			if(i > 0 && dif[i] == dif[i - 1])
				count ++;
			else{
				if(count > 0){	//reset count.
					res += fn(count + 2);		//Note bug： count+2，不是count+1
					count = 0;
				}
			}
		}
		//in case 最后一个等差数列组。
		if(count > 0)
			res += fn(count + 2);	//Note bug： count+2，不是count+1
		
        return res;
    }
	/**
	 * 给出一个等差数列的数字个个数，计算这里面有多少个等差数列。如1,2,3,4有[123][234][1234]3个。
	 * @param n 等差数列里数字的个数
	 * @return  这个等差数列里包含的等差数列的个数
	 */
	private int fn(int n){
		//等差数列1 + 2 + ... + (n - 2)
		if(n <= 2)
			return 0;
		else
			return (n-1)*(n-2)/2;
	}
	
	public static void main(String[] args) {
		P413_ArithmeticSlices p = new P413_ArithmeticSlices();
		System.out.println(p.numberOfArithmeticSlices(new int[]{1,3,5,7,9,10,2,3,4}));	//7
	}

}
