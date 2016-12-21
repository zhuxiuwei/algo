package LeetCode.round1.medium;
/**
 * 161221
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2
Output: 6
Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
 */
public class P477_TotalHammingDistance {

	/**
	 * 思路：记录每个1 bit和0 bit出现的次数，每个bit相乘加和即是结果。想了半天想到。
	 * O(n). AC: 45ms
	 * @param nums
	 * @return
	 */
	public int totalHammingDistance(int[] nums) {
		if(nums == null || nums.length <= 1)
				return 0;
		int[] countOf1 = new int[32];
		int[] countOf0 = new int[32];
		
		//fill countOf1 and countOf0
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			for (int j = 0; j < 31; j++) {
				if((n & 1) == 1)
					countOf1[j] ++;
				else
					countOf0[j] ++;
				n = n >> 1;
			}
		}
		
		//Get result based on countOf1 and countOf0
		int sum = 0;
		for (int i = 0; i < countOf0.length; i++) 
			sum += countOf0[i] * countOf1[i];
		return sum;
    }
	
	/**
	 * O(n^2)，两两调用hammingDistance方法。对超长case超时了。
	 * @param nums
	 * @return
	 */
	public int totalHammingDistance_overtime(int[] nums) {
		if(nums == null || nums.length <= 1)
				return 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			int x = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				int y = nums[j];
				sum += hammingDistance(x, y);
			}
		}
		return sum;
    }
	private int hammingDistance(int x, int y){
		int xor = x ^ y;
		int count = 0;
		for (int i = 0; i < 32; i++){ 
			count += xor & 1;
		 	xor = xor >>> 1; 
		}
		return count;
	}
	
	public static void main(String[] args) {
		P477_TotalHammingDistance p = new P477_TotalHammingDistance();
		System.out.println(p.totalHammingDistance_overtime(new int[]{4, 14, 2, 6, 13}));	//20
		System.out.println(p.totalHammingDistance(new int[]{4, 14, 2, 6, 13}));	//20
	}

}
