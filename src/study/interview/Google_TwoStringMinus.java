package study.interview;
import java.util.Stack;

/**
 * Given two String, calculate minus result of the two strings. String may startWith '-' sign. Ignore '+' sign scenario.
 * @author Xiuwei Zhu
 * @date 15/4/8
 */
public class Google_TwoStringMinus {
	
	//Max number to process each time(to avoid Integer Max value overflow)
	int maxLength = 8;
	
	//define supported operations.
	enum Mode{add, minus}
	
	/**
	 * Given two String, calculate minus result of the two strings.
	 * @param a	String a
	 * @param b	String b
	 * @return	result of a-b
	 * @throws Exception 
	 */
	public String getResult(String a, String b) throws Exception{
		String result = "";
		
		//check if both parameters are valid
		checkParams(a);
		checkParams(b);
		
		//remove Redundant zero prefix. e.g. "000123" change to "123".
		a = removeRedundantZeroPrefix(a);
		b = removeRedundantZeroPrefix(b);
		
		//Determine which method to call: add, or minus
		boolean aStartWithMinus = a.startsWith("-");
		boolean bStartWithMinus = b.startsWith("-");
		
		if(!aStartWithMinus && !bStartWithMinus)	//positive positive
			result = addOrMinus(a, b, Mode.minus);
		else if(!aStartWithMinus && bStartWithMinus){	//positive negative
			b = b.substring(1);
			result = addOrMinus(a, b, Mode.add);
		}
		else if(aStartWithMinus && !bStartWithMinus){	//negative positive
			a = a.substring(1);
			result = addOrMinus(a, b, Mode.add);
			if(!result.equals("0"))
				result = "-" + result;
		}else if(aStartWithMinus && bStartWithMinus){	//negative negative
			a = a.substring(1);
			b = b.substring(1);
			result = addOrMinus(b, a, Mode.minus);
		}
		
		return result;
	}
	
	/**
	 * Get add/minus result of two string values.
	 * @param a	String a(absolute value)
	 * @param b String b(absolute value)
	 * @return a+b or a-b
	 */
	private String addOrMinus(String a, String b, Mode mode){
		
		//for minus operation, if absolute value of b is larger than absolute value of a, exchange a & b, and add '-' to final result.
 		boolean abExchanged = false;
		if(mode == Mode.minus && !compareTwoStringIntValue(a, b)){	//When minus, If abs[a] < abs[b], exchange a, b
			String temp = a;
			a = b;
			b = temp;
			abExchanged = true;
		}
		
		Stack<String> stack = new Stack<String>();	//Use stack to store each temporary result
		int largerLength = a.length() >= b.length() ? a.length(): b.length();
		int loopTime = largerLength % maxLength == 0 ? largerLength/maxLength :  largerLength/maxLength + 1;
		int endA = a.length() - 1;
		int endB = b.length() - 1;
		int startA = -1;
		int startB = -1;
		boolean jinWei = false;
		for(int i = 0; i < loopTime; i ++){
			int part1 = 0;
			if(endA > 0){
				startA = endA - maxLength + 1 >= 0 ? endA - maxLength + 1: 0;
				part1 = Integer.parseInt(a.substring(startA, endA + 1));
			}else if(endA == 0)
				part1 = Integer.parseInt(a.substring(endA, 1));
			
			int part2 = 0;
			if(endB > 0){
				startB = endB - maxLength + 1 >= 0 ? endB - maxLength + 1: 0;
				part2 = Integer.parseInt(b.substring(startB, endB + 1));
			}else if(endB == 0)
				part2 = Integer.parseInt(b.substring(endB, 1));
			
			//For add operation
			if(mode == Mode.add){
				int temp = part1 + part2;
				if(jinWei)
					temp ++;
				if((temp + "").length() > maxLength){
					stack.push(addZeroPrefix(temp + "".substring(1), maxLength));
					jinWei = true;
				}
				else{
					stack.push(addZeroPrefix(temp + "", maxLength));
					jinWei = false;
				}
			}
			
			//For Minus operation
			if(mode == Mode.minus){
				int temp = part1 - part2;
				if(jinWei)
					temp --;
				if(temp < 0){
					stack.push(addZeroPrefix((int)Math.pow(10, maxLength) + temp + "", maxLength));
					jinWei = true;
				}else{
					stack.push(addZeroPrefix(temp + "", maxLength));
					jinWei = false;
				}
			}
			endA = startA - 1;
			endB = startB - 1;
		}
		
		//assemble result
		StringBuilder sb = new StringBuilder();
		if(jinWei)
			sb.append(1);
		while(!stack.isEmpty()){
			sb.append(stack.pop());
		}
		String result = removeRedundantZeroPrefix(sb.toString());
		if(abExchanged)
			result = "-" + result;
		
		return result;
	}
	
	/**
	 * Check if the parameter is valid: number, or number starts with a minus sign.
	 * @param a	String a
	 * @throws Exception Throw exception if parameter is invalid
	 */
	private void checkParams(String a) throws Exception{
		if(a == null || a.isEmpty())	//empty string
			throw new Exception("Invalid parameter: empty String!");
		
		for(int i = 0; i < a.length(); i ++){
			char c = a.charAt(i);
			if(c >= '0' && c <= '9')	//valid normal number
				;
			else if (c == '-' && i == 0 && a.length() > 1)	//start with '-' sign.
				;
			else	//contain invalid number
				throw new Exception("Invalid parameter, not a valid number: " + a);
		}
	}
	
	/**
	 * add zero as prefix to a string.
	 * @param a	Source String.
	 * @param targetLength 
	 * @return e.g. a = "123", targetLength=5, return 00123 
	 */
	private String addZeroPrefix(String a, int targetLength){
		int temp = targetLength - a.length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < temp; i ++){
			sb.append("0");
		}
		return sb.append(a).toString();
	}
	
	/**
	 * After final result is calculated, remove redundant '0' prefix in result. e.g. change 000000123 to 123.
	 */
	private String removeRedundantZeroPrefix(String source){
		boolean findNoneZeroChar = false;
		for(int i = 0; i < source.length(); i++){
			char c = source.charAt(i);
			if(c != '0'){	//find 1st char that is not '0'
				findNoneZeroChar = true;
				if(i == 0)
					break;	//no redundant '0'
				else{
					source = source.substring(i);
					break;
				}
			}
		}
		if(!findNoneZeroChar)	//means string only contains 0, like '00000'
			return "0";
		return source;
	}
	
	/**
	 * If Integer a value >= Integer b value, return true; else return false;
	 * @param a
	 * @param b
	 * @return If Integer a value >= Integer b value, return true; else return false;
	 */
	private boolean compareTwoStringIntValue(String a, String b){
		if(a.equals(b) || a.length() > b.length())
			return true;
		else if(a.length() < b.length())
			return false;
		else{
			for(int i = 0; i < a.length(); i ++){
				char c1 = a.charAt(i);
				char c2 = b.charAt(i);
				if(c1 == c2)
					;
				else if(c1 > c2)
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	/**
	 * Test Cases
	 */
	public static void main(String[] args) throws Exception {
		Google_TwoStringMinus g = new Google_TwoStringMinus();
		//positive positive
		System.out.println(g.getResult("010000000000000000000000000001", "0002"));	//9999999999999999999999999999	
		System.out.println(g.getResult("22222222", "111111111"));	//-88888889
		
		//negative negative
		System.out.println(g.getResult("-01", "-100000012"));	//100000011
		System.out.println(g.getResult("-10000000", "-20000000000000000"));		//19999999990000000
		
		//positive negative
		System.out.println(g.getResult("10000111111111111111111111002121231212", "-2121231211"));	//10000111111111111111111111004242462423
		System.out.println(g.getResult("22222222", "-100000000"));	//122222222
		
		//negative positive
		System.out.println(g.getResult("-111111111", "100000000"));		//-211111111
		System.out.println(g.getResult("-100000000", "111111111"));	//-211111111
		
		//contain 0
		System.out.println(g.getResult("0000", "-0"));	//0
		System.out.println(g.getResult("-0", "0")); //0
		System.out.println(g.getResult("0", "-00"));	 //0
		System.out.println(g.getResult("-0", "-0"));	//0
		System.out.println(g.getResult("0", "-111111111"));	//111111111
		System.out.println(g.getResult("-111111111", "0"));	//-111111111
		System.out.println(g.getResult("111111111", "0"));	//111111111
		System.out.println(g.getResult("0", "111111111"));	//-111111111
		
		//exception
//		System.out.println(g.getResult(null, "0")); //java.lang.Exception: Invalid parameter: empty String!
		System.out.println(g.getResult("1a2", "0")); // java.lang.Exception: Invalid parameter, not a valid number: 1a2
	}

}
