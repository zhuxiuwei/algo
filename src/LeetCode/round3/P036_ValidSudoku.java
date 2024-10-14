package LeetCode.round3;

import java.util.*;

/**
 * 241009 medium
 * https://leetcode.com/problems/valid-sudoku/description/?envType=study-plan-v2&envId=top-interview-150
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 *
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * Constraints:
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */
public class P036_ValidSudoku {


    /**
     * AC: Runtime 3 ms Beats 56.77%, Memory 44.80 MB Beats 20.83%
     * 感觉写的比较ugly。不优雅。写的也比较耗时。
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> chars;
        //check rows
        for (int i = 0; i < board.length; i++) {
            chars = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if(!check1To9(board[i][j], chars)){
                    return false;
                }
            }
        }

        //check columns -- 可以和check rows合并，不过合并后执行速度并没有提升。
        for (int i = 0; i < board.length; i++) {
            chars = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if(!check1To9(board[j][i], chars)){
                    return false;
                }
            }
        }

        //check all 3x3 squares。代码很丑啊！
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(0, 0, 2, 2));
        pairs.add(new Pair(0, 3, 2, 5));
        pairs.add(new Pair(0, 6, 2, 8));
        pairs.add(new Pair(3, 0, 5, 2));
        pairs.add(new Pair(3, 3, 5, 5));
        pairs.add(new Pair(3, 6, 5, 8));
        pairs.add(new Pair(6, 0, 8, 2));
        pairs.add(new Pair(6, 3, 8, 5));
        pairs.add(new Pair(6, 6, 8, 8));
        for(Pair pair: pairs){
            chars = new HashSet<>();
            for (int i = pair.leftR; i <= pair.rightR; i++) {
                for (int j = pair.leftC; j <= pair.rightC; j++) {
                    if(!check1To9(board[i][j], chars)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //检查1-9是否重复出现过
    private boolean check1To9(char c, Set<Character> nums){
        if(c == '.')
            return true;

        if(nums.contains(c)) {
            return false;
        }
        else {
            nums.add(c);
            return true;
        }
    }

    //用于记录一个3x3矩阵的左上、右下顶点的横纵坐标。
    static class Pair{
        int leftR; //左上角顶点行索引
        int leftC; //左上角顶点列索引
        int rightR; //右下角顶点行索引
        int rightC; //右下角顶点列索引
        public Pair(int leftR, int leftC, int rightR, int rightC){
            this.leftC = leftC;
            this.leftR = leftR;
            this.rightC = rightC;
            this.rightR = rightR;
        }
    }

    public static void main(String[] args) {
        P036_ValidSudoku p = new P036_ValidSudoku();
        char[][] board = new char[9][];
        board[0] = new char[]{'.','.','.','.','5','.','.','1','.'};
        board[1] = new char[]{'.','4','.','3','.','.','.','.','.'};
        board[2] = new char[]{'.','.','.','.','.','3','.','.','1'};
        board[3] = new char[]{'8','.','.','.','.','.','.','2','.'};
        board[4] = new char[]{'.','.','2','.','7','.','.','.','.'};
        board[5] = new char[]{'.','1','5','.','.','.','.','.','.'};
        board[6] = new char[]{'.','.','.','.','.','2','.','.','.'};
        board[7] = new char[]{'.','2','.','9','.','.','.','.','.'};
        board[8] = new char[]{'.','.','4','.','.','.','.','.','.'};
        System.out.println(p.isValidSudoku(board)); //false
    }
}
