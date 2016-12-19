package LeetCode.round1.easy;
/**
 * 161219
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, calculate the Hamming distance.
Note:
0 ≤ x, y < 2^31.

Example:
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
 */
public class P461_HammingDistance {

	/**
	 * 1 time AC: 13ms 
	 */
	public int hammingDistance(int x, int y){
		int xor = x ^ y;
		int count = 0;
		for (int i = 0; i < 32; i++){ 
			count += xor & 1;
		 	xor = xor >>> 1; 
		}
		return count;
	}
	
	public static void main(String[] args) {
		P461_HammingDistance p = new P461_HammingDistance();
		System.out.println(p.hammingDistance(1, 4));
	}

}
