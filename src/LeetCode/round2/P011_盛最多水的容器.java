package LeetCode.round2;

/**
 * 200212 Medium
 *Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 Note: You may not slant the container and n is at least 2.

 The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

 Example:
 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49
 */
public class P011_盛最多水的容器 {

    // 正确思路：https://leetcode.com/problems/container-with-most-water/solution/
    // 双指针，左右往中间走，比较左右高度，哪个低，就把哪个指针往中间走，在这个过程中记录下来最大值。
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int high = height[left] < height[right]? height[left]: height[right];
            int length = right - left;
            int square = high * length;
            if(res < square)
                res = square;
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return res;
    }

    // 错误思路：双指针，两轮遍历，而不是指针碰撞。
    // 还是错误，在{2,3,4,5,18,17,6}，应该返回17（18 -> 17之间），错误返回16(4 -> 6之间)
    public int maxArea_wrong2(int[] height) {
        int left = 0, right = height.length - 1;
        int lastLeft = 0, lastRight = height.length - 1;
        int res = 0;

        //左指针往右，找到最高时候的位置。
        while (left < right){
            int h = height[left] < height[right]? height[left]: height[right];
            int l = right - left;
            int square = h * l;
            if(square > res) {
                res = square;
                lastLeft = left;
            }
            left ++;
        }

        //有指针往左，找到最高时候的位置。
        while (lastLeft < right){
            int h = height[lastLeft] < height[right]? height[lastLeft]: height[right];
            int l = right - lastLeft;
            int square = h * l;
            if(square > res) {
                res = square;
            }
            right --;
        }

        return res;
    }

    // 错误思路：双指针，左右往中间走，左边往右时找比左边高的，右边往左时找比右边高的，计算左右面积，如果更大就更新。
    // 思路在{2,3,10,5,7,8,9} 下错误。应该36，返回24.
    public int maxArea_wrong(int[] height) {
        int left = 0, right = height.length - 1;
        int lastLeft = 0, lastRight = height.length - 1;
        int res = 0;
        while (left < right){
            int h = height[left] < height[right]? height[left]: height[right];
            int l = right - left;
            int square = h * l;
            if(square > res) {
                res = square;
                lastLeft = left;
                lastRight = right;
            }
            while (left < right){
                left ++;
                if(height[left] > height[lastLeft] && left < right){
                    h = height[left] < height[right]? height[left]: height[right];
                    l = right - left;
                    square = h * l;
                    if(square > res) {
                        res = square;
                        lastLeft = left;
                    }
                }
                right --;
                if(height[right] > height[lastRight] && left < right){
                    h = height[left] < height[right]? height[left]: height[right];
                    l = right - left;
                    square = h * l;
                    if(square > res) {
                        res = square;
                        lastRight = right;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P011_盛最多水的容器 p = new P011_盛最多水的容器();
        System.out.println(p.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));    //49
        System.out.println(p.maxArea(new int[]{2,4,8}));    //4
        System.out.println(p.maxArea(new int[]{8,4,2}));    //4
        System.out.println(p.maxArea(new int[]{8,4,2,9,2,4,8}));    //48
        System.out.println(p.maxArea(new int[]{2,3,10,5,7,8,9}));   //36
        System.out.println(p.maxArea(new int[]{2,3,4,5,18,17,6}));   //17
    }
}
