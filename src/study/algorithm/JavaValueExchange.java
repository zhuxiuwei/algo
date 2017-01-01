package study.algorithm;
/**
 * 基本类型是否是值传递。
 * @author xwzhu
 *
 */
public class JavaValueExchange {

	public static void exchange(int i, int j){
		int temp = i;
		 i = j;
		 j = temp;
	}
	
	public static void exchange(String a, String b){
		String temp = a;
		 a = b;
		 b = temp;
	}
	
	public static void change(int i){
		i = i + 1;
	}
	
	public static void change(String s){
		s = s + " changed";
	}
	
	public static void main(String[] args) {
		
		int i = 1;
		int j = 2;
		JavaValueExchange.exchange(i, j);
		System.out.println(i + " " + j);	//1 2

		String s1 = "a";
		String s2 = "b";
		JavaValueExchange.exchange(s1, s2);
		System.out.println(s1 + " " + s2);	//a b
		
		int k = 1;
		JavaValueExchange.change(k);	
		System.out.println(k);		//1
		
		String s3 = "c";
		JavaValueExchange.change(s3);
		System.out.println(s3);		//c
		
	}

}
