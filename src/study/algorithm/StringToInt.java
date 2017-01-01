package study.algorithm;
/***
 * @author xwzhu
 * @date 150324
 * 把一个字符串转int。支持溢出判断。支持非字符检查。支持正负符号
 */
public class StringToInt {
	
	public int convert(String s) throws Exception{
		Integer result = 0;
		boolean negative = false;
		if(!outOfRange(s)){
			if(s.startsWith("-")){
				negative = true;
				s = s.substring(1);
			}else if(s.startsWith("+")){
				s = s.substring(1);
			}
			
			//下面这段是核心
			int base = 1;
			for(int i = s.length() - 1; i >=0 ; i --){
				char c = s.charAt(i);
				if(c >= '0' && c <= '9'){
					result = result + base*((int)(c - '0'));
					base = base * 10;
				}else{
					throw new Exception("Not a leagal Number!");
				}
			}
				
		}
		if(negative)
			result = result * -1;
		return result;
	}
	
	//检测是否越界。
	public boolean outOfRange(String s) throws Exception{
		boolean result = false;
		if(s.startsWith("-")){	//负数
			String min = Integer.MIN_VALUE + "";
			if(min.length() < s.length())
				throw new Exception("Smaller than min value");
			else if (min.length() > s.length() )
				return false;
			else{
				for (int i = 0; i < s.length(); i++) {
					if(s.charAt(i) > min.charAt(i))
						throw new Exception("Smaller than min value");
				}
			}
		}else{	//正数
			if(s.startsWith("+"))
				s = s.substring(1);
			String max = Integer.MAX_VALUE + "";
			if(max.length() < s.length())
				throw new Exception("Bigger than max value");
			else if (max.length() > s.length())
				return false;
			else{
				for (int i = 0; i < s.length(); i++) {
					if(s.charAt(i) > max.charAt(i))
						throw new Exception("Bigger than max value");
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(Integer.MIN_VALUE);
		StringToInt sti = new StringToInt();
		int i;
		try {
			i = sti.convert("2147483640");
			System.out.println(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
