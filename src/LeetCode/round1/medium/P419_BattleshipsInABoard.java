package LeetCode.round1.medium;

/**
 * 161117
Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */
public class P419_BattleshipsInABoard {

	/**
	 * AC: 4ms, 40%
	 * Solution 1: Need to update the value of the board.
	 */
	public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] == 'X'){		//Found a ship
					count ++;
					board[i][j] = '.';
					updateShipBody(board, i, j);	//then, update the ship to '.'
				}
			}
		}
        return count;
    }
	public void updateShipBody(char[][] board, int i, int j) {
		if(j < board[i].length - 1 && board[i][j + 1] == 'X'){	//go right
			j ++;
			while(j < board[i].length && board[i][j] == 'X'){
				board[i][j] = '.';
				j ++;
			}
		}else if(i < board.length - 1 && board[i + 1][j] == 'X'){	//go down
			i ++;
			while(i < board.length && board[i][j] == 'X'){
				board[i][j] = '.';
				i ++;
			}
		}
    }
	
	/**
	 * AC: 4ms, 40%
	 * Solution 2: Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
	 * My solution use 0 extra memory... But when it meets a 'X', it has to look back one element to confirm if it's head of the ship.
	 */
	public int countBattleships2(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j] == 'X'){		//Found a part of ship
					if(isHeadOfShip(board, i, j))	//check if the part of ship is 'head' of the ship. If yes, count ++.
						count ++;
				}
			}
		}
        return count;
    }
	public boolean isHeadOfShip(char[][] board, int i, int j) {	//check left and up to see if the 'X' is head of a ship.
		if(j != 0 && i != 0)	//check up and left 
			return board[i][j-1] == '.' && board[i-1][j] == '.';
		else if(j == 0 && i > 0) //check up only
			return board[i-1][j] == '.';
		else if(i == 0 && j > 0) //check left only
			return board[i][j-1] == '.';
		else	//first element must be true
			return true;
    }
	
	public static void main(String[] args) {
		P419_BattleshipsInABoard p = new P419_BattleshipsInABoard();
		System.out.println(p.countBattleships(new char[][]{{'X','.','.','X'},{'.','.','.','X'},{'X','.','.','X'}}));
		System.out.println(p.countBattleships2(new char[][]{{'X','.','.','X'},{'.','.','.','X'},{'X','.','.','X'}}));
	}

}
