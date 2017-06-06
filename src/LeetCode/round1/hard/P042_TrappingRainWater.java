package LeetCode.round1.hard;
/**
 * 170606
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class P042_TrappingRainWater {

	public int trap(int[] height) {
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
		//[5,2,1,2,1,5]
	}

}
