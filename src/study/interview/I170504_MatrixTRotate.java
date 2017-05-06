package study.interview;

import java.util.Arrays;

/**
 * 矩阵转置
 */
public class I170504_MatrixTRotate {
	
	public int[][] rotate(int[][] origin){
		if(origin == null || origin.length == 0)
			return null;
		
		int originRow = origin.length;
		int originColumn = origin[0].length;
		int[][] res = new int[originColumn][originRow];
		for (int i = 0; i < originRow; i++) {
			for (int j = 0; j < originColumn; j++) {
				res[j][i] = origin[i][j];
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		I170504_MatrixTRotate i = new I170504_MatrixTRotate();
		int[][] origin = new int[][]{{1,2,3},{4,5,6}};
		System.out.println(Arrays.deepToString(i.rotate(origin)));
		origin = new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		System.out.println(Arrays.deepToString(i.rotate(origin)));
	}

}
