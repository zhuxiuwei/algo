package LeetCode.round3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 240923 easy
 * https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class P027_RemoveElement {
    /**
     * AC: Runtime 0 ms Beats 100.00%, Memory 41.95 MB Beats 31.91%
     * 写的没啥技术含量。。。时间空间都是O(n)
     */
    public int removeElement(int[] nums, int val) {
        List<Integer> notValNums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                notValNums.add(nums[i]);
            }
        }
        Iterator<Integer> it = notValNums.iterator();
        int idx = 0;
        while (it.hasNext()){
            nums[idx ++] = it.next();
        }
        return notValNums.size();
    }
}
