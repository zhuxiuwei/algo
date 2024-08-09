package study.backTracking;

/**
 * N Queens problem
 * @author xiuzhu
 * 161114
 */
public class NQueens {

	private int n = 8;	//default it 8. 8 queens problem.
	private int solutionIndex = 0;
	public NQueens(int n){
		this.n = n;
	}
	
	/**
	 * Algorithm entry point. 
	 * @return
	 */
	public void printSolution(){
		int[] res = new int[n];		//Use an array to specify each queen's location in a row.
		printSolution_helper(res, 0);
	}
	/**
	 * Recursive helper method.
	 * @param res Record legal column location of each row.
	 * @param row current row.
	 */
	private void printSolution_helper(int[] res, int row){
		for (int i = 0; i < res.length; i++) {	//check each column in the row.
			if(isLegalLocation(res, row, i)){
				res[row] = i;
				if(row == res.length - 1){	//last row reached. can print solution.
					//Leverage res array, print solution
					System.out.println(String.format("\r\nSolution %s: " , ++solutionIndex));
					for (int j = 0; j < res.length; j++) {
						for (int k = 0; k < res.length; k++) {
							if(res[j] == k)
								System.out.print("♕ ");
							else
								System.out.print("○ ");
							if(k == res.length - 1)
								System.out.println("");
						}
					}
				}else {
					printSolution_helper(res, row + 1);
				}
			}
		}
	}
	/**
	 * Given a location, judge if it's allowed to put a queen here. 
	 * @param res the result array
	 * @param rowIndex row of the location
	 * @param columnIndex column of the location
	 * @return
	 */
	private boolean isLegalLocation(int[] res, int rowIndex, int columnIndex){
		if(rowIndex == 0)	//For first row, always return true.
			return true;
		for (int i = 0; i < rowIndex; i++) { //Judge if in same column.
			if(res[i] == columnIndex)
				return false;
		}
		for (int i = 0; i < rowIndex; i++) { //Judge if in same diagonal.
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
