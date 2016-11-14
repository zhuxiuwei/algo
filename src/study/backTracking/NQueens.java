package study.backTracking;

/**
 * N Queens problem
 * @author xiuzhu
 * 161114
 */
public class NQueens {

	private int n = 8;	//default it 8. 8 queens problem.
	private boolean canContinue = true;
	private int solutionIndex = 0;
	public NQueens(int n){
		this.n = n;
	}
	
	/**
	 * Algorithm entry point. 
	 * @return
	 */
	public void printSolution(){
		int[] res = new int[n];		//Use an array to specify each queen's location in each row.
		printSolution_helper(res, 0);
	}
	/**
	 * Recursive helper method.
	 * @param res Record legal column location of each row.
	 * @param row current row.
	 */
	private void printSolution_helper(int[] res, int row){
		if(row == 0 && !canContinue)
			return;	//quit the whole method.

		for (int i = 0; i < res.length; i++) {
			if(isLegalLocation(res, row, i)){
				res[row] = i;
				canContinue = true;
				if(row == res.length - 1){	//last row reached. can print solution.
					//Leverage res array, print solution
					System.out.println(String.format("\r\nPrint solution: %s " , ++ solutionIndex));
					for (int j = 0; j < res.length; j++) {
						for (int k = 0; k < res.length; k++) {
							if(res[j] == k)
								System.out.print("♚ ");
							else
								System.out.print("○ ");
							if(k == res.length - 1)
								System.out.println("");
						}
					}
				}else
					printSolution_helper(res, row + 1);
			}else{
				if(i == res.length - 1)
					canContinue = false;	//backtracking: need to go back to previous row.
			}
		}
	}
	/**
	 * Given a location, judge if it's allowed. 
	 * @param res the result array
	 * @param rowIndex row of the location
	 * @param columnIndex column of the location
	 * @return
	 */
	private boolean isLegalLocation(int[] res, int rowIndex, int columnIndex){
		if(rowIndex == 0)
			return true;
		for (int i = 0; i < rowIndex; i++) {	//is in same column.
			if(res[i] == columnIndex)
				return false;
		}
		for (int i = 0; i < rowIndex; i++) {	//is in same diagonal.
			if(Math.abs(columnIndex - res[i]) == Math.abs(rowIndex - i))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		NQueens q = new NQueens(8);
		q.printSolution();
	}

}
