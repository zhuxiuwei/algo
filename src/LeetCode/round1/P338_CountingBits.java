package LeetCode.round1;

import java.util.Arrays;

/**  
 * 160908
 * Difficulty: Medium
Given a non negative integer number num. For every numbers i in the range 0 �� i �� num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class P338_CountingBits {
	
	public int[] countBits(int num) {
		if(num == 0)
			return new int[]{0};
		else if(num == 1)
			return new int[]{0, 1};
		else{
			int[] r = new int[num + 1];
			r[1] = 1;
			int factor = 2;
			for (int i = 2; i < r.length; i++) {
				if(i < factor * 2)
					r[i] = r[i - factor] + 1;
				else{
					r[i] = 1;
					factor *= 2;
				}
			}
			return r;
		}
    }

	public static void main(String[] args) {
		P338_CountingBits p = new P338_CountingBits();
		System.out.println(Arrays.toString(p.countBits(0)));	//[0]
		System.out.println(Arrays.toString(p.countBits(1)));	//[0, 1]
		System.out.println(Arrays.toString(p.countBits(5)));	//[0, 1, 1, 2, 1, 2]
		System.out.println(Arrays.toString(p.countBits(16)));	//[0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1]
	}

}
/**
 * 一次通过。
 */
