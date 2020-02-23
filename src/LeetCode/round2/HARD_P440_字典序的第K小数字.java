package LeetCode.round2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 200223 hard
 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 注意：1 ≤ k ≤ n ≤ 109。

 示例 :
 输入:
 n: 13   k: 2
 输出:
 10
 解释:
 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 */
public class HARD_P440_字典序的第K小数字 {

    /**
     * 不会。看了参考答案。https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/ben-ti-shi-shang-zui-wan-zheng-ju-ti-de-shou-mo-sh/
     * 执行用时 : 0 ms , 在所有 Java 提交中击败了 100.00% 的用户。 内存消耗 : 36.3 MB , 在所有 Java 提交中击败了 5.23% 的用户
     * n: 整数1到n，即上限； k: 第k小；
     */
    public int findKthNumber(int n, int k) {
        long cur = 1;
        while (k > 1) {
            long count = getCount(cur, n);  //根据前缀具有的合法int值个数，不断收敛。
            if(count >= k){ //当前前缀能涵盖住k
                k--;
                cur = cur * 10 > n ? cur + 1: cur * 10;
            }else { //当前前缀不能涵盖k
                k -= count;
                cur += 1;
            }
        }
        return (int)cur;
    }

    /**
     * @param prefix 前缀，可以是任意数字，如1、10、11等
     * @param n 上限
     * @return 在n上限情况下，prefix前缀下有多少个 <=n 的 int值。
     */
    private long getCount(long prefix, int n){  //之所以用long，因为int的话在计算大数时会溢出。
        long cur = prefix, next = cur + 1, count = 0;
        while (cur <= n){
            if(next > n)
                count += n - cur + 1;
            else
                count += next - cur;
            cur *= 10;
            next *= 10;
        }
        return count;
    }


    //！！！！！！！！！！ 此方法在输入(4289384, 1922239)时超出内存限制（IntStr2）。 在(7747794, 5857460)时超出时间限制(IntStr，相比IntStr2优化了内存)
    // 思路： 自定义字典序排序的IntStr类，把所有n的数字都变成IntStr类进行字典排序，然后取出k-1位置即可。
    int findKthNumber_exceedMemory(int n, int k) {
        List<IntStr> intStrs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {  //！！！！！！！！注意bug： i的开始和结束条件
            intStrs.add(new IntStr(i));
        }
        Collections.sort(intStrs);
        return intStrs.get(k - 1).val;  //IntStr
//        return Integer.parseInt(intStrs.get(k - 1).val);  //IntStr2
    }

    //字典序排序的IntStr类 - 不存放str，str在比较时才生成，节省内存。
    static class IntStr implements Comparable<IntStr>{

        int val;    //不是string!!!!
        public IntStr(int n){
            val = n;
        }
        @Override
        public int compareTo(IntStr o) {
            if(o.val == this.val)
                return 0;
            String v1 = this.val + "", v2 = o.val + "";     //在compare时现生成string
            int minLength = Math.min(v1.length(), v2.length());
            for (int i = 0; i < minLength; i++) {
                char cThis = v1.charAt(i);
                char cO = v2.charAt(i);
                if(cO == cThis)
                    continue;
                else if(cO > cThis)
                    return -1;
                else
                    return 1;
            }
            if(v1.length() > v2.length())
                return 1;
            else
                return 0;
        }
        @Override
        public String toString() {
            return val + "";
        }
    }

    //字典序排序的IntStr2类
    static class IntStr2 implements Comparable<IntStr2>{

        String val;
        public IntStr2(int n){
            val = n + "";
        }
        @Override
        public int compareTo(IntStr2 o) {
            if(o.val == this.val)
                return 0;
            int minLength = Math.min(this.val.length(), o.val.length());
            for (int i = 0; i < minLength; i++) {
                char cThis = this.val.charAt(i);
                char cO = o.val.charAt(i);
                if(cO == cThis)
                    continue;
                else if(cO > cThis)
                    return -1;
                else
                    return 1;
            }
            if(this.val.length() > o.val.length())
                return 1;
            else
                return 0;
        }
        @Override
        public String toString() {
            return val + "";
        }
    }

    public static void main(String[] args) {
        HARD_P440_字典序的第K小数字 p = new HARD_P440_字典序的第K小数字();
//        p.getCount(100, 103);
        System.out.println(p.findKthNumber(13, 2)); //10
        System.out.println(p.findKthNumber(1, 1)); //1
        System.out.println(p.findKthNumber(103, 14)); //18
        System.out.println(p.findKthNumber(103, 15)); //19
        System.out.println(p.findKthNumber(103, 16)); //2
        System.out.println(p.findKthNumber(103, 17)); //20
        System.out.println(p.findKthNumber(4289384, 1922239)); //2730010
        System.out.println(p.findKthNumber(7747794, 5857460));  //6271710
        System.out.println(p.findKthNumber(681692778, 351251360));  //416126219 123084780
    }
}
