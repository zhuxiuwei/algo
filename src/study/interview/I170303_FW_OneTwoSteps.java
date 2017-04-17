package study.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Free wheel 170303 面试题。
 * 1. 给你N个台阶，每次只能走1或者2步，给出全部可能的走法。
 * 2. 用栈再实现一遍。（没做）
 * @author Zhu Xiuwei
 */

public class I170303_FW_OneTwoSteps {

	/**
	 * 给出N个台阶，每次只能走1或者2步，列出全部走法。
	 * @param total
	 * @return
	 */
	public List<List<Integer>> step(int total){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> curr = new ArrayList<Integer>();
		step_helper(total, res, curr);
		return res;
	}
	private void step_helper(int total, List<List<Integer>> res, List<Integer> current){
		if(total > 0){
			current.add(1);	//走1
			step_helper(total - 1, res, current);	
			current.remove(current.size() - 1);		
			if(total > 1){
				current.add(2);	//走1
				step_helper(total - 2, res, current);
				current.remove(current.size() - 1);	//!!!!!!!!!注意走完也要删除。否则结果诡异。
			}
		}
		if(total == 0){		//a solution found
			List<Integer> newList = new ArrayList<Integer>(current); 
			res.add(newList);
		}
	}
	
	public static void main(String[] args) {
		I170303_FW_OneTwoSteps i = new I170303_FW_OneTwoSteps();
		System.out.println(i.step(4));
	}

}
