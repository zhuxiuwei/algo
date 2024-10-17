package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 241017 easy
 * https://leetcode.com/problems/plus-one/description/?envType=study-plan-v2&envId=top-interview-150
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Example 1:
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 *
 * Example 2:
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * Incrementing by one gives 4321 + 1 = 4322.
 * Thus, the result should be [4,3,2,2].

 * Example 3:
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9.
 * Incrementing by one gives 9 + 1 = 10.
 * Thus, the result should be [1,0].
 *
 * Constraints:
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits does not contain any leading 0's.
 */
public class P066_PlusOne {
    /**
     * AC: Runtime 1 ms Beats 5.61%. Memory 41.30 MB Beats 97.98%
     * 一次过。
     */
    public int[] plusOne(int[] digits) {
        Stack<Integer> nums = new Stack<>();
        boolean jinWei = false;
        for (int i = digits.length - 1; i >=0 ; i--) {
            int n = digits[i];
            if(i == digits.length - 1 || jinWei) {
                n++;
            }
            if(n == 10){
                jinWei = true;
                n = 0;
            }else {
                jinWei = false;
            }
            nums.push(n);
        }
        if(jinWei)
            nums.push(1);
        int[] res = new int[nums.size()];
        for (int i = 0; !nums.isEmpty() ; i++) {
            res[i] = nums.pop();
        }
        return res;
    }
}
