package LeetCode.round1.hard;
/**
 * 170606
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
## [Amazon 面经(OA+Onsite)](http://www.jiuzhang.com/qa/2623/)

 */
public class P042_TrappingRainWater {

	/**
	 * 典型双指针问题。对撞指针。
	 * 不会。 参考：https://discuss.leetcode.com/topic/3016/share-my-short-solution/3
	 * @param height
	 * @return
	 */
	public int trap(int[] height){
		if(height == null || height.length < 3)
			return 0;
	    int left = 0;
	    int right = height.length-1;
	    int res = 0;
	    int leftmax=0;
	    int rightmax=0;
	    while(left < right){
	        leftmax=Math.max(leftmax,height[left]);
	        rightmax=Math.max(rightmax,height[right]);
	        if(leftmax<rightmax){
	        	res += (leftmax-height[left]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
	            left ++;
	        }
	        else{
	        	res += (rightmax-height[right]);
	        	right --;
	        }
	    }
	    return res;
	}

	/**
	 * ！！！挺复杂，还思录错了！！！！。思路：从一个递减，到一个递增为止，计算一次水的面积。
	 * 这个思路不对。对原题图示的例子可以正确计算，对[5,2,1,2,1,5]这样的例子错误返回2.（正确14）
	 * @param height
	 * @return
	 */
	public int trap_wrong(int[] height) {
		if(height == null || height.length < 3)
			return 0;
		int res = 0;
		boolean inDown = false, inUp = false;
		int left = 0, right = 0;
		for (int i = 1; i < height.length; i++) {
			if(height[i] > height[i - 1]){
				if(!inDown && !inUp){
					//do nothing
				}else if(inDown && !inUp){	//开始有潜在的积水了。
					inUp = true;
					right = i;
				}else if(inDown && inUp){
					right = i;
				}else if(!inDown && inUp){
					//unreachable
				}
			}else if(height[i] < height[i - 1]){
				if(!inDown && !inUp){
					inDown = true;
					left = i - 1;
				}else if(inDown && !inUp){
				}else if(!inDown && inUp){
					//unreachable
				}else if(inDown && inUp){	//time to calculate
					int low = Math.min(height[left], height[right]);
					for (int j = left + 1; j < right; j++) {
						int sub = low - height[j];
						if(sub < 0) sub = 0;
						res += sub;
					}
					inDown = true;
					inUp = false;
					left = i - 1;
				}
			}else{
				if(!inDown && !inUp){
					//do nothing
				}else if(inDown && !inUp){
					//do nothing
				}else if(!inDown && inUp){
					//unreachable
				}else if(inDown && inUp){
					right = i;
				}
			}
		}

		if(inDown && inUp){
			int low = Math.min(height[left], height[right]);
			for (int j = left + 1; j < right; j++) {
				int sub = low - height[j];
				if(sub < 0) sub = 0;
				res += sub;
			}
		}

        return res;
    }

	public static void main(String[] args) {
		P042_TrappingRainWater p = new P042_TrappingRainWater();
		System.out.println(p.trap(new int[]{0,1,0,2,1,0,1,3,3,1,2,1}));	//6
		System.out.println(p.trap(new int[]{5,2,1,2,1,5}));	//14
		System.out.println(p.trap(new int[]{0,0,0,1}));	//0

	}

}
