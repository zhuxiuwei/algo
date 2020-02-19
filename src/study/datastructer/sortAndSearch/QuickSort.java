package study.datastructer.sortAndSearch;
/**
 * @author xwzhu
 * @date 140325
 */
import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int[]a, int start, int end){
		if(a == null || a.length == 0)
			return;

		int anchor = a[start];

		int i = start;
		int j = end;
		while(i < j){

			//left to right
			while(i < j && a[j] >= anchor)
				j--;
			a[i] = a[j];

			while( i < j && a[i] <= anchor)
				i++;
			a[j] = a[i];
		}
		a[i] = anchor;

		if(start < i - 1)	//小于等于也行
			quickSort(a, start, i-1);

		if(end > i + 1)		//大于等于也行
			quickSort(a, i + 1, end);
	}

	public static void main(String[] args) {
		int[] a = {123,4,3,7,5,23,89,3,8};
		System.out.println(Arrays.toString(a));
		QuickSort.quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));

		int[] b = {123};
		System.out.println(Arrays.toString(b));
		QuickSort.quickSort(b, 0, b.length - 1);
		System.out.println(Arrays.toString(b));
	}

}
