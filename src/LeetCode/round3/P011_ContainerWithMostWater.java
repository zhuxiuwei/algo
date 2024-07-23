package LeetCode.round3;

import LeetCode.round2.P011_盛最多水的容器;

/**
 * 240723
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant(倾斜) the container.
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * Constraints:
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class P011_ContainerWithMostWater {


    /**
     * AC: 88%, mem: 44%
     * 双指针
     * 还是思路不会，看的答案思路。
     * 思路：双指针，左右往中间走，比较左右高度，哪个低，就把哪个指针往中间走，在这个过程中记录下来最大值。
     */
    public int maxArea(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        int oldI = 0, oldJ = height.length - 1;
        int i = oldI, j = oldJ;
        int res = (j - i) * (Math.min(height[i], height[j]));   //初始面积
        while (i < j){
            if(height[i] <= height[j]){
                i++;
            }else {
                j--;
            }
            int newRes = (j - i) * (Math.min(height[i], height[j]));
            res = res < newRes ? newRes: res;
        }

        return res;
    }

    /**
     * 双指针法
     * 思路：左右俩指针，一个从左走到右，一个从右走到左，找到一个找到比自己之前更高的，就等另一边也找到，然后算一下面积是否更大。
     * 等都走到最对侧的时候，算法退出。
     * 思路不对，结果不对：
     * System.out.println(p.maxArea(new int[]{2,3,10,5,7,8,9}));   //36  -- 错误输出15
     * System.out.println(p.maxArea(new int[]{2,3,4,5,18,17,6}));   //17  -- 错误输出15
     */
    public int maxArea_wrong(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        int oldI = 0, oldJ = height.length - 1;
        int i = oldI, j = oldJ;
        int res = (j - i) * (Math.min(height[i], height[j]));   //初始面积
        while(true) {
            //左边指针，往右走，找到变高的
            while (i < height.length - 1) {
                i++;
                if (height[i] > height[oldI]) {
                    oldI = i;
                    break;
                }
            }
            int newRes = Math.abs(j - i) * (Math.min(height[i], height[j]));   //新面积
            res = res < newRes ? newRes: res;

            //右边指针，往左走，找到变高的
            while (j > 0) {
                j--;
                if (height[j] > height[oldJ]) {
                    oldJ = j;
                    break;
                }
            }

            newRes = Math.abs(j - i) * (Math.min(height[i], height[j]));   //新面积
            res = res < newRes ? newRes: res;

            if(i == height.length - 1 && j == 0)
                break;
        }
        return res;
    }

    /**
     * 暴力法-双重遍历，O(n^2)
     * 会超时
     */
    public int maxArea_Violent_Timeout(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < height.length -1; i++) {
            for (int j = height.length - 1; j > i; j--) {
                int tempRes = (j - i) * (Math.min(height[i], height[j]));
                if(tempRes > res) {
                    res = tempRes;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        P011_ContainerWithMostWater p = new P011_ContainerWithMostWater();
        System.out.println(p.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));    //49
        System.out.println(p.maxArea(new int[]{2,4,8}));    //4
        System.out.println(p.maxArea(new int[]{8,4,2}));    //4
        System.out.println(p.maxArea(new int[]{8,4,2,9,2,4,8}));    //48
        System.out.println(p.maxArea(new int[]{2,3,10,5,7,8,9}));   //36
        System.out.println(p.maxArea(new int[]{2,3,4,5,18,17,6}));   //17
    }
}
