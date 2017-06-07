package study.interview.jiuzhang;
/**
 * 170607
 * Leet code: https://leetcode.com/problems/trapping-rain-water/#/description 
 * 自己不会，看的答案后，自己再实现一遍。
 */
public class TrapWater1 {
	
	public int trap(int[] A){
		if(A == null || A.length <= 2)
			return 0;
		
		int leftMax = 0, rightMax = 0;
		int left = 0, right = A.length - 1;
		int res = 0;
		
		while(left < right){
			leftMax = Math.max(leftMax, A[left]);
			rightMax = Math.max(rightMax, A[right]);
			if(leftMax < rightMax){
				res += leftMax - A[left];
				left ++;
			}else{
				res += rightMax - A[right];
				right --;
			}
		}
		
		return res;
	}

	public static void main(String[] args) {
		TrapWater1 t = new TrapWater1();
		System.out.println(t.trap(new int[]{0,1,0,2,1,0,1,3,3,1,2,1}));	//6
		System.out.println(t.trap(new int[]{5,2,1,2,1,5}));	//14
	}

}
