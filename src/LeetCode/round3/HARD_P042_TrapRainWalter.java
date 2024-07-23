package LeetCode.round3;

/**
 * 240723 hard
 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 输出: 6
 */
public class HARD_P042_TrapRainWalter {

    /**
     * 不会，看的别人的思路: https://leetcode.com/problems/trapping-rain-water/solutions/5010235/faster-lesser-detailed-explaination-no-two-pointer-step-by-step-explaination-python-java/?envType=study-plan-v2&envId=top-100-liked
     * 双数组思路：时间复杂度也是O(n)
     * 第一个数组A：记录height[i]左边最高的高度
     * 第一个数组B：记录height[i]右边最高的高度
     * 位置i能积攒的雨水量： min(A[i], B[i]) - height[i]
     * 我通过举例画图，可以看出来这个思路是对的。
     *
     * 1ms，69.36%。 mem: 68.7%
     */
    public int trap(int[] height) {
        if(height == null || height.length <= 2)
            return 0;
        int res = 0;
        int a[] = new int[height.length], b[] = new int[height.length];
        int leftHigh = 0, rightHigh = 0;
        //a数组赋值
        for (int i = 0; i < height.length; i++) {
            a[i] = leftHigh;
            if(leftHigh < height[i]){
                leftHigh = height[i];
            }
        }
        //b数组赋值
        for (int i = height.length - 1; i > 0 ; i--) {
            b[i] = rightHigh;
            if(rightHigh < height[i]){
                rightHigh = height[i];
            }
        }

        //计算结果
        for (int i = 0; i < height.length; i++) {
            int tmp = Math.min(a[i], b[i]) - height[i];
            if(tmp > 0)
                res += tmp;
        }
        return res;
    }


    /**
     * O(n^2)思路，思路不对。
     * 找一个洼地的时候，只考虑了左边高度，没有考虑右边高度。
     * 如{3,1,2,1}应该返回1，这里返回5.
     */
    public int trap_Wrong(int[] height) {
        if(height == null || height.length <= 2)
            return 0;
        int res = 0, tmpAdd = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if(height[j] >= height[i]){
                    res += tmpAdd;
                    tmpAdd = 0;
                    i = j - 1;
                    break;
                }else {
                    tmpAdd += height[i] - height[j];  //错误。考虑蓄水的时候不能只考虑左边高度，也要考虑右边。{3,1,2,1}应该返回1，这里返回5.
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        HARD_P042_TrapRainWalter p = new HARD_P042_TrapRainWalter();
        System.out.println(p.trap(new int[]{0,1,0,2,1,0,1,3,3,1,2,1}));	//6
        System.out.println(p.trap(new int[]{5,2,1,2,1,5}));	//14
        System.out.println(p.trap(new int[]{0,0,0,1}));	//0
        System.out.println(p.trap(new int[]{4,2,0,3,2,5}));	//9
        System.out.println(p.trap(new int[]{3,1,2,1}));	//1
    }
}
