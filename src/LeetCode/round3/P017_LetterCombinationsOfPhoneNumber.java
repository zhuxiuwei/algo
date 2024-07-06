package LeetCode.round3;

import java.util.*;

/**
 * 240705 https://leetcode.com/problems/letter-combinations-of-a-phone-number/?envType=study-plan-v2&envId=top-100-liked
 * # Backtracking 第一次做
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 示例图片无法贴，就是典型03年诺基亚的键盘。
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * Input: digits = ""
 * Output: []
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class P017_LetterCombinationsOfPhoneNumber {

    /**
     * AC: 2ms, 45%。 内存: 82%
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0 || digits == null) {
            return res;
        }

        Map<Character, List<String>> numToLetter = new HashMap<>();
        numToLetter.put('2', Arrays.asList("a", "b", "c"));
        numToLetter.put('3', Arrays.asList("d", "e", "f"));
        numToLetter.put('4', Arrays.asList("g", "h", "i"));
        numToLetter.put('5', Arrays.asList("j", "k", "l"));
        numToLetter.put('6', Arrays.asList("m", "n", "o"));
        numToLetter.put('7', Arrays.asList("p", "q", "r", "s"));
        numToLetter.put('8', Arrays.asList("t", "u", "v"));
        numToLetter.put('9', Arrays.asList("w", "x", "y", "z"));

        letterCombinations_helper(numToLetter, res, digits, 0);

        return res;
    }

    /**
     * @param numToLetterMap 保存电话键盘数字到字母映射关系的map
     * @param res 最终返回结果
     * @param digits 输入的数字字符串
     * @param index 处理到的index位置
     */
    private void letterCombinations_helper(Map<Character, List<String>> numToLetterMap, List<String> res, String digits, int index) {
        if(index < digits.length()) {
            char c = digits.charAt(index);
            List<String> currentNumLetters = numToLetterMap.get(c);
            List<String> newRes = new ArrayList<>();
            for (String s1 : currentNumLetters) {
                if (res.size() > 0) {
                    for (String s2 : res) {
                        newRes.add(s2 + s1);
                    }
                } else {
                    newRes.add(s1);
                }
            }
            res.clear();
            res.addAll(newRes);     //！！！注意这两行，不能直接res=newRes，这样res最后还是空列表。
            letterCombinations_helper(numToLetterMap, res, digits, ++index);
        }
    }

    public static void main(String[] args) {
        P017_LetterCombinationsOfPhoneNumber p = new P017_LetterCombinationsOfPhoneNumber();
        System.out.println(p.letterCombinations("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(p.letterCombinations("")); // []
        System.out.println(p.letterCombinations("2")); // ["a","b","c"]
    }
}
