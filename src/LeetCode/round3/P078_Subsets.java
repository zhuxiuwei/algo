package LeetCode.round3;

import java.util.*;

/**
 * 240716 - backtracking - medium
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class P078_Subsets {

    /**
     * AC: 1ms, 52%； mem: 71%
     * 关键是先想明白生成全部解的步骤
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null)
            return res;
        Map<Integer, Integer> valToNumIdxMap = new HashMap<>(); //保存nums[]里每个数字的索引。（nums里的数字都是unique的）
        for (int i = 0; i < nums.length; i++) {
            valToNumIdxMap.put(nums[i], i);
        }
        Stack<List<Integer>> processingStack = new Stack<>();
        List<Integer> newList = new ArrayList<>();
        processingStack.push(newList);
        res.add(newList);   //！！！这步骤不能忘了，否则返回结果里少了'[]'这个空结果
        subsets_helper(nums, processingStack, res, valToNumIdxMap);
        return res;
    }

    private void subsets_helper(int[] nums,  Stack<List<Integer>> processingStack,
                                List<List<Integer>> res, Map<Integer, Integer> valToNumIdxMap){
        while (!processingStack.isEmpty()){
            List<Integer> currentList = processingStack.pop();
            int startIdx = currentList.isEmpty() ? 0: valToNumIdxMap.get(currentList.get(currentList.size() - 1)) + 1;
            for (int i = startIdx; i < nums.length; i++) {
                List<Integer> newList = new ArrayList<>(currentList);
                newList.add(nums[i]);
                processingStack.push(newList);
                res.add(newList);
            }
        }
    }

    public static void main(String[] args) {
        P078_Subsets p = new P078_Subsets();
        System.out.println(p.subsets(new int[]{1,2,3}));    //[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        System.out.println(p.subsets(new int[]{0}));    //[[],[0]]
    }

}
