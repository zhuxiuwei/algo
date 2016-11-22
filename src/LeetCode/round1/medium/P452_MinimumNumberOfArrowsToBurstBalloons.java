package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        Collections.sort(list, new IntArrayComparator());

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

		//test IntArrayComparator
//        int arr[][]=new int[][]{{12,3},{1,2},{5,15},{1,2},{5,9},{12,3, 1}};
//        List<int[]> list=new ArrayList<int[]>();
//        for(int i=0;i<arr.length;i++)
//            list.add(arr[i]);
//        IntArrayComparator c = new IntArrayComparator();
//        Collections.sort(list, c);
//        for(int[] i: list)	//[1, 2] [1, 2] [5, 9] [5, 15] [12, 3] [12, 3, 1] 
//        	System.out.print(Arrays.toString(i) + " ");
    }
}
/**
 * int multi-dimension array comparator
 */
class IntArrayComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] o1, int[] o2) {
        
    	//both empty array
    	if((o1 == null && o2 == null )||
    			(o1.length == 0 && o2.length == 0))
    		return 0;
    	
    	if(o1 == null || o1.length == 0)	//o1 is empty o2 not, then o1 smaller than o2
    		return -1;
    	
    	if(o2 == null || o2.length == 0)	//o2 is empty o1 not, then o1 bigger than o2
    		return 1;
    	
    	int i = 0;
    	while(i < o1.length && i < o2.length){
    		if(o1[i] < o2[i])
    			return -1;
    		else if(o1[i] > o2[i])
    			return 1;
    		else
    			i++;
    	}
    	return o1.length - o2.length;
    }
}