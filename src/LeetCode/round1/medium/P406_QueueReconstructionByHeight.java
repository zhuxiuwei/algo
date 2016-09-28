package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 160926
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

	public int[][] reconstructQueue(int[][] people) {
		
		//like insert sort.
		for (int i = 0; i < people.length; i++) {
			int index = -1;
			int[] first = new int[]{Integer.MAX_VALUE,0};
			for (int j = i; j < people.length; j++) {
				int tempK = people[j][1] == 0 ? 0 : people[j][1] - i;
				if(tempK == 0 && people[j][0] < first[0]){
					index = j;
					first = people[j];
				}
			}
			//move first to proper position.
			int[] temp = people[i];
			people[i] = first;
			people[index] = temp;
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
