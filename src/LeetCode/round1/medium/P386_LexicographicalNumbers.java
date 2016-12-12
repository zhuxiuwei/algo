package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 161210 Given an integer n, return 1 - n in lexicographical order. For
 * example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9]. Please optimize
 * your algorithm to use less time and space. The input size may be as large as
 * 5,000,000.
 */
public class P386_LexicographicalNumbers {

	/**
	 * DFS. Refer:　https://discuss.leetcode.com/topic/55377/simple-java-dfs-solution/2
	 * AC: 337ms, 14.4%
	 */
	public List<Integer> lexicalOrder(int n) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 1; i < 10 ; i++) 
			dfs(n, i, res);
		return res;
	}
	private void dfs(int n, int i, List<Integer> res){
		if(i > n)
			return;
		
		res.add(i);
		for(int j = 0; j < 10; j ++){
            if(10* i + j <= n)
            	dfs(n, 10* i + j, res);
        }
	}
	
	/**
	 * 想写一个O(n)的写法。写一会不想写了。一个solution见https://discuss.leetcode.com/topic/55184/java-o-n-time-o-1-space-iterative-solution-130ms/2
	 * @param n
	 * @return
	 */
	public List<Integer> lexicalOrder_wrong2(int n) {
		List<Integer> res = new ArrayList<Integer>();

		if (n < 10) {
			for (int i = 1; i <= n; i++)
				res.add(i);
			return res;
		}

		int count = (n + "").length();
		int base = 10;
		res.add(1);
out: 	while (true) {
			int temp = (int) 10 * res.get(res.size() - 1);
			if (temp <= n) {
				res.add(temp);
				base *= 10;
				continue;
			}else
				temp /= 10;
			for (int j = 1; j <= 9; j++) {
				if (temp + j <= n)
					res.add(temp + j);
				else
					break out;
			}
			res.add(res.get(res.size() - 1) / 10 + 1) ;
			base /= 10;
		}
		return res;
	}

	/**
	 * 理解错误。如应该是1,10,100,11、、、19.而不是1,10,11.。。19,100。
	 */
	public List<Integer> lexicalOrder_wrong(int n) {
		List<Integer> res = new ArrayList<Integer>();

		if (n < 10) {
			for (int i = 1; i <= n; i++)
				res.add(i);
			return res;
		}

		int count = (n + "").length();
		int high = (int) (n / Math.pow(10, count - 1));
		for (int i = 1; i <= 9; i++) {
			res.add(i); // For only 1 number.
			for (int j = 1; j < count; j++) {
				if (j == count - 1 && i > high) // when reach highest number
					break;
				int max = (int) Math.pow(10, j);
				int base = i * max;
				for (int k = 0; k < max; k++) {
					int temp = base + k;
					if (temp <= n)
						res.add(base + k);
					else
						break;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		P386_LexicographicalNumbers p = new P386_LexicographicalNumbers();
		System.out.println(p.lexicalOrder(132));
	}

}
