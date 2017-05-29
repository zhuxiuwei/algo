package study.interview.jiuzhang;

import java.util.Arrays;

/**
 * [《Amazon HackerRank OA 面经》](http://www.jiuzhang.com/qa/748/) 170529  
 * Merge 2 arrays in 1 array.
	static void mergeArray(int[] a, int[] b, int M)
	两个sorted array都有M个元素，但是a的capacity是M， b的capacity是2M，最后是把a中的元素加入到b中，保持sorted。
 */
public class Merge2ArraysInto1Array {

	public static void mergeArray(int[] a, int[] b, int M){
		if(a == null || b == null || a.length == 0 || b.length == 0){
			System.out.println("Empty Array!");
			return;
		}
		if(a.length != M || b.length != 2*M){	//invalid input
			System.out.println("Invalid input!");
			return;
		}
		
		for (int i = M; i < b.length; i++) {
			b[i] = Integer.MIN_VALUE;	//特殊占位字符
		}
		
		int bMoveCount[] = new int[M];	//记录b里面的元素，每个元素需要移动的次数。
		int lastIdx = -1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if(a[i] < b[j]){
					lastIdx = j;
					for (int k = lastIdx; k < bMoveCount.length; k++) 
						bMoveCount[k] ++;
					j =  lastIdx - 1;
					break;
				}
			}
		}
		
		//move b first. ！！！！！！！！！！！！注意必须从后往前赋值。！！！！！！！！！！！！
		for (int i = M - 1; i >= 0; i--) {
			if(bMoveCount[i] > 0){
				b[i + bMoveCount[i]] = b[i];
				b[i] = Integer.MIN_VALUE;
			}
		}
		
		//then fill a to b
		for (int i = 0, cur = 0; i < b.length; i++) {
			if(b[i] == Integer.MIN_VALUE)
				b[i] = a[cur++];
		}
		
		//display result
		System.out.println(Arrays.toString(b));
	}

	
	public static void main(String[] args) {
		int[] a = new int[]{3,8,12,16,32};
		int[] b = new int[]{1,8,9,11,28,0,0,0,0,0};
		mergeArray(a, b, a.length);
	}

}
