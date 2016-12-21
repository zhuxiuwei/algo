package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 161221
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input:
"tree"
Output:
"eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"
Output:
"cccaaa"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"
Output:
"bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */
public class P451_SortCharactersByFrequency {

	/**
	 * AC: 50ms, 44.39%.
	 */
	public String frequencySort(String s) {
		if(s == null)
			return null;
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> charToCountMap = new HashMap<Character, Integer>();
		Map<Integer, List<Character>> countToCharMap = new TreeMap<Integer, List<Character>>(Collections.reverseOrder());
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int count = charToCountMap.getOrDefault(c, 0);
			charToCountMap.put(c, count + 1);
		}
		for(Map.Entry<Character, Integer> entry: charToCountMap.entrySet()){
			int count = entry.getValue();
			List<Character> chars = countToCharMap.getOrDefault(count, new ArrayList<Character>());
			chars.add(entry.getKey());
			if(!countToCharMap.containsKey(count))
				countToCharMap.put(count, chars);
		}
		for(int count: countToCharMap.keySet()){
			List<Character> cc = countToCharMap.get(count);
			for (char c: cc) {
				for (int i = 0; i < count; i++) 
					sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Wrong. 只适用于全部由大小写字母组成的字符串。实际可以有别的字符。
	 * @param s
	 * @return
	 */
	public String frequencySort_wrong(String s) {
		if(s == null)
			return null;
		StringBuilder sb = new StringBuilder();
		int[] small = new int[26], large = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c >= 'a' && c <= 'z')
				small[c - 'a'] ++;
			else
				large[c - 'A'] ++;
		}
		Map<Integer, List<Character>> countToCharMap = new TreeMap<Integer, List<Character>>(Collections.reverseOrder());
		fillMap(countToCharMap, small, 'a');
		fillMap(countToCharMap, large, 'A');
		for(int count: countToCharMap.keySet()){
			List<Character> cc = countToCharMap.get(count);
			for (char c: cc) {
				for (int i = 0; i < count; i++) 
					sb.append(c);
			}
		}
		return sb.toString();
	}
	private void fillMap(Map<Integer, List<Character>> countToCharMap, int[] characters, char base){
		for (int i = 0; i < characters.length; i++) {
			int count = characters[i];
			if(count > 0){
				List<Character> list = countToCharMap.getOrDefault(count, new ArrayList<Character>());
				list.add((char)(base + i));
				if(!countToCharMap.containsKey(count))
					countToCharMap.put(count, list);
			}
		}
	}
	
	public static void main(String[] args) {
		P451_SortCharactersByFrequency p = new P451_SortCharactersByFrequency();
		System.out.println(p.frequencySort("2a554442f544asfasssffffasss"));		//sssssssffffff44444aaaa55522
	}

}
