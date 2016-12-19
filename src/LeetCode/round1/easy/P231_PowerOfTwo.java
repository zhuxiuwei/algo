package LeetCode.round1.easy;
/**
 * 161201
Given an integer, write a function to determine if it is a power of two.
 */
public class P231_PowerOfTwo {

	/**
	 * AC: 2ms, 19.2%.
	 * @param num
	 * @return
	 */
	public boolean isPowerOfTwo_NonLoop_refer(int n){
		return (n > 0) && ((n & (n - 1)) == 0);
	}
	
	/**
	 * AC: 2ms, 20%.
	 */
	public boolean isPowerOfTwo(int n) {
		if(n <= 0)
			return false;
		
		int k = 1;
		for (int i = 0; i < 32; i ++) {
			if((k ^ n) == 0)
				return true;
			k = k << 1;
		}
		return false;
    }
	
	public static void main(String[] args) {
		P231_PowerOfTwo p = new P231_PowerOfTwo();
		System.out.println(p.isPowerOfTwo_NonLoop_refer(8192));
		System.out.println(p.isPowerOfTwo(8192));
	}

}
