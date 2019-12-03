package study.interview;

import java.util.ArrayList;
import java.util.List;
/**
 * 170531
 */
public class I170531_Amazon_GameScore {

	/**
	 * @param blocks
	 * @param n
	 * @return
	 */
	public int totalScore(String[] blocks, int n)
    {
		if(blocks == null || blocks.length != n)
			return Integer.MIN_VALUE;	//means an invalid value.
		int sum = 0;
		List<Integer> preScores = new ArrayList<Integer>();
		for (int i = 0; i < blocks.length; i++) {
			String block = blocks[i];
			if(block.equals("Z")){	//the last score is removed, as the last throw never happened.
				if(preScores.size() > 0){
					sum -= preScores.get(preScores.size() - 1);
					preScores.remove(preScores.size() - 1);
				}
			}else if(block.equals("+")){	//score for this throw is sum of the last two scores.
				int pre2score = preScores.size() >= 2 ? preScores.get(preScores.size() - 2): 0;
				int pre1score = preScores.size() >= 1 ? preScores.get(preScores.size() - 1): 0;
				int num = pre2score + pre1score;
				preScores.add(num);
				sum += num;
			}else if(block.equals("X")){	//score for this throw is double the last scores.
				int num = preScores.size() > 0 ? 2 * preScores.get(preScores.size() - 1) : 0;
				preScores.add(num);
				sum += num;
			}else{	//Integer. score is value of the integer.
				int num = Integer.parseInt(block);
				sum += num;
				preScores.add(num);
			}
		}
		return sum;
    }
	
	public static void main(String[] args) {
		I170531_Amazon_GameScore a = new I170531_Amazon_GameScore();
		System.out.println(a.totalScore(new String[]{"5","-2","4","Z","X","9","+","+"}, 8)); //27
		System.out.println(a.totalScore(new String[]{"+","1","+","2","+","Z"}, 6)); //4
		System.out.println(a.totalScore(new String[]{"X","3","+","Z"}, 4)); //3
	}

}
