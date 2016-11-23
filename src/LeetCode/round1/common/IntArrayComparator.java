package LeetCode.round1.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * 161121
 * int multi-dimension array comparator
 */
public class IntArrayComparator implements Comparator<int[]>{
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
    
    public static void main(String[] args)
    {
		//test IntArrayComparator
        int arr[][]=new int[][]{{12,3},{1,2},{5,15},{1,2},{5,9},{12,3, 1}};
        List<int[]> list=new ArrayList<int[]>();
        for(int i=0;i<arr.length;i++)
            list.add(arr[i]);
        IntArrayComparator c = new IntArrayComparator();
        Collections.sort(list, c);
        for(int[] i: list)	//[1, 2] [1, 2] [5, 9] [5, 15] [12, 3] [12, 3, 1] 
        	System.out.print(Arrays.toString(i) + " ");
    }
}