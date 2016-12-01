package LeetCode.round1.medium;

import java.util.Stack;

/**
 * 161128-1201
Given an encoded string, return it's decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Examples:
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class P394_DecodeString {

	/**
	 * 经过decodeString_Wrong()的失败，明白了所有可能的情况后，就可以设计好Module，然后实现就不是大问题了。比较顺利。 
	 * ！！！唯一一个容易出bug的地方在第36行的while(i < s.length())找配对Sting里。尤其是第42,43行。
	 * AC: 4ms, 42%.
	 */
	public String decodeString(String s) {
		StringBuilder sb = new StringBuilder(), tempSB = new StringBuilder();
		Stack<StringCount> stack = new Stack<StringCount>();
		int count = -1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c >= '0' && c <= '9'){	//number
				tempSB.append(c);
			}else if(c == '['){
				//time to push stack
				count = Integer.parseInt(tempSB.toString());
				tempSB.setLength(0);
				i ++;
				while(i < s.length()){	//找到接下来配对的string。
					char d = s.charAt(i);
					if(d != ']' && !(d >= '0' && d <= '9'))	//not number or ]. must be string. (impossible to be [)
						tempSB.append(d);
					else{
						//!!!!!容易bug的地方！！！！第67行情况类似。
						i --;	//不是string，记得要回退。 
						break;
					}
					i ++;
				}
				StringCount sc = new StringCount(count, tempSB.toString());
				stack.push(sc);
				count = -1; tempSB.setLength(0);
			}else if(c == ']'){	//time to pop one.
				StringCount sc = stack.pop();
				StringBuilder tempsb2 = new StringBuilder();
				for (int j = 0; j < sc.count; j++) 
					tempsb2.append(sc.content);
				if(!stack.isEmpty()){
					StringCount sc2 = stack.peek();
					sc2.content += tempsb2.toString();
				}else
					sb.append(tempsb2);
			}else{	//special string repeat only once. 出现在这里的string，一定都是没有 数字+[ 开头的，比如"sd2[f2[e]g]i"里的sd,g,i。 
				tempSB.append(c);
				i++;
				while(i < s.length()){
					char d = s.charAt(i);
					if(d != ']' && !(d >= '0' && d <= '9'))	//not number or ]. must be string. (impossible to be [)
						tempSB.append(d);
					else{
						i --;
						break;
					}
					i ++;
				}
				if(!stack.isEmpty()){
					StringCount sc2 = stack.peek();
					sc2.content += tempSB.toString();
				}else
					sb.append(tempSB);
				count = -1; tempSB.setLength(0);
			}
		}
		return sb.toString();
	}
	class StringCount{
		private int count;
		private String content;
		public StringCount(int count,  String content){
			this.content = content;
			this.count = count;
		}
		@Override
		public String toString(){
			return count + ": " + content;
		}
	}
	
	/**
	 * 花了不少时间，垃圾代码。拆西墙补东墙的状态。发现一个新fail case，修改，老的case又过不了。推翻重做吧。
	 * 失败的原因，是可能出现的情况没有想清楚。导致一出现一个新情况，就开始打补丁，越高越乱。想清楚后，重新再写，就好很多了。
	 * @param s
	 * @return
	 */
	public String decodeString_Wrong(String s) {
        StringBuilder sb = new StringBuilder(), tempSb = new StringBuilder(), instackSb = new StringBuilder();
        int tempCount = 0;
        int appendDirection = 0;	//0: left, 1: right
        String tempS = "", tempInstackS = "";
        Stack<StringCount> stack = new Stack<StringCount>();
        boolean isInNum = false, isInString = false;
        for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c >= '0' && c <= '9'){	//number
				if(isInString){
					tempS = tempSb.toString();
					isInString = false;
					StringCount sc = new StringCount(tempCount, tempS);
					if(sc.count == 0) sc.count = 1;
					stack.push(sc);
					tempCount = 0;
					tempS = "";
				}
				if(!isInNum){
					tempSb.setLength(0);
					isInNum = true;
				}
				tempSb.append(c);
			}else if(c== '['){	//[
				tempCount = tempCount == 0 ? Integer.parseInt(tempSb.toString()): tempCount * Integer.parseInt(tempSb.toString());
				isInNum = false;
			}else if(c == ']'){	//]
				if(isInString){
					tempS = tempSb.toString();
					isInString = false;
					StringCount sc = new StringCount(tempCount, tempS);
					if(sc.count == 0) sc.count = 1;
					stack.push(sc);
					tempCount = 0;
					tempS = "";
				}
				while(!stack.isEmpty()){
					StringCount sc = stack.pop();
					
					//!!!Things got complicate when we consider appendDirection...
					for (int j = 0; j < sc.count; j++) {
						if(appendDirection == 0)
							instackSb.append(sc.content);
						else if(appendDirection == 1)
							instackSb.append(sc.content + tempInstackS);
					}
					if(appendDirection == 1)
						tempInstackS = instackSb.toString();
					else if(appendDirection == 0)
						tempInstackS = tempInstackS + instackSb.toString();
					
					instackSb.setLength(0);
					if(stack.isEmpty()){	//time to append to final result sb
						sb.append(tempInstackS);
						tempInstackS = "";
					}else{
						if(i != s.length() - 1){
							if(s.charAt(i + 1) == ']'){
								//], append right
								appendDirection = 1;
							}else{
								//number, append left
								appendDirection = 0;
							}
						}else{
							
						}
					}
				}
			}
			else{		//string
				if(!isInString){
					tempSb.setLength(0);
					isInString = true;
				}
				tempSb.append(c);
			}
		}
        if(!s.endsWith("]"))	//fix for s not end with ']', like '2[abc]3[cd]ef'. If not add this, will miss the last 'ef' wrongly.
        	sb.append(tempSb);
        return sb.toString();
    }
	
	public static void main(String[] args) {
		P394_DecodeString p = new P394_DecodeString();
		System.out.println(p.decodeString("3[a]2[bc]"));	//aaabcbc
		System.out.println(p.decodeString("3[a2[c]2[a]]"));		//accaaaccaaaccaa
		System.out.println(p.decodeString("3[ab2[c2[f3[d]]]]"));	//abcfdddfdddcfdddfdddabcfdddfdddcfdddfdddabcfdddfdddcfdddfddd
		System.out.println(p.decodeString("2[2[b]]"));	//bbbb
		System.out.println(p.decodeString("2[abc]3[cd]ef"));	//abcabccdcdcdef  
		System.out.println(p.decodeString("sd2[f2[e]g]i"));		//sdfeegfeegi
	}

}
