package LeetCode.round1.easy;
/**
 * 160926
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
Note:
1. All letters in hexadecimal (a-f) must be in lowercase.
2. The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; 
	otherwise, the first character in the hexadecimal string will not be the zero character.
3. The given number is guaranteed to fit within the range of a 32-bit signed integer.
4. You must not use any method provided by the library which converts/formats the number to hex directly.

Example 1:
Input:
26
Output:
"1a"

Example 2:
Input:
-1
Output:
"ffffffff"

 */
/**
 * Note: 3 bugs for notice!
 */
public class P405_ConvertANumberToHexadecimal {
	
	/**
	 * AC: 8ms
	 */
	public String toHex(int num) {
		if(num == 0)	//!!!! Note bug 1: edge case.
			return "0";
		
        StringBuilder sb = new StringBuilder();
        boolean zeroSkipped = false;
        int base = 15 << 28;	//!!!! Note bug 2: 想表示11110000000000000000000000000000，不能写成2^31+2^30+2^29+2^28，结果不时预期的。
        for (int i = 7; i >= 0; i--) {
        	//System.out.println(Integer.toBinaryString(base));
			int temp = base & num;
			temp = temp >>> i * 4;	//!!!! Note bug 3: 每次得到temp后，别忘了右移到最低4位，才能做0~e的映射。
			//System.out.println(temp);
			if(temp != 0){
				if(temp < 10)
					sb.append(temp);
				else{
					switch(temp){
						case 10:
							sb.append("a");
							break;
						case 11:
							sb.append("b");
							break;
						case 12:
							sb.append("c");
							break;
						case 13:
							sb.append("d");
							break;
						case 14:
							sb.append("e");
							break;
						case 15:
							sb.append("f");
							break;
					}
				}
				zeroSkipped = true;
			}else if(zeroSkipped)
				sb.append(temp);
			base = base >>> 4;
		}
        
        return sb.toString();
    }

	public static void main(String[] args) {
		P405_ConvertANumberToHexadecimal p = new P405_ConvertANumberToHexadecimal();
		System.out.println(p.toHex(0));
	}

}
