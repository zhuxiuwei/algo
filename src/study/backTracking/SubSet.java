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
		for (int i = 0; i < x.length; i++) 
			printSolution_helper(i, x, t, solution);
	}
	
	public void printSolution_helper(int index, int[] x, int t, List<Integer> solution){
		if(t == 0){	//found a solution, then print it
			for (int i: solution) 
				System.out.println(i + " ");
			System.out.println();
		}
		else{
			if(index < x.length){
				if(t >= x[index]){
					solution.add(x[index]);
					printSolution_helper(++index, x, t - x[index], solution);
				}else{
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

}
