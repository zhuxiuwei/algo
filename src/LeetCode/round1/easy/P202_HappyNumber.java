package LeetCode.round1.easy;
/**
 * 161206
Write an algorithm to determine if a number is "happy".
A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
Example: 19 is a happy number
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
public class P202_HappyNumber {

	/**
	 * ！！！！ 有几个bug注意。
	 * AC: 2ms, 84.5%
	 * @param n
	 * @return
	 */
	public boolean isHappy(int n) {
		if(n <= 0)
			return false;
		if(n == 1)
			return true;
		
		boolean reachSingle = false;	//note bug. For 1111111
		while(n < 10){
			reachSingle = true;
			n *= n;
		}
		
		int sum = 0;
		while(true){
	        while(n > 0){
	        	int low = n % 10;
	        	sum += low * low;
	        	n = n / 10;
	        }
	        if(sum >= 10){
	        	n = sum;
	        	sum = 0;
	        }
	        else{
	        	if(reachSingle)	
	        		break;
	        	else{
	        		if(sum == 1)	//note bug。不加这个，19就死循环了。
	        			return true;
	        		while(sum < 10)
	        			sum *= sum;
	        		reachSingle = true;	
	        	}
	        }
		}
        return sum == 1;
    }
	
	public static void main(String[] args) {
		P202_HappyNumber p = new P202_HappyNumber();
		System.out.println(p.isHappy(19));	//true
		System.out.println(p.isHappy(7));	//true
		System.out.println(p.isHappy(1111111));	//true
	}

}
