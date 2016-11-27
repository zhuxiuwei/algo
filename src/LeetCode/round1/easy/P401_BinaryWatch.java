package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 161126
 A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 
 Note:
 - The order of output does not matter.
 - The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 - The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class P401_BinaryWatch {

	/**
	 * AC: 4ms, 52.19%
	 * 典型backtracking问题。！！！这是第一个做的还算顺利的。基本没太曲折。 
	 * @param num
	 * @return
	 */
	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<String>();
		if(num < 0 || num > 8)	//out of range
			return res;

		int maxHourBit = 3, maxMinBit = 5;
		for (int i = 0; i <= num; i++) {
			if(i <= maxHourBit && (num - i) <= maxMinBit){
				List<Integer> possibleHours = getPossibleSum(11, i, 4);
				List<Integer> possibleMins = getPossibleSum(59, num - i, 6);
				for (int j = 0; j < possibleHours.size(); j++) {
					for (int j2 = 0; j2 < possibleMins.size(); j2++) 
						if(possibleMins.get(j2) < 10)
							res.add(possibleHours.get(j) + ":0" + possibleMins.get(j2));
						else
							res.add(possibleHours.get(j) + ":" + possibleMins.get(j2));
				}
			}
		}
        return res;
    }
	/**
	 * @param maxSum Max sum value. e.g. for hour it's 59.
	 * @param bitsCount total desired bit=1 count
	 * @param highestBitIdx 
	 * @return
	 */
	private List<Integer> getPossibleSum(int maxSum, int bitsCount, int highestBitIdx){
		List<Integer> res = new ArrayList<Integer>();
		if(bitsCount <= highestBitIdx)
			helper(maxSum, res, 0, 0, 0, bitsCount, highestBitIdx);
		return res;
	}
	/**
	 * @param maxSum Max sum value. e.g. for minute it's 59, for hour its' 11.
	 * @param res result list.
	 * @param temp current temp sum.
	 * @param currentBitIdx  current bit index
	 * @param current1Count current bit=1 count
	 * @param bitCount total desired bit=1 count
	 * @param highestBitIdx
	 */
	private void helper(int maxSum, List<Integer> res, int temp, int currentBitIdx, int current1Count, int bitCount, int highestBitIdx){
		if(current1Count == bitCount && temp <= maxSum && currentBitIdx <= highestBitIdx){	//one result found.
			res.add(temp);
			return;
		}
		if(current1Count < bitCount && currentBitIdx < highestBitIdx){
			int oldTemp = temp;
			temp = (1 << currentBitIdx) ^ temp;
			//Let current idx = 1, go deeper.
			helper(maxSum, res, temp, currentBitIdx + 1, current1Count + 1, bitCount, highestBitIdx);	
			//backtracking. Let current idx = 0, go deeper.
			helper(maxSum, res, oldTemp, currentBitIdx + 1, current1Count, bitCount, highestBitIdx);
		}
	}
	
	public static void main(String[] args) {
		
	}

}
