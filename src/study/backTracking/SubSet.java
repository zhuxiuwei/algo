package study.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Subset problem
 * @author Xiuzhu
 * 161115
 */
public class SubSet {

	/**
	 * Given a set X of positive integers and target integer T, print all subsets of elements in X that add up to T
	 * @param x The array.
	 * @param t The target
	 */
	public void printSolution(int[] x, int t){
		if(t == 0 || x == null || x.length == 0)	//special cases
			return;
		List<Integer> solution = new ArrayList<Integer>(); 
		printSolution_helper(0, x, t, solution);
	}
	/**
	 * helper recursive method.
	 * @param index Start index in array x.
	 * @param x The array.
	 * @param t The target.
	 * @param solution Current solution, contain all current legal numbers.
	 */
	private void printSolution_helper(int index, int[] x, int t, List<Integer> solution){
		if(t == 0){	//found a solution, then print it
			for (int i: solution) 
				System.out.print(i + " ");
			System.out.println();
		}
		else{
			while(index < x.length){
				if(t >= x[index]){
					solution.add(x[index]);
					int newT = t - x[index];
					printSolution_helper(++index, x, newT, solution);
					solution.remove(solution.size() - 1);
				}else{
					index ++;
				}
			}
		}
	}
	
	//test
	public static void main(String[] args) {
		SubSet s = new SubSet();
		s.printSolution(new int[]{8, 7, 15}, 15);
		System.out.println("-----------------");
		s.printSolution(new int[]{8, 6, 7, 5, 3, 10, 9}, 15);
		System.out.println("-----------------");
		s.printSolution(new int[]{8, 6, 7, 5, 3, 10, 9, 2, 15}, 30);
	}

}
