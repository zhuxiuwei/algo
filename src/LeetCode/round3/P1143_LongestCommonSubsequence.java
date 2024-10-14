package LeetCode.round3;

import java.util.*;

/**
 * 240907 medium
 * https://leetcode.com/problems/longest-common-subsequence/description/
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class P1143_LongestCommonSubsequence {

    /**
     * 第四次尝试。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();

        //记录text2里每个char出现的位置
        Map<Character, List<Integer>> text2CharIdxMap = new HashMap<>();
        for (int i = 0; i <= chars2.length - 1 ; i++) {
            char c = chars2[i];
            List<Integer> indexes = text2CharIdxMap.getOrDefault(c, new ArrayList<>());
            indexes.add(0, i);   //List次序是递减的
            text2CharIdxMap.put(c, indexes);
        }

        Map<Character, Set<Integer>> visitedNodesMap = new HashMap<>();
        return findLongestPath(chars1, chars1.length - 1, text2CharIdxMap, visitedNodesMap);
    }

    private int findLongestPath(char[] chars1, int startIdx,
                                Map<Character, List<Integer>> text2CharIdxMap,
                                Map<Character, Set<Integer>> visitedNodesMap){
        int res = 0;
//        for (int i = startIdx; i >= 0 ; i--) {
//            if(text2CharIdxMap.containsKey())
//        }
        return res;
    }

    /**
     * 第三次尝试，思路：
     * 看text1每个char，在text2出现的位置，记录到Map<Character, List<Integer>>中
     * 从后往前遍历Map<Character, List<Integer>>，记录能找到的最长的递增序列长度，就是结果
     * 例子："mhzbicwbe","fmhzhijwbk"
     * mhzbicwbe里每个char对应的map value是： [1],[2,4],[3],[8],[5],[null],[7],[8],[null]
     * 最长递增序列是：[1],[2],[3],[5],[7],[8]，结果是其长度，6.
     *
     * ！！！！！思路还是错了。
     * 反例："abcba","abcbcba" -- 期望5，实际返回3。
     * abcba里每个char对应的map value是： [0,6],[1,3,5],[2,4]
     * 最长递增序列是：[0],[1],[2]结果是其长度，3。错了。。。。。
     */
    public int longestCommonSubsequence_wrong(String text1, String text2) {
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();

        //记录text2里每个char最后出现的位置
        Map<Character, List<Integer>> charLastIdxInText2Map = new HashMap<>();
        for (int i = 0; i <= chars2.length - 1 ; i++) {
            char c = chars2[i];
            List<Integer> indexes = charLastIdxInText2Map.getOrDefault(c, new ArrayList<>());
            indexes.add(i);
            charLastIdxInText2Map.put(c, indexes);
        }

        //map转int[][]
        Integer[][] charLastIdxInText2 = new Integer[charLastIdxInText2Map.size()][];
        for (int i = 0, idx = 0; i < chars1.length && idx < charLastIdxInText2Map.size(); i++) {
            if(charLastIdxInText2Map.containsKey(chars1[i])){
                Integer[] arr = new Integer[charLastIdxInText2Map.get(chars1[i]).size()];
                charLastIdxInText2[idx++] = charLastIdxInText2Map.get(chars1[i]).toArray(arr);
            }
        }

        //调用【找出二维数组里最长递增序列的长度】算法，是个dp算法。
        return findLen(charLastIdxInText2);

        /**
         * 第二次尝试，依旧不对。 思路： 从后往前遍历location[]，记录能找到的最大的递减序列长度，就是结果
         * 以下代码逻辑不对。(p.longestCommonSubsequence("mhzbicwbe","fmhzhijwbk") 期望6，还是输出4. 按此逻辑，错误识别为： mhz b。
         */
//        int[] visitedMinIdx = new int[text1.length()];  //降低遍历成本
//        for (int i = 0; i < text1.length(); i++) {
//            int lastIdx = -1, tmpRes = 0;
//            for (int j = i; j < text1.length(); j++) {
//                char c = chars1[j];
//                List<Integer> indexes = charLastIdxInText2Map.get(c);
//                if(indexes != null){
//                    for (int idx = visitedMinIdx[j]; idx < indexes.size(); idx++) {
//                        int idxVal = indexes.get(idx);
//                        if(idxVal > lastIdx){
//                            tmpRes ++;
//                            visitedMinIdx[j] = idxVal;
//                            lastIdx = idxVal;
//                            break;
//                        }
//                    }
//                }
//                res = Math.max(res, tmpRes);
//            }
//        }
//        return res;

    }

    /**
     * 找出二维数组里最长递增序列的长度
     * dp自底向上算法。
     * hot100里也有类似题目：[300. Longest Increasing Subsequence]https://leetcode.com/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked
     */
    private int findLen(Integer[][] arr){
        int[][] cache = new int[arr.length][];
        boolean isLast = true;
        for (int arrIdx = arr.length - 1; arrIdx >=0 ; arrIdx--) {
            if(arr[arrIdx] != null) {
                Integer[] arrVal = arr[arrIdx];
                int[] cacheVal = new int[arr[arrIdx].length];
                cache[arrIdx] = cacheVal;
                if(isLast){     //最后一个元素
                    for (int cacheValIdx = 0; cacheValIdx < cacheVal.length; cacheValIdx++) {
                        cacheVal[cacheValIdx] = 1;
                    }
                    isLast = false;
                }else {
                    for (int arrValIdx = 0; arrValIdx < arrVal.length; arrValIdx++) {
                        int[] nextCacheVal = cache[arrIdx + 1];
                        Integer[] nextArrVal = arr[arrIdx + 1];
                        for (int nextArrValIdx = 0; nextArrValIdx < nextArrVal.length; nextArrValIdx++) {
                            int tmpV = nextCacheVal[nextArrValIdx];
                            if(arrVal[arrValIdx] < nextArrVal[nextArrValIdx]){
                                tmpV ++;
                            }
                            cacheVal[arrValIdx] = Math.max(tmpV, cacheVal[arrValIdx]);
                        }
                    }
                }
            }
        }
        if(cache.length == 0 || cache[0] == null)
            return 0;
        return cache[0][0];
    }

    /**
     * 第一次尝试：暴力法。O(n^3)
     * 但結果是錯的。(p.longestCommonSubsequence("mhzbicwbe","fmhzhijwbk") 期望6，输出4. 因为按此逻辑，错误识别为： mhz b。
     */
    public int longestCommonSubsequence_baoli_wrong(String text1, String text2) {
        int res = 0;
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        for (int char1Start = 0; char1Start < chars1.length; char1Start++) {
            int lastChar2Idx = 0, tmpRes = 0;
            for (int i = char1Start; i < chars1.length; i++) {
                char c1 = chars1[i];
                for (int j = lastChar2Idx; j < text2.length(); j++) {
                    char c2 = chars2[j];
                    if(c1 == c2){
                        tmpRes ++;
                        res = Math.max(res, tmpRes);
                        lastChar2Idx = j + 1;
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P1143_LongestCommonSubsequence p = new P1143_LongestCommonSubsequence();
        System.out.println(p.longestCommonSubsequence("mhzbicwbe","fmhzhijwbk"));    //6 -- mhz i wb
        System.out.println(p.longestCommonSubsequence("abcde","ace"));  //3
        System.out.println(p.longestCommonSubsequence("abc","abc"));    //3
        System.out.println(p.longestCommonSubsequence("abc","def"));    //0
        System.out.println(p.longestCommonSubsequence("oxcpqrsvwf","shmtulqrypy"));    //2 -- qr
        System.out.println(p.longestCommonSubsequence("mhazbicwbe","fmhgzhijwbk"));    //6 -- mh z i wb
        System.out.println(p.longestCommonSubsequence("abcba","abcbcba"));    //5
    }
}
