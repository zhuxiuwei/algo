package study.interview;

import java.util.*;

/**
 * 240809
 * Given an array, return the next greater element for every element.。
 * 输入：[2, 1, 2, 4, 3]  输出： [4, 2, 4, -1, -1]
 * 很像leetcode 739 Daily Temperatures https://github.com/zhuxiuwei/algo/blob/master/src/LeetCode/round3/P739_DailyTemperatures.java
 */
public  class I240809_DataVisor_FindNextLargerNumInArray {
    public int[] findNextLarger(int[] nums){
        if(nums == null)
            return null;
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        Stack<Integer> numStack = new Stack<>();
        HashMap<Integer, List<Integer>> num2IdxMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if(numStack.isEmpty()){
                numStack.push(n);
                List<Integer> idxes = num2IdxMap.getOrDefault(n, new ArrayList<>());
                idxes.add(i);
                num2IdxMap.put(n, idxes);
            }else {
                while (!numStack.isEmpty()) {
                    int top = numStack.peek();
                    if (n > top) {    //找到下一个更大的
                        numStack.pop();
                        int idx = num2IdxMap.get(top).get(num2IdxMap.get(top).size() - 1);
                        num2IdxMap.get(top).remove(num2IdxMap.get(top).size() - 1);
                        res[idx] = n;
                    } else {
                        break;
                    }
                }
                //当前元素入栈。 注意这段代码的位置，写错了好几次，不应该放在上面的while里！！！
                numStack.push(n);
                List<Integer> idxes = num2IdxMap.getOrDefault(n, new ArrayList<>());
                idxes.add(i);
                num2IdxMap.put(n, idxes);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        I240809_DataVisor_FindNextLargerNumInArray p = new I240809_DataVisor_FindNextLargerNumInArray();
        int[] nums = new int[]{2, 1, 2, 4, 3};  //[4, 2, 4, -1, -1]
        System.out.println(Arrays.toString(p.findNextLarger(nums)));

        nums = new int[]{2};  //[-1]
        System.out.println(Arrays.toString(p.findNextLarger(nums)));

        nums = new int[]{1,2,3,4,5};  //[2,3,4,5,-1]
        System.out.println(Arrays.toString(p.findNextLarger(nums)));

        nums = new int[]{73,74,75,71,69,72,76,73};  //[74,75,76,72,72,76,-1,-1]
        System.out.println(Arrays.toString(p.findNextLarger(nums)));

    }
}