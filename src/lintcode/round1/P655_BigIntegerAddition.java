package lintcode.round1;

import java.util.Stack;

/**
 * 170613 Medium
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 注意事项
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

样例
Given num1 = "123", num2 = "45"
return "168"
 */
public class P655_BigIntegerAddition {
	
	/**
	 * ！！！！！！！！1个注意。！！！！！！！！
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
    	if(num1 == null)
    		return num2;
    	if(num2 == null)
    		return num1;
    	
    	boolean jinwei = false;
    	Stack<Integer> s1 = new Stack<Integer>();
    	Stack<Integer> s2 = new Stack<Integer>();
    	Stack<Integer> res = new Stack<Integer>();
    	for (int i = 0; i < num1.length(); i++) 
			s1.push(Integer.parseInt(num1.charAt(i) + ""));
    	for (int i = 0; i < num2.length(); i++) 
			s2.push(Integer.parseInt(num2.charAt(i) + ""));
    	
    	while(!s1.isEmpty() || !s2.isEmpty()){
    		int n1 = s1.isEmpty() ? 0 : s1.pop();
    		int n2 = s2.isEmpty() ? 0 : s2.pop();
    		int sum = n1 + n2;
    		if(jinwei)
    			sum ++;
    		if(sum >= 10){
    			sum -= 10;
    			jinwei = true;
    		}else
    			jinwei = false;
    		res.push(sum);
    	}
    	StringBuilder sb = new StringBuilder();
    	if(jinwei)	//！！！！！！！！注意这个1要最先append。
    		sb.append("1");
    	while(!res.isEmpty()){
    		sb.append(res.pop());
    	}
    	return sb.toString();
    }
    
	public static void main(String[] args) {
		P655_BigIntegerAddition p = new P655_BigIntegerAddition();
		System.out.println(p.addStrings("45", "123"));
		System.out.println(p.addStrings("99", "9999"));
	}

}
