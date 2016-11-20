package LeetCode.round1.easy;

/**
 * 161119
Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:
Input: [1,2,3]
Output: 3

Explanation:
Only three moves are needed (remember each move increments two elements): [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class P453_MinimumMovesToEqualArrayElements {

	/**
	 * AC: 17ms, 28%.
	 * 纯是找规律找出来的。并不是很明白其中的数学道理。找到的规律如下：
	 * 1. 对于序列min, max, max，... max，假设这里只有min和max，其中max出现的次数为n次，则返回结果为n * (max - min)
	 * 2. 结果最关键取决于最小的数字和最大的数字。比如，[min,a,b,...z,max]返回的结果为N，则[min,a+1,b,...z,max]结果为N+1, [min,a+2,b-1,...z,max]结果为N+2-1=N+1。。即如果知道了min,a,b,...z,max的结果，则无论a到z怎么变化（在min max范围内），都可以根据N推导出来结果。
	 * 则基于1和2 ，能计算出任意序列的结果。 Example: {3,33,67,100})) result is 191, 推导： (100-3)*3+(33-100)+(67-100)=191
	 * @param nums
	 * @return
	 */
	public int minMoves(int[] nums) {
		if (nums == null || nums.length <= 1) // edge case
			return 0;

		// step1: get min max;
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= min)
				min = nums[i];
			if (nums[i] >= max)
				max = nums[i];
		}

		// step2: get base result based on min and max value
		int baseResult = (max - min) * (nums.length - 1);

		// step3: adjust base result to get final result
		boolean meetMin = false;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != min)
				baseResult += (nums[i] - max);
			else {
				if (!meetMin) { // in case there're several min values. Only the FIRST ONE min doesn't involve in baseResult adjustment.
					meetMin = true;
					continue;
				}
				baseResult += (nums[i] - max);
			}
		}

		return baseResult;
	}

	/**
	 * AC: result is correct but timeout for [1,2147483647]
	 * ********注意，虽然直观的算，写起来也错了好几次。。。。********
	 * 最直观的写法。每次都老老实实的每个加一（除了max），然后比较是不是都相等了。
	 */
	public int minMoves_timeout(int[] nums) {
		if (nums == null || nums.length <= 1)
			return 0;
		int lastMaxIndex = 0, res = 0, min = 0, max = 0;
		while (true) {
			res++;
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < min)
					min = nums[i];
				if (nums[i] > max) {
					max = nums[i];
					lastMaxIndex = i;
				}
				nums[i]++;
			}
			nums[lastMaxIndex]--;
			if (min == max)
				break;
		}
		return res - 1;
	}

	public static void main(String[] args) {
		P453_MinimumMovesToEqualArrayElements p = new P453_MinimumMovesToEqualArrayElements();
		System.out.println(p.minMoves(new int[] { 1, -2 })); // 3
		System.out.println(p.minMoves(new int[] { 1, 2, 3 })); // 3
		System.out.println(p.minMoves(new int[] { 3, 33, 67, 100 })); // 191: (100-3)*3 + (33-100) + (67-100)
	}
}
