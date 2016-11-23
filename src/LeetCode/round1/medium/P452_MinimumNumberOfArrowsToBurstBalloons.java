package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import LeetCode.round1.common.IntArrayComparator;

/**
 * 161121-22
给一些气球区间，最少几箭能打破全部气球
Example:
Input: [[10,16], [2,8], [1,6], [7,12]]
Output: 2
Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */
public class P452_MinimumNumberOfArrowsToBurstBalloons {

	/**
	 * Greedy. Always shots balloons when one balloons starts after any balloon's end.
	 * AC: 51ms, 50%
	 * @param points
	 * @return
	 */
	public int findMinArrowShots(int[][] points) {
        int res = 0;
        if(points == null || points.length < 1)
        	return res;
        
        //sort the array first.
        List<int[]> list = new ArrayList<int[]>();
        for(int i=0; i < points.length; i++)
            list.add(points[i]);
        Collections.sort(list, new IntArrayComparator());	//IntArrayComparator class is in "LeetCode.round1.common" package 

        int smallestEnd = Integer.MAX_VALUE;
        for(int[] ba: list){
        	if(ba[0] > smallestEnd){	//time to Burst Balloons
        		res ++;
        		smallestEnd = ba[1];
        	}else{
        		if(ba[1] < smallestEnd)
        			smallestEnd = ba[1];
        	}
        }
        return res + 1;
    }
	
	
	
	public static void main(String[] args)
    {
		P452_MinimumNumberOfArrowsToBurstBalloons p = new P452_MinimumNumberOfArrowsToBurstBalloons();
		System.out.println(p.findMinArrowShots(new int[][]{{10,16}, {2,8}, {1,6}, {7,12}}));	//2
		System.out.println(p.findMinArrowShots(new int[][]{{10,16}, {2,8}}));	//2
		System.out.println(p.findMinArrowShots(new int[][]{{10,16}, {2,18}}));	//1
		System.out.println(p.findMinArrowShots(new int[][]{{10,16}, {16,18}}));	//1
		System.out.println(p.findMinArrowShots(new int[][]{{6,12}, {1,8}, {3,9}, {2,5}, {10,11}, {4,7}}));	//2.
		System.out.println(p.findMinArrowShots(new int[][]{{1,2}}));	//1
    }
}

