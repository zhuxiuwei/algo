package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 161217
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
For example, Given n = 3,
You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
public class P059_SpiralMatrixII {
	
	/**
	 * AC: 3ms, 14.48%. ！！！！！！！！！！！ 有俩bug！！！！！！！！！
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n){
		if(n < 0)
			return null;
		int dir = 0;	//0: right, 1: down,; 2: left. 3: up
		int maxUp = 0, maxRight = n - 1, maxDown = n - 1, maxLeft = 0;
		int idx = 0;
		int res[][] = new int[n][n];
		for (int i = 0; i < n * n; i++) {
			switch(dir){
				case 0:	//In up. go right
					if(idx <= maxRight){
						res[maxUp][idx ++] = i + 1;
					}else{
						dir ++;
						maxUp ++;
						idx = maxUp;
						i --;	//!!!!!!!!! bug1. 这个时候必须减一。因为进入else，i也被+1了。
					}
					break;
				case 1:	//In right. go down
					if(idx <= maxDown){
						res[idx ++][maxRight] = i + 1;
					}else{
						dir ++;
						maxRight -- ;
						idx = maxRight;
						i --;
					}
					break;
				case 2:	//In down. go left
					if(idx >= maxLeft){		//!!!!! bug 2: 这里是用>=，不要也写成<=
						res[maxDown][idx --] = i + 1;
					}else{
						dir ++;
						maxDown -- ;
						idx = maxDown;
						i --;
					}
					break;
				case 3:	//In left. go up
					if(idx >= maxUp){
						res[idx --][maxLeft] = i + 1;
					}else{
						dir = 0;
						maxLeft ++ ;
						idx = maxLeft;
						i --;
					}
					break;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		P059_SpiralMatrixII p = new P059_SpiralMatrixII();
		System.out.println(Arrays.deepToString(p.generateMatrix(0)));
	}

}
