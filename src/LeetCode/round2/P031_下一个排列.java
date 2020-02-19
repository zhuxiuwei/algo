package LeetCode.round2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 200219
 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 必须原地修改，只允许使用额外常数空间。

 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class P031_下一个排列 {

    /**
     * 执行用时 : 2 ms , 在所有 Java 提交中击败了 8.36% 的用户
     * 内存消耗 : 39.3 MB , 在所有 Java 提交中击败了 22.61% 的用户
     思路和https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/  基本一样，但是人家实现的更简洁。
     我这个版本写起来小错误很容易犯。
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1)
            return;

        //辅助记录交换位置
        int[] location = new int[nums.length];
        for (int i = 0; i < location.length; i++)
            location[i] = -1;

        boolean breakOut = false;
        for (int i = nums.length -1; i >= 0 ; i--) {
            breakOut = false;
            for (int j = i - 1; j >= 0 ; j--) {
                if(nums[j] < nums[i]){
                    location[i] = j;
                    if(j + 1 == i)
                        breakOut = true;
                    break;
                }
            }
            if(breakOut)
                break;
        }

        //扫描location进行交换
        int pos = -1, max = -1;
        for (int i = 0; i < location.length; i++) {
            if(location[i] >= max && location[i] > -1){
                max = location[i];
                pos = i;     //找到交换点
            }
        }

        //对交换点的数据进行交换
        if(pos != -1) {
            int temp = nums[pos];
            nums[pos] = nums[max];
            nums[max] = temp;
        }else { //数字是全降序的
            pos = 0;
            int temp = nums[pos];
            nums[pos] = nums[nums.length - 1];
            nums[nums.length - 1] = temp;
        }

        //对max后的数据进行升序排序
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i = max + 1; i < nums.length; i++)
            pq.add(nums[i]);

        for (int i = max + 1; i < nums.length; i++){
            nums[i] = pq.poll();
        }
    }

    public static void main(String[] args) {
        P031_下一个排列 p = new P031_下一个排列();
        p.nextPermutation(new int[]{1,2,3});    //[1, 3, 2]
        p.nextPermutation(new int[]{3,2,1});    //[1, 2, 3]
        p.nextPermutation(new int[]{1,1,5});    //[1, 5, 1]
        p.nextPermutation(new int[]{1,3,4,2});  //[1, 4, 2, 3]
        p.nextPermutation(new int[]{1,3,2});    //[2, 1, 3]
        p.nextPermutation(new int[]{1,2});    //[2, 1]
        p.nextPermutation(new int[]{2,1});    //[1, 2]
    }
}
