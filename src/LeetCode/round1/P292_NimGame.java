package LeetCode.round1;
/**
 * 160908
 * Difficulty: Easy
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.
For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
 */
public class P292_NimGame {

	public boolean canWinNim(int n) {
		if(n <= 0)
			return false;
		else
			return n % 4 != 0;
	}

	public static void main(String[] args) {
		P292_NimGame p = new P292_NimGame();
		System.out.println(p.canWinNim(1));	//true
		System.out.println(p.canWinNim(5));	//true
		System.out.println(p.canWinNim(8));	//false
	}

}
/**
 * 大意错了一次。 写成了 return n % 4 == 0;
*/
