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
     * 思路：
     * 对于一个数组 a b c... z的解，设为p(a,b,c,...,z)
     * 则 p(a,b,c,...,z) =List(a, p(b,c,...,z))
     * 其中，p的解需要递归求解，当p的参数只有两个元素 a、b时，可以退出递归，其解为List(List(a,b), List(b, a))。
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        if(nums.length == 1) {
            List<Integer> l1 = new ArrayList<>();
            l1.add(nums[0] );
            res.add(l1);
            return res;
        }

        Set<Integer> numsSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numsSet.add(nums[i]);
        }
        return permute_helper(numsSet);
    }

    private List<List<Integer>> permute_helper(Set<Integer> nums) {
        if(nums.size() == 2){   //递归退出条件。返回List(List(a, b), List(b, a))
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> l1 = new ArrayList<>(nums);
            res.add(l1);
            List<Integer> l2 = new ArrayList<>();
            l2.add(l1.get(1));
            l2.add(l1.get(0));
            res.add(l2);
            return res;
        }else {
            List<List<Integer>> res = new ArrayList<>();
            for(Integer num: nums){
                Set<Integer> subSet = new HashSet<>(nums);
                subSet.remove(num);
                List<List<Integer>> subSetRes = permute_helper(subSet);
                for(List<Integer> subListResList: subSetRes){
                    subListResList.add(0, num);
                    res.add(subListResList);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        P046_全排列 p = new P046_全排列();
        System.out.println(p.permute(new int[]{1}));        //[[1]]
        System.out.println(p.permute(new int[]{1,2}));      //[[1, 2], [2, 1]]
        System.out.println(p.permute(new int[]{1,2,3}));    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        System.out.println(p.permute(new int[]{1,2,3,4}));  //[[1, 2, 3, 4], [1, 2, 4, 3], [1, 3, 2, 4], [1, 3, 4, 2], [1, 4, 2, 3], [1, 4, 3, 2], [2, 1, 3, 4], [2, 1, 4, 3], [2, 3, 1, 4], [2, 3, 4, 1], [2, 4, 1, 3], [2, 4, 3, 1], [3, 1, 2, 4], [3, 1, 4, 2], [3, 2, 1, 4], [3, 2, 4, 1], [3, 4, 1, 2], [3, 4, 2, 1], [4, 1, 2, 3], [4, 1, 3, 2], [4, 2, 1, 3], [4, 2, 3, 1], [4, 3, 1, 2], [4, 3, 2, 1]]
    }
}
