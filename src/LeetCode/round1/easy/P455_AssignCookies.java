package LeetCode.round1.easy;

import java.util.Arrays;

/**
 * 161119
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. Each child i has a greed factor gi, 
which is the minimum size of a cookie that the child will be content with; and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, 
and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
Note:
You may assume the greed factor is always positive. 
You cannot assign more than one cookie to one child.

Example 1:
Input: [1,2,3], [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.

Example 2:
Input: [1,2], [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
 */
public class P455_AssignCookies {

	/**
	 * AC: 20ms. 
	 * 典型Greedy问题。 Note 1 bug.
	 * 
	 * @param g Children greed factors
	 * @param s Size of cookies
	 * @return 
	 */
	public int findContentChildren(int[] g, int[] s) {
		if(s == null || s.length == 0 || g == null || g.length == 0)
			return 0;
		
		int res = 0;
		//first sort
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0, j = 0;
        while(i < g.length && j < s.length){
        	if(g[i] <= s[j]){
        		i ++;
        		j ++;
        		res ++;
        	}else
        		j++;	//!!!!!Note bug: can not break here! {10, 9, 8, 7}, {5,6,7,8} will return 0.
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		P455_AssignCookies p = new P455_AssignCookies();
		System.out.println(p.findContentChildren(new int[]{10, 9, 8, 7}, new int[]{5,6,7,8}));
	}

}
