package LeetCode.round2;

/**
 * 200218 hard
 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 输出: 6
 */
public class HARD_P042_接雨水 {

    /**
     * 思路硬背下来吧。自己想不太容易。https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     * 仔细看解释大概也明白。
     * 在双指针中，有LMax和RMax，水位高度由更小的一个值决定（木桶效应）。在双指针收敛的过程中，因为是矮的一方移动，所以矮的一方指针势必存在能和另一侧指针形成一个蓄水区的条件。
     * 然而到底能不能蓄水，还要看当前指针高度，相比‘背后’max指针高度是不是低了，低了的话才真正行程一个新的蓄水点。即必须形成 ＼__／  这种趋势才行。
     */
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
        HARD_P042_接雨水 p = new HARD_P042_接雨水();
        System.out.println(p.trap(new int[]{0,1,0,2,1,0,1,3,3,1,2,1}));	//6
        System.out.println(p.trap(new int[]{5,2,1,2,1,5}));	//14
        System.out.println(p.trap(new int[]{0,0,0,1}));	//0
    }
}
