package LeetCode.round2;

/**
 * 200215 hard
 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 输出: 6
 */
public class P042_接雨水 {
    public int trap(int[] height) {
        if(height == null || height.length <= 2)
            return 0;
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, res = 0;
        while (left < right){
            if(height[left] < height[right]){
                if(height[left] >= leftMax) {
                    leftMax = height[left];
                }else {
                    res += leftMax - height[left];
                }
                left ++;
            }else{
                if(height[right] >= rightMax) {
                    rightMax = height[right];
                }else {
                    res += rightMax - height[right];
                }
                right --;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P042_接雨水 p = new P042_接雨水();
        System.out.println(p.trap(new int[]{0,1,0,2,1,0,1,3,3,1,2,1}));	//6
        System.out.println(p.trap(new int[]{5,2,1,2,1,5}));	//14
        System.out.println(p.trap(new int[]{0,0,0,1}));	//0
    }
}
