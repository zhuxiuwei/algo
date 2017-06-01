package study.interview.jiuzhang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * [《Amazon HackerRank OA 面经》](http://www.jiuzhang.com/qa/748/) 170601
 * Find max and 2nd max in an array by minimum times.
 */
public class FindMax2ndMaxInArray {

	public static int[] find(int[] arr){
		if(arr == null || arr.length < 2)
			return null;
		Map<Integer, List<Integer>> trace = new HashMap<Integer, List<Integer>>();
		int count = (int)Math.ceil((double)arr.length/(double)2), nextSize = count;
		for (int c = 0; c < count; c++) {
			int[] temp = new int[nextSize];	//保存此轮发现的较小的数字
			for (int i = 0, j = 0; i < arr.length; i += 2, j++) {
				if(i + 1 < arr.length){
					int small = Math.min(arr[i], arr[i + 1]);
					int big = Math.max(arr[i], arr[i + 1]);
					temp[j] = small;	//存储小数
					List<Integer> list = trace.getOrDefault(small, new ArrayList<Integer>());
					list.add(big);
					trace.put(small, list);
					if(trace.containsKey(big)) trace.remove(big);
				}else
					temp[j] = arr[i];
			}
			arr = temp;
			nextSize =  (int)Math.ceil((double)nextSize/(double)2);
		}
		int min = arr[0];
		int min2nd = findMin(trace.get(min));
		return new int[]{min, min2nd};
	}
	
	private static int findMin(List<Integer> ints){
		int min = Integer.MAX_VALUE;
		for(int i : ints){
			if(i < min)
				min = i;
		}
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(find(new int[]{2,1})));
		System.out.println(Arrays.toString(find(new int[]{2,1,3,5})));
		System.out.println(Arrays.toString(find(new int[]{2,1,3,5,7,11,2,0,8,7})));
	}
}
