package LeetCode.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:

 输入: 123
 输出: 321
  示例 2:

 输入: -123
 输出: -321
 示例 3:

 输入: 120
 输出: 21
 注意:

 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class P007_Reverse_Integer {

    /**
     *  不考虑溢出的话比较简单。 我用了投机取巧的办法。把返回结果先变成long。
     *  考虑溢出，网上看到不少优雅的解法，但是不想花时间理解了。
     *  我这个方案比较长。
     */
    public int reverse(int x) {
        boolean isNegative = x < 0;
        if(isNegative)      //！！！！！！注意负数问题！
            x = -x;
        long res = 0L;      // ！！！！投机取巧
        List<Integer> nums = new ArrayList<>();
        while (x > 0){
            int last = x % 10;
            nums.add(last);
            x = x / 10;
        }
        for (int i = nums.size() - 1; i >= 0; i--)
            res += Math.pow(10, i) * nums.get( nums.size() - i - 1);
        if(res > Integer.MAX_VALUE)
            return 0;
        return isNegative? -(int)res: (int)res;
    }

    public static void main(String[] args) {
        P007_Reverse_Integer p = new P007_Reverse_Integer();
        System.out.println(p.reverse(123));
        System.out.println(p.reverse(120));
        System.out.println(p.reverse(-22)); //负数的处理
        System.out.println(p.reverse(0));
        System.out.println(p.reverse(1534236469));  //反转后超过int上限的处理
    }
}
