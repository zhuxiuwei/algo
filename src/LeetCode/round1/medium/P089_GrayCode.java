package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 161209
The gray code is a binary numeral system where two successive values differ in only one bit.
Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
public class P089_GrayCode {

	/**
	 * AC: 4ms, 8.2%
	 * 注意俩bug。
	 */
	public List<Integer> grayCode(int n) {
		Set<Integer> visited = new HashSet<Integer>();
		List<Integer> res = new ArrayList<Integer>();
		int s = 0;
		res.add(s);
		visited.add(s);
		boolean changed = true;
		while (changed) {
			changed = false;
			for (int i = 0; i < n; i++) {
				int s2 = notOnIthBit(s, i); // ！！！注意这个地方，第i个bit取反。第一次写错了。
				if (!visited.contains(s2)) {
					res.add(s2);
					visited.add(s2);
					s = s2;
					changed = true;
					i = -1; // ！！！！注意这里，想让i从0从头开始，要i=-1,而不是i=0.否则下次一开始执行i++，就成从1开始了。
				}
			}
		}
		return res;
	}
	/**
	 * 对n在第i个bit上取反。
	 */
	private int notOnIthBit(int n, int i) {
		int temp = (1 << i);
		int n2 = temp | n;
		if (n == n2) // ith bit is 1
			n2 = temp ^ n;
		return n2;
	}

	public static void main(String[] args) {
		P089_GrayCode p = new P089_GrayCode();
		System.out.println(p.grayCode(3)); // [0, 1, 3, 2, 6, 7, 5, 4]
	}

}
