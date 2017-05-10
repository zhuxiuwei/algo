package LeetCode.round1.easy;

import java.util.Arrays;

/**
 * 160509
For a web developer, it is very important to know how to design a web page's size. So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:
1. The area of the rectangular web page you designed must equal to the given target area.
2. The width W should not be larger than the length L, which means L >= W.
3. The difference between length L and width W should be as small as possible.

You need to output the length L and the width W of the web page you designed in sequence.
Example:
Input: 4
Output: [2, 2]
Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 
But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
Note:
The given area won't exceed 10,000,000 and is a positive integer
The web page's width and length you designed must be positive integers.
 */
public class P492_ConstructTheRectangle {

	/**
	 * AC: 100ms, 17.2%
	 * @param area
	 * @return
	 */
	public int[] constructRectangle(int area) {
		int[] res = new int[2];
		int start = (int)Math.sqrt(area);
		for (int i = start; i <= area; i++) {
			if(area % i == 0){
				int other = area / i;
				if(i >= other){
					res[0] = i;
					res[1] = area / i;
				}else{
					res[1] = i;
					res[0] = area / i;
				}
				break;
			}
		}
        return res;
	}
	
	/**
	 * 结果不对。4,6，10000000都不对。
	 * @param area
	 * @return
	 */
	public int[] constructRectangle_wrong(int area) {
		int[] res = new int[2];
		int bLength = getBinaryLength(area);
		int start = all1IntOfLength(bLength / 2);
		for (int i = start; i <= area; i++) {
			if(area % i == 0){
				int other = area / i;
				if(i >= other){
					res[0] = i;
					res[1] = area / i;
				}else{
					res[1] = i;
					res[0] = area / i;
				}
				break;
			}
		}
        return res;
    }
	private int getBinaryLength(int area){
		int i = 0;
		for (i = 0; area != 0; i++) {
			area = area >>> 1;
		}
		return i;
	}
	private int all1IntOfLength(int length){
		int i = 1;
		for (int j = 0; j < length - 1; j++) 
			i = (i << 1) | 1;
		return i;
	}
	
	public static void main(String[] args) {
		P492_ConstructTheRectangle p = new P492_ConstructTheRectangle();
		System.out.println(Arrays.toString(p.constructRectangle(32)));
		System.out.println(Arrays.toString(p.constructRectangle(16)));
		System.out.println(Arrays.toString(p.constructRectangle(15)));
		System.out.println(Arrays.toString(p.constructRectangle(11)));
		System.out.println(Arrays.toString(p.constructRectangle(6)));	//3,2
		System.out.println(Arrays.toString(p.constructRectangle(10000000)));	//3200,3125
		System.out.println(Math.sqrt(10));
	}

}
