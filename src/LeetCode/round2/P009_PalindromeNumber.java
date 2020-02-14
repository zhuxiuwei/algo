package LeetCode.round2;

import java.util.ArrayList;
import java.util.List;

/**
 Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

 Example 1:
 Input: 121
 Output: true

 Example 2:
 Input: -121
 Output: false
 Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

 Example 3:
 Input: 10
 Output: false
 Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class P009_PalindromeNumber {

    /**
     * 在边界值处有bug。再次投机取巧，x=int最大值直接返回结果。
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x < 0 || x == Integer.MAX_VALUE)
            return false;
        int oldX = x;
        List<Integer> nums = new ArrayList<>();
        while (x != 0){
            int num = x % 10;
            nums.add(num);
            x = x / 10;
        }
        int reverse = 0;
        for (int i = 0; i < nums.size(); i++) {
            reverse += Math.pow(10, nums.size() -i - 1) * nums.get(i);
        }
        return reverse == oldX;
    }

    public static void main(String[] args) {
        P009_PalindromeNumber p = new P009_PalindromeNumber();
        System.out.println(p.isPalindrome(121));
        System.out.println(p.isPalindrome(10));
        System.out.println(p.isPalindrome(2147483647));
    }
}
