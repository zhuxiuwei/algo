package LeetCode.round3;

import java.util.*;

/**
 240726 Medium
 给定一个没有重复数字的序列，返回其所有可能的全排列。

 Example 1:
 Input: nums = [1,2,3]
 Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 Example 2:
 Input: nums = [0,1]
 Output: [[0,1],[1,0]]
 Example 3:
 Input: nums = [1]
 Output: [[1]]

 Constraints:
 1 <= nums.length <= 6
 -10 <= nums[i] <= 10
 All the integers of nums are unique.
 */
public class P046_全排列 {

    /**
     * AC: 5ms 9%, mem 15.8%
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        //////重复代码太多
        if(nums.length == 1) {
            List<Integer> l1 = new ArrayList<>();
            l1.add(nums[0] );
            res.add(l1);
            return res;
        }
        if(nums.length == 2) {   //交换两个位置，返回
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();
            l1.add(nums[0] );
            l1.add(nums[1] );
            l2.add(nums[1] );
            l2.add(nums[0] );
            res.add(l1);
            res.add(l2);
            return res;
        }
        /////////

        Set<Integer> numsSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numsSet.add(nums[i]);
        }
        for(Integer num: numsSet){
            Set<Integer> tmpSet = new HashSet<>(numsSet);
            tmpSet.remove(num);
            List<List<Integer>> subListRes = permute_helper(num,  tmpSet);
            for(List<Integer> subListResList: subListRes){
                subListResList.add(0, num);
                res.add(subListResList);
            }
        }
        return  res;
    }

    private List<List<Integer>> permute_helper(int cur, Set<Integer> nums) {
        if(nums.size() == 2){   //交换两个位置，返回
            List<Integer> l1 = new ArrayList<>(nums);
            List<Integer> l2 = new ArrayList<>();
            l2.add(l1.get(1));
            l2.add(l1.get(0));
            List<List<Integer>> tmpRes = new ArrayList<>();
            tmpRes.add(l1);
            tmpRes.add(l2);
            return tmpRes;
        }else {
            List<List<Integer>> tmpRes = new ArrayList<>();
            for(Integer num: nums){
                Set<Integer> tmpSet = new HashSet<>(nums);
                tmpSet.remove(num);
                List<List<Integer>> subListRes = permute_helper(cur,  tmpSet);
                for(List<Integer> subListResList: subListRes){
                    subListResList.add(0, num);
                    tmpRes.add(subListResList);
                }
            }
            return tmpRes;
        }
    }

    public static void main(String[] args) {
        P046_全排列 p = new P046_全排列();
        System.out.println(p.permute(new int[]{1}));
        System.out.println(p.permute(new int[]{1,2}));
        System.out.println(p.permute(new int[]{1,2,3}));
        System.out.println(p.permute(new int[]{1,2,3,4}));
    }
}
