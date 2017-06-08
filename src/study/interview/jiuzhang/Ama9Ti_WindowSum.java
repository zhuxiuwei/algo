package study.interview.jiuzhang;

import java.util.Arrays;

/**
 * 170608
 * https://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649456518&idx=1&sn=1357066e1910ce736804fef716511af1&chksm=887e118ebf0998986ff9455c00e7dd76a2f20ddb76719f5787da1958938cc53cd5cde0838c97&mpshare=1&scene=1&srcid=03176Lr2wNzzdXnbczqd5Rt5&key=5657e61c2ec7753dd17978d58491302bd6abe854dfb2438ad09c292e55b6717bd174d5bb9f49aab3eb7edfb7b1fbcd1383bd01894017b5da7563d754126cecdd8ffbcc02c72c99607f8f7e342bd15cc7&ascene=0&uin=MTUyMzg3NjAwMA%3D%3D&devicetype=iMac+MacBookAir7%2C1+OSX+OSX+10.12.3+build(16D32)&version=12020010&nettype=WIFI&fontScale=100&pass_ticket=0AiIToHJN8yqpuqRAsA5PaaQMJr8KtvlnZ2EqkX0zx%2BEZweRvHKyF%2ByjmycpUbVn
 * http://www.cnblogs.com/zcy-backend/p/6734304.html 
 * 
 * 一个滑动窗遍历数组，求滑动窗的和。
 */
public class Ama9Ti_WindowSum {

	/**
	 * 一个滑动窗遍历数组，求滑动窗的和。
	 * @param nums a list of integers
	 * @param k size of window
	 * @return the sum of element inside the window at each moving
	 */
	public int[] winSum(int[] nums, int k){
		if(nums == null || nums.length == 0 || k < 1)
			return new int[]{};
		
		
		int left = 0, right = 0;
		int sum = 0, resIdx = 0;
		
		if(k >= nums.length){	//!!!!!!!!!!!注意这个special case。！！！！！！！！！！！
			int res[] = new int[1];
			for (int i = 0; i < nums.length; i++) {
				sum += nums[i];
			}
			res[0] = sum;
			return res;
		}
		
		int[] res = new int[nums.length - k + 1];
		while(right < nums.length){
			sum += nums[right];
			if(right - left + 1 == k ){	//reach size k
				res[resIdx++] = sum;
				sum -= nums[left];
				left++;
			}
			right++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Ama9Ti_WindowSum p = new Ama9Ti_WindowSum();
		int[] a = new int[]{1,3,6,2,7,4};
		System.out.println(Arrays.toString(p.winSum(a, 0)));	//[]
		System.out.println(Arrays.toString(p.winSum(a, 1)));	//[1,3,6,2,7,4]
		System.out.println(Arrays.toString(p.winSum(a, 2)));	//[4,9,8,9,11]
		System.out.println(Arrays.toString(p.winSum(a, 5)));	//[19,22]
		System.out.println(Arrays.toString(p.winSum(a, 6)));	//[23]
		System.out.println(Arrays.toString(p.winSum(a, 8)));	//[23]
	}

}
