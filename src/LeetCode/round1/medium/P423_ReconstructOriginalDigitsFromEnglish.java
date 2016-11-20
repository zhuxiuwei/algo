package LeetCode.round1.medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 161120
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
Note:
- Input contains only lowercase English letters.
- Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
- Input length is less than 50,000.

Example 1:
Input: "owoztneoer"
Output: "012"

Example 2:
Input: "fviefuro"
Output: "45"
 */
public class P423_ReconstructOriginalDigitsFromEnglish {
	
	/**
	 * 巧妙地解法。O(N)。 
	 * AC： 53ms, 25%
	 * @param s
	 * @return
	 */
	public String originalDigits(String s) {
		int[] count = new int[10];	//recored each number count.
		Map<Character, Integer> cToI = new HashMap<Character, Integer>();	//store each character count
		cToI.put('e', 0);
		cToI.put('f', 0);
		cToI.put('g', 0);
		cToI.put('h', 0);
		cToI.put('i', 0);
		cToI.put('n', 0);
		cToI.put('o', 0);
		cToI.put('r', 0);
		cToI.put('s', 0);
		cToI.put('t', 0);
		cToI.put('u', 0);
		cToI.put('v', 0);
		cToI.put('w', 0);
		cToI.put('x', 0);
		cToI.put('z', 0);
		
		//create map based on s.
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			cToI.put(c, cToI.get(c) + 1);
		}
		
		//count each number count.
		//round1: determine '8, 4, 2, 6, 0' count.
		count[8] = cToI.get('g');	
		updateMap(cToI, 8, count[8]);
		count[4] = cToI.get('u');	
		updateMap(cToI, 4, count[4]);
		count[2] = cToI.get('w');	
		updateMap(cToI, 2, count[2]);
		count[6] = cToI.get('x');	
		updateMap(cToI, 6, count[6]);
		count[0] = cToI.get('z');	
		updateMap(cToI, 0, count[0]);
		//round2: determine '3, 5, 7, 1' count.
		count[3] = cToI.get('h');	
		updateMap(cToI, 3, count[3]);
		count[5] = cToI.get('f');	
		updateMap(cToI, 5, count[5]);
		count[7] = cToI.get('s');	
		updateMap(cToI, 7, count[7]);
		count[1] = cToI.get('o');	
		updateMap(cToI, 1, count[1]);
		//round3: determine '9' count.
		count[9] = cToI.get('e');	
		updateMap(cToI, 9, count[9]);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count.length; i++) 
			for (int j = 0; j < count[i]; j++) 
				sb.append(i);
		return sb.toString();
    }
	private void updateMap(Map<Character, Integer> cToI, int number, int count){
		String[] ne = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
		for (int i = 0; i < ne[number].length(); i++) {
			char c = ne[number].charAt(i);
			cToI.put( c, cToI.get(c) - count);
		}
	}
	
	/**
	 * !!!!!!! 非常ugly的代码，写了半天才功能上正确（各种集合使用上的坑），结果OJ时，最后在一个超长的case上timeout了。
	 */
	public String originalDigits_timeout(String s) {
		//put s to linked list to remove elements easily.
		List<Character> so = new LinkedList<Character>();
		for (int i = 0; i < s.length(); i++) 
			so.add(s.charAt(i));
		
		String[] ne = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
		
		//build dictionary
		Map<Character, HashSet<Integer>	> mapEnToInt = new HashMap<Character, HashSet<Integer>>();
		for (int i = 0; i < ne.length; i++) {
			for (int j = 0; j < ne[i].length(); j++) {
				char c = ne[i].charAt(j);
				if(!mapEnToInt.containsKey(c)){
					HashSet<Integer> numSet = new HashSet<Integer>();
					numSet.add(i);
					mapEnToInt.put(c, numSet);
				}else
					mapEnToInt.get(c).add(i);
			}
		}
		
		List<Integer> res = new ArrayList<Integer>();	//store numbers found.
		HashSet<HashSet<Integer>> tempSet = new HashSet<HashSet<Integer>>(); 
		int lastNumFound = -1;
		while(!so.isEmpty()){
for1:		for(char c: so){
				HashSet<Integer> nums = mapEnToInt.get(c);	//all numbers that contains this character
				if(nums != null){
					boolean matched = false;
//					Iterator<HashSet<Integer>> it = tempSet.iterator();
//					while(it.hasNext()){
//						HashSet<Integer> set = it.next();
//						if(intersect(set, nums)){
//							matched = true;
//							if(set.size() == 1){	//a number found
//								lastNumFound = set.iterator().next();
//								//it.remove();		//!!!!!!!!!!!!!!!!!!!!!!!!!!不知道为啥这里死活不work!!!!就只好调用下面的set.clear()了。
//								set.clear();
//								res.add(lastNumFound);
//								break for1;
//							}
//						}
//					}
					for(HashSet<Integer> set: tempSet){
						if(intersect(set, nums)){
							matched = true;
							if(set.size() == 1){	//a number found
								lastNumFound = set.iterator().next(); 
								res.add(lastNumFound);
								set.clear();	//
								break for1;
							}
						}
					}
					if(!matched)
						tempSet.add(new HashSet<Integer>(nums));
				}
			}
			
			Iterator<HashSet<Integer>> iterator = tempSet.iterator();
//			while(iterator.hasNext()){
//				HashSet<Integer> ss = iterator.next();
//				if (ss.size() == 1) {
//					iterator.remove();
//				}
//			}
			//remove lastNumFound from LinkedList
			String ss = ne[lastNumFound];
			for (int i = 0; i < ss.length(); i++) 
				so.remove((Character)ss.charAt(i));
		}
		//add tempset to res
		Collections.sort(res);
		StringBuilder sb = new StringBuilder();
		for (int r: res) 
			sb.append(r);
		return sb.toString();
    }
	//If a and b has intersection, then return true and leave a unchanged. Otherwise return false and make a = a^b.
	private boolean intersect(HashSet<Integer> a, HashSet<Integer> b){
		boolean res= false;
		for (int i : b) {
			if(a.contains(i)){
				res = true;
				break;
			}
		}
		if(res)
			a.retainAll(b);
		return res;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		P423_ReconstructOriginalDigitsFromEnglish p = new P423_ReconstructOriginalDigitsFromEnglish();
		System.out.println(p.originalDigits("owoztneoer"));
		Scanner sc = new Scanner(new File("E:\\1.txt"));
		System.out.println(p.originalDigits(sc.nextLine()));
	}

}
