package LeetCode.round1.medium;
/**
 * 161219
There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.
We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
Find the last number that remains starting with a list of length n.

Example:
Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6
Output:
6
 */
public class P390_EliminationGame {

	/**
	 * O(lgn). Refer: https://discuss.leetcode.com/topic/59293/java-easiest-solution-o-logn-with-explanation
	 * AC: 79ms, 73.78%
	 */
	public int lastRemaining(int n) {
		boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            if (left || remaining % 2 ==1) {
                head = head + step;
            }
            remaining = remaining / 2;
            step = step * 2;
            left = !left;
        }
        return head;
	}
	
	/**
	 * O(n)比较直观法。在1000000000时AC超时但结果对。
	 * 对返回结果，注意2个bug。得错几次。
	 * @param n
	 * @return
	 */
	public int lastRemaining_ON_Timeout(int n) {
		if(n <= 0)
			return 0;
		if(n == 1)	//！！！注意bug: 1要特殊对待。！！！！
			return 1;
		
		int idx = -2, base = 2;
		boolean right = true;
		for (int i = 0; i < n - 1; i++) {
			if(right){
				idx += base;
				if(idx + base > n - 1){	 //need to change direction now
					idx = ((idx + base/2) <= (n - 1)) ? idx + base/2: idx - base/2;
					base *= 2;
					right = false;
					i++;	//别忘了加1
				}
			}else{
				idx -= base;
				if(idx - base < 0){	 //need to change direction now
					idx = ((idx - base/2) >= 0) ? idx - base/2: idx + base/2;
					base *= 2;
					right = true;
					i++;
				}
			}
		}
		
		//！！！！以下判断返回结果时，注意bug！！！！要考虑是当前index，还是下一个index。而且要按照left、right两种情况来考虑。而且别忘了最后要加1。
		int res = 0;
		if(right)
			res = idx + 1 + base/2 > n - 1? idx + 1 : idx + 1 + base/2 ;
		else
			res = idx + 1 - base/2 <= 0? idx + 1 : idx + 1 - base/2 ;
		return res;
	}
	
	public static void main(String[] args) {
		P390_EliminationGame p = new P390_EliminationGame();
		long start = System.currentTimeMillis();
		System.out.println(p.lastRemaining_ON_Timeout(1000000000));	//534765398
		System.out.println("Time in ms: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		System.out.println(p.lastRemaining(1000000000));	//534765398
		System.out.println("Time in ms: " + (System.currentTimeMillis() - start));
	}

}
