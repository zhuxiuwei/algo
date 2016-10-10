package LeetCode.round1.easy;
/**
 * 161010
Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class P415_AddStrings {

	/**
	 * one time AC. 21ms.
	 */
	public String addStrings(String num1, String num2) {
		if (num1 == null || num1.isEmpty())
			return num2;
		if (num2 == null || num2.isEmpty())
			return num1;

		// make sure num1 & num2 length are the same(to facility coding).
		StringBuilder sb = new StringBuilder();
		if (num1.length() > num2.length()) {
			for (int i = 0; i < num1.length() - num2.length(); i++)
				sb.append('0');
			sb.append(num2);
			num2 = sb.toString();
		} else {
			for (int i = 0; i < num2.length() - num1.length(); i++)
				sb.append('0');
			sb.append(num1);
			num1 = sb.toString();
		}
		sb.setLength(0); // reset sb

		int[] res = new int[num2.length() + 1];
		boolean jinwei = false;
		for (int i = num1.length() - 1; i >= 0; i--) {
			int i1 = Integer.parseInt(num1.charAt(i) + "");
			int i2 = Integer.parseInt(num2.charAt(i) + "");
			int temp = jinwei ? i1 + i2 + 1: i1 + i2; 
			res[i + 1] = temp % 10;
			jinwei = temp >= 10;
		}

		if(jinwei)
			sb.append(1);
		for (int i = 1; i < res.length; i++) 
			sb.append(res[i]);
		return sb.toString();
	}

	public static void main(String[] args) {
		P415_AddStrings p = new P415_AddStrings();
		System.out.println(p.addStrings("9999", "11"));
	}

}
