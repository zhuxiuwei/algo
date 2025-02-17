package LeetCode.round3;

/**
 * 241107 Medium
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that
 * the given researcher has published at least h papers that have each been cited at least h times.
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 *
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 *
 * Constraints:
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class P274_HIndex {

    /**
     * AC: Runtime 20 ms Beats 5.03%, Memory 41.51 MB Beats 41.33%
     * 暴力解法。有一个错误点。
     */
    public int hIndex(int[] citations) {
        int maxPossibleValue = Integer.MIN_VALUE;
        for (int i = 0; i < citations.length; i++) {
            if(citations[i] > maxPossibleValue)
                maxPossibleValue = citations[i];
        }
        maxPossibleValue = Math.max(maxPossibleValue, citations.length);    //初始最大值： max(数组最大元素，数组长度)

        for (int possibleRes = maxPossibleValue; possibleRes >= 0 ; possibleRes--) {
            int tmpCount = 0;
            for (int idx = 0; idx < citations.length; idx++) {
                int n = citations[idx];
                if(n >= possibleRes && possibleRes > 0){    //！！！！！需要加上【possibleRes > 0】这个条件，否则[0]这个
                    tmpCount ++;
                }
                if(tmpCount >= possibleRes)
                    return tmpCount;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        P274_HIndex p = new P274_HIndex();
        System.out.println(p.hIndex(new int[]{3,0,6,1,5}));    //3
        System.out.println(p.hIndex(new int[]{1,3,1}));    //1
        System.out.println(p.hIndex(new int[]{0}));    //0

    }
}
