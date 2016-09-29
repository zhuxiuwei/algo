package LeetCode.round1.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
/*
 * !!!!Note
 * 1. 注意straightForward方法的bug。
 * 2. 用kthSmallest_heap的方法，开始想到了用队列，但是发现插入的顺序没法保证，卡在那儿了，期初不想用优先级队列。后来看discussion，别人用的也是heap(优先级队列。)
 * 3. 用kthSmallest_heap的方法，注意使用辅助class来简化编程。如果都记录int下标和val的话，实现时比较麻烦。
 */
public class P378_KthSmallestElementInASortedMatrix {
	
	/**
	 * Use min heap.(PriorityQueue in Java)
	 * Time: O(nlogn). Space: O(n^2)
	 * AC: 65ms, 12.9%.
	 */
	public int kthSmallest_heap(int[][] matrix, int k) {
		if(matrix.length == 1)
			return matrix[0][0];
		
		Set<Val> set = new HashSet<Val>();	//avoid dup
		Queue<Val> q = new PriorityQueue<Val>();
		Val v = new Val(0, 0, matrix[0][0]);
		q.offer(v);
		set.add(v);
		for (int i = 1; i < k; i++) {
			v = q.poll();
			if(v.idx2 < matrix.length - 1){	//right
				Val right = new Val(v.idx1, v.idx2 + 1, matrix[v.idx1][v.idx2 + 1]);
				if(!set.contains(right)){	//avoid dup
					q.offer(right);
					set.add(right);
				}
			}
			if(v.idx1 < matrix.length - 1){	//down
				Val down = new Val(v.idx1 + 1, v.idx2, matrix[v.idx1 + 1][v.idx2]);
				if(!set.contains(down)){	//avoid dup
					q.offer(down);
					set.add(down);
				}
			}
		}
		
		return q.poll().val;
    }
	class Val implements Comparable<Val>{
		int idx1;
		int idx2;
		int val;
		public Val(int idx1, int idx2, int val){
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.val = val;
		}
		@Override
		public int compareTo(Val o) {
			return this.val - o.val;
		}
		@Override
		public boolean equals(Object o){	//avoid dup
			if(o == null)
				return false;
			if(!(o instanceof Val))
				return false;
			Val other = (Val)o;
			return this.idx1 == other.idx1 && this.idx2 == other.idx2 && this.val == other.val;
		}
		@Override
		public int hashCode(){
			return idx1 + idx2 + val;
		}
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
		System.out.println(p.kthSmallest_straightForward(matrix, 4));
		System.out.println(p.kthSmallest_heap(matrix, 4));
		
		matrix = new int[][]{{12}};
		System.out.println(p.kthSmallest_straightForward(matrix, 1));
		System.out.println(p.kthSmallest_heap(matrix, 1));
	}

}
