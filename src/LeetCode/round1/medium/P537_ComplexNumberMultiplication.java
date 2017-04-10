package LeetCode.round1.medium;

/**
 * 170409
 * @author Zhu xiuwei
Given two strings representing two complex numbers.
You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.

Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
 */
public class P537_ComplexNumberMultiplication {

	/**
	 * 1 Time AC. 6ms.
	 */
	public String complexNumberMultiply(String a, String b) {
		int[] ar = getAB(a);
		int[] br = getAB(b);
		int real = ar[0] * br[0] - ar[1] * br[1];
		int complex = ar[0] * br[1] + ar[1] * br[0];
		return real + "+" + complex + "i";
    }
	
	/**
	 * Parse complex numbers string.
	 * @param s complex numbers string
	 * @return real number and complex numbers in a array.
	 */
	private int[] getAB(String s){
		int[] res = new int[2];
		res[0] = Integer.parseInt(s.substring(0, s.indexOf("+")));
		res[1] = Integer.parseInt(s.substring(s.indexOf("+") + 1, s.indexOf("i")));
		return res;
	}
	
	public static void main(String[] args) {
		P537_ComplexNumberMultiplication p = new P537_ComplexNumberMultiplication();
		System.out.println(p.complexNumberMultiply("1+1i", "1+1i"));	//0+2i
		System.out.println(p.complexNumberMultiply("1+-1i", "1+-1i"));	//0+-2i
	}

}
