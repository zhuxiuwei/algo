package LeetCode.round1.medium;

/**
 * 160911, 160912
Given an array of integers, every element appears three times except for one. Find that single one. (注：这个single one只出现一次，不包括2次。)
Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class P137_SingleNumberII {

	/**
	 * Solution that needs assistant length=32 array (constant memory usage).
	 * Watch out for bug: bug when Integer.MIN_VALUE is the single one. e.g. {-2147483648, 3, 3, 3} may wrongly return -2147483647.
	 */
	public int singleNumber_withConstantMemory(int[] nums) {
		int[] count = new int[32];
		
		//assign 'count' array.
		for (int n: nums) {
			for(int i =0; n != 0 ; i ++){
				if((n & 1) > 0)
					count[i] ++; 
				n = n >>> 1;	//!!!!注意： 必须用无符号右移。否则，-1 >> 1  永远是 -1， 死循环了。
			}
		}
		
		//get result based on 'count' array values
		int res = 0;
		if(count[31] %3 == 0){	//0 or positive number
			for (int i = 0; i < count.length; i++) {
				if(count[i] % 3 == 1)
					res += Math.pow(2, i);
			}
		}else{	//negative number
			boolean jiewei = true;
			for (int i = 0; i < count.length; i++) {
				if(count[i] % 3 == 1){
					if(jiewei){
						if(i == 31)	//!!!!注意BUG fix: 不加这个判断，当唯一的数是Integer.MIN_VALUE时，会出错。  {-2147483648, 3, 3, 3} 错误返回-2147483647
							return Integer.MIN_VALUE;
						res += Math.pow(2, i);	//2^31=2147483648，正好为Integer.MAX_VALUE+1。超出了正数的表达范围。不能按照正常的处理逻辑来考虑Integer.MAX_VALUE（即不能由正数的补码+1来获得），需要特殊处理。
						jiewei = false;
					}
				}else{
					if(!jiewei)
						res += Math.pow(2, i);
				}
			}
			
			res = 0 - res;	//负数 
		}
		
		return res; 
    }
	
	/*
	 * !!!Fail: 对负数，不能正常工作！
	 * Java中负数的二进制表示：http://blog.csdn.net/garybrother/article/details/5991918
	 */
	public int singleNumber_constantMemory_fail(int[] nums) {
		int[] count = new int[32];
		
		//assign 'count' array.
		for (int n: nums) {
			for(int i =0; n > 0 ; i ++){
				if((n & 1) > 0)		// <- bug here! Can not handle negative number。
					count[i] ++;
				n = n >> 1;
			}
		}
		
		int res = 0;
		//get result based on 'count' array values
		for (int i = 0; i < count.length; i++) {
			if(count[i] % 3 != 0)
				res += Math.pow(2, i);
		}
		
		return res; 
    }
	
	public static void main(String[] args) {
		P137_SingleNumberII p = new P137_SingleNumberII();
		System.out.println(p.singleNumber_withConstantMemory(new int[]{-2,-2,1,1,-3,1,-3,-3,-9,-2}));
		System.out.println(p.singleNumber_withConstantMemory(new int[]{3,-2147483647,3,3,}));
		System.out.println(p.singleNumber_withConstantMemory(new int[]{3,3,-2147483648,3}));
		
		
		System.out.println(Integer.toBinaryString(2147483647)); 
		System.out.println(Integer.toBinaryString(-2147483646));
		System.out.println(Integer.toBinaryString(-2147483647));
		System.out.println(Integer.toBinaryString(-2147483648)); 
		System.out.println((int)Math.pow(2, 31)); 
	}
}

/*
 * 1. 先实现了需要constant memory的solution。实现的过程中，注意两个bug:
 * 		a. 对于负数的处理。（这里需要熟悉Java中对负数的二进制表示。）。同时注意用到了无符号右移操作。
 * 		b. 对于Integer.MIN_VALUE边界值的处理需要注意。  Integer.MAX_VALUE + 1 = Integer.MIN_VALUE。 Integer.MAX_VALUE只用到了31个bit，故表达的数字的绝对值，比Integer.MIN_VALUE是要小1的。
 */
