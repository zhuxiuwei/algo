package lintcode.round1;
/**
 * 170604 Medium
For an array A, if i < j, and A [i] > A [j], called (A [i], A [j]) is a reverse pair.
return total of reverse pairs in A.

Example
Given A = [2, 4, 1, 3, 5] , (2, 1), (4, 1), (4, 3) are reverse pairs. return 3

Leetcode也有: https://leetcode.com/problems/reverse-pairs/#/description
 */
public class P532_ReversePairs {

	/**
	 * 利用分治策略。O(nlgn) solution
	 */
	private int res = 0;

	public long reversePairs(int[] A) {
		if (A == null || A.length <= 1)
			return 0;
		helper(A, 0, A.length - 1);
		return res;
	}

	private void helper(int[] A, int begin, int end) {
		int mid = (begin + end) / 2;
		if (begin < mid)
			helper(A, begin, mid);
		if (mid + 1 < end)
			helper(A, mid + 1, end);
		merge(A, begin, end);
	}

	private void merge(int[] A, int begin, int end) {
		int mid = (begin + end) / 2;
		int left[] = new int[mid - begin + 2];
		int right[] = new int[end - mid + 1];
		for (int i = 0; i < left.length - 1; i++)
			left[i] = A[begin + i];
		for (int i = 0; i < right.length - 1; i++)
			right[i] = A[mid + 1 + i];
		left[left.length - 1] = Integer.MAX_VALUE; // 哨兵
		right[right.length - 1] = Integer.MAX_VALUE; // 哨兵
		int l = 0, r = 0;
		for (int i = begin; i <= end; i++) {
			if (right[r] < left[l]) {
				A[i] = right[r++];
				res += left.length - l - 1; // ！！！！！！！！！！注意bug！！！！！！！！不是简单地res += 1.
			} else {
				A[i] = left[l++];
			}
		}
	}

	/**
	 * @param A an array
	 * @return total of reverse pairs O(n^2) solution
	 */
	public long reversePairs_ON2(int[] A) {
		int res = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] > A[j])
					res++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		P532_ReversePairs p = new P532_ReversePairs();
		System.out.println(p.reversePairs(new int[] { 2, 4, 1, 3, 5 })); // 3
	}

}
