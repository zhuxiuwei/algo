package study.interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Amazon online test。 170530
 * Get all Anagrams in a string.
 */
public class I170530_Amazon_GetAnagramIndices {
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	// RETURN AN EMPTY LIST IF NO ANAGRAM FOUND
	public List<Integer> getAnagramIndices(String haystack, String needle) {
		List<Integer> res = new LinkedList<>();
		if (haystack == null || needle == null || haystack.length() == 0 || needle.length() == 0 || needle.length() > haystack.length())
			return res;
		Map<Character, Integer> map = new HashMap<>();
		for (char c : needle.toCharArray()){
			int val = map.getOrDefault(c, 0) + 1;
			map.put(c, val);
		}
		int counter = map.size(), left = 0, right = 0;
		while (right < haystack.length()) {
			char c = haystack.charAt(right);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				if (map.get(c) == 0)	//one character match
					counter--;
			}
			right++;

			while (counter == 0) { // a matched anagram found.
				char leftC = haystack.charAt(left);
				if (map.containsKey(leftC)) {
					map.put(leftC, map.get(leftC) + 1);
					if (map.get(leftC) > 0) 
						counter++;
				}
				if (right - left == needle.length())	//exact start index located.
					res.add(left);
				left++;
			}

		}
		return res;
	}
	
	public static void main(String[] args) {
		I170530_Amazon_GetAnagramIndices a = new I170530_Amazon_GetAnagramIndices();
		System.out.println(a.getAnagramIndices("abdcghbaabcdij", "bcda")); //[0,8]
		System.out.println(a.getAnagramIndices("bbbababaaabbbb", "ab")); //[2,3,4,5,6,9]
		System.out.println(a.getAnagramIndices("abab", "ab"));	//[0,1,2]
		System.out.println(a.getAnagramIndices("cbaebabacd", "abc"));	//[0,6]
		System.out.println(a.getAnagramIndices("abacbabc", "abc"));	//[1,2,3,5]
	}
}
