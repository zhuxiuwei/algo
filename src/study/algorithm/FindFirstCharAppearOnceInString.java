/**
 * 找到字符串中，第一个出现一次的字符。如bbbasdd返回a
 * @author xwzhu
 * @date 2015.03.17
 */
package study.algorithm;

import java.util.HashMap;
import java.util.Map;

public class FindFirstCharAppearOnceInString {
	//时间复杂度是O(n^2)遍历法，也就是从头开始取字符串中的一个字符，将其与其后的所有字符比较，如果有相同的字符，那么就证明它不是只出现一次的字符。当第一次出现遍历完其后字符并且没有重复时，表明这个字符就是第一个只出现一次的字符。
	public static char method1(char[] chars){
		if(chars == null || chars.length == 0)
			return ' ';
		else if(chars.length == 1){
			return chars[0];
		}else{
			for(int i = 0; i < chars.length; i ++){
				char c = chars[i];
				for (int j = 0; j < chars.length; j++) {
					if(i == j){
						if(i == chars.length - 1)	//找到了，符合条件的元素是数组最后一个元素。
							return chars[i];
					}
					else{
						char c2 = chars[j];
						if(c == c2)
							break;
						if(j == chars.length - 1)	//找到了，符合条件的元素不是数组最后一个元素。
							return chars[i];
					}
				}
			}
		}
		return ' ';
	}
	
	//使用哈希表,时间复杂度O(n)。定义哈希表的键值(Key)是字符的ASCII值，而值(Value)是该字符出现的次数。同时我们需要扫描两次字符串，第一次扫描字符串时，每扫描到一个字符就在哈希表的对应项中把次数加1。
	//接下来第二次扫描的时候，每扫描到一个字符就能在哈希表中得到该字符出现的次数。找出第一个Value为1的那个key就是我们需要找到那个字符。
	public static char method2(char[] chars){
		if(chars == null || chars.length == 0)
			return ' ';
		else{
			Map<Character, Integer> times = new HashMap<Character, Integer>();
			
			//first round scan
			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				if(!times.containsKey(c))
					times.put(c, 1);
				else
					times.put(c, times.get(c) + 1);
			}
			
			//second round scan
			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				int time = times.get(c);
				if(time == 1)
					return c;	//找到了
			}
		}
		return ' ';	
	}

	public static void main(String[] args) {
		char[] ss = {'a','a','b','d'};	//b
		char[] ss2 = {'a','a','b'};		//b
		char[] ss3 = {'b', 'a','a'};	//b
		char[] ss4 = {'a','b'};		//a
		char[] ss5 = {'a', 'a'};	//empty
		char[] ss6 = {'a'};		//a
		char[] ss7 = {};		//empty
		char[] ss8 = null;		//empty
		System.out.println(FindFirstCharAppearOnceInString.method1(ss));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss2));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss3));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss4));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss5));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss6));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss7));
		System.out.println(FindFirstCharAppearOnceInString.method1(ss8));
		System.out.println("--------");
		System.out.println(FindFirstCharAppearOnceInString.method2(ss));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss2));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss3));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss4));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss5));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss6));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss7));
		System.out.println(FindFirstCharAppearOnceInString.method2(ss8));
		System.out.println("--------");
	}
}
