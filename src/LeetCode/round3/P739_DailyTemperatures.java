package LeetCode.round3;

import java.util.Arrays;
import java.util.Stack;

/**
 * 240731
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 * Constraints:
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class P739_DailyTemperatures {

    /**
     * O(n)解法。 AC: 80%, mem: 12%
     * 思路不会，参考的方案，然后完善思路、自己写的：https://leetcode.com/problems/daily-temperatures/solutions/4651723/easy-stack-friendly-explained/?envType=study-plan-v2&envId=top-100-liked
     * 大致思路：
     * 利用栈，维护还没有找到比当天温度更高温度的数组的下标。栈里越往下的数组idx，对应数组里的温度值越高。
     * 1、从前往后遍历每个元素；
     * 2、对于每个元素(温度)，依次判断栈里idx对应的温度，是否有比它值小的。
     *      如果有比它小的，说明栈里这个idx找到了第一个比它温度高的天数（就是前的元素），计算天数差，存入结果里。
     *      如果没有，停止栈遍历，并将当前元素入栈。
     *  ！！！！！教训： 我开始知道这个是栈的问题。思路就总是怎么让数组里的元素从后往前入栈去计算，怎么也想不到O(n)的解法。
     *  ！！！！！思路打开，栈不是之后数组从后往前看这一个用法！
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null)
            return null;
        int[] res = new int[temperatures.length];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()){
                int oldIdx = stack.peek();
                if(temperatures[oldIdx] < temperatures[i]){
                    res[oldIdx] = i - oldIdx;
                    stack.pop();
                }else {
                    break;
                }
            }
            stack.push(i);
        }

        return res;
    }

    //O(n^2)暴力法。最后的testcase会超时。
    public int[] dailyTemperatures_timeout(int[] temperatures) {
        if(temperatures == null)
            return null;
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if(temperatures[j] > temperatures[i]){
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P739_DailyTemperatures p = new P739_DailyTemperatures();
        System.out.println(Arrays.toString(p.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));   //[1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(p.dailyTemperatures(new int[]{30,40,50,60})));    //[1,1,1,0]
        System.out.println(Arrays.toString(p.dailyTemperatures(new int[]{30,60,90})));  //[1,1,0]
    }
}
