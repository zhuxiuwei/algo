package study.interview.jiuzhang;

/**
 * 170608
 * https://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649456518&idx=1&sn=1357066e1910ce736804fef716511af1&chksm=887e118ebf0998986ff9455c00e7dd76a2f20ddb76719f5787da1958938cc53cd5cde0838c97&mpshare=1&scene=1&srcid=03176Lr2wNzzdXnbczqd5Rt5&key=5657e61c2ec7753dd17978d58491302bd6abe854dfb2438ad09c292e55b6717bd174d5bb9f49aab3eb7edfb7b1fbcd1383bd01894017b5da7563d754126cecdd8ffbcc02c72c99607f8f7e342bd15cc7&ascene=0&uin=MTUyMzg3NjAwMA%3D%3D&devicetype=iMac+MacBookAir7%2C1+OSX+OSX+10.12.3+build(16D32)&version=12020010&nettype=WIFI&fontScale=100&pass_ticket=0AiIToHJN8yqpuqRAsA5PaaQMJr8KtvlnZ2EqkX0zx%2BEZweRvHKyF%2ByjmycpUbVn
 * http://www.cnblogs.com/zcy-backend/p/6734304.html
 * https://leetcode.com/problems/longest-palindrome/#/description
Given a string which consists of lowercase or uppercase letters, 
find the length of the longest palindromes(回文) that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input: "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class Ama9Ti_LongestPalindrome {
	/**
	 * AC: 17ms, 60.71%.
	 * !!!!!!!!!!注意一个BUG!!!!!!!!!!!!!
	 */
	public int longestPalindrome(String s) {
		int hash[] = new int[128];
		int res = 0;
		boolean hasOddNumber = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			hash[c] ++;
		}
		for (int i = 0; i < s.length(); i++) {
			int v = hash[s.charAt(i)];
			if ( v % 2 != 0){
				hasOddNumber = true;
				res += (v - 1);
			}else
				res += v;
			hash[s.charAt(i)] = 0;	//!!!!注意BUG：如果不置0res会算多。（abccccdd错误返回21）。 ！！！！同时注意用到了LeetCode P200_NumberOfIslands(medium)学到的“投机取巧”思路。
		}
		if(hasOddNumber) res++;
		return res;
	}
	
	public static void main(String[] args) {
		Ama9Ti_LongestPalindrome a = new Ama9Ti_LongestPalindrome();
		System.out.println(a.longestPalindrome("aAzZ"));	//1
		System.out.println(a.longestPalindrome("abccccdd"));	//7
	}

}
