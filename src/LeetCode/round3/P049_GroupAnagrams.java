package LeetCode.round3;

import java.util.*;

/**
 * 240713 Medium
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class P049_GroupAnagrams {
    /**
     * AC: 31ms 9.9%, mem: 5.13%
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        /**
         * K: Anagrams
         * V: 此Anagrams对应的str list。
         */
        Map<Anagrams, List<String>> anagramsToStrListMap = new HashMap<>();
        for(String str: strs){
            Anagrams anagrams = new Anagrams(str);
            List<String> list = null;
            if(!anagramsToStrListMap.containsKey(anagrams)){
                list = new ArrayList<>();
                anagramsToStrListMap.put(anagrams, list);
            }else {
                list = anagramsToStrListMap.getOrDefault(anagrams, new ArrayList<>());
            }
            list.add(str);
        }
        res.addAll(anagramsToStrListMap.values());
        return res;
    }

    /**
     * 用于记录一个字符串，和其出现的次数
     */
    class CharAndCount{
        char theChar;
        int count;

        public CharAndCount(char c, int count){
            this.count = count;
            this.theChar = c;
        }
        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if (o == null)
                return false;
            if (!(o instanceof CharAndCount))
                return false;
            CharAndCount other = (CharAndCount) o;
            return this.theChar == other.theChar && this.count == other.count;
        }

        @Override
        public int hashCode() {
            int res = 31 * (int)theChar;
            res = 31 * res + count;
            return res;
        }

        @Override
        public String toString() {
            return theChar + ": " + count;
        }
    }

    /**
     * Anagrams类，其中包含若干个CharAndCount
     */
    class Anagrams{
        Set<CharAndCount> charSet = new HashSet<>();
        public Anagrams(String s){
            char[] chars = s.toCharArray();
            Map<Character, Integer> charAndCountMap = new HashMap<>();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                int count = charAndCountMap.getOrDefault(c, 0) + 1;
                charAndCountMap.put(c, count);
            }
            for(Character c: charAndCountMap.keySet()){
                CharAndCount charAndCount = new CharAndCount(c, charAndCountMap.get(c));
                this.charSet.add(charAndCount);
            }
        }
        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if (o == null)
                return false;
            if (!(o instanceof Anagrams))
                return false;
            Anagrams other = (Anagrams) o;
            if(other.charSet.size() != this.charSet.size())
                return false;
            for(CharAndCount charAndCount: this.charSet){
                if(!other.charSet.contains(charAndCount))
                    return false;
            }
            return true;
        }


        @Override
        public int hashCode() {
            int res = 0;
            for(CharAndCount c: this.charSet){
               res = res + c.hashCode();
            }
            return res;
        }
        //以下写法不对！！ 这么写，不能保证equals的对象，hashcode相同。如：rub、bur俩字符串，生成的hashcode就不一样。
//        public int hashCode() {
//            int res = 0;
//            for(CharAndCount c: this.charSet){
//                res = 31 * res + c.hashCode();
//            }
//            return res;
//        }
    }

    public static void main(String[] args) {
        P049_GroupAnagrams p = new P049_GroupAnagrams();
        System.out.println(p.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"})); //[["bat"],["nat","tan"],["ate","eat","tea"]]
        System.out.println(p.groupAnagrams(new String[]{""}));  // [[""]]
        System.out.println(p.groupAnagrams(new String[]{"a"})); // [["a"]]
        System.out.println(p.groupAnagrams(new String[]{"att","tat","taa"})); // [[att, tat], [taa]]
        System.out.println(p.groupAnagrams(new String[]{"tt","t"})); // [["tt"],[["t"]]
        System.out.println(p.groupAnagrams(new String[]{"bur","rub"})); //[[rub, bur]]

    }
}
