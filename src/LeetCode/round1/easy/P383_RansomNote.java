package LeetCode.round1.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 160917
Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   
Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.

Note:
You may assume that both strings contain only lowercase letters.
canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
 */
public class P383_RansomNote {

	/**
	 * Optimal solution, AC: 18 ms
	 * Time: O(n). Space: 32 * 26 byte constant space.
	 */
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] alphabet = new int[26];
		
		//Construct the alphabet
		for (int i = 0; i < magazine.length(); i++) {
			alphabet[magazine.charAt(i) - 'a'] ++;
		}
		
		//look up
		for (int i = 0; i < ransomNote.length(); i++) {
			int idx = ransomNote.charAt(i) - 'a';
			if(alphabet[idx] <= 0)
				return false;
			else
				alphabet[idx] --;
		}
		
        return true;	
    }
	
	/**
	 * Use HashTable, AC: 80 ms
	 * Time: O(n). Space: O(n)
	 */
	public boolean canConstruct_HashTable(String ransomNote, String magazine) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		//Construct map
		for (int i = 0; i < magazine.length(); i++) {
			if(!map.containsKey(magazine.charAt(i)))
				map.put(magazine.charAt(i), 1);
			else
				map.put(magazine.charAt(i), map.get(magazine.charAt(i)) + 1);
		}
		
		//look up
		for (int i = 0; i < ransomNote.length(); i++) {
			if(!map.containsKey(ransomNote.charAt(i)) || map.get(ransomNote.charAt(i)) < 1)
				return false;
			else
				map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i)) - 1);
		}
		
        return true;	
    }
	
	/**
	 * Straight forward way using Java String API, AC: 224 ms
	 * Time: O(n^2). Space: 0
	 */
	public boolean canConstruct_JavaAPI(String ransomNote, String magazine) {
        for (int i = 0; i < ransomNote.length(); i++) {
			if(magazine.contains(ransomNote.charAt(i) + "")){
				magazine = magazine.replaceFirst(ransomNote.charAt(i) + "", "");	//!!!! Note bug here: can not use replace(). otherwise will fail like "aa", "aab".(Coz replace() will replace ALL matched) 
				continue;
			}
			else
				return false;
		}	
        return true;	
    }
	
	public static void main(String[] args) {
		String ransomNote = "aa";
		String magazine = "aab";
		System.out.println(new P383_RansomNote().canConstruct_JavaAPI(ransomNote, magazine));
		System.out.println(new P383_RansomNote().canConstruct_HashTable(ransomNote, magazine));
		System.out.println(new P383_RansomNote().canConstruct(ransomNote, magazine));
	}

}

/**
 * 1. canConstruct()利用Int【32】数组的最佳思路没想到，注意。
 * 2. 暴力解法canConstruct_JavaAPI()注意bug：replace()方法会替换全部match字符串，要用replaceFirst
 */
