package LeetCode.round1.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 160926-28
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and 
	k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
Note: The number of people is less than 1,100.

Example
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class P406_QueueReconstructionByHeight {

	/**
	 * !!! 不是很顺畅，想通思路花了一些时间。编程实现的时候也需要小心。
	 * Time: O(n^2), Space: O(n) -- Need an assistant hash table. 
	 * AC: 175 ms
	 */
	public int[][] reconstructQueue(int[][] people) {
		if(people == null)
			return null;
		
		Map<int[], Integer> m = new HashMap<int[], Integer>();	//store new k value; new K value is updated constantly. 
		for (int i = 0; i < people.length; i++) 
			m.put(people[i], people[i][1]);
		
		//like insert sort.
		for (int i = 0; i < people.length; i++) {
			int index = -1;	//will store index that find its final proper position.
			int[] first = new int[]{Integer.MAX_VALUE,0};	//store proper array value that finds its final position.
			for (int j = i; j < people.length; j++) {
				int newK = m.get(people[j]);
				if( i >= 1 && people[j][0] <= people[i - 1][0])
					if(newK != 0)
						m.put(people[j], --newK);
				if(newK == 0 && people[j][0] < first[0]){	//'more proper' people found.
					index = j;
					first = people[j];
				}
			}
			//move first to proper position.
			if(i != index){
				int[] temp = people[i];
				people[i] = first;
				people[index] = temp;
			}
		}
		return people;
    }
	
	/**
	 * ！！！！Bug: 以下思路不对。[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]可以work。 {6,0},{5,0},{4,0},{3,2},{2,2},{1,4}就错了。
	 */
	public int[][] reconstructQueue_fail(int[][] people) {
		if(people == null)
			return null;
		
		//like insert sort.
		for (int i = 1; i < people.length; i++) {
			for(int j = i; j > 0; j --){
				int sumPres = people[j - 1][0] + people[j - 1][1];
				int sumCur = people[j][0] + people[j][1];
				if(sumCur < sumPres){	//swap
					int[] temp = people[j - 1];
					people[j - 1] = people[j];
					people[j] = temp;
				}else if(sumCur == sumPres){
					if(people[j - 1][1] != 0 && people[j][1] != 0){
						if(people[j - 1][0] > people[j][0]){	//swap
							int[] temp = people[j - 1];
							people[j - 1] = people[j];
							people[j] = temp;
						}
					}else if(people[j][1] == 0){	//swap
						int[] temp = people[j - 1];
						people[j - 1] = people[j];
						people[j] = temp;
					}
				}
			}
		}
		return people;
    }
	
	public static void main(String[] args) {
		P406_QueueReconstructionByHeight p = new P406_QueueReconstructionByHeight();
		int[][] a = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};	//[[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]
		p.reconstructQueue(a);
		System.out.println(Arrays.deepToString(a));
		
		a = new int[][]{{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};	//[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
		p.reconstructQueue(a);
		System.out.println(Arrays.deepToString(a));
	}

}
