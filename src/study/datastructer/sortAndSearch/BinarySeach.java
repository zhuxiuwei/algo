package study.datastructer.sortAndSearch;

/**
 * @author xwzhu
 * @date 150325
 */
public class BinarySeach {

	public static boolean search(int a[], int target, int begin, int end){
		boolean found = false;
		if(a.length == 0)
			return false;

		int middle = (begin + end)/2;
		int middleValue = a[middle];
		if(middleValue == target)
			found = true;
		else{
			if(target > middleValue && end >= middle + 1)
				found = search(a, target, middle + 1, end);
			if(target < middleValue && begin <= middle - 1)
				found = search(a, target, begin, middle - 1);
		}
		
		return found;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5};
		System.out.println(BinarySeach.search(a, 4, 0, a.length - 1));
	}

}
