package LeetCode.round2;

import java.util.Arrays;

/**
 190229 Easy
 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

 示例 1:
 给定数组 nums = [1,1,2],
 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 你不需要考虑数组中超出新长度后面的元素。

 示例 2:
 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 你不需要考虑数组中超出新长度后面的元素。

 说明:
 为什么返回数值是整数，但输出的答案是数组呢?
 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

 你可以想象内部操作如下:
 // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 int len = removeDuplicates(nums);
 // 在函数里修改输入数组对于调用者是可见的。
 // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 for (int i = 0; i < len; i++) {
     print(nums[i]);
 }
 */
public class P026_删除排序数组中的重复项 {
    //执行用时 : 1 ms , 在所有 Java 提交中击败了 99.92% 的用户。内存消耗 : 41.1 MB , 在所有 Java 提交中击败了 22.38% 的用户
    //一次AC，顺利。
    public int removeDuplicates(int[] nums) {
        int res = 0;
        if(nums != null && nums.length > 0){
            int lastNumIdx = 0;
            //删除重复数字，并统计数组长度
            for (int i = 1; i < nums.length; i++) {
                if(nums[i] != nums[lastNumIdx])
                    nums[++lastNumIdx] = nums[i];
            }
            res = lastNumIdx + 1;
            //删除重复后，末尾置零
            for (int i = lastNumIdx + 1; i < nums.length; i++)
                nums[i] = 0;
        }
//        System.out.println(Arrays.toString(nums));
        return res;
    }

    public static void main(String[] args) {
        P026_删除排序数组中的重复项 p = new P026_删除排序数组中的重复项();
        System.out.println(p.removeDuplicates(new int[]{1,1,2})); //2
        System.out.println(p.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4})); //5
    }
}

